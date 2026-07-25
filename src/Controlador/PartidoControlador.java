package Controlador;

import DAO.EquipoDAO;
import DAO.PartidoDAO;
import DAO.PronosticoDAO;
import Vista.VistaPrincipal;
import Vista.VistaRegistroPartido;
import Vista.VistaModificarPartido;
import Vista.VistaRegistroResultados;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Modelo.Partido;
import Modelo.ValidacionPronostico;

public class PartidoControlador {

    private final VistaPrincipal vistaPrincipal;
    private final VistaRegistroPartido vistaRegistro;
    private final VistaModificarPartido vistaModificar;
    private final VistaRegistroResultados vistaResultados;

    private final EquipoDAO equipoDAO;
    private final PartidoDAO partidoDAO;
    private final PronosticoDAO pronosticoDAO;

    public PartidoControlador(VistaPrincipal vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
        this.vistaRegistro = vistaPrincipal.getVistaRegistro();
        this.vistaModificar = vistaPrincipal.getVistaModificar();
        this.vistaResultados = vistaPrincipal.getVistaResultados();

        this.equipoDAO = new EquipoDAO();
        this.partidoDAO = new PartidoDAO();
        this.pronosticoDAO = new PronosticoDAO();

        cargarEquipos();
        listarPartidos();

        vistaRegistro.getBtnGuardar().addActionListener(e -> registrarPartido());
        vistaRegistro.getBtnLimpiar().addActionListener(e -> limpiarRegistro());

        vistaModificar.getBtnBuscar().addActionListener(e -> buscarPartidoModificar());
        vistaModificar.getBtnGuardarCambios().addActionListener(e -> guardarCambiosModificar());

        vistaResultados.getBtnBuscar().addActionListener(e -> buscarPartidoResultado());
        vistaResultados.getBtnGuardarResultado().addActionListener(e -> guardarResultado());
    }

