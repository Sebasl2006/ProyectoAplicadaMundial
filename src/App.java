import DAO.Conexion;
import Vista.VistaPrincipal;

public class App {
    public static void main(String[] args) throws Exception {
           VistaPrincipal vista = new VistaPrincipal();
           Conexion conexion = new Conexion();

        vista.setVisible(true);
        Conexion.conectar(); // Llamada al método conectar() para establecer la conexión

    }
}
