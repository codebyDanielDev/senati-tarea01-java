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

        // Bucle infinito: sigue recibiendo paquetes hasta ingresar 0 o hasta llenar el camion (break)
        while (true) {
            System.out.print("Peso del paquete en kg (0 para terminar): ");
            double peso = lector.nextDouble();

            // 0 es la senial de salida: rompe el bucle y pasa al resumen final
            if (peso == 0) {
                break;
            }

            // Ningun paquete puede pesar mas de 200 kg; si pasa, se rechaza y se pide otro (continue)
            if (peso > 200) {
                System.out.println("Paquete rechazado: excede 200 kg");
                rechazados++;
                continue;
            }

            double espacioLibre = capacidad - pesoTotal;
            // Si el paquete no cabe en el espacio que queda, se rechaza e informa cuanto queda
            if (peso > espacioLibre) {
                System.out.printf("Paquete rechazado: no cabe (quedan %.1f kg)%n", espacioLibre);
                rechazados++;
                continue;
            }

            pesoTotal = pesoTotal + peso;
            cargados++;

            // Guarda el peso del paquete mas pesado cargado hasta ahora
            if (peso > masPesado) {
                masPesado = peso;
            }

            System.out.printf("Paquete %d cargado: %.1f kg - Total: %.1f kg%n", cargados, peso, pesoTotal);

            // Al llegar a 950 kg o mas se considera lleno y se termina la carga
            if (pesoTotal >= 950) {
                System.out.println("Camion lleno");
                break;
            }
        }

        double ocupacion = pesoTotal * 100 / capacidad;

        System.out.println("Paquetes cargados: " + cargados + " | Rechazados: " + rechazados);
        System.out.printf("Peso total: %.1f kg%n", pesoTotal);
        System.out.printf("Ocupacion: %.2f %%%n", ocupacion);
        System.out.printf("Paquete mas pesado: %.1f kg%n", masPesado);

        lector.close();
    }
}