    private void cargarEquipos() {
        vistaRegistro.getCmbEquipoA().removeAllItems();
        vistaRegistro.getCmbEquipoB().removeAllItems();
        vistaModificar.getCmbNuevoEquipoA().removeAllItems();
        vistaModificar.getCmbNuevoEquipoB().removeAllItems();

        ResultSet listado = equipoDAO.listarEquipos();

        try {
            while (listado.next()) {
                String equipo = listado.getString("equipo");

                vistaRegistro.getCmbEquipoA().addItem(equipo);
                vistaRegistro.getCmbEquipoB().addItem(equipo);
                vistaModificar.getCmbNuevoEquipoA().addItem(equipo);
                vistaModificar.getCmbNuevoEquipoB().addItem(equipo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PartidoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // ==================== REGISTRO DE PARTIDOS ====================

    private void registrarPartido() {
    String codigoTexto =
            vistaRegistro.getTxtCodigo().getText().trim();

    if (!ValidacionPronostico.validarCodigoPartido(codigoTexto)) {
        JOptionPane.showMessageDialog(
                null,
                "El código del partido debe tener exactamente 3 dígitos."
        );
        return;
    }

    Date fechaSeleccionada =
            vistaRegistro.getDateChooserFecha().getDate();

    if (fechaSeleccionada == null) {
        JOptionPane.showMessageDialog(
                null,
                "Selecciona una fecha."
        );
        return;
    }

    Date horaSeleccionada =
            (Date) vistaRegistro.getSpnHora().getValue();

    SimpleDateFormat formatoFecha =
            new SimpleDateFormat("yyyy-MM-dd");

    SimpleDateFormat formatoHora =
            new SimpleDateFormat("HH:mm:ss");

    String fecha = formatoFecha.format(fechaSeleccionada);
    String hora = formatoHora.format(horaSeleccionada);

    String equipoA =
            (String) vistaRegistro.getCmbEquipoA().getSelectedItem();

    String equipoB =
            (String) vistaRegistro.getCmbEquipoB().getSelectedItem();

    if (equipoA == null
            || equipoB == null
            || equipoA.equals(equipoB)) {

        JOptionPane.showMessageDialog(
                null,
                "Selecciona dos equipos diferentes."
        );
        return;
    }

    int idPartido = Integer.parseInt(codigoTexto);
    int idEquipoA = equipoDAO.buscarEquipo(equipoA);
    int idEquipoB = equipoDAO.buscarEquipo(equipoB);

    Partido p = new Partido(
            idPartido,
            fecha,
            hora,
            idEquipoA,
            idEquipoB
    );

    boolean resultado = partidoDAO.insertarPartido(p);

    if (resultado) {
        JOptionPane.showMessageDialog(
                null,
                "Partido registrado correctamente."
        );

        limpiarRegistro();
        listarPartidos();

    } else {
        JOptionPane.showMessageDialog(
                null,
                "No se pudo registrar el partido. "
                + "Verifica que el código no esté repetido."
        );
    }
}
    private void listarPartidos() {
        DefaultTableModel modelo = vistaRegistro.getModeloTabla();
        modelo.setRowCount(0);

        ResultSet listado = partidoDAO.listarPartidos();

        try {
            while (listado.next()) {
                int idPartido = listado.getInt("idPartido");
                String fecha = listado.getString("fecha");
                String hora = listado.getString("hora");
                String equipoA = listado.getString("equipoA");
                String equipoB = listado.getString("equipoB");

                modelo.addRow(new Object[] { idPartido, fecha, hora, equipoA, equipoB });
            }
        } catch (SQLException ex) {
            Logger.getLogger(PartidoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  private void limpiarRegistro() {
    vistaRegistro.getTxtCodigo().setText("");
    vistaRegistro.getDateChooserFecha().setDate(null);
    vistaRegistro.getSpnHora().setValue(new Date());

    vistaRegistro.getCmbEquipoA().setSelectedIndex(0);
    vistaRegistro.getCmbEquipoB().setSelectedIndex(0);
}

    // ==================== MODIFICAR PARTIDO ====================

    private void buscarPartidoModificar() {
        String codigoTexto = vistaModificar.getTxtCodigoBuscar().getText().trim();

        if (!ValidacionPronostico.validarNumero(codigoTexto)) {
            JOptionPane.showMessageDialog(null, "Ingresa un código de partido válido (solo números).");
            return;
        }

        int idPartido = Integer.parseInt(codigoTexto);
        ResultSet listado = partidoDAO.buscarPartido(idPartido);

        try {
            if (listado.next()) {
                vistaModificar.getTxtCodigo().setText(String.valueOf(listado.getInt("idPartido")));
                vistaModificar.getTxtFecha().setText(listado.getString("fecha"));
                vistaModificar.getTxtHora().setText(listado.getString("hora"));

                vistaModificar.getCmbEquipoA().removeAllItems();
                vistaModificar.getCmbEquipoA().addItem(listado.getString("equipoA"));

                vistaModificar.getCmbEquipoB().removeAllItems();
                vistaModificar.getCmbEquipoB().addItem(listado.getString("equipoB"));

                boolean tienePronosticos = pronosticoDAO.existePronosticoParaPartido(idPartido);

                vistaModificar.getCmbNuevoEquipoA().setEnabled(!tienePronosticos);
                vistaModificar.getCmbNuevoEquipoB().setEnabled(!tienePronosticos);
                vistaModificar.getBtnGuardarCambios().setEnabled(!tienePronosticos);

                if (tienePronosticos) {
                    JOptionPane.showMessageDialog(null,
                            "No se pueden modificar los equipos: ya existen pronósticos registrados para este partido.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No existe ningún partido con ese código.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PartidoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void guardarCambiosModificar() {
        String codigoTexto = vistaModificar.getTxtCodigo().getText().trim();

        if (codigoTexto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Primero busca un partido válido.");
            return;
        }

        int idPartido = Integer.parseInt(codigoTexto);

        String nuevoEquipoA = (String) vistaModificar.getCmbNuevoEquipoA().getSelectedItem();
        String nuevoEquipoB = (String) vistaModificar.getCmbNuevoEquipoB().getSelectedItem();

        if (nuevoEquipoA == null || nuevoEquipoB == null || nuevoEquipoA.equals(nuevoEquipoB)) {
            JOptionPane.showMessageDialog(null, "Selecciona dos equipos diferentes.");
            return;
        }

        int idEquipoA = equipoDAO.buscarEquipo(nuevoEquipoA);
        int idEquipoB = equipoDAO.buscarEquipo(nuevoEquipoB);

        boolean resultado = partidoDAO.actualizarEquipos(idPartido, idEquipoA, idEquipoB);

        if (resultado) {
            JOptionPane.showMessageDialog(null, "Equipos actualizados correctamente.");
            listarPartidos();
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo actualizar el partido.");
        }
    }

    // ==================== REGISTRO DE RESULTADOS ====================

    private void buscarPartidoResultado() {
        String codigoTexto = vistaResultados.getTxtCodigoBuscar().getText().trim();

        if (!ValidacionPronostico.validarNumero(codigoTexto)) {
            JOptionPane.showMessageDialog(null, "Ingresa un código de partido válido (solo números).");
            return;
        }

        int idPartido = Integer.parseInt(codigoTexto);
        ResultSet listado = partidoDAO.buscarPartido(idPartido);

        try {
            if (listado.next()) {
                vistaResultados.getTxtCodigo().setText(String.valueOf(listado.getInt("idPartido")));
                vistaResultados.getTxtFecha().setText(listado.getString("fecha"));
                vistaResultados.getTxtHora().setText(listado.getString("hora"));
                vistaResultados.getTxtEquipoA().setText(listado.getString("equipoA"));
                vistaResultados.getTxtEquipoB().setText(listado.getString("equipoB"));
            } else {
                JOptionPane.showMessageDialog(null, "No existe ningún partido con ese código.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(PartidoControlador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void guardarResultado() {
        String codigoTexto = vistaResultados.getTxtCodigo().getText().trim();

        if (codigoTexto.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Primero busca un partido válido.");
            return;
        }

        int idPartido = Integer.parseInt(codigoTexto);
        int golesA = (int) vistaResultados.getSpnGolesA().getValue();
        int golesB = (int) vistaResultados.getSpnGolesB().getValue();

        boolean resultado = partidoDAO.registrarResultado(idPartido, golesA, golesB);

        if (resultado) {
            JOptionPane.showMessageDialog(null, "Resultado guardado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No se pudo guardar el resultado.");
        }
    }
}