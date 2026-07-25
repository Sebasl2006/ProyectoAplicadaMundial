package Modelo;

public class Pronostico {
    int idUsuario, idPartido, golesEquipoA, golesEquipoB;

    public Pronostico(int idUsuario, int idPartido, int golesEquipoA, int golesEquipoB) {
        this.idUsuario = idUsuario;
        this.idPartido = idPartido;
        this.golesEquipoA = golesEquipoA;
        this.golesEquipoB = golesEquipoB;
    }

    public int getIdUsuario() { return idUsuario; }
    public int getIdPartido() { return idPartido; }
    public int getGolesEquipoA() { return golesEquipoA; }
    public int getGolesEquipoB() { return golesEquipoB; }
}