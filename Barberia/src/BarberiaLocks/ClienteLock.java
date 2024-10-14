class ClienteLock extends Thread {
    private final BarberiaLock barberia; 
    private final int id; 

    public ClienteLock(BarberiaLock barberia, int id) {
        this.barberia = barberia; 
        this.id = id; 
    }

    @Override
    public void run() {
        if (barberia.entrarCliente(id)) { 
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); 
            }
        }
    }
}
