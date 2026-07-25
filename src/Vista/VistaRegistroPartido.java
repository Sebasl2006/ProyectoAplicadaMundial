package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class VistaRegistroPartido extends JPanel {   // <-- ya no extiende JFrame

    private final Color AZUL_FONDO = new Color(6, 26, 64);
    private final Color AZUL_PANEL = new Color(15, 45, 90);
    private final Color DORADO = new Color(247, 191, 66);
    private final Color VERDE = new Color(0, 168, 107);
    private final Color ROJO = new Color(214, 48, 49);

    private JPanel panelFormulario;
    private JPanel panelBotones;

    private JLabel lblTitulo, lblCodigo, lblFecha, lblHora, lblEquipoA, lblEquipoB, lblLista;

    private JTextField txtCodigo, txtFecha, txtHora;
    private JComboBox<String> cmbEquipoA, cmbEquipoB;
    private JButton btnGuardar, btnLimpiar, btnVolver;

    private JTable tablaPartidos;
    private JScrollPane scrollTabla;
    private javax.swing.table.DefaultTableModel modeloTabla;

    public VistaRegistroPartido() {
        setLayout(null);
        setPreferredSize(new java.awt.Dimension(1100, 700)); // mismo tamaño que tenía el JFrame
        setBackground(AZUL_FONDO);
        iniciarComponentes();
    }

    private void iniciarComponentes() {

        //==================== TITULO TABLA ====================
        lblLista = new JLabel("PARTIDOS REGISTRADOS");
        lblLista.setBounds(40, 340, 350, 30);
        lblLista.setForeground(DORADO);
        lblLista.setFont(new Font("Arial", Font.BOLD, 22));
        add(lblLista);

        //==================== TABLA ====================
        modeloTabla = new javax.swing.table.DefaultTableModel();
        modeloTabla.addColumn("Código");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Hora");
        modeloTabla.addColumn("Equipo A");
        modeloTabla.addColumn("Equipo B");

        tablaPartidos = new JTable(modeloTabla);
        tablaPartidos.setRowHeight(28);
        tablaPartidos.setFont(new Font("Arial", Font.PLAIN, 15));
        tablaPartidos.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
        tablaPartidos.getTableHeader().setBackground(AZUL_PANEL);
        tablaPartidos.getTableHeader().setForeground(Color.WHITE);

        scrollTabla = new JScrollPane(tablaPartidos);
        scrollTabla.setBounds(40, 380, 1000, 250);
        add(scrollTabla);

        //==================== TITULO ====================
        lblTitulo = new JLabel("⚽ REGISTRO DE PARTIDOS");
        lblTitulo.setBounds(320, 20, 500, 40);
        lblTitulo.setForeground(DORADO);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        add(lblTitulo);

        //==================== FORMULARIO ====================
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

        txtFecha = new JTextField();
        txtFecha.setBounds(220, 53, 200, 30);
        txtFecha.setFont(new Font("Arial", Font.PLAIN, 15));
        panelFormulario.add(txtFecha);

        lblHora = new JLabel("Hora:");
        lblHora.setBounds(30, 90, 180, 25);
        lblHora.setForeground(Color.WHITE);
        lblHora.setFont(new Font("Arial", Font.BOLD, 16));
        panelFormulario.add(lblHora);

        txtHora = new JTextField();
        txtHora.setBounds(220, 88, 200, 30);
        txtHora.setFont(new Font("Arial", Font.PLAIN, 15));
        panelFormulario.add(txtHora);

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

        //==================== BOTONES ====================
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

    //==================== GETTERS ====================
    public JTextField getTxtCodigo() { return txtCodigo; }
    public JTextField getTxtFecha() { return txtFecha; }
    public JTextField getTxtHora() { return txtHora; }
    public JComboBox<String> getCmbEquipoA() { return cmbEquipoA; }
    public JComboBox<String> getCmbEquipoB() { return cmbEquipoB; }
    public JButton getBtnGuardar() { return btnGuardar; }
    public JButton getBtnLimpiar() { return btnLimpiar; }
    public JButton getBtnVolver() { return btnVolver; }
    public JTable getTablaPartidos() { return tablaPartidos; }
    public javax.swing.table.DefaultTableModel getModeloTabla() { return modeloTabla; }
}