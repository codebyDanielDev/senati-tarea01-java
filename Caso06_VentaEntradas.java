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

        // Bucle infinito: sigue atendiendo compras hasta que el usuario escriba F (break)
        while (true) {
            System.out.print("Tipo de entrada (G, V o F para terminar): ");
            String tipo = lector.next().toUpperCase();

            // F es la senial de salida: rompe el bucle y pasa al resumen final
            if (tipo.equals("F")) {
                break;
            }

            System.out.print("Cantidad: ");
            int cantidad = lector.nextInt();

            // Valida el tipo: si no es G ni V, cuenta la compra como rechazada y pide otra (continue)
            if (!tipo.equals("G") && !tipo.equals("V")) {
                System.out.println("Tipo no valido");
                comprasRechazadas++;
                continue;
            }

            // Valida la cantidad: solo se permite de 1 a 6 entradas por compra
            if (cantidad < 1 || cantidad > 6) {
                System.out.println("Cantidad no valida: debe ser de 1 a 6");
                comprasRechazadas++;
                continue;
            }

            int disponibles;
            double precio;
            // Toma el stock y el precio segun el tipo de entrada
            if (tipo.equals("G")) {
                disponibles = generalesDisponibles;
                precio = 80;
            } else {
                disponibles = vipDisponibles;
                precio = 200;
            }

            // Si piden mas de las que quedan, rechaza la compra e informa cuantas hay
            if (cantidad > disponibles) {
                System.out.println("Entradas insuficientes: quedan " + disponibles);
                comprasRechazadas++;
                continue;
            }

            double monto = cantidad * precio;
            // Descuento de 10% al comprar 4 o mas entradas
            if (cantidad >= 4) {
                monto = monto * 0.90;
            }

            // Descuenta del stock y suma a las vendidas del tipo que se compro
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
        System.out.printf("Ocupacion del aforo: %.2f %%%n", ocupacion);
        System.out.println("Compras aceptadas: " + comprasAceptadas + " | Compras rechazadas: " + comprasRechazadas);

        lector.close();
    }
}
