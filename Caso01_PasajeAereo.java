import java.util.Locale;
import java.util.Scanner;

public class Caso01_PasajeAereo {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner lector = new Scanner(System.in);

        System.out.print("Destino (1 = Cusco, 2 = Arequipa, 3 = Piura, 4 = Iquitos): ");
        int destino = lector.nextInt();

        double tarifa = 0;
        // Asigna la tarifa base segun el destino; si no existe, avisa y termina el programa
        switch (destino) {
            case 1:
                tarifa = 180;
                break;
            case 2:
                tarifa = 220;
                break;
            case 3:
                tarifa = 250;
                break;
            case 4:
                tarifa = 320;
                break;
            default:
                System.out.println("Destino no valido");
                return;
        }

        System.out.print("Clase (E = economica, N = negocios): ");
        String clase = lector.next().toUpperCase();

        int kilosPermitidos = 0;
        // Define los kilos permitidos segun la clase (negocios ademas sube la tarifa 80%)
        // Si la clase no es E ni N, avisa y termina el programa
        if (clase.equals("E")) {
            kilosPermitidos = 23;
        } else if (clase.equals("N")) {
            tarifa = tarifa * 1.8;
            kilosPermitidos = 32;
        } else {
            System.out.println("Clase no valida");
            return;
        }

        System.out.print("Edad del pasajero: ");
        int edad = lector.nextInt();
        System.out.print("Dias de anticipacion: ");
        int dias = lector.nextInt();
        System.out.print("Kilos de equipaje: ");
        double kilos = lector.nextDouble();

        // Descuento por edad: bebe paga 10%, ninio 75%, adulto mayor 85%; entra solo al primero que cumpla
        if (edad < 2) {
            tarifa = tarifa * 0.10;
        } else if (edad <= 11) {
            tarifa = tarifa * 0.75;
        } else if (edad >= 65) {
            tarifa = tarifa * 0.85;
        }

        // Descuento por compra anticipada; se acumula con el de edad porque es otro if
        if (dias >= 30) {
            tarifa = tarifa * 0.85;
        } else if (dias >= 15) {
            tarifa = tarifa * 0.92;
        }

        double exceso = 0;
        // Si el equipaje pasa el limite, cobra S/ 12 por cada kilo de mas
        if (kilos > kilosPermitidos) {
            exceso = (kilos - kilosPermitidos) * 12;
        }

        double tasa = 28.90;
        double total = tarifa + exceso + tasa;

        System.out.printf("Tarifa con descuentos: S/ %.2f%n", tarifa);
        System.out.printf("Exceso de equipaje: S/ %.2f%n", exceso);
        System.out.printf("Tasa de embarque: S/ %.2f%n", tasa);
        System.out.printf("Total: S/ %.2f%n", total);

        lector.close();
    }
}
