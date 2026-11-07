import java.util.Locale;
import java.util.Scanner;

public class Caso03_ControlCalidad {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner lector = new Scanner(System.in);

        System.out.print("Unidades del lote: ");
        int lote = lector.nextInt();
        System.out.print("Unidades revisadas: ");
        int revisadas = lector.nextInt();
        System.out.print("Defectos menores: ");
        int menores = lector.nextInt();
        System.out.print("Defectos críticos: ");
        int criticos = lector.nextInt();
        System.out.print("Precio unitario: ");
        double precio = lector.nextDouble();
        System.out.print("¿Proveedor certificado? (S/N): ");
        String certificado = lector.next().toUpperCase();

        int muestraMinima = (int) Math.ceil(lote / 10.0);
        if (revisadas < muestraMinima) {
            System.out.println("Muestra insuficiente: revisar al menos " + muestraMinima + " unidades");
            return;
        }

        if (criticos >= 1) {
            System.out.println("Decisión: Lote RECHAZADO: defectos críticos");
            System.out.println("Pago al proveedor: S/ 0.00");
            return;
        }

        double porcentaje = menores * 100.0 / revisadas;

        double maximo;
        if (certificado.equals("S")) {
            maximo = 3;
        } else {
            maximo = 2;
        }

        double valorLote = lote * precio;
        String decision;
        double pago;

        if (porcentaje <= maximo) {
            decision = "Lote ACEPTADO";
            pago = valorLote;
        } else if (porcentaje <= maximo + 3) {
            decision = "Lote ACEPTADO CON DESCUENTO";
            pago = valorLote * 0.90;
        } else {
            decision = "Lote RECHAZADO: exceso de defectos";
            pago = 0;
        }

        System.out.printf("Defectuosas: %.2f %%%n", porcentaje);
        System.out.println("Decisión: " + decision);
        System.out.printf("Pago al proveedor: S/ %.2f%n", pago);

        lector.close();
    }
}
