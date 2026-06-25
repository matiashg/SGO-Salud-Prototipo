package principal;

import dao.PacienteDAO;
import dao.TurnoDAO;
import modelo.Paciente;
import modelo.Persona;
import modelo.Turno;
import java.util.ArrayList;
import util.ArchivoUtil;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        PacienteDAO pacienteDAO = new PacienteDAO();
        TurnoDAO turnoDAO = new TurnoDAO();

        String[] especialidades = {
            "Cardiología",
            "Clínica Médica",
            "Pediatría",
            "Traumatología"
        };

        int opcion = 0;

        do {
            try {
                System.out.println("================================");
                System.out.println("       SISTEMA SGO-SALUD        ");
                System.out.println("================================");
                System.out.println("1. Registrar paciente");
                System.out.println("2. Buscar paciente por DNI");
                System.out.println("3. Listar pacientes");
                System.out.println("4. Registrar turno médico");
                System.out.println("5. Listar turnos");
                System.out.println("6. Actualizar obra social");
                System.out.println("7. Mostrar pacientes con ArrayList");
                System.out.println("8. Mostrar especialidades");
                System.out.println("9. Exportar pacientes a archivo TXT");
                System.out.println("10. Salir");
                System.out.print("Seleccione una opción: ");

                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {

                    case 1:
                        registrarPaciente(scanner, pacienteDAO);
                        break;

                    case 2:
                        buscarPaciente(scanner, pacienteDAO);
                        break;

                    case 3:
                        pacienteDAO.listarPacientes();
                        break;

                    case 4:
                        registrarTurno(scanner, turnoDAO);
                        break;

                    case 5:
                        turnoDAO.listarTurnos();
                        break;

                    case 6:
                        actualizarObraSocial(scanner, pacienteDAO);
                        break;

                    case 7:
                        mostrarPacientesArrayList(pacienteDAO);
                        break;

                    case 8:
                        mostrarEspecialidades(especialidades);
                        break;

                    case 9:
                        exportarPacientes(pacienteDAO);
                        break;

                    case 10:
                        System.out.println("Sistema finalizado.");
                        break;

                    default:
                        System.out.println("Opción inválida.");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: debe ingresar un número válido.");
                scanner.nextLine();
            }

            System.out.println();

        } while (opcion != 10);

        scanner.close();
    }

    private static void registrarPaciente(
            Scanner scanner,
            PacienteDAO pacienteDAO) {

        System.out.println("--- Registrar Paciente ---");

        System.out.print("DNI: ");
        int dni = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();

        System.out.print("Fecha de nacimiento (AAAA-MM-DD): ");
        String fechaNacimiento = scanner.nextLine();

        System.out.print("Obra social: ");
        String obraSocial = scanner.nextLine();

        Paciente paciente = new Paciente(
                dni,
                nombre,
                fechaNacimiento,
                obraSocial
        );

        pacienteDAO.registrarPaciente(paciente);
    }

    private static void buscarPaciente(
            Scanner scanner,
            PacienteDAO pacienteDAO) {

        System.out.println("--- Buscar Paciente ---");

        System.out.print("Ingrese DNI: ");
        int dni = scanner.nextInt();
        scanner.nextLine();

        Paciente paciente =
                pacienteDAO.buscarPaciente(dni);

        if (paciente != null) {

            Persona persona = paciente;

            System.out.println("Paciente encontrado:");
            persona.mostrarDatos();

        } else {
            System.out.println("Paciente no encontrado.");
        }
    }

    private static void registrarTurno(
            Scanner scanner,
            TurnoDAO turnoDAO) {

        System.out.println("--- Registrar Turno Médico ---");

        System.out.print("Fecha del turno (AAAA-MM-DD): ");
        String fecha = scanner.nextLine();

        System.out.print("Hora del turno (HH:MM:SS): ");
        String hora = scanner.nextLine();

        System.out.print("DNI del paciente: ");
        int dniPaciente = scanner.nextInt();

        System.out.print("ID del médico: ");
        int idMedico = scanner.nextInt();
        scanner.nextLine();

        Turno turno = new Turno(
                fecha,
                hora,
                dniPaciente,
                idMedico
        );

        turnoDAO.registrarTurno(turno);
    }

    private static void actualizarObraSocial(
            Scanner scanner,
            PacienteDAO pacienteDAO) {

        System.out.println("--- Actualizar Obra Social ---");

        System.out.print("Ingrese DNI del paciente: ");
        int dni = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nueva obra social: ");
        String nuevaObraSocial = scanner.nextLine();

        pacienteDAO.actualizarObraSocial(
                dni,
                nuevaObraSocial
        );
    }

    private static void mostrarPacientesArrayList(
            PacienteDAO pacienteDAO) {

        System.out.println("--- Pacientes cargados en ArrayList ---");

        ArrayList<Paciente> pacientes =
                pacienteDAO.obtenerPacientes();

        if (pacientes.isEmpty()) {

            System.out.println(
                    "No hay pacientes registrados.");

        } else {

            for (Paciente paciente : pacientes) {

                System.out.println(
                        paciente.getDni()
                        + " - "
                        + paciente.getNombreCompleto()
                        + " - "
                        + paciente.getObraSocial()
                );
            }
        }
    }

    private static void mostrarEspecialidades(
            String[] especialidades) {

        System.out.println("--- Especialidades disponibles ---");

        for (int i = 0; i < especialidades.length; i++) {

            System.out.println(
                    (i + 1)
                    + ". "
                    + especialidades[i]
            );
        }
    }

    private static void exportarPacientes(
            PacienteDAO pacienteDAO) {

        System.out.println("--- Exportar Pacientes ---");

        ArrayList<Paciente> pacientes =
                pacienteDAO.obtenerPacientes();

        ArchivoUtil.exportarPacientes(pacientes);
    }
}