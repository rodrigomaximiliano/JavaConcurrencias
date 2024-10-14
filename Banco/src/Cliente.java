public class Cliente {
    public enum Operacion {DEPOSITAR, EXTRAER}
    private Operacion operacion;
    private int monto;

    public Cliente(Operacion operacion, int monto) {
        this.operacion = operacion;
        this.monto = monto;
    }

    public Operacion getOperacion() {
        return operacion;
    }

    public int getMonto() {
        return monto;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "operacion=" + operacion +
                ", monto=" + monto +
                '}';
    }
}
