import java.util.Locale;
import java.util.Scanner;

public class Caso06_VentaEntradas {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner lector = new Scanner(System.in);

        int generalesDisponibles = 12;
        int vipDisponibles = 4;
        int generalesVendidas = 0;
        int vipVendidas = 0;
        int comprasAceptadas = 0;
        int comprasRechazadas = 0;
        double ingresos = 0;

        while (true) {
            System.out.print("Tipo de entrada (G, V o F para terminar): ");
            String tipo = lector.next().toUpperCase();

            if (tipo.equals("F")) {
                break;
            }

            System.out.print("Cantidad: ");
            int cantidad = lector.nextInt();

            if (!tipo.equals("G") && !tipo.equals("V")) {
                System.out.println("Tipo no válido");
                comprasRechazadas++;
                continue;
            }

            if (cantidad < 1 || cantidad > 6) {
                System.out.println("Cantidad no válida: debe ser de 1 a 6");
                comprasRechazadas++;
                continue;
            }

            int disponibles;
            double precio;
            if (tipo.equals("G")) {
                disponibles = generalesDisponibles;
                precio = 80;
            } else {
                disponibles = vipDisponibles;
                precio = 200;
            }

            if (cantidad > disponibles) {
                System.out.println("Entradas insuficientes: quedan " + disponibles);
                comprasRechazadas++;
                continue;
            }

            double monto = cantidad * precio;
            if (cantidad >= 4) {
                monto = monto * 0.90;
            }

            if (tipo.equals("G")) {
                generalesDisponibles = generalesDisponibles - cantidad;
                generalesVendidas = generalesVendidas + cantidad;
            } else {
                vipDisponibles = vipDisponibles - cantidad;
                vipVendidas = vipVendidas + cantidad;
            }

            ingresos = ingresos + monto;
            comprasAceptadas++;
            System.out.printf("Compra %d: %d entradas %s - S/ %.2f%n", comprasAceptadas, cantidad, tipo, monto);
        }

        double ocupacion = (generalesVendidas + vipVendidas) * 100.0 / 16;

        System.out.println("Entradas vendidas: General " + generalesVendidas + " | VIP " + vipVendidas);
        System.out.printf("Ingresos: S/ %.2f%n", ingresos);
        System.out.printf("Ocupación del aforo: %.2f %%%n", ocupacion);
        System.out.println("Compras aceptadas: " + comprasAceptadas + " | Compras rechazadas: " + comprasRechazadas);

        lector.close();
    }
}
