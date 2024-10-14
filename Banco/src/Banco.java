import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;

public class Banco {
    public static void main(String[] args) {
        final int NUM_CAJEROS = 3;
        final LinkedBlockingQueue<Cliente> colaClientes = new LinkedBlockingQueue<>();
        final Boveda boveda = new Boveda();
        final LibroDiario libroDiario = new LibroDiario();

        
        Cajero[] cajeros = new Cajero[NUM_CAJEROS];
        Thread[] hilosCajeros = new Thread[NUM_CAJEROS];
        for (int i = 0; i < NUM_CAJEROS; i++) {
            cajeros[i] = new Cajero(colaClientes, boveda, libroDiario);
            hilosCajeros[i] = new Thread(cajeros[i]);
            hilosCajeros[i].start();
        }

    
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(random.nextInt(1000)); 
                Cliente cliente = new Cliente(
                        random.nextBoolean() ? Cliente.Operacion.DEPOSITAR : Cliente.Operacion.EXTRAER,
                        random.nextInt(1000) + 1);
                System.out.println("Cliente llegó: " + cliente);
                colaClientes.put(cliente);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        libroDiario.mostrarRegistros();

        int dineroInicial = 10000;
        int totalDepositado = libroDiario.getTotalDepositado();
        int totalExtraido = libroDiario.getTotalExtraido();

        int dineroFinal = dineroInicial + totalDepositado - totalExtraido;

        System.out.println("Estado final de la bóveda: " + dineroFinal);
        System.out.println("Total depositado: " + totalDepositado);
        System.out.println("Total extraído: " + totalExtraido);

        if (dineroFinal > dineroInicial) {
            System.out.println("La bóveda tiene más dinero que al principio del día.");
        } else if (dineroFinal < dineroInicial) {
            System.out.println("La bóveda tiene menos dinero que al principio del día.");
        } else {
            System.out.println("La bóveda tiene la misma cantidad de dinero que al principio del día.");
        }

        for (Cajero cajero : cajeros) {
            cajero.setActivo(false);
        }

        for (Thread hilo : hilosCajeros) {
            try {
                hilo.join(); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }
}
