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

        // do-while: calcula al menos un anio y repite mientras el valor en libros siga sobre el residual
        do {
            anio++;
            double depreciacion = valorLibros * 0.25;

            // Si depreciar 25% dejaria el valor por debajo del residual, se ajusta
            // para depreciar solo hasta el residual (ultimo anio); si no, se resta normal
            if (valorLibros - depreciacion < valorResidual) {
                depreciacion = valorLibros - valorResidual;
                valorLibros = valorResidual;
            } else {
                valorLibros = valorLibros - depreciacion;
            }

            acumulada = acumulada + depreciacion;
            System.out.printf("%d | %.2f | %.2f%n", anio, depreciacion, valorLibros);
        } while (valorLibros > valorResidual);

        System.out.println("Anios de depreciacion: " + anio);
        System.out.printf("Depreciacion acumulada: S/ %.2f%n", acumulada);

        lector.close();
    }
}
