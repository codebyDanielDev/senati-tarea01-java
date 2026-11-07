import java.util.Locale;
import java.util.Scanner;

public class Caso10_CargaCamion {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner lector = new Scanner(System.in);

        double capacidad = 1000;
        double pesoTotal = 0;
        double masPesado = 0;
        int cargados = 0;
        int rechazados = 0;

        while (true) {
            System.out.print("Peso del paquete en kg (0 para terminar): ");
            double peso = lector.nextDouble();

            if (peso == 0) {
                break;
            }

            if (peso > 200) {
                System.out.println("Paquete rechazado: excede 200 kg");
                rechazados++;
                continue;
            }

            double espacioLibre = capacidad - pesoTotal;
            if (peso > espacioLibre) {
                System.out.printf("Paquete rechazado: no cabe (quedan %.1f kg)%n", espacioLibre);
                rechazados++;
                continue;
            }

            pesoTotal = pesoTotal + peso;
            cargados++;

            if (peso > masPesado) {
                masPesado = peso;
            }

            System.out.printf("Paquete %d cargado: %.1f kg - Total: %.1f kg%n", cargados, peso, pesoTotal);

            if (pesoTotal >= 950) {
                System.out.println("Camión lleno");
                break;
            }
        }

        double ocupacion = pesoTotal * 100 / capacidad;

        System.out.println("Paquetes cargados: " + cargados + " | Rechazados: " + rechazados);
        System.out.printf("Peso total: %.1f kg%n", pesoTotal);
        System.out.printf("Ocupación: %.2f %%%n", ocupacion);
        System.out.printf("Paquete más pesado: %.1f kg%n", masPesado);

        lector.close();
    }
}
