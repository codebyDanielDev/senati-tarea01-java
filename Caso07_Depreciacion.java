import java.util.Locale;
import java.util.Scanner;

public class Caso07_Depreciacion {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner lector = new Scanner(System.in);

        System.out.print("Valor inicial del equipo: ");
        double valorInicial = lector.nextDouble();

        double valorResidual = valorInicial * 0.10;
        double valorLibros = valorInicial;
        double acumulada = 0;
        int anio = 0;

        do {
            anio++;
            double depreciacion = valorLibros * 0.25;

            if (valorLibros - depreciacion < valorResidual) {
                depreciacion = valorLibros - valorResidual;
                valorLibros = valorResidual;
            } else {
                valorLibros = valorLibros - depreciacion;
            }

            acumulada = acumulada + depreciacion;
            System.out.printf("%d | %.2f | %.2f%n", anio, depreciacion, valorLibros);
        } while (valorLibros > valorResidual);

        System.out.println("Años de depreciación: " + anio);
        System.out.printf("Depreciación acumulada: S/ %.2f%n", acumulada);

        lector.close();
    }
}
