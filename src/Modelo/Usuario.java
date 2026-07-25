package Modelo;

public class Usuario {
    int idUsuario;
    String codigo, nombre;

    public Usuario(int idUsuario, String codigo, String nombre) {
        this.idUsuario = idUsuario;
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getIdUsuario() { return idUsuario; }
    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
}
