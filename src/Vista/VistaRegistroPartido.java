package Vista;

import com.toedter.calendar.JDateChooser;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class VistaRegistroPartido extends JPanel {

    private final Color AZUL_FONDO = new Color(6, 26, 64);
    private final Color AZUL_PANEL = new Color(15, 45, 90);
    private final Color DORADO = new Color(247, 191, 66);
    private final Color VERDE = new Color(0, 168, 107);
    private final Color ROJO = new Color(214, 48, 49);

    private JPanel panelFormulario;
    private JPanel panelBotones;

    private JLabel lblTitulo;
    private JLabel lblCodigo;
    private JLabel lblFecha;
    private JLabel lblHora;
    private JLabel lblEquipoA;
    private JLabel lblEquipoB;
    private JLabel lblLista;

    private JTextField txtCodigo;
    private JDateChooser dateChooserFecha;
    private JSpinner spnHora;

    private JComboBox<String> cmbEquipoA;
    private JComboBox<String> cmbEquipoB;

    private JButton btnGuardar;
    private JButton btnLimpiar;
    private JButton btnVolver;

    private JTable tablaPartidos;
    private JScrollPane scrollTabla;
    private DefaultTableModel modeloTabla;

    public VistaRegistroPartido() {
        setLayout(null);
        setPreferredSize(new java.awt.Dimension(1100, 760));
        setBackground(AZUL_FONDO);
        iniciarComponentes();
    }

    private void iniciarComponentes() {

        lblLista = new JLabel("PARTIDOS REGISTRADOS");
        lblLista.setBounds(40, 340, 350, 30);
        lblLista.setForeground(DORADO);
        lblLista.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblLista);

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Hora");
        modeloTabla.addColumn("Equipo A");
        modeloTabla.addColumn("Equipo B");

        tablaPartidos = new JTable(modeloTabla);
        tablaPartidos.setRowHeight(28);
        tablaPartidos.setFont(new Font("Arial", Font.PLAIN, 15));
        tablaPartidos.getTableHeader()
                .setFont(new Font("Arial", Font.BOLD, 15));
        tablaPartidos.getTableHeader().setBackground(AZUL_PANEL);
        tablaPartidos.getTableHeader().setForeground(Color.WHITE);

        scrollTabla = new JScrollPane(tablaPartidos);
        scrollTabla.setBounds(40, 380, 1000, 250);
        add(scrollTabla);

        lblTitulo = new JLabel("⚽ REGISTRO DE PARTIDOS");
        lblTitulo.setBounds(320, 20, 500, 40);
        lblTitulo.setForeground(DORADO);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        add(lblTitulo);

        panelFormulario = new JPanel();
        panelFormulario.setLayout(null);
        panelFormulario.setBounds(40, 90, 1000, 240);
        panelFormulario.setBackground(AZUL_PANEL);
        panelFormulario.setBorder(new LineBorder(Color.WHITE, 2));
        add(panelFormulario);

        lblCodigo = new JLabel("Código del partido:");
        lblCodigo.setBounds(30, 20, 180, 25);
        lblCodigo.setForeground(Color.WHITE);
        lblCodigo.setFont(new Font("Arial", Font.BOLD, 16));
        panelFormulario.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(220, 18, 200, 30);
        txtCodigo.setFont(new Font("Arial", Font.PLAIN, 15));
        panelFormulario.add(txtCodigo);

        lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(30, 55, 180, 25);
        lblFecha.setForeground(Color.WHITE);
        lblFecha.setFont(new Font("Arial", Font.BOLD, 16));
        panelFormulario.add(lblFecha);

        dateChooserFecha = new JDateChooser();
        dateChooserFecha.setBounds(220, 53, 200, 30);
        dateChooserFecha.setDateFormatString("yyyy-MM-dd");
        dateChooserFecha.setFont(new Font("Arial", Font.PLAIN, 15));
        panelFormulario.add(dateChooserFecha);

        lblHora = new JLabel("Hora:");
        lblHora.setBounds(30, 90, 180, 25);
        lblHora.setForeground(Color.WHITE);
        lblHora.setFont(new Font("Arial", Font.BOLD, 16));
        panelFormulario.add(lblHora);

        SpinnerDateModel modeloHora = new SpinnerDateModel(
                new Date(),
                null,
                null,
                Calendar.MINUTE
        );

        spnHora = new JSpinner(modeloHora);
        spnHora.setBounds(220, 88, 200, 30);
        spnHora.setFont(new Font("Arial", Font.PLAIN, 15));

        JSpinner.DateEditor editorHora =
                new JSpinner.DateEditor(spnHora, "HH:mm:ss");

        spnHora.setEditor(editorHora);
        panelFormulario.add(spnHora);

        lblEquipoA = new JLabel("Equipo A:");
        lblEquipoA.setBounds(30, 130, 180, 25);
        lblEquipoA.setForeground(Color.WHITE);
        lblEquipoA.setFont(new Font("Arial", Font.BOLD, 16));
        panelFormulario.add(lblEquipoA);

        cmbEquipoA = new JComboBox<>();
        cmbEquipoA.setBounds(220, 128, 400, 30);
        panelFormulario.add(cmbEquipoA);

        lblEquipoB = new JLabel("Equipo B:");
        lblEquipoB.setBounds(30, 165, 180, 25);
        lblEquipoB.setForeground(Color.WHITE);
        lblEquipoB.setFont(new Font("Arial", Font.BOLD, 16));
        panelFormulario.add(lblEquipoB);

        cmbEquipoB = new JComboBox<>();
        cmbEquipoB.setBounds(220, 163, 400, 30);
        panelFormulario.add(cmbEquipoB);

        panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 3, 15, 0));
        panelBotones.setOpaque(false);
        panelBotones.setBounds(280, 200, 440, 35);
        panelFormulario.add(panelBotones);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBackground(ROJO);
        btnLimpiar.setForeground(Color.WHITE);
        btnLimpiar.setFont(new Font("Arial", Font.BOLD, 14));
        panelBotones.add(btnLimpiar);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(VERDE);
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Arial", Font.BOLD, 14));
        panelBotones.add(btnGuardar);

        btnVolver = new JButton("Volver");
        btnVolver.setBackground(new Color(70, 70, 70));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 14));
        panelBotones.add(btnVolver);
    }

    public JTextField getTxtCodigo() {
        return txtCodigo;
    }

    public JDateChooser getDateChooserFecha() {
        return dateChooserFecha;
    }

    public JSpinner getSpnHora() {
        return spnHora;
    }

    public JComboBox<String> getCmbEquipoA() {
        return cmbEquipoA;
    }

    public JComboBox<String> getCmbEquipoB() {
        return cmbEquipoB;
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public JButton getBtnVolver() {
        return btnVolver;
    }

    public JTable getTablaPartidos() {
        return tablaPartidos;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }
}