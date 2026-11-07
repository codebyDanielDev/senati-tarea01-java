import java.util.Locale;
import java.util.Scanner;

public class Caso09_FacturacionLlamadas {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner lector = new Scanner(System.in);

        int numeroLlamada = 0;
        int minutosGratisUsados = 0;
        double totalSinIgv = 0;

        // Bucle infinito: sigue registrando llamadas hasta escribir F o hasta superar el limite (break)
        while (true) {
            System.out.print("Tipo de llamada (L, N, I o F para terminar): ");
            String tipo = lector.next().toUpperCase();

            // F es la senial de salida: rompe el bucle y pasa a la facturacion
            if (tipo.equals("F")) {
                break;
            }

            System.out.print("Duracion en segundos: ");
            int segundos = lector.nextInt();

            String nombreTipo;
            double tarifa;

            // Asigna el nombre y la tarifa por minuto segun el tipo de llamada
            // Si el tipo no existe, avisa y pide otra llamada (continue) sin cobrar nada
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
                    System.out.println("Tipo de llamada no valido");
                    continue;
            }

            int minutos = (int) Math.ceil(segundos / 60.0);
            int minutosACobrar = minutos;

            // Solo las llamadas locales tienen minutos gratis (60 en total entre todas las llamadas)
            if (tipo.equals("L")) {
                int gratisDisponibles = 60 - minutosGratisUsados;
                int gratisEnEstaLlamada;
                // Si la llamada cabe en los minutos gratis que quedan, es toda gratis;
                // si no, solo se descuentan los que quedan y el resto se cobra
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

            // Si el acumulado pasa de S/ 50, suspende el servicio y sale del bucle
            if (totalSinIgv > 50.00) {
                System.out.println("Servicio suspendido por exceder el limite");
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
