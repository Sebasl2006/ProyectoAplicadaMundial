package Vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class VistaRankingAciertos extends JPanel {

    // ==================== COLORES MUNDIALISTAS ====================

    private final Color AZUL_FONDO = new Color(6, 26, 64);
    private final Color AZUL_PANEL = new Color(15, 45, 90);
    private final Color DORADO = new Color(247, 191, 66);
    private final Color VERDE = new Color(0, 168, 107);
    private final Color ROJO = new Color(214, 48, 49);

    private JTable tblRanking;
    private DefaultTableModel modeloRanking;

    private JComboBox<String> cmbMostrar;
    private JButton btnActualizar;
    private JButton btnExportar;
    private JButton btnVolver;

    public VistaRankingAciertos() {

        setLayout(null);
        setBackground(AZUL_FONDO);
        setPreferredSize(new Dimension(1000, 700));

        crearTitulo();
        crearFiltroMostrar();
        crearBotones();
        crearTabla();
    }

    // ==================== TITULO ====================

    private void crearTitulo() {

        JLabel lblTitulo = new JLabel(" Ranking de usuarios por aciertos");
        lblTitulo.setBounds(30, 20, 550, 40);
        lblTitulo.setForeground(DORADO);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 26));
        add(lblTitulo);
    }

    // ==================== FILTRO "MOSTRAR" ====================

    private void crearFiltroMostrar() {

        JLabel lblMostrar = new JLabel("Mostrar:");
        lblMostrar.setBounds(600, 30, 80, 30);
        lblMostrar.setForeground(Color.WHITE);
        lblMostrar.setFont(new Font("Arial", Font.BOLD, 15));
        add(lblMostrar);

        cmbMostrar = new JComboBox<>();
        cmbMostrar.addItem("Top 5");
        cmbMostrar.addItem("Top 10");
        cmbMostrar.addItem("Top 20");
        cmbMostrar.addItem("Todos");
        cmbMostrar.setSelectedItem("Top 10");

        cmbMostrar.setBounds(680, 28, 130, 32);
        cmbMostrar.setBackground(Color.WHITE);
        cmbMostrar.setFont(new Font("Arial", Font.BOLD, 14));
        add(cmbMostrar);
    }

    // ==================== BOTONES ====================

    private void crearBotones() {

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setBounds(830, 20, 140, 32);
        btnActualizar.setBackground(DORADO);
        btnActualizar.setForeground(Color.BLACK);
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 13));
        btnActualizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(btnActualizar);

        btnExportar = new JButton("⇩ Exportar");
        btnExportar.setBounds(830, 58, 140, 32);
        btnExportar.setBackground(VERDE);
        btnExportar.setForeground(Color.WHITE);
        btnExportar.setFont(new Font("Arial", Font.BOLD, 13));
        btnExportar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(btnExportar);

        btnVolver = new JButton("← Volver");
        btnVolver.setBounds(30, 605, 150, 35);
        btnVolver.setBackground(new Color(70, 70, 70));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFont(new Font("Arial", Font.BOLD, 13));
        btnVolver.setCursor(new Cursor(Cursor.HAND_CURSOR));
        add(btnVolver);
    }

    // ==================== TABLA ====================

    private void crearTabla() {

        modeloRanking = new DefaultTableModel(
                new Object[] { "Pos", "Usuario", "Pronósticos Totales", "Aciertos", "Fallos", "% Aciertos" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // tabla de solo lectura
            }
        };

        tblRanking = new JTable(modeloRanking);

        tblRanking.setRowHeight(40);
        tblRanking.setBackground(AZUL_PANEL);
        tblRanking.setForeground(Color.WHITE);
        tblRanking.setGridColor(new Color(60, 90, 130));
        tblRanking.setSelectionBackground(new Color(30, 70, 130));
        tblRanking.setSelectionForeground(Color.WHITE);
        tblRanking.setFont(new Font("Arial", Font.PLAIN, 14));
        tblRanking.setFillsViewportHeight(true);

        JTableHeader encabezado = tblRanking.getTableHeader();
        encabezado.setBackground(new Color(10, 35, 75));
        encabezado.setForeground(DORADO);
        encabezado.setFont(new Font("Arial", Font.BOLD, 14));
        encabezado.setReorderingAllowed(false);

        // Centrado para todas las columnas normales
        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        centrado.setBackground(AZUL_PANEL);
        centrado.setForeground(Color.WHITE);

        for (int i = 0; i < tblRanking.getColumnCount(); i++) {
            if (i != 1) { // la columna "Usuario" queda alineada a la izquierda
                tblRanking.getColumnModel().getColumn(i).setCellRenderer(centrado);
            }
        }

        DefaultTableCellRenderer usuarioRenderer = new DefaultTableCellRenderer();
        usuarioRenderer.setHorizontalAlignment(DefaultTableCellRenderer.LEFT);
        usuarioRenderer.setBackground(AZUL_PANEL);
        usuarioRenderer.setForeground(Color.WHITE);
        usuarioRenderer.setFont(new Font("Arial", Font.BOLD, 14));
        tblRanking.getColumnModel().getColumn(1).setCellRenderer(usuarioRenderer);

        // Renderer especial con barra de progreso para "% Aciertos"
        tblRanking.getColumnModel().getColumn(5).setCellRenderer(new BarraPorcentajeRenderer());

        // Anchos de columna aproximados al mockup
        tblRanking.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblRanking.getColumnModel().getColumn(1).setPreferredWidth(180);
        tblRanking.getColumnModel().getColumn(2).setPreferredWidth(140);
        tblRanking.getColumnModel().getColumn(3).setPreferredWidth(90);
        tblRanking.getColumnModel().getColumn(4).setPreferredWidth(90);
        tblRanking.getColumnModel().getColumn(5).setPreferredWidth(220);

        JScrollPane scroll = new JScrollPane(tblRanking);
        scroll.setBounds(30, 100, 940, 450);
        scroll.setBorder(new LineBorder(DORADO, 2));
        scroll.getViewport().setBackground(AZUL_PANEL);

        add(scroll);
    }

    // ==================== RENDERER: BARRA DE PROGRESO PARA % ACIERTOS
    // ====================

    private class BarraPorcentajeRenderer extends JPanel implements javax.swing.table.TableCellRenderer {

        private double porcentaje = 0;
        private String texto = "0.0%";

        public BarraPorcentajeRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {

            if (value instanceof Double) {
                porcentaje = (Double) value;
            } else if (value != null) {
                try {
                    porcentaje = Double.parseDouble(value.toString());
                } catch (NumberFormatException ex) {
                    porcentaje = 0;
                }
            }

            texto = String.format("%.1f%%", porcentaje);
            setBackground(isSelected ? new Color(30, 70, 130) : AZUL_PANEL);
            return this;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int margen = 10;
            int anchoTotal = getWidth() - (margen * 2) - 55; // deja espacio para el texto del %
            int alto = 18;
            int y = (getHeight() - alto) / 2;

            // Fondo de la barra (vacío)
            g2.setColor(new Color(40, 60, 100));
            g2.fillRoundRect(margen, y, anchoTotal, alto, 8, 8);

            // Relleno según el porcentaje, con color según desempeño
            int anchoRelleno = (int) (anchoTotal * (porcentaje / 100.0));

            Color colorBarra;
            if (porcentaje >= 60) {
                colorBarra = VERDE;
            } else if (porcentaje >= 40) {
                colorBarra = DORADO;
            } else {
                colorBarra = ROJO;
            }

            g2.setColor(colorBarra);
            g2.fillRoundRect(margen, y, Math.max(anchoRelleno, 6), alto, 8, 8);

            // Borde de la barra
            g2.setColor(Color.WHITE);
            g2.drawRoundRect(margen, y, anchoTotal, alto, 8, 8);

            // Texto del porcentaje a la derecha
            g2.setFont(new Font("Arial", Font.BOLD, 13));
            g2.drawString(texto, margen + anchoTotal + 8, y + 14);
        }
    }

    // ==================== GETTERS ====================

    public JTable getTblRanking() {
        return tblRanking;
    }

    public DefaultTableModel getModeloRanking() {
        return modeloRanking;
    }

    public JComboBox<String> getCmbMostrar() {
        return cmbMostrar;
    }

    public JButton getBtnActualizar() {
        return btnActualizar;
    }

    public JButton getBtnExportar() {
        return btnExportar;
    }

    public JButton getBtnVolver() {
         return btnVolver; }
}