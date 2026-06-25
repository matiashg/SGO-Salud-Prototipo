package util;

import modelo.Paciente;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ArchivoUtil {

    public static void exportarPacientes(
            ArrayList<Paciente> pacientes) {

        try {

            FileWriter archivo =
                    new FileWriter("pacientes_exportados.txt");

            PrintWriter escritor =
                    new PrintWriter(archivo);

            escritor.println("===== PACIENTES SGO-SALUD =====");

            for (Paciente paciente : pacientes) {

                escritor.println(
                        paciente.getDni()
                        + " - "
                        + paciente.getNombreCompleto()
                );
            }

            escritor.close();

            System.out.println(
                    "Archivo generado correctamente.");

        } catch (Exception e) {

            System.out.println(
                    "Error al exportar pacientes.");

            System.out.println(
                    e.getMessage());
        }
    }
}