package DAO;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {
    private Connection con;
    private Statement sentencia;

    public ResultSet listarUsuarios() {
        con = Conexion.conectar();
        sentencia = Conexion.obtenerSentencia();
        ResultSet listado = null;

        try {
            listado = sentencia.executeQuery("select * from usuarios");
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listado;
    }

    public int buscarUsuarioPorCodigo(String codigo) {
        con = Conexion.conectar();
        int idUsuario = 0;

        try {
            PreparedStatement sentencia = con.prepareStatement(
                    "SELECT id_usuario FROM usuarios WHERE codigo = ?");
            sentencia.setString(1, codigo);
            ResultSet listado = sentencia.executeQuery();

            while (listado.next()) {
                idUsuario = listado.getInt("id_usuario");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idUsuario;
    }
}