package Modelo;

public class Partido {
    int idPartido, idEquipoA, idEquipoB;
    String fecha, hora;

    public Partido(int idPartido, String fecha, String hora, int idEquipoA, int idEquipoB) {
        this.idPartido = idPartido;
        this.fecha = fecha;
        this.hora = hora;
        this.idEquipoA = idEquipoA;
        this.idEquipoB = idEquipoB;
    }

    public int getIdPartido() { return idPartido; }
    public String getFecha() { return fecha; }
    public String getHora() { return hora; }
    public int getIdEquipoA() { return idEquipoA; }
    public int getIdEquipoB() { return idEquipoB; }
}