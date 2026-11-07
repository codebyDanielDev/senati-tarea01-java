import java.util.Locale;
import java.util.Scanner;

public class Caso05_SeguroSalud {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner lector = new Scanner(System.in);

        System.out.print("Plan (B = básico, P = plus, F = full): ");
        String plan = lector.next().toUpperCase();

        double coberturaConsulta;
        double coberturaEmergencia;
        double coberturaHospital;
        double tope;

        switch (plan) {
            case "B":
                coberturaConsulta = 60;
                coberturaEmergencia = 70;
                coberturaHospital = 50;
                tope = 5000;
                break;
            case "P":
                coberturaConsulta = 80;
                coberturaEmergencia = 90;
                coberturaHospital = 70;
                tope = 15000;
                break;
            case "F":
                coberturaConsulta = 100;
                coberturaEmergencia = 100;
                coberturaHospital = 90;
                tope = 50000;
                break;
            default:
                System.out.println("Plan no válido");
                return;
        }

        System.out.print("Tipo de atención (C = consulta, E = emergencia, H = hospitalización): ");
        String atencion = lector.next().toUpperCase();

        double porcentaje;
        double deducible;

        switch (atencion) {
            case "C":
                porcentaje = coberturaConsulta;
                deducible = 0;
                break;
            case "E":
                porcentaje = coberturaEmergencia;
                deducible = 50;
                break;
            case "H":
                porcentaje = coberturaHospital;
                deducible = 200;
                break;
            default:
                System.out.println("Tipo de atención no válido");
                return;
        }

        System.out.print("Monto de la atención: ");
        double monto = lector.nextDouble();

        double pagaAseguradora = 0;
        if (monto > deducible) {
            pagaAseguradora = (monto - deducible) * porcentaje / 100;
            if (pagaAseguradora > tope) {
                pagaAseguradora = tope;
            }
        }

        double pagaPaciente = monto - pagaAseguradora;

        System.out.printf("Paga la aseguradora: S/ %.2f%n", pagaAseguradora);
        System.out.printf("Paga el paciente: S/ %.2f%n", pagaPaciente);

        lector.close();
    }
}
