import java.util.Locale;
import java.util.Scanner;

public class Caso02_TaxiAplicativo {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner lector = new Scanner(System.in);

        System.out.print("Distancia en km: ");
        double distancia = lector.nextDouble();
        System.out.print("Minutos de espera: ");
        int espera = lector.nextInt();
        System.out.print("Hora de inicio (0 a 23): ");
        int hora = lector.nextInt();
        System.out.print("¿Va al aeropuerto? (S/N): ");
        String aeropuerto = lector.next().toUpperCase();
        System.out.print("Nivel de demanda (1 a 3): ");
        int demanda = lector.nextInt();

        double multiplicador = 0;
        switch (demanda) {
            case 1:
                multiplicador = 1.0;
                break;
            case 2:
                multiplicador = 1.3;
                break;
            case 3:
                multiplicador = 1.6;
                break;
            default:
                System.out.println("Nivel de demanda no válido");
                return;
        }

        double costoDistancia;
        if (distancia <= 5) {
            costoDistancia = distancia * 1.80;
        } else {
            costoDistancia = 5 * 1.80 + (distancia - 5) * 1.50;
        }

        double costoEspera = 0;
        if (espera > 3) {
            costoEspera = (espera - 3) * 0.30;
        }

        double subtotal = 4.50 + costoDistancia + costoEspera;

        if (hora >= 22 || hora < 5) {
            subtotal = subtotal * 1.20;
        }

        double total = subtotal * multiplicador;

        if (aeropuerto.equals("S")) {
            total = total + 8.00;
        }

        if (total < 9.00) {
            System.out.println("Total a pagar: S/ 9.00 (tarifa mínima)");
        } else {
            System.out.printf("Total a pagar: S/ %.2f%n", total);
        }

        lector.close();
    }
}
