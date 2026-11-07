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
        System.out.print("Defectos criticos: ");
        int criticos = lector.nextInt();
        System.out.print("Precio unitario: ");
        double precio = lector.nextDouble();
        System.out.print("Proveedor certificado? (S/N): ");
        String certificado = lector.next().toUpperCase();

        int muestraMinima = (int) Math.ceil(lote / 10.0);
        // Se debe revisar al menos el 10% del lote; si no, avisa cuantas faltan y termina
        if (revisadas < muestraMinima) {
            System.out.println("Muestra insuficiente: revisar al menos " + muestraMinima + " unidades");
            return;
        }

        // Un solo defecto critico rechaza el lote completo y no se paga nada
        if (criticos >= 1) {
            System.out.println("Decision: Lote RECHAZADO: defectos criticos");
            System.out.println("Pago al proveedor: S/ 0.00");
            return;
        }

        double porcentaje = menores * 100.0 / revisadas;

        double maximo;
        // El proveedor certificado tiene mas tolerancia de defectos (3%) que el normal (2%)
        if (certificado.equals("S")) {
            maximo = 3;
        } else {
            maximo = 2;
        }

        double valorLote = lote * precio;
        String decision;
        double pago;

        // Hasta el maximo se acepta y paga completo; hasta 3 puntos mas se paga con 10% de descuento
        // Si pasa de eso, se rechaza y no se paga
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
        System.out.println("Decision: " + decision);
        System.out.printf("Pago al proveedor: S/ %.2f%n", pago);

        lector.close();
    }
}
