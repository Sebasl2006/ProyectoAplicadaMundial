import Controlador.PartidoControlador;
import Controlador.PruebaArduino;
import Controlador.ReporteControlador;
import DAO.Conexion;
import Vista.VistaPrincipal;


public class App {

 public static void main(String[] args) throws Exception {

        System.out.println("Iniciando aplicación...");

        VistaPrincipal vista = new VistaPrincipal();
        System.out.println("VistaPrincipal creada.");

        new PartidoControlador(vista);
        System.out.println("Controlador creado.");

        new ReporteControlador(vista.getVistaPronosticosUsuario(), vista.getVistaRankingAciertos());
        System.out.println("ReporteControlador creado.");

        vista.setVisible(true);
        System.out.println("Ventana visible.");

        new Thread(() -> {
            new PruebaArduino().iniciar();
        }).start();

    }
}