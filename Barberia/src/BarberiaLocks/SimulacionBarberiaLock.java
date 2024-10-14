public class SimulacionBarberiaLock {
    public static void main(String[] args) {
        final int NUM_SILLAS = 3; 
        final int NUM_CLIENTES = 10; 
        BarberiaLock barberia = new BarberiaLock(NUM_SILLAS); 
        BarberoLock barbero = new BarberoLock(barberia); 
        barbero.start(); 

        for (int i = 1; i <= NUM_CLIENTES; i++) { 
            ClienteLock cliente = new ClienteLock(barberia, i);
            cliente.start(); 
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
