class Barbero extends Thread {
    private final Barberia barberia;

    public Barbero(Barberia barberia) {
        this.barberia = barberia;
    }

    @Override
    public void run() {
        while (true) {
            int idCliente = barberia.cortarCabello();
            try {
                Thread.sleep(2000); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            System.out.println("Barbero ha terminado de cortar el cabello al cliente " + idCliente + ".");
            barberia.cabelloCortado();
        }
    }
}
