package dao;

import modelo.Turno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TurnoDAO {

    public boolean existeColision(
            String fecha,
            String hora,
            int idMedico) {

        String sql =
                "SELECT COUNT(*) "
                + "FROM turnos "
                + "WHERE fecha_turno=? "
                + "AND hora_turno=? "
                + "AND id_medico=? "
                + "AND estado <> 'Cancelado'";

        try (
                Connection con =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, fecha);
            ps.setString(2, hora);
            ps.setInt(3, idMedico);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al verificar turno: "
                    + e.getMessage());
        }

        return false;
    }

    public void registrarTurno(Turno turno) {

        if (existeColision(
                turno.getFechaTurno(),
                turno.getHoraTurno(),
                turno.getIdMedico())) {

            System.out.println(
                    "Error: el médico ya posee "
                    + "un turno en ese horario.");

            return;
        }

        String sql =
                "INSERT INTO turnos "
                + "(fecha_turno,hora_turno,estado,"
                + "dni_paciente,id_medico) "
                + "VALUES (?,?,?,?,?)";

        try (
                Connection con =
                        ConexionBD.conectar();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(1, turno.getFechaTurno());
            ps.setString(2, turno.getHoraTurno());
            ps.setString(3, "Pendiente");
            ps.setInt(4, turno.getDniPaciente());
            ps.setInt(5, turno.getIdMedico());

            ps.executeUpdate();

            System.out.println(
                    "Turno registrado correctamente.");

        } catch (SQLException e) {

            System.out.println(
                    "Error al registrar turno: "
                    + e.getMessage());
        }
    }

    public void listarTurnos() {

        String sql =
                "SELECT * "
                + "FROM turnos "
                + "ORDER BY fecha_turno, hora_turno";

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
                        "Turno: "
                        + rs.getInt("id_turno")
                        + " | Fecha: "
                        + rs.getString("fecha_turno")
                        + " | Hora: "
                        + rs.getString("hora_turno")
                        + " | Paciente DNI: "
                        + rs.getInt("dni_paciente")
                        + " | Médico ID: "
                        + rs.getInt("id_medico")
                        + " | Estado: "
                        + rs.getString("estado")
                );
            }

        } catch (SQLException e) {

            System.out.println(
                    "Error al listar turnos: "
                    + e.getMessage());
        }
    }
}