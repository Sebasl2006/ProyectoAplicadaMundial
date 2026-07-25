package DAO;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import Modelo.Partido;

public class PartidoDAO {
    private Connection con;
    private Statement sentencia;

    public ResultSet listarPartidos() {
        con = Conexion.conectar();
        sentencia = Conexion.obtenerSentencia();
        ResultSet listado = null;

        try {
            listado = sentencia.executeQuery(
                    "SELECT p.idPartido, p.fecha, p.hora, p.golesEquipoA, p.golesEquipoB, "
                  + "ea.equipo AS equipoA, eb.equipo AS equipoB\n"
                  + "FROM Partido p\n"
                  + "INNER JOIN Equipo ea ON p.idEquipoA = ea.idEquipo\n"
                  + "INNER JOIN Equipo eb ON p.idEquipoB = eb.idEquipo\n"
                  + "ORDER BY p.fecha, p.hora;");
        } catch (SQLException ex) {
            Logger.getLogger(PartidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    public ResultSet buscarPartido(int idPartido) {
        con = Conexion.conectar();
        ResultSet listado = null;

        try {
            PreparedStatement sentencia = con.prepareStatement(
                    "SELECT p.idPartido, p.fecha, p.hora, p.idEquipoA, p.idEquipoB, "
                  + "p.golesEquipoA, p.golesEquipoB, ea.equipo AS equipoA, eb.equipo AS equipoB\n"
                  + "FROM Partido p\n"
                  + "INNER JOIN Equipo ea ON p.idEquipoA = ea.idEquipo\n"
                  + "INNER JOIN Equipo eb ON p.idEquipoB = eb.idEquipo\n"
                  + "WHERE p.idPartido = ?;");
            sentencia.setInt(1, idPartido);
            listado = sentencia.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(PartidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    public boolean insertarPartido(Partido p) {
        con = Conexion.conectar();

        try {
            PreparedStatement sentencia = con.prepareStatement(
                    "INSERT INTO Partido (idPartido, fecha, hora, idEquipoA, idEquipoB) "
                  + "VALUES (?,?,?,?,?)");
            sentencia.setInt(1, p.getIdPartido());
            sentencia.setString(2, p.getFecha());
            sentencia.setString(3, p.getHora());
            sentencia.setInt(4, p.getIdEquipoA());
            sentencia.setInt(5, p.getIdEquipoB());
            sentencia.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PartidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean actualizarEquipos(int idPartido, int idEquipoA, int idEquipoB) {
        con = Conexion.conectar();

        try {
            PreparedStatement sentencia = con.prepareStatement(
                    "UPDATE Partido SET idEquipoA = ?, idEquipoB = ? WHERE idPartido = ?");
            sentencia.setInt(1, idEquipoA);
            sentencia.setInt(2, idEquipoB);
            sentencia.setInt(3, idPartido);
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PartidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean registrarResultado(int idPartido, int golesA, int golesB) {
        con = Conexion.conectar();

        try {
            PreparedStatement sentencia = con.prepareStatement(
                    "UPDATE Partido SET golesEquipoA = ?, golesEquipoB = ? WHERE idPartido = ?");
            sentencia.setInt(1, golesA);
            sentencia.setInt(2, golesB);
            sentencia.setInt(3, idPartido);
            return sentencia.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PartidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}