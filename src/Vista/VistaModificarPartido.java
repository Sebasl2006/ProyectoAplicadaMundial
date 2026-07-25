package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class VistaModificarPartido extends JPanel {

    //==================== COLORES ====================

    private final Color AZUL_FONDO = new Color(6, 26, 64);
    private final Color AZUL_PANEL = new Color(15, 45, 90);
    private final Color DORADO = new Color(247, 191, 66);
    private final Color VERDE = new Color(0, 168, 107);
    private final Color ROJO = new Color(214, 48, 49);
    private final Color GRIS = new Color(70, 70, 70);

    //==================== PANELES ====================

    private JPanel panelBusqueda;
    private JPanel panelInfo;
    private JPanel panelNuevosEquipos;
    private JPanel panelBotones;

    //==================== LABELS ====================

    private JLabel lblTitulo;
    private JLabel lblBuscarPartido;
    private JLabel lblCodigoBuscar;
    private JLabel lblInfoPartido;
    private JLabel lblCodigo;
    private JLabel lblFecha;
    private JLabel lblHora;
    private JLabel lblEquipoA;
    private JLabel lblEquipoB;
    private JLabel lblNuevosEquipos;
    private JLabel lblNuevoEquipoA;
    private JLabel lblNuevoEquipoB;
    private JLabel lblAdvertencia;

    //==================== CAMPOS ====================

    private JTextField txtCodigoBuscar;
    private JTextField txtCodigo;
    private JTextField txtFecha;
    private JTextField txtHora;

    //==================== COMBOS ====================

    private JComboBox<String> cmbEquipoA;      // info actual (deshabilitado)
    private JComboBox<String> cmbEquipoB;      // info actual (deshabilitado)
    private JComboBox<String> cmbNuevoEquipoA; // nuevos equipos
    private JComboBox<String> cmbNuevoEquipoB; // nuevos equipos

    //==================== BOTONES ====================

    private JButton btnBuscar;
    private JButton btnCancelar;
    private JButton btnGuardarCambios;
    private JButton btnVolver;

    public VistaModificarPartido() {
        setLayout(null);
        setPreferredSize(new java.awt.Dimension(1100, 760));
        setBackground(AZUL_FONDO);
        iniciarComponentes();
    }

    private void iniciarComponentes() {

        //==================== TITULO ====================

        lblTitulo = new JLabel("✏ MODIFICAR PARTIDOS");
        lblTitulo.setBounds(320, 20, 500, 40);
        lblTitulo.setForeground(DORADO);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        add(lblTitulo);

        //==================== SECCION: BUSCAR PARTIDO ====================

        lblBuscarPartido = new JLabel("BUSCAR PARTIDO");
        lblBuscarPartido.setBounds(40, 90, 350, 30);
        lblBuscarPartido.setForeground(DORADO);
        lblBuscarPartido.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblBuscarPartido);

        panelBusqueda = new JPanel();
        panelBusqueda.setLayout(null);
        panelBusqueda.setBounds(40, 125, 1000, 60);
        panelBusqueda.setBackground(AZUL_PANEL);
        panelBusqueda.setBorder(new LineBorder(Color.WHITE, 2));
        add(panelBusqueda);

        lblCodigoBuscar = new JLabel("Código del partido:");
        lblCodigoBuscar.setBounds(20, 15, 180, 30);
        lblCodigoBuscar.setForeground(Color.WHITE);
        lblCodigoBuscar.setFont(new Font("Arial", Font.BOLD, 16));
        panelBusqueda.add(lblCodigoBuscar);

        txtCodigoBuscar = new JTextField();
        txtCodigoBuscar.setBounds(210, 13, 220, 32);
        txtCodigoBuscar.setFont(new Font("Arial", Font.PLAIN, 15));
        panelBusqueda.add(txtCodigoBuscar);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(450, 13, 120, 32);
        btnBuscar.setBackground(DORADO);
        btnBuscar.setForeground(Color.BLACK);
        btnBuscar.setFont(new Font("Arial", Font.BOLD, 15));
        panelBusqueda.add(btnBuscar);

        //==================== SECCION: INFORMACION DEL PARTIDO ====================

        lblInfoPartido = new JLabel("INFORMACIÓN DEL PARTIDO");
        lblInfoPartido.setBounds(40, 200, 400, 30);
        lblInfoPartido.setForeground(DORADO);
        lblInfoPartido.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblInfoPartido);

        panelInfo = new JPanel();
        panelInfo.setLayout(null);
        panelInfo.setBounds(40, 235, 1000, 230);
        panelInfo.setBackground(AZUL_PANEL);
        panelInfo.setBorder(new LineBorder(Color.WHITE, 2));
        add(panelInfo);

        lblCodigo = new JLabel("Código:");
        lblCodigo.setBounds(30, 20, 150, 25);
        lblCodigo.setForeground(Color.WHITE);
        lblCodigo.setFont(new Font("Arial", Font.BOLD, 16));
        panelInfo.add(lblCodigo);

        txtCodigo = new JTextField();
        txtCodigo.setBounds(220, 18, 300, 30);
        txtCodigo.setFont(new Font("Arial", Font.PLAIN, 15));
        txtCodigo.setEditable(false); // viene de la búsqueda, no se edita directo
        panelInfo.add(txtCodigo);

        lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(30, 55, 150, 25);
        lblFecha.setForeground(Color.WHITE);
        lblFecha.setFont(new Font("Arial", Font.BOLD, 16));
        panelInfo.add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(220, 53, 300, 30);
        txtFecha.setFont(new Font("Arial", Font.PLAIN, 15));
        panelInfo.add(txtFecha);

        lblHora = new JLabel("Hora:");
        lblHora.setBounds(30, 90, 150, 25);
        lblHora.setForeground(Color.WHITE);
        lblHora.setFont(new Font("Arial", Font.BOLD, 16));
        panelInfo.add(lblHora);

        txtHora = new JTextField();
        txtHora.setBounds(220, 88, 300, 30);
        txtHora.setFont(new Font("Arial", Font.PLAIN, 15));
        panelInfo.add(txtHora);

        lblEquipoA = new JLabel("Equipo A:");
        lblEquipoA.setBounds(30, 125, 150, 25);
        lblEquipoA.setForeground(Color.WHITE);
        lblEquipoA.setFont(new Font("Arial", Font.BOLD, 16));
        panelInfo.add(lblEquipoA);

        cmbEquipoA = new JComboBox<>();
        cmbEquipoA.setBounds(220, 123, 300, 30);
        cmbEquipoA.setEnabled(false); // solo informativo
        panelInfo.add(cmbEquipoA);

        lblEquipoB = new JLabel("Equipo B:");
        lblEquipoB.setBounds(30, 160, 150, 25);
        lblEquipoB.setForeground(Color.WHITE);
        lblEquipoB.setFont(new Font("Arial", Font.BOLD, 16));
        panelInfo.add(lblEquipoB);

        cmbEquipoB = new JComboBox<>();
        cmbEquipoB.setBounds(220, 158, 300, 30);
        cmbEquipoB.setEnabled(false); // solo informativo
        panelInfo.add(cmbEquipoB);

        //==================== SECCION: NUEVOS EQUIPOS ====================

        lblNuevosEquipos = new JLabel("NUEVOS EQUIPOS");
        lblNuevosEquipos.setBounds(40, 480, 350, 30);
        lblNuevosEquipos.setForeground(DORADO);
        lblNuevosEquipos.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblNuevosEquipos);

        panelNuevosEquipos = new JPanel();
        panelNuevosEquipos.setLayout(null);
        panelNuevosEquipos.setBounds(40, 515, 1000, 150);
        panelNuevosEquipos.setBackground(AZUL_PANEL);
        panelNuevosEquipos.setBorder(new LineBorder(Color.WHITE, 2));
        add(panelNuevosEquipos);

        lblNuevoEquipoA = new JLabel("Equipo A");
        lblNuevoEquipoA.setBounds(30, 20, 150, 25);
        lblNuevoEquipoA.setForeground(Color.WHITE);
        lblNuevoEquipoA.setFont(new Font("Arial", Font.BOLD, 16));
        panelNuevosEquipos.add(lblNuevoEquipoA);

        cmbNuevoEquipoA = new JComboBox<>();
        cmbNuevoEquipoA.setBounds(220, 18, 300, 30);
        panelNuevosEquipos.add(cmbNuevoEquipoA);

        lblNuevoEquipoB = new JLabel("Equipo B");
        lblNuevoEquipoB.setBounds(30, 55, 150, 25);
        lblNuevoEquipoB.setForeground(Color.WHITE);
        lblNuevoEquipoB.setFont(new Font("Arial", Font.BOLD, 16));
        panelNuevosEquipos.add(lblNuevoEquipoB);

        cmbNuevoEquipoB = new JComboBox<>();
        cmbNuevoEquipoB.setBounds(220, 53, 300, 30);
        panelNuevosEquipos.add(cmbNuevoEquipoB);

        lblAdvertencia = new JLabel("<html>* No se pueden modificar los equipos si existen "
                + "pronósticos registrados para este partido.</html>");
        lblAdvertencia.setBounds(30, 95, 900, 45);
        lblAdvertencia.setForeground(ROJO);
        lblAdvertencia.setFont(new Font("Arial", Font.ITALIC, 14));
        panelNuevosEquipos.add(lblAdvertencia);

        //==================== BOTONES ====================

        panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 3, 15, 0));
        panelBotones.setOpaque(false);
        panelBotones.setBounds(500, 675, 540, 45);
        add(panelBotones);

        btnVolver = new JButton("Volver");
        btnVolver.setBackground(new Color(70, 70, 70));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 15));
        panelBotones.add(btnVolver);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(GRIS);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 15));
        panelBotones.add(btnCancelar);

        btnGuardarCambios = new JButton("Guardar cambios");
        btnGuardarCambios.setBackground(VERDE);
        btnGuardarCambios.setForeground(Color.WHITE);
        btnGuardarCambios.setFont(new Font("Arial", Font.BOLD, 15));
        panelBotones.add(btnGuardarCambios);
    }

    //==================== GETTERS ====================

    public JTextField getTxtCodigoBuscar() { return txtCodigoBuscar; }
    public JButton getBtnBuscar() { return btnBuscar; }

    public JTextField getTxtCodigo() { return txtCodigo; }
    public JTextField getTxtFecha() { return txtFecha; }
    public JTextField getTxtHora() { return txtHora; }
    public JComboBox<String> getCmbEquipoA() { return cmbEquipoA; }
    public JComboBox<String> getCmbEquipoB() { return cmbEquipoB; }

    public JComboBox<String> getCmbNuevoEquipoA() { return cmbNuevoEquipoA; }
    public JComboBox<String> getCmbNuevoEquipoB() { return cmbNuevoEquipoB; }

    public JButton getBtnCancelar() { return btnCancelar; }
    public JButton getBtnGuardarCambios() { return btnGuardarCambios; }
    public JButton getBtnVolver() { return btnVolver; }  
}