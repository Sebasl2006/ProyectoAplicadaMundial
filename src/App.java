import Controlador.PartidoControlador;
import DAO.Conexion;
import Vista.VistaPrincipal;

public class App {
    public static void main(String[] args) throws Exception {
      
       System.out.println("Iniciando aplicación...");

        VistaPrincipal vista = new VistaPrincipal();
        System.out.println("VistaPrincipal creada.");

        new PartidoControlador(vista);
        System.out.println("Controlador creado.");

        vista.setVisible(true);
        System.out.println("Ventana visible.");
           
    }
}
