package Modelo;

public class ValidacionPronostico {
    public static boolean validarNumero(String texto) {
        return texto != null && texto.matches("\\d+");
    }

     public static boolean validarCodigoPartido(String texto) {
        return texto != null && texto.matches("\\d{3}");
    }
}