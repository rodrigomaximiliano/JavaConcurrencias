public class Boveda {
    private int dinero;

    public Boveda() {
        this.dinero = 10000; 
    }

    public synchronized void depositar(int monto) {
        dinero += monto;
    }

    public synchronized boolean extraer(int monto) {
        if (dinero >= monto) {
            dinero -= monto;
            return true;
        } else {
            return false; 
        }
    }

    public synchronized int getDinero() {
        return dinero;
    }
}