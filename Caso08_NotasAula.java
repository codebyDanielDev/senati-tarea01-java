import java.util.Locale;
import java.util.Scanner;

public class Caso08_NotasAula {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner lector = new Scanner(System.in);

        System.out.print("Número de estudiantes: ");
        int numeroEstudiantes = lector.nextInt();

        int considerados = 0;
        int aprobados = 0;
        double sumaPromedios = 0;
        double mejorPromedio = -1;
        String mejorEstudiante = "";

        for (int i = 1; i <= numeroEstudiantes; i++) {
            System.out.print("Nombre y tres notas: ");
            String nombre = lector.next();

            double suma = 0;
            boolean notasValidas = true;

            for (int j = 1; j <= 3; j++) {
                double nota = lector.nextDouble();
                if (nota < 0 || nota > 20) {
                    notasValidas = false;
                }
                suma = suma + nota;
            }

            if (!notasValidas) {
                System.out.println(nombre + ": nota inválida, no se considera");
                continue;
            }

            double promedio = suma / 3;

            String condicion;
            if (promedio >= 11) {
                condicion = "Aprobado";
                aprobados++;
            } else {
                condicion = "Desaprobado";
            }

            System.out.printf("%s: promedio %.2f - %s%n", nombre, promedio, condicion);

            considerados++;
            sumaPromedios = sumaPromedios + promedio;

            if (promedio > mejorPromedio) {
                mejorPromedio = promedio;
                mejorEstudiante = nombre;
            }
        }

        if (considerados == 0) {
            System.out.println("Sin estudiantes válidos");
        } else {
            double porcentajeAprobados = aprobados * 100.0 / considerados;
            double promedioGeneral = sumaPromedios / considerados;

            System.out.println("Estudiantes considerados: " + considerados);
            System.out.printf("Aprobados: %d (%.2f %%)%n", aprobados, porcentajeAprobados);
            System.out.printf("Promedio general: %.2f%n", promedioGeneral);
            System.out.printf("Mejor promedio: %s (%.2f)%n", mejorEstudiante, mejorPromedio);
        }

        lector.close();
    }
}
