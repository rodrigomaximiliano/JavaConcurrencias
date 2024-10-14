public class SimulacionBarberia {
    public static void main(String[] args) {
        Barberia barberia = new Barberia(5);
        Barbero barbero = new Barbero(barberia);
        barbero.start();

        
        for (int i = 1; i <= 10; i++) {
            new Cliente(barberia, i).start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
