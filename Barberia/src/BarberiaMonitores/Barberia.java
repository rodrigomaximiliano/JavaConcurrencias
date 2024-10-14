import java.util.LinkedList;
import java.util.Queue;

class Barberia {
    private final int maxSillas;
    private final Queue<Integer> colaEspera;
    private boolean barberoDormido;

    public Barberia(int maxSillas) {
        this.maxSillas = maxSillas;
        this.colaEspera = new LinkedList<>();
        this.barberoDormido = true;
    }

    public synchronized void entrar(int idCliente) {
        if (colaEspera.size() == maxSillas) {
            System.out.println("Cliente " + idCliente + " se va porque no hay sillas disponibles.");
            return;
        }

        colaEspera.add(idCliente);
        System.out.println("Cliente " + idCliente + " se sienta en una silla.");
        
        
        if (barberoDormido) {
            barberoDormido = false;
            System.out.println("Cliente " + idCliente + " despierta al barbero.");
            notifyAll();
        }
        
        
        while (barberoDormido) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public synchronized int cortarCabello() {
        while (colaEspera.isEmpty()) {
            barberoDormido = true;
            System.out.println("Barbero se duerme.");
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        int idCliente = colaEspera.poll();
        System.out.println("Barbero corta el cabello al cliente " + idCliente + ".");
        return idCliente;
    }

    public synchronized void cabelloCortado() {
        notifyAll();
    }
}
