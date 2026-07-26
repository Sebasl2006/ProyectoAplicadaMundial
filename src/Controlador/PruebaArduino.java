package Controlador;

import DAO.PronosticoDAO;

public class PruebaArduino {

    public void iniciar() {

        ConexionArduino arduino = new ConexionArduino();
        PronosticoDAO pronosticoDAO = new PronosticoDAO();

        if (!arduino.conectar()) {
            System.out.println("No se pudo iniciar Arduino");
            return;
        }

        System.out.println("Esperando información del Arduino...");

        while (true) {

            String mensaje = arduino.leerLinea();

            if (mensaje != null && !mensaje.trim().isEmpty()) {

                mensaje = mensaje.trim();

                System.out.println("Arduino envió: " + mensaje);

                String respuesta =
                        procesarPronostico(mensaje, pronosticoDAO);

                arduino.enviarRespuesta(respuesta);

                System.out.println(
                        "Respuesta enviada: " + respuesta
                );
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }

        arduino.desconectar();
    }

    private String procesarPronostico(
            String mensaje,
            PronosticoDAO pronosticoDAO
    ) {

        String[] datos = mensaje.split(",");

        if (datos.length != 5) {
            return "ERROR";
        }

        if (!datos[0].equals("PRONOSTICO")) {
            return "ERROR";
        }

        try {

            return pronosticoDAO.registrarPronostico(
                    datos[1],
                    datos[2],
                    Integer.parseInt(datos[3]),
                    Integer.parseInt(datos[4])
            );

        } catch (NumberFormatException e) {
            return "ERROR";
        }
    }
}