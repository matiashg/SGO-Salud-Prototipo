package dao;

import modelo.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PacienteDAO {

    public void registrarPaciente(Paciente paciente) {

        String sql =
                "INSERT INTO pacientes "
                + "(dni,nombre_completo,"
                + "fecha_nacimiento,"
                + "obra_social) "
                + "VALUES (?,?,?,?)";

        try (
                Connection con =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    paciente.getDni());

            ps.setString(
                    2,
                    paciente.getNombreCompleto());

            ps.setString(
                    3,
                    paciente.getFechaNacimiento());

            ps.setString(
                    4,
                    paciente.getObraSocial());

            ps.executeUpdate();

            System.out.println(
                    "Paciente registrado.");

        } catch (SQLException e) {

            System.out.println(
                    "Error: "
                    + e.getMessage());
        }
    }

    public Paciente buscarPaciente(
            int dni) {

        String sql =
                "SELECT * "
                + "FROM pacientes "
                + "WHERE dni=?";

        try (
                Connection con =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(1, dni);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return new Paciente(
                        rs.getInt("dni"),
                        rs.getString(
                                "nombre_completo"),
                        rs.getString(
                                "fecha_nacimiento"),
                        rs.getString(
                                "obra_social")
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage());
        }

        return null;
    }

    public void listarPacientes() {

        String sql =
                "SELECT * "
                + "FROM pacientes "
                + "ORDER BY nombre_completo";

        try (
                Connection con =
                        ConexionBD.conectar();

                Statement st =
                        con.createStatement();

                ResultSet rs =
                        st.executeQuery(sql)
        ) {

            while (rs.next()) {

                System.out.println(
                        rs.getInt("dni")
                        + " - "
                        + rs.getString(
                                "nombre_completo")
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    e.getMessage());
        }
    }
    public ArrayList<Paciente> obtenerPacientes() {

    ArrayList<Paciente> listaPacientes =
            new ArrayList<>();

    String sql =
            "SELECT * "
            + "FROM pacientes "
            + "ORDER BY nombre_completo";

    try (
            Connection con =
                    ConexionBD.conectar();

            Statement st =
                    con.createStatement();

            ResultSet rs =
                    st.executeQuery(sql)
    ) {

        while (rs.next()) {

            Paciente paciente =
                    new Paciente(
                            rs.getInt("dni"),
                            rs.getString("nombre_completo"),
                            rs.getString("fecha_nacimiento"),
                            rs.getString("obra_social")
                    );

            listaPacientes.add(paciente);
        }

    } catch (SQLException e) {

        System.out.println(
                "Error al obtener pacientes: "
                + e.getMessage());
    }

    return listaPacientes;
}
    public void actualizarObraSocial(
        int dni,
        String nuevaObraSocial) {

    String sql =
            "UPDATE pacientes "
            + "SET obra_social=? "
            + "WHERE dni=?";

    try (
            Connection con =
                    ConexionBD.conectar();

            PreparedStatement ps =
                    con.prepareStatement(sql)
    ) {

        ps.setString(1, nuevaObraSocial);
        ps.setInt(2, dni);

        int filasAfectadas =
                ps.executeUpdate();

        if (filasAfectadas > 0) {

            System.out.println(
                    "Obra social actualizada correctamente.");

        } else {

            System.out.println(
                    "No se encontró paciente con ese DNI.");
        }

    } catch (SQLException e) {

        System.out.println(
                "Error al actualizar obra social: "
                + e.getMessage());
    }
}
}