package Controlador;


public class PruebaArduino {

    public static void main(String[] args) {

        ConexionArduino arduino = new ConexionArduino();

        if (!arduino.conectar()) {
            System.out.println("No se pudo iniciar la prueba");
            return;
        }

        System.out.println("Esperando información del Arduino...");

        while (true) {

            try {
                String mensaje = arduino.leerLinea();

                if (mensaje != null && !mensaje.trim().isEmpty()) {

                    mensaje = mensaje.trim();

                    System.out.println(
                            "Arduino envio: " + mensaje
                    );

                    if (mensaje.startsWith("PRONOSTICO,")) {

                        arduino.enviarRespuesta("OK");

                        System.out.println(
                                "Se respondio OK al Arduino"
                        );
                    }
                }

                Thread.sleep(100);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        arduino.desconectar();
    }
}