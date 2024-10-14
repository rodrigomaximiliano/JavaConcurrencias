class Cliente extends Thread {
    private final Barberia barberia;
    private final int id;

    public Cliente(Barberia barberia, int id) {
        this.barberia = barberia;
        this.id = id;
    }

    @Override
    public void run() {
        barberia.entrar(id);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        barberia.cabelloCortado();
    }
}
