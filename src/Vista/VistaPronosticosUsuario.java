package Vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class VistaPronosticosUsuario extends JPanel {

    private JTextField txtDesde;
    private JTextField txtHasta;
    private JButton btnFiltrar;
    private JButton btnExportar;
    private JButton btnActualizar;
    private JButton btnVolver;

    private PanelGrafico panelGrafico;

    public VistaPronosticosUsuario() {

        setLayout(null);
        setBackground(new Color(18, 18, 18));
        setPreferredSize(new Dimension(850, 650));

        crearTitulo();
        crearFiltroFechas();
        crearBotonesLaterales();
        crearGrafico();
    }

    private void crearTitulo() {

        JLabel lblTitulo = new JLabel("Cantidad de pronósticos registrados");
        lblTitulo.setBounds(20, 15, 500, 35);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 17));
        add(lblTitulo);
    }

    private void crearFiltroFechas() {

        JLabel lblRango = new JLabel("RANGO DE FECHAS");
        lblRango.setBounds(20, 60, 300, 25);
        lblRango.setForeground(Color.WHITE);
        lblRango.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblRango);

        JPanel panelFiltro = new JPanel();
        panelFiltro.setLayout(null);
        panelFiltro.setBounds(20, 90, 630, 55);
        panelFiltro.setBackground(new Color(25, 25, 25));
        panelFiltro.setBorder(new LineBorder(Color.LIGHT_GRAY));
        add(panelFiltro);

        JLabel lblDesde = new JLabel("Desde:");
        lblDesde.setBounds(15, 17, 60, 25);
        lblDesde.setForeground(Color.WHITE);
        lblDesde.setFont(new Font("Arial", Font.PLAIN, 13));
        panelFiltro.add(lblDesde);

        txtDesde = new JTextField();
        txtDesde.setBounds(80, 15, 130, 28);
        panelFiltro.add(txtDesde);

        JLabel lblHasta = new JLabel("Hasta:");
        lblHasta.setBounds(230, 17, 60, 25);
        lblHasta.setForeground(Color.WHITE);
        lblHasta.setFont(new Font("Arial", Font.PLAIN, 13));
        panelFiltro.add(lblHasta);

        txtHasta = new JTextField();
        txtHasta.setBounds(290, 15, 130, 28);
        panelFiltro.add(txtHasta);

        btnFiltrar = crearBoton("Filtrar");
        btnFiltrar.setBounds(460, 12, 150, 32);
        panelFiltro.add(btnFiltrar);
    }

    private void crearBotonesLaterales() {

        btnExportar = crearBoton("⇩  Exportar");
        btnExportar.setBounds(680, 60, 125, 42);
        btnExportar.setBackground(new Color(0, 168, 107)); 
        add(btnExportar);

        btnActualizar = crearBoton("Actualizar");
        btnActualizar.setBounds(680, 110, 125, 42);
        add(btnActualizar);

        btnVolver = crearBoton("← Volver");
        btnVolver.setBounds(25, 590, 150, 35);
        add(btnVolver);
    }

    private JButton crearBoton(String texto) {

        JButton boton = new JButton(texto);
        boton.setBackground(new Color(24, 24, 24));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Arial", Font.PLAIN, 13));
        boton.setBorder(new LineBorder(Color.LIGHT_GRAY));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }

    private void crearGrafico() {

        JLabel lblSubtitulo = new JLabel("PRONOSTICOS POR USUARIO");
        lblSubtitulo.setBounds(25, 165, 300, 30);
        lblSubtitulo.setForeground(Color.WHITE);
        lblSubtitulo.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblSubtitulo);

        panelGrafico = new PanelGrafico();
        panelGrafico.setBounds(25, 205, 780, 355);
        panelGrafico.setBorder(new LineBorder(Color.LIGHT_GRAY));
        add(panelGrafico);
    }

    // ==================== GETTERS ====================

    public JTextField getTxtDesde() {
        return txtDesde;
    }

    public JTextField getTxtHasta() {
        return txtHasta;
    }

    public JButton getBtnFiltrar() {
        return btnFiltrar;
    }

    public JButton getBtnExportar() {
        return btnExportar;
    }

    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    public JButton getBtnVolver() { 
        return btnVolver; }

    // Se llama desde el controlador para pintar el gráfico con datos reales
    public void setDatos(List<String> nombres, List<Integer> cantidades) {
        panelGrafico.setDatos(nombres, cantidades);
    }

    // ==================== PANEL INTERNO: DIBUJA EL GRAFICO DE BARRAS
    // ====================

    private static class PanelGrafico extends JPanel {

        private List<String> nombres = new ArrayList<>();
        private List<Integer> cantidades = new ArrayList<>();

        public PanelGrafico() {
            setBackground(new Color(25, 25, 25));
        }

        public void setDatos(List<String> nombres, List<Integer> cantidades) {
            this.nombres = nombres;
            this.cantidades = cantidades;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (nombres.isEmpty()) {
                return;
            }

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int margenIzq = 50;
            int margenInf = 50;
            int margenSup = 20;
            int ancho = getWidth() - margenIzq - 20;
            int alto = getHeight() - margenInf - margenSup;

            int max = 1;
            for (int c : cantidades) {
                if (c > max)
                    max = c;
            }

            // Eje Y y líneas guía
            g2.setColor(Color.LIGHT_GRAY);
            g2.drawLine(margenIzq, margenSup, margenIzq, margenSup + alto);
            g2.drawLine(margenIzq, margenSup + alto, margenIzq + ancho, margenSup + alto);

            int pasos = 5;
            for (int i = 0; i <= pasos; i++) {
                int valor = (max * i) / pasos;
                int y = margenSup + alto - (alto * i / pasos);
                g2.setColor(new Color(60, 60, 60));
                g2.drawLine(margenIzq, y, margenIzq + ancho, y);
                g2.setColor(Color.WHITE);
                g2.drawString(String.valueOf(valor), 15, y + 5);
            }

            // Barras
            int n = nombres.size();
            int espacioPorBarra = ancho / n;
            int anchoBarra = Math.max(20, espacioPorBarra - 20);

            for (int i = 0; i < n; i++) {
                int valor = cantidades.get(i);
                int alturaBarra = (int) ((valor / (double) max) * alto);

                int x = margenIzq + i * espacioPorBarra + (espacioPorBarra - anchoBarra) / 2;
                int y = margenSup + alto - alturaBarra;

                g2.setColor(new Color(76, 175, 80));
                g2.fillRect(x, y, anchoBarra, alturaBarra);

                g2.setColor(Color.WHITE);
                g2.drawString(String.valueOf(valor), x + anchoBarra / 2 - 5, y - 5);

                String nombre = nombres.get(i);
                g2.drawString(nombre, x - 5, margenSup + alto + 20);
            }
        }
    }
}