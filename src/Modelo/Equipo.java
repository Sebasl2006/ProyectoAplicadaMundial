package Modelo;

public class Equipo {
    int idEquipo;
    String equipo;

    public Equipo(int idEquipo, String equipo) {
        this.idEquipo = idEquipo;
        this.equipo = equipo;
    }

    public int getIdEquipo() { return idEquipo; }
    public String getEquipo() { return equipo; }
}