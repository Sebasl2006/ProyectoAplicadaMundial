package Controlador;

import com.fazecast.jSerialComm.SerialPort;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ConexionArduino {

    private SerialPort puerto;
    private BufferedReader entrada;
    private PrintWriter salida;

    public boolean conectar() {

        try {
            puerto = SerialPort.getCommPort("COM3");

            puerto.setBaudRate(9600);
            puerto.setNumDataBits(8);
            puerto.setNumStopBits(SerialPort.ONE_STOP_BIT);
            puerto.setParity(SerialPort.NO_PARITY);

            puerto.setComPortTimeouts(
                    SerialPort.TIMEOUT_READ_SEMI_BLOCKING,
                    1000,
                    0
            );

            if (!puerto.openPort()) {
                System.out.println("No se pudo abrir el puerto COM3");
                return false;
            }

            entrada = new BufferedReader(
                    new InputStreamReader(
                            puerto.getInputStream()
                    )
            );

            salida = new PrintWriter(
                    puerto.getOutputStream(),
                    true
            );

            System.out.println("Arduino conectado correctamente en COM3");

            return true;

        } catch (Exception e) {
            System.out.println(
                    "Error al conectar con Arduino: "
                    + e.getMessage()
            );

            return false;
        }
    }

    public String leerLinea() {

        try {
            if (entrada != null && entrada.ready()) {
                return entrada.readLine();
            }

        } catch (Exception e) {
            System.out.println(
                    "Error al leer desde Arduino: "
                    + e.getMessage()
            );
        }

        return null;
    }

    public void enviarRespuesta(String respuesta) {

        if (salida != null) {
            salida.println(respuesta);
            salida.flush();

            System.out.println(
                    "Java envio al Arduino: " + respuesta
            );
        }
    }

    public void desconectar() {

        try {
            if (puerto != null && puerto.isOpen()) {
                puerto.closePort();

                System.out.println("Arduino desconectado");
            }

        } catch (Exception e) {
            System.out.println(
                    "Error al desconectar Arduino: "
                    + e.getMessage()
            );
        }
    }
}