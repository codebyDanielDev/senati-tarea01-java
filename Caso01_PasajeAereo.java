import java.util.Locale;
import java.util.Scanner;

public class Caso01_PasajeAereo {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner lector = new Scanner(System.in);

        System.out.print("Destino (1 = Cusco, 2 = Arequipa, 3 = Piura, 4 = Iquitos): ");
        int destino = lector.nextInt();

        double tarifa = 0;
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
                System.out.println("Destino no válido");
                return;
        }

        System.out.print("Clase (E = económica, N = negocios): ");
        String clase = lector.next().toUpperCase();

        int kilosPermitidos = 0;
        if (clase.equals("E")) {
            kilosPermitidos = 23;
        } else if (clase.equals("N")) {
            tarifa = tarifa * 1.8;
            kilosPermitidos = 32;
        } else {
            System.out.println("Clase no válida");
            return;
        }

        System.out.print("Edad del pasajero: ");
        int edad = lector.nextInt();
        System.out.print("Días de anticipación: ");
        int dias = lector.nextInt();
        System.out.print("Kilos de equipaje: ");
        double kilos = lector.nextDouble();

        if (edad < 2) {
            tarifa = tarifa * 0.10;
        } else if (edad <= 11) {
            tarifa = tarifa * 0.75;
        } else if (edad >= 65) {
            tarifa = tarifa * 0.85;
        }

        if (dias >= 30) {
            tarifa = tarifa * 0.85;
        } else if (dias >= 15) {
            tarifa = tarifa * 0.92;
        }

        double exceso = 0;
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
