import java.util.concurrent.LinkedBlockingQueue;

public class Cajero implements Runnable {
    private LinkedBlockingQueue<Cliente> colaClientes;
    private Boveda boveda;
    private LibroDiario libroDiario;
    private boolean activo = true;
    private int dineroEnCaja;

    public Cajero(LinkedBlockingQueue<Cliente> colaClientes, Boveda boveda, LibroDiario libroDiario) {
        this.colaClientes = colaClientes;
        this.boveda = boveda;
        this.libroDiario = libroDiario;
        this.dineroEnCaja = 10000;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public void run() {
        while (activo || !colaClientes.isEmpty()) {
            try {
                Cliente cliente = colaClientes.take();
                if (cliente.getOperacion() == Cliente.Operacion.DEPOSITAR) {
                    manejarDeposito(cliente);
                } else {
                    manejarExtraccion(cliente);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void manejarDeposito(Cliente cliente) {
        dineroEnCaja += cliente.getMonto();
        boveda.depositar(cliente.getMonto());
        libroDiario.registrar(Cliente.Operacion.DEPOSITAR, cliente.getMonto());
        System.out.println("Cliente depositó: " + cliente.getMonto());
    }

    private void manejarExtraccion(Cliente cliente) {
        if (dineroEnCaja >= cliente.getMonto()) {
            dineroEnCaja -= cliente.getMonto();
            libroDiario.registrar(Cliente.Operacion.EXTRAER, cliente.getMonto());
            System.out.println("Cliente extrajo: " + cliente.getMonto());
        } else {
            int dineroNecesario = cliente.getMonto() - dineroEnCaja;
            if (boveda.extraer(dineroNecesario)) {
                dineroEnCaja = 0;
                libroDiario.registrar(Cliente.Operacion.EXTRAER, cliente.getMonto());
                System.out.println("Cliente extrajo: " + cliente.getMonto() + " (saliendo de la bóveda)");
            } else {
                System.out.println("No hay suficiente dinero para extraer: " + cliente.getMonto());
                return; 
            }
        }

        // Verificar si el cajero tiene más de 20,000 pesos después de la extracción
        if (dineroEnCaja > 20000) {
            int excedente = dineroEnCaja - 10000;
            dineroEnCaja = 10000;
            boveda.depositar(excedente);
            System.out.println("Llevando excedente de " + excedente + " a la bóveda.");
        }
    }
}
