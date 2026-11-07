import java.util.Locale;
import java.util.Scanner;

public class Caso08_NotasAula {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner lector = new Scanner(System.in);

        System.out.print("Numero de estudiantes: ");
        int numeroEstudiantes = lector.nextInt();

        int considerados = 0;
        int aprobados = 0;
        double sumaPromedios = 0;
        double mejorPromedio = -1;
        String mejorEstudiante = "";

        // Bucle externo: se repite una vez por cada estudiante
        for (int i = 1; i <= numeroEstudiantes; i++) {
            System.out.print("Nombre y tres notas: ");
            String nombre = lector.next();

            double suma = 0;
            boolean notasValidas = true;

            // Bucle interno: lee las 3 notas del estudiante y las va sumando
            for (int j = 1; j <= 3; j++) {
                double nota = lector.nextDouble();
                // Una nota fuera del rango de 0 a 20 invalida a este estudiante
                // (igual se terminan de leer las 3 notas para no desordenar la entrada)
                if (nota < 0 || nota > 20) {
                    notasValidas = false;
                }
                suma = suma + nota;
            }

            // Si alguna nota fue invalida, avisa y salta al siguiente estudiante sin contarlo
            if (!notasValidas) {
                System.out.println(nombre + ": nota invalida, no se considera");
                continue;
            }

            double promedio = suma / 3;

            String condicion;
            // Aprobado desde 11 de promedio; solo los aprobados suman al contador
            if (promedio >= 11) {
                condicion = "Aprobado";
                aprobados++;
            } else {
                condicion = "Desaprobado";
            }

            System.out.printf("%s: promedio %.2f - %s%n", nombre, promedio, condicion);

            considerados++;
            sumaPromedios = sumaPromedios + promedio;

            // Guarda al estudiante con el mejor promedio hasta el momento
            if (promedio > mejorPromedio) {
                mejorPromedio = promedio;
                mejorEstudiante = nombre;
            }
        }

        // Evita dividir entre cero: si nadie fue valido solo avisa, si no muestra las estadisticas
        if (considerados == 0) {
            System.out.println("Sin estudiantes validos");
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
