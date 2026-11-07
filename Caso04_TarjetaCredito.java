import java.util.Locale;
import java.util.Scanner;

public class Caso04_TarjetaCredito {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner lector = new Scanner(System.in);

        System.out.print("Deuda total: ");
        double deuda = lector.nextDouble();
        System.out.print("Pago realizado: ");
        double pago = lector.nextDouble();
        System.out.print("Días de atraso: ");
        int diasAtraso = lector.nextInt();
        System.out.print("Tipo de tarjeta (C = Clásica, G = Gold): ");
        String tarjeta = lector.next().toUpperCase();

        double tasaInteres;
        if (tarjeta.equals("C")) {
            tasaInteres = 0.035;
        } else if (tarjeta.equals("G")) {
            tasaInteres = 0.028;
        } else {
            System.out.println("Tipo de tarjeta no válido");
            return;
        }

        if (pago >= deuda) {
            System.out.println("Deuda cancelada. Sin intereses");
            return;
        }

        double pagoMinimo = deuda * 0.05;
        if (pagoMinimo < 50) {
            pagoMinimo = 50;
        }
        if (deuda < 50) {
            pagoMinimo = deuda;
        }

        double saldoPendiente = deuda - pago;
        double interes = saldoPendiente * tasaInteres;

        double mora = 0;
        String estado = "Al día";
        if (pago < pagoMinimo) {
            estado = "En mora";
            mora = saldoPendiente * 0.001 * diasAtraso;
            double moraMaxima = saldoPendiente * 0.10;
            if (mora > moraMaxima) {
                mora = moraMaxima;
            }
        }

        double nuevoSaldo = saldoPendiente + interes + mora;

        System.out.printf("Pago mínimo: S/ %.2f%n", pagoMinimo);
        System.out.printf("Saldo pendiente: S/ %.2f%n", saldoPendiente);
        System.out.printf("Interés: S/ %.2f%n", interes);
        System.out.printf("Mora: S/ %.2f%n", mora);
        System.out.printf("Nuevo saldo: S/ %.2f%n", nuevoSaldo);
        System.out.println("Estado: " + estado);

        lector.close();
    }
}
