package DAO;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {

    private static Connection con;
    private static Statement sentencia;

    public static Connection conectar() {
        try {
            if (con != null && con.isValid(0)) {
                System.out.println("Conexion ya existente");
            } else {
                con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/pronosticosPartido",
                        "root",
                        "sebas2006cc3@.");

                sentencia = con.createStatement();

                System.out.println("Conexion exitosa");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;
    }

    public static Statement obtenerSentencia() {
        if (sentencia == null) {
            try {
                sentencia = con.createStatement();
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return sentencia;
    }

    // ===== MÉTODO NUEVO =====
    public static Connection getConexion() {
        return conectar();
    }

    // ===== MÉTODO NUEVO =====
    public static PreparedStatement prepararSentencia(String sql) throws SQLException {

        if (con == null || con.isClosed()) {
            conectar();
        }

        return con.prepareStatement(sql);
    }

    // ===== MÉTODO NUEVO =====
    public static void cerrarConexion() {

        try {

            if (sentencia != null) {
                sentencia.close();
            }

            if (con != null && !con.isClosed()) {
                con.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
}