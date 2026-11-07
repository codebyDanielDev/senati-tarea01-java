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
        System.out.print("Dias de atraso: ");
        int diasAtraso = lector.nextInt();
        System.out.print("Tipo de tarjeta (C = Clasica, G = Gold): ");
        String tarjeta = lector.next().toUpperCase();

        double tasaInteres;
        // Define la tasa de interes segun el tipo de tarjeta; si no es C ni G, avisa y termina
        if (tarjeta.equals("C")) {
            tasaInteres = 0.035;
        } else if (tarjeta.equals("G")) {
            tasaInteres = 0.028;
        } else {
            System.out.println("Tipo de tarjeta no valido");
            return;
        }

        // Si el pago cubre toda la deuda no hay intereses ni mora, y termina
        if (pago >= deuda) {
            System.out.println("Deuda cancelada. Sin intereses");
            return;
        }

        double pagoMinimo = deuda * 0.05;
        // El pago minimo es el 5% de la deuda, pero nunca menos de S/ 50
        if (pagoMinimo < 50) {
            pagoMinimo = 50;
        }
        // Excepcion: si la deuda total es menor a S/ 50, el minimo es la deuda completa
        if (deuda < 50) {
            pagoMinimo = deuda;
        }

        double saldoPendiente = deuda - pago;
        double interes = saldoPendiente * tasaInteres;

        double mora = 0;
        String estado = "Al dia";
        // Si pago menos que el minimo, queda en mora y se cobra 0.1% diario por los dias de atraso
        if (pago < pagoMinimo) {
            estado = "En mora";
            mora = saldoPendiente * 0.001 * diasAtraso;
            double moraMaxima = saldoPendiente * 0.10;
            // La mora tiene un tope de 10% del saldo pendiente
            if (mora > moraMaxima) {
                mora = moraMaxima;
            }
        }

        double nuevoSaldo = saldoPendiente + interes + mora;

        System.out.printf("Pago minimo: S/ %.2f%n", pagoMinimo);
        System.out.printf("Saldo pendiente: S/ %.2f%n", saldoPendiente);
        System.out.printf("Interes: S/ %.2f%n", interes);
        System.out.printf("Mora: S/ %.2f%n", mora);
        System.out.printf("Nuevo saldo: S/ %.2f%n", nuevoSaldo);
        System.out.println("Estado: " + estado);

        lector.close();
    }
}
