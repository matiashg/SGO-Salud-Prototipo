package principal;

import dao.ConexionBD;
import java.sql.Connection;

public class PruebaConexion {

    public static void main(String[] args) {

        try {

            Connection conexion =
                    ConexionBD.conectar();

            System.out.println(
                    "Conexión exitosa a MySQL");

            conexion.close();

        } catch (Exception e) {

            System.out.println(
                    "Error de conexión");

            System.out.println(
                    e.getMessage());

        }

    }
}