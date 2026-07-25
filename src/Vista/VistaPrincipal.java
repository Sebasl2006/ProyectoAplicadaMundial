package Vista;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class VistaPrincipal extends JFrame {

    private JPanel panelMenu;
    private JPanel panelImagen;

    private JLabel lblTitulo;
    private JLabel lblImagen;

    private JComboBox<String> cmbPartidos;
    private JComboBox<String> cmbReportes;

    // ============ Contenedor con CardLayout ============
    private JPanel panelContenedor;
    private CardLayout cardLayout;

    private VistaRegistroPartido vistaRegistro;
    private VistaModificarPartido vistaModificar;
    private VistaRegistroResultados vistaResultados;

    public VistaPrincipal() {

        setTitle("Sistema de Pronósticos Mundial 2026");
        setSize(1050, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        Color fondo = new Color(6, 26, 64);
        Color panel = new Color(12, 43, 92);
        Color dorado = new Color(247, 191, 66);
        Color rojo = new Color(194, 24, 91);

        getContentPane().setBackground(fondo);
        setLayout(null);

        //================== TITULO ==================
        lblTitulo = new JLabel("🏆 SISTEMA DE PRONÓSTICOS - MUNDIAL 2026");
        lblTitulo.setBounds(180, 20, 700, 40);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(dorado);
        add(lblTitulo);

        //================ CONTENEDOR PRINCIPAL (CardLayout) ======================
        cardLayout = new CardLayout();
        panelContenedor = new JPanel(cardLayout);
        panelContenedor.setBounds(0, 90, 1050, 550);
        add(panelContenedor);

        // ---- Tarjeta HOME (menu + imagen) ----
        JPanel panelHome = new JPanel();
        panelHome.setLayout(null);
        panelHome.setBackground(fondo);

        panelMenu = new JPanel();
        panelMenu.setLayout(null);
        panelMenu.setBounds(25, 0, 260, 500);
        panelMenu.setBackground(panel);
        panelMenu.setBorder(new LineBorder(Color.WHITE, 2));
        panelHome.add(panelMenu);

        JLabel lblMenu = new JLabel("MENÚ");
        lblMenu.setBounds(90, 20, 100, 30);
        lblMenu.setForeground(Color.WHITE);
        lblMenu.setFont(new Font("Arial", Font.BOLD, 22));
        panelMenu.add(lblMenu);

        //================== PARTIDOS =================
        JLabel lblPartidos = new JLabel("⚽ PARTIDOS");
        lblPartidos.setBounds(25, 80, 200, 25);
        lblPartidos.setForeground(Color.WHITE);
        lblPartidos.setFont(new Font("Arial", Font.BOLD, 18));
        panelMenu.add(lblPartidos);

        cmbPartidos = new JComboBox<>();
        cmbPartidos.addItem("Seleccione una opción");
        cmbPartidos.addItem("Registro");
        cmbPartidos.addItem("Modificar");
        cmbPartidos.addItem("Resultados");
        cmbPartidos.setBounds(20, 115, 210, 35);
        cmbPartidos.setBackground(rojo);
        cmbPartidos.setForeground(Color.BLACK);
        cmbPartidos.setFont(new Font("Arial", Font.BOLD, 15));
        panelMenu.add(cmbPartidos);

        cmbPartidos.addActionListener(e -> {
            String opcion = (String) cmbPartidos.getSelectedItem();

            if (opcion.equals("Registro")) {
                cardLayout.show(panelContenedor, "REGISTRO");
            } else if (opcion.equals("Modificar")) {
                cardLayout.show(panelContenedor, "MODIFICAR");
            } else if (opcion.equals("Resultados")) {
                cardLayout.show(panelContenedor, "RESULTADOS");
            }
        });

        //================== REPORTES =================
        JLabel lblReportes = new JLabel("📊 REPORTES");
        lblReportes.setBounds(25, 220, 200, 25);
        lblReportes.setForeground(Color.WHITE);
        lblReportes.setFont(new Font("Arial", Font.BOLD, 18));
        panelMenu.add(lblReportes);

        cmbReportes = new JComboBox<>();
        cmbReportes.addItem("Seleccione una opción");
        cmbReportes.addItem("Pronósticos por Usuario");
        cmbReportes.addItem("Ranking de Aciertos");
        cmbReportes.setBounds(20, 255, 210, 35);
        cmbReportes.setBackground(rojo);
        cmbReportes.setForeground(Color.BLACK);
        cmbReportes.setFont(new Font("Arial", Font.BOLD, 15));
        panelMenu.add(cmbReportes);

        //================= PANEL IMAGEN =================
        panelImagen = new JPanel();
        panelImagen.setLayout(new BorderLayout());
        panelImagen.setBounds(320, 0, 690, 500);
        panelImagen.setBackground(Color.WHITE);
        panelImagen.setBorder(new LineBorder(dorado, 4));
        panelHome.add(panelImagen);

        ImageIcon icono = new ImageIcon("resources/imagenes/fifa.png");
        lblImagen = new JLabel(icono);
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setFont(new Font("Arial", Font.BOLD, 24));
        lblImagen.setForeground(panel);
        panelImagen.add(lblImagen, BorderLayout.CENTER);

        // ---- Tarjeta REGISTRO ----
        vistaRegistro = new VistaRegistroPartido();
        vistaRegistro.getBtnVolver().addActionListener(e ->
            cardLayout.show(panelContenedor, "HOME")
        );

        // ---- Tarjeta MODIFICAR ----
        vistaModificar = new VistaModificarPartido();
        vistaModificar.getBtnCancelar().addActionListener(e ->
            cardLayout.show(panelContenedor, "HOME")
        );
        vistaModificar.getBtnVolver().addActionListener(e ->     
         cardLayout.show(panelContenedor, "HOME")
        );  

        JScrollPane scrollModificar = new JScrollPane(vistaModificar);
        scrollModificar.setBorder(null);
        scrollModificar.getVerticalScrollBar().setUnitIncrement(16);
        scrollModificar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollModificar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // ---- Tarjeta RESULTADOS ----
        vistaResultados = new VistaRegistroResultados();
        vistaResultados.getBtnCancelar().addActionListener(e ->
            cardLayout.show(panelContenedor, "HOME")
        );

        JScrollPane scrollResultados = new JScrollPane(vistaResultados);
        scrollResultados.setBorder(null);
        scrollResultados.getVerticalScrollBar().setUnitIncrement(16);
        scrollResultados.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollResultados.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Agregamos TODAS las tarjetas al contenedor
        panelContenedor.add(panelHome, "HOME");
        panelContenedor.add(vistaRegistro, "REGISTRO");
        panelContenedor.add(scrollModificar, "MODIFICAR");     // <-- esto era lo que faltaba
        panelContenedor.add(scrollResultados, "RESULTADOS");

        // La tarjeta que se ve al iniciar
        cardLayout.show(panelContenedor, "HOME");
    }

    //================ GETTERS ===================
    public JComboBox<String> getCmbPartidos() { return cmbPartidos; }
    public JComboBox<String> getCmbReportes() { return cmbReportes; }
    public VistaRegistroPartido getVistaRegistro() { return vistaRegistro; }
    public VistaModificarPartido getVistaModificar() { return vistaModificar; }
    public VistaRegistroResultados getVistaResultados() { return vistaResultados; }
}