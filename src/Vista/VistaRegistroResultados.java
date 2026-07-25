package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.LineBorder;

public class VistaRegistroResultados extends JPanel {

    //==================== COLORES ====================

    private final Color AZUL_FONDO = new Color(6, 26, 64);
    private final Color AZUL_PANEL = new Color(15, 45, 90);
    private final Color DORADO = new Color(247, 191, 66);
    private final Color VERDE = new Color(0, 168, 107);
    private final Color GRIS = new Color(70, 70, 70);

    //==================== PANELES ====================

    private JPanel panelBusqueda;
    private JPanel panelInfo;
    private JPanel panelResultado;
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
    private JLabel lblResultadoPartido;
    private JLabel lblGolesA;
    private JLabel lblGolesB;

    //==================== CAMPOS ====================

    private JTextField txtCodigoBuscar;
    private JTextField txtCodigo;
    private JTextField txtFecha;
    private JTextField txtHora;
    private JTextField txtEquipoA;
    private JTextField txtEquipoB;

    //==================== SPINNERS (goles) ====================

    private JSpinner spnGolesA;
    private JSpinner spnGolesB;

    //==================== BOTONES ====================

    private JButton btnBuscar;
    private JButton btnCancelar;
    private JButton btnGuardarResultado;

    public VistaRegistroResultados() {
        setLayout(null);
        setPreferredSize(new java.awt.Dimension(1100, 750));
        setBackground(AZUL_FONDO);
        iniciarComponentes();
    }

    private void iniciarComponentes() {

        //==================== TITULO ====================

        lblTitulo = new JLabel("📋 REGISTRO DE RESULTADOS");
        lblTitulo.setBounds(300, 20, 500, 40);
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
        txtCodigo.setEditable(false);
        panelInfo.add(txtCodigo);

        lblFecha = new JLabel("Fecha:");
        lblFecha.setBounds(30, 55, 150, 25);
        lblFecha.setForeground(Color.WHITE);
        lblFecha.setFont(new Font("Arial", Font.BOLD, 16));
        panelInfo.add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(220, 53, 300, 30);
        txtFecha.setFont(new Font("Arial", Font.PLAIN, 15));
        txtFecha.setEditable(false);
        panelInfo.add(txtFecha);

        lblHora = new JLabel("Hora:");
        lblHora.setBounds(30, 90, 150, 25);
        lblHora.setForeground(Color.WHITE);
        lblHora.setFont(new Font("Arial", Font.BOLD, 16));
        panelInfo.add(lblHora);

        txtHora = new JTextField();
        txtHora.setBounds(220, 88, 300, 30);
        txtHora.setFont(new Font("Arial", Font.PLAIN, 15));
        txtHora.setEditable(false);
        panelInfo.add(txtHora);

        lblEquipoA = new JLabel("Equipo A:");
        lblEquipoA.setBounds(30, 125, 150, 25);
        lblEquipoA.setForeground(Color.WHITE);
        lblEquipoA.setFont(new Font("Arial", Font.BOLD, 16));
        panelInfo.add(lblEquipoA);

        txtEquipoA = new JTextField();
        txtEquipoA.setBounds(220, 123, 300, 30);
        txtEquipoA.setFont(new Font("Arial", Font.PLAIN, 15));
        txtEquipoA.setEditable(false);
        panelInfo.add(txtEquipoA);

        lblEquipoB = new JLabel("Equipo B:");
        lblEquipoB.setBounds(30, 160, 150, 25);
        lblEquipoB.setForeground(Color.WHITE);
        lblEquipoB.setFont(new Font("Arial", Font.BOLD, 16));
        panelInfo.add(lblEquipoB);

        txtEquipoB = new JTextField();
        txtEquipoB.setBounds(220, 158, 300, 30);
        txtEquipoB.setFont(new Font("Arial", Font.PLAIN, 15));
        txtEquipoB.setEditable(false);
        panelInfo.add(txtEquipoB);

        //==================== SECCION: RESULTADO DEL PARTIDO ====================

        lblResultadoPartido = new JLabel("RESULTADO DEL PARTIDO");
        lblResultadoPartido.setBounds(40, 480, 400, 30);
        lblResultadoPartido.setForeground(DORADO);
        lblResultadoPartido.setFont(new Font("Arial", Font.BOLD, 20));
        add(lblResultadoPartido);

        panelResultado = new JPanel();
        panelResultado.setLayout(null);
        panelResultado.setBounds(40, 515, 1000, 150);
        panelResultado.setBackground(AZUL_PANEL);
        panelResultado.setBorder(new LineBorder(Color.WHITE, 2));
        add(panelResultado);

        lblGolesA = new JLabel("Goles Equipo A", javax.swing.SwingConstants.CENTER);
        lblGolesA.setBounds(150, 20, 200, 25);
        lblGolesA.setForeground(Color.WHITE);
        lblGolesA.setFont(new Font("Arial", Font.BOLD, 18));
        panelResultado.add(lblGolesA);

        spnGolesA = new JSpinner(new SpinnerNumberModel(0, 0, 99, 1));
        spnGolesA.setBounds(200, 60, 100, 40);
        spnGolesA.setFont(new Font("Arial", Font.BOLD, 20));
        panelResultado.add(spnGolesA);

        lblGolesB = new JLabel("Goles Equipo B", javax.swing.SwingConstants.CENTER);
        lblGolesB.setBounds(650, 20, 200, 25);
        lblGolesB.setForeground(Color.WHITE);
        lblGolesB.setFont(new Font("Arial", Font.BOLD, 18));
        panelResultado.add(lblGolesB);

        spnGolesB = new JSpinner(new SpinnerNumberModel(0, 0, 99, 1));
        spnGolesB.setBounds(700, 60, 100, 40);
        spnGolesB.setFont(new Font("Arial", Font.BOLD, 20));
        panelResultado.add(spnGolesB);

        //==================== BOTONES ====================

        panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(1, 2, 15, 0));
        panelBotones.setOpaque(false);
        panelBotones.setBounds(770, 685, 270, 40);
        add(panelBotones);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(GRIS);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 15));
        panelBotones.add(btnCancelar);

        btnGuardarResultado = new JButton("Guardar resultado");
        btnGuardarResultado.setBackground(VERDE);
        btnGuardarResultado.setForeground(Color.WHITE);
        btnGuardarResultado.setFont(new Font("Arial", Font.BOLD, 15));
        panelBotones.add(btnGuardarResultado);
    }

    //==================== GETTERS ====================

    public JTextField getTxtCodigoBuscar() { return txtCodigoBuscar; }
    public JButton getBtnBuscar() { return btnBuscar; }

    public JTextField getTxtCodigo() { return txtCodigo; }
    public JTextField getTxtFecha() { return txtFecha; }
    public JTextField getTxtHora() { return txtHora; }
    public JTextField getTxtEquipoA() { return txtEquipoA; }
    public JTextField getTxtEquipoB() { return txtEquipoB; }

    public JSpinner getSpnGolesA() { return spnGolesA; }
    public JSpinner getSpnGolesB() { return spnGolesB; }

    public JButton getBtnCancelar() { return btnCancelar; }
    public JButton getBtnGuardarResultado() { return btnGuardarResultado; }
}
