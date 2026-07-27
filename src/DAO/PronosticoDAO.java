package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

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


    public Map<String, Integer> cantidadPronosticosPorUsuario() {
        Map<String, Integer> datos = new LinkedHashMap<>();

        String sql = """
                SELECT u.nombre,
                    COUNT(p.idPronostico) AS cantidad
                FROM Usuario u
                LEFT JOIN Pronostico p
                    ON u.idUsuario = p.idUsuario
                GROUP BY u.idUsuario, u.nombre
                ORDER BY cantidad DESC
                """;
        try {
            Connection conexion = Conexion.getConexion();
            PreparedStatement ps =
                    conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                datos.put(
                        rs.getString("nombre"),
                        rs.getInt("cantidad")
                );
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(
                    "Error al consultar pronósticos: "
                    + e.getMessage()
            );
        }
        return datos;
    }

    public Map<String, Integer> cantidadPronosticosPorFecha(
            String fechaDesde,
            String fechaHasta
    ) {
        Map<String, Integer> datos = new LinkedHashMap<>();
        String sql = """
                SELECT u.nombre,
                    COUNT(p.idPronostico) AS cantidad
                FROM Usuario u
                LEFT JOIN Pronostico p
                    ON u.idUsuario = p.idUsuario
                    AND DATE(p.fechaRegistro)
                    BETWEEN ? AND ?
                GROUP BY u.idUsuario, u.nombre
                ORDER BY cantidad DESC
                """;
        try {
            Connection conexion = Conexion.getConexion();
            PreparedStatement ps =
                    conexion.prepareStatement(sql);
            ps.setString(1, fechaDesde);
            ps.setString(2, fechaHasta);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                datos.put(
                        rs.getString("nombre"),
                        rs.getInt("cantidad")
                );
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(
                    "Error al consultar pronósticos: "
                    + e.getMessage()
            );
        }
        return datos;
    }
    public DefaultTableModel obtenerRankingAciertos() {
        String[] columnas = {
            "Posición",
            "Usuario",
            "Pronósticos",
            "Aciertos",
            "Fallos"
        };
        DefaultTableModel modelo =
                new DefaultTableModel(columnas, 0) {
                    @Override
                    public boolean isCellEditable(
                            int fila,
                            int columna
                    ) {
                        return false;
                    }
                };
        String sql = """
                SELECT
                    u.nombre,
                    COUNT(p.idPronostico) AS totalPronosticos,
                    SUM(
                        CASE
                            WHEN pa.golesEquipoA IS NOT NULL
                            AND pa.golesEquipoB IS NOT NULL
                            AND p.golesEquipoA = pa.golesEquipoA
                            AND p.golesEquipoB = pa.golesEquipoB
                            THEN 1
                            ELSE 0
                        END
                    ) AS aciertos,

                    SUM(
                        CASE
                            WHEN pa.golesEquipoA IS NOT NULL
                            AND pa.golesEquipoB IS NOT NULL
                            AND (
                                p.golesEquipoA <> pa.golesEquipoA
                                OR p.golesEquipoB <> pa.golesEquipoB
                            )
                            THEN 1
                            ELSE 0
                        END
                    ) AS fallos

                FROM Usuario u

                LEFT JOIN Pronostico p
                    ON u.idUsuario = p.idUsuario

                LEFT JOIN Partido pa
                    ON p.idPartido = pa.idPartido

                GROUP BY u.idUsuario, u.nombre

                ORDER BY aciertos DESC,
                        totalPronosticos DESC,
                        u.nombre ASC
                """;

        try {
            Connection conexion = Conexion.getConexion();
            PreparedStatement ps =
                    conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            int posicion = 1;
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    posicion,
                    rs.getString("nombre"),
                    rs.getInt("totalPronosticos"),
                    rs.getInt("aciertos"),
                    rs.getInt("fallos")
                });
                posicion++;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(
                    "Error al obtener ranking: "
                    + e.getMessage()
            );
        }
        return modelo;
    }
}