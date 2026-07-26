package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PronosticoDAO {

    public String registrarPronostico(
            String codigoUsuario,
            String codigoPartido,
            int golesEquipoA,
            int golesEquipoB
    ) {

        Connection conexion = null;

        try {
            conexion = Conexion.getConexion();

            if (conexion == null) {
                System.out.println("No existe conexión con MySQL");
                return "ERROR";
            }

            Integer idUsuario = buscarUsuario(
                    conexion,
                    codigoUsuario
            );

            if (idUsuario == null) {
                System.out.println(
                        "Usuario no encontrado: " + codigoUsuario
                );

                return "USUARIO_INVALIDO";
            }

            int idPartido;

            try {
                idPartido = Integer.parseInt(codigoPartido);

            } catch (NumberFormatException e) {
                return "PARTIDO_INVALIDO";
            }

            if (!existePartido(conexion, idPartido)) {
                System.out.println(
                        "Partido no encontrado: " + codigoPartido
                );

                return "PARTIDO_INVALIDO";
            }

            if (existePronostico(
                    conexion,
                    idUsuario,
                    idPartido
            )) {
                System.out.println(
                        "El usuario ya registró este pronóstico"
                );

                return "DUPLICADO";
            }

            String sql = """
                    INSERT INTO Pronostico
                    (
                        golesEquipoA,
                        golesEquipoB,
                        idUsuario,
                        idPartido
                    )
                    VALUES (?, ?, ?, ?)
                    """;

            PreparedStatement sentencia =
                    conexion.prepareStatement(sql);

            sentencia.setInt(1, golesEquipoA);
            sentencia.setInt(2, golesEquipoB);
            sentencia.setInt(3, idUsuario);
            sentencia.setInt(4, idPartido);

            int filasInsertadas = sentencia.executeUpdate();

            sentencia.close();

            if (filasInsertadas > 0) {
                System.out.println(
                        "Pronóstico registrado correctamente"
                );

                return "OK";
            }

            return "ERROR";

        } catch (SQLException e) {

            if (e.getErrorCode() == 1062) {
                System.out.println(
                        "Pronóstico duplicado: " + e.getMessage()
                );

                return "DUPLICADO";
            }

            System.out.println(
                    "Error al registrar pronóstico: "
                    + e.getMessage()
            );

            return "ERROR";

        } catch (Exception e) {
            System.out.println(
                    "Error general: " + e.getMessage()
            );

            return "ERROR";

        } finally {
            try {
                if (conexion != null) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.out.println(
                        "Error al cerrar la conexión: "
                        + e.getMessage()
                );
            }
        }
    }

    private Integer buscarUsuario(
            Connection conexion,
            String codigoUsuario
    ) throws SQLException {

        String sql = """
                SELECT idUsuario
                FROM Usuario
                WHERE codigo = ?
                """;

        PreparedStatement sentencia =
                conexion.prepareStatement(sql);

        sentencia.setString(1, codigoUsuario);

        ResultSet resultado = sentencia.executeQuery();

        Integer idUsuario = null;

        if (resultado.next()) {
            idUsuario = resultado.getInt("idUsuario");
        }

        resultado.close();
        sentencia.close();

        return idUsuario;
    }

    private boolean existePartido(
            Connection conexion,
            int idPartido
    ) throws SQLException {

        String sql = """
                SELECT idPartido
                FROM Partido
                WHERE idPartido = ?
                """;

        PreparedStatement sentencia =
                conexion.prepareStatement(sql);

        sentencia.setInt(1, idPartido);

        ResultSet resultado = sentencia.executeQuery();

        boolean existe = resultado.next();

        resultado.close();
        sentencia.close();

        return existe;
    }

    private boolean existePronostico(
            Connection conexion,
            int idUsuario,
            int idPartido
    ) throws SQLException {

        String sql = """
                SELECT idPronostico
                FROM Pronostico
                WHERE idUsuario = ?
                AND idPartido = ?
                """;

        PreparedStatement sentencia =
                conexion.prepareStatement(sql);

        sentencia.setInt(1, idUsuario);
        sentencia.setInt(2, idPartido);

        ResultSet resultado = sentencia.executeQuery();

        boolean existe = resultado.next();

        resultado.close();
        sentencia.close();

        return existe;
    }


    public boolean existePronosticoParaPartido(int idPartido) {

    String sql = "SELECT COUNT(*) FROM Pronostico WHERE idPartido = ?";

    try {

        Connection conexion = Conexion.getConexion();
        PreparedStatement ps = conexion.prepareStatement(sql);

        ps.setInt(1, idPartido);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt(1) > 0;
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return false;
}
}