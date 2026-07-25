package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import Modelo.Pronostico;

public class PronosticoDAO {
    private Connection con;

    public boolean agregarPronostico(Pronostico p) {
        con = Conexion.conectar();

        try {
            PreparedStatement sentencia = con.prepareStatement(
                    "INSERT INTO Pronostico (golesEquipoA, golesEquipoB, idUsuario, idPartido) "
                  + "VALUES (?,?,?,?)");
            sentencia.setInt(1, p.getGolesEquipoA());
            sentencia.setInt(2, p.getGolesEquipoB());
            sentencia.setInt(3, p.getIdUsuario());
            sentencia.setInt(4, p.getIdPartido());
            sentencia.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PronosticoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean existePronosticoParaPartido(int idPartido) {
        con = Conexion.conectar();
        boolean existe = false;

        try {
            PreparedStatement sentencia = con.prepareStatement(
                    "SELECT COUNT(*) AS total FROM Pronostico WHERE idPartido = ?");
            sentencia.setInt(1, idPartido);
            ResultSet listado = sentencia.executeQuery();

            if (listado.next()) {
                existe = listado.getInt("total") > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PronosticoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return existe;
    }

    public ArrayList<String> contarPronosticosPorUsuario() {
        con = Conexion.conectar();
        Statement sentencia = Conexion.obtenerSentencia();
        ArrayList<String> datos = new ArrayList<>();

        try {
            ResultSet listado = sentencia.executeQuery(
                    "SELECT u.nombre, COUNT(pr.idPronostico) AS total\n"
                  + "FROM Usuario u\n"
                  + "LEFT JOIN Pronostico pr ON u.idUsuario = pr.idUsuario\n"
                  + "GROUP BY u.idUsuario, u.nombre;");

            while (listado.next()) {
                datos.add(listado.getString("nombre") + "," + listado.getString("total"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PronosticoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }

    public ArrayList<String> listarConAciertos() {
        con = Conexion.conectar();
        Statement sentencia = Conexion.obtenerSentencia();
        ArrayList<String> datos = new ArrayList<>();

        try {
            ResultSet listado = sentencia.executeQuery(
                    "SELECT u.nombre, pr.idPartido, pr.golesEquipoA, pr.golesEquipoB,\n"
                  + "pa.golesEquipoA AS realA, pa.golesEquipoB AS realB\n"
                  + "FROM Pronostico pr\n"
                  + "INNER JOIN Usuario u ON pr.idUsuario = u.idUsuario\n"
                  + "INNER JOIN Partido pa ON pr.idPartido = pa.idPartido\n"
                  + "WHERE pa.golesEquipoA IS NOT NULL AND pa.golesEquipoB IS NOT NULL;");

            while (listado.next()) {
                String nombre = listado.getString("nombre");
                int idPartido = listado.getInt("idPartido");
                int golesA = listado.getInt("golesEquipoA");
                int golesB = listado.getInt("golesEquipoB");
                int realA = listado.getInt("realA");
                int realB = listado.getInt("realB");

                String acierto = (golesA == realA && golesB == realB) ? "Acierto" : "Fallo";
                datos.add(nombre + "," + idPartido + "," + golesA + "," + golesB + "," + acierto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PronosticoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return datos;
    }
}