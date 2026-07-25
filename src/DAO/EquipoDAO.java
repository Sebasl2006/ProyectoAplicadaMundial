package DAO;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EquipoDAO {
    private Connection con;
    private Statement sentencia;

    public ResultSet listarEquipos() {
        con = Conexion.conectar();
        sentencia = Conexion.obtenerSentencia();
        ResultSet listado = null;

        try {
            listado = sentencia.executeQuery("SELECT * FROM Equipo ORDER BY equipo");
        } catch (SQLException ex) {
            Logger.getLogger(EquipoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    public int buscarEquipo(String equipo) {
        con = Conexion.conectar();
        int idEquipo = 0;

        try {
            PreparedStatement sentencia = con.prepareStatement(
                    "SELECT idEquipo FROM Equipo WHERE equipo = ?");
            sentencia.setString(1, equipo);
            ResultSet listado = sentencia.executeQuery();

            while (listado.next()) {
                idEquipo = listado.getInt("idEquipo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(EquipoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idEquipo;
    }
}