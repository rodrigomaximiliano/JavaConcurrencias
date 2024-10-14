import java.util.ArrayList;
import java.util.List;

public class LibroDiario {
    private List<String> registros = new ArrayList<>();
    private int totalDepositado = 0;
    private int totalExtraido = 0;

    public void registrar(Cliente.Operacion operacion, int monto) {
        registros.add(operacion + ": " + monto);
        if (operacion == Cliente.Operacion.DEPOSITAR) {
            totalDepositado += monto;
        } else {
            totalExtraido += monto;
        }
    }

    public void mostrarRegistros() {
        System.out.println("Registros del día:");
        for (String registro : registros) {
            System.out.println(registro);
        }
    }

    public int getTotalDepositado() {
        return totalDepositado;
    }

    public int getTotalExtraido() {
        return totalExtraido;
    }
}
