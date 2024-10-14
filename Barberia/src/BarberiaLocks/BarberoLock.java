class BarberoLock extends Thread {
    private final BarberiaLock barberia; 

    public BarberoLock(BarberiaLock barberia) {
        this.barberia = barberia; 
    }

    @Override
    public void run() {
        while (true) { 
            barberia.cortarCabello(); 
            try {
                Thread.sleep(2000); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); 
            }
            barberia.cabelloCortado(); 
        }
    }
}
