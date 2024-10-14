import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class BarberiaLock {
    private final int sillas;
    private final Queue<Integer> clientesEnEspera = new LinkedList<>();
    private final Lock lock = new ReentrantLock();
    private final Condition barberoDurmiendo = lock.newCondition();

    public BarberiaLock(int sillas) {
        this.sillas = sillas;
    }

    public void cortarCabello() {
        lock.lock();
        try {
            while (clientesEnEspera.isEmpty()) { 
                System.out.println("Barbero se duerme.");
                barberoDurmiendo.await();
            }

            int clienteId = clientesEnEspera.poll();
            System.out.println("Barbero corta el cabello al cliente " + clienteId + "."); 
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); 
        } finally {
            lock.unlock();
        }
    }

    public void cabelloCortado() {
        lock.lock();
        try {
            System.out.println("Barbero ha terminado de cortar el cabello.");
        } finally {
            lock.unlock();
        }
    }

    public boolean entrarCliente(int id) {
        lock.lock();
        try {
            if (clientesEnEspera.size() < sillas) { 
                clientesEnEspera.offer(id);
                System.out.println("Cliente " + id + " se sienta en una silla.");
                if (clientesEnEspera.size() == 1) {
                    System.out.println("Cliente " + id + " despierta al barbero."); 
                    barberoDurmiendo.signal(); 
                }
                return true;
            } else {
                System.out.println("Cliente " + id + " se va porque no hay sillas disponibles.");
                return false;
            }
        } finally {
            lock.unlock();
        }
    }
}
