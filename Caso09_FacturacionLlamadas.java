import java.util.Locale;
import java.util.Scanner;

public class Caso09_FacturacionLlamadas {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner lector = new Scanner(System.in);

        int numeroLlamada = 0;
        int minutosGratisUsados = 0;
        double totalSinIgv = 0;

        while (true) {
            System.out.print("Tipo de llamada (L, N, I o F para terminar): ");
            String tipo = lector.next().toUpperCase();

            if (tipo.equals("F")) {
                break;
            }

            System.out.print("Duración en segundos: ");
            int segundos = lector.nextInt();

            String nombreTipo;
            double tarifa;

            switch (tipo) {
                case "L":
                    nombreTipo = "local";
                    tarifa = 0.08;
                    break;
                case "N":
                    nombreTipo = "nacional";
                    tarifa = 0.15;
                    break;
                case "I":
                    nombreTipo = "internacional";
                    tarifa = 0.90;
                    break;
                default:
                    System.out.println("Tipo de llamada no válido");
                    continue;
            }

            int minutos = (int) Math.ceil(segundos / 60.0);
            int minutosACobrar = minutos;

            if (tipo.equals("L")) {
                int gratisDisponibles = 60 - minutosGratisUsados;
                int gratisEnEstaLlamada;
                if (minutos <= gratisDisponibles) {
                    gratisEnEstaLlamada = minutos;
                } else {
                    gratisEnEstaLlamada = gratisDisponibles;
                }
                minutosGratisUsados = minutosGratisUsados + gratisEnEstaLlamada;
                minutosACobrar = minutos - gratisEnEstaLlamada;
            }

            double costo = minutosACobrar * tarifa;
            totalSinIgv = totalSinIgv + costo;
            numeroLlamada++;

            System.out.printf("Llamada %d (%s): %d min - S/ %.2f%n", numeroLlamada, nombreTipo, minutos, costo);

            if (totalSinIgv > 50.00) {
                System.out.println("Servicio suspendido por exceder el límite");
                break;
            }
        }

        double igv = totalSinIgv * 0.18;
        double totalAPagar = totalSinIgv + igv;

        System.out.println("Minutos locales gratis usados: " + minutosGratisUsados);
        System.out.printf("Total sin IGV: S/ %.2f%n", totalSinIgv);
        System.out.printf("IGV: S/ %.2f%n", igv);
        System.out.printf("Total a pagar: S/ %.2f%n", totalAPagar);

        lector.close();
    }
}
