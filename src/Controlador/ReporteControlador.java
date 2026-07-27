package Controlador;

import DAO.PronosticoDAO;
import Vista.VistaPronosticosUsuario;
import Vista.VistaRankingAciertos;
import util.ExportadorExcel;
import javax.swing.JFileChooser;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReporteControlador {

    private final VistaPronosticosUsuario vistaPronosticos;
    private final VistaRankingAciertos vistaRanking;
    private final PronosticoDAO pronosticoDAO;

    public ReporteControlador(VistaPronosticosUsuario vistaPronosticos, VistaRankingAciertos vistaRanking) {
        this.vistaPronosticos = vistaPronosticos;
        this.vistaRanking = vistaRanking;
        this.pronosticoDAO = new PronosticoDAO();

        // ==================== EVENTOS: VISTA PRONOSTICOS POR USUARIO ====================

        vistaPronosticos.getBtnActualizar().addActionListener(e -> cargarPronosticosPorUsuario());
        vistaPronosticos.getBtnFiltrar().addActionListener(e -> filtrarPronosticosPorFecha());

        // ==================== EVENTOS: VISTA RANKING DE ACIERTOS ====================

        vistaRanking.getBtnActualizar().addActionListener(e -> cargarRankingAciertos());
        vistaRanking.getCmbMostrar().addActionListener(e -> cargarRankingAciertos());

        vistaRanking.getBtnExportar().addActionListener(e -> exportarRanking());
        vistaPronosticos.getBtnExportar().addActionListener(e -> exportarGraficoPronosticos());

        // Carga inicial al abrir el programa
        cargarPronosticosPorUsuario();
        cargarRankingAciertos();
    }

    // ==================== GRAFICO DE BARRAS: PRONOSTICOS POR USUARIO ====================

    private void cargarPronosticosPorUsuario() {
        Map<String, Integer> datos = pronosticoDAO.cantidadPronosticosPorUsuario();
        pintarGrafico(datos);
    }

    private void filtrarPronosticosPorFecha() {
        String desde = vistaPronosticos.getTxtDesde().getText().trim();
        String hasta = vistaPronosticos.getTxtHasta().getText().trim();

        if (desde.isEmpty() || hasta.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingresa ambas fechas (formato: YYYY-MM-DD).");
            return;
        }

        if (!desde.matches("\\d{4}-\\d{2}-\\d{2}") || !hasta.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JOptionPane.showMessageDialog(null, "Formato de fecha inválido. Usa YYYY-MM-DD (ej. 2026-06-11).");
            return;
        }

        Map<String, Integer> datos = pronosticoDAO.cantidadPronosticosPorFecha(desde, hasta);

        if (datos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay pronósticos registrados en ese rango de fechas.");
        }

        pintarGrafico(datos);
    }

    private void pintarGrafico(Map<String, Integer> datos) {
        List<String> nombres = new ArrayList<>();
        List<Integer> cantidades = new ArrayList<>();

        for (Map.Entry<String, Integer> entrada : datos.entrySet()) {
            nombres.add(entrada.getKey());
            cantidades.add(entrada.getValue());
        }

        vistaPronosticos.setDatos(nombres, cantidades);
    }

    // ==================== TABLA: RANKING DE ACIERTOS ====================

    private void cargarRankingAciertos() {

        DefaultTableModel modeloOriginal = pronosticoDAO.obtenerRankingAciertos();

        List<Object[]> filas = new ArrayList<>();

        for (int i = 0; i < modeloOriginal.getRowCount(); i++) {
            String nombre = (String) modeloOriginal.getValueAt(i, 1);
            int totalPronosticos = (int) modeloOriginal.getValueAt(i, 2);
            int aciertos = (int) modeloOriginal.getValueAt(i, 3);
            int fallos = (int) modeloOriginal.getValueAt(i, 4);

            double porcentaje = totalPronosticos > 0 ? (aciertos * 100.0 / totalPronosticos) : 0.0;

            filas.add(new Object[] { nombre, totalPronosticos, aciertos, fallos, porcentaje });
        }

        filas.sort((a, b) -> {
            double pctA = (double) a[4];
            double pctB = (double) b[4];

            if (pctB != pctA) {
                return Double.compare(pctB, pctA);
            }
            return Integer.compare((int) b[1], (int) a[1]);
        });

        String filtro = (String) vistaRanking.getCmbMostrar().getSelectedItem();
        int limite = filas.size();

        if ("Top 5".equals(filtro)) {
            limite = Math.min(5, filas.size());
        } else if ("Top 10".equals(filtro)) {
            limite = Math.min(10, filas.size());
        } else if ("Top 20".equals(filtro)) {
            limite = Math.min(20, filas.size());
        }

        DefaultTableModel modeloFinal = vistaRanking.getModeloRanking();
        modeloFinal.setRowCount(0);

        for (int i = 0; i < limite; i++) {
            Object[] fila = filas.get(i);

            modeloFinal.addRow(new Object[] {
                    i + 1,
                    fila[0],
                    fila[1],
                    fila[2],
                    fila[3],
                    fila[4]
            });
        }
    }

    // ==================== EXPORTAR A EXCEL ====================

    private void exportarRanking() {
        JFileChooser selector = new JFileChooser();
        selector.setSelectedFile(new File("ranking_aciertos.xlsx"));

        int seleccion = selector.showSaveDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            String ruta = selector.getSelectedFile().getAbsolutePath();

            if (!ruta.toLowerCase().endsWith(".xlsx")) {
                ruta += ".xlsx";
            }

            ExportadorExcel.exportarTabla(vistaRanking.getTblRanking(), ruta);
        }
    }

    private void exportarGraficoPronosticos() {
        Map<String, Integer> datos = pronosticoDAO.cantidadPronosticosPorUsuario();

        List<String> nombres = new ArrayList<>();
        List<Integer> cantidades = new ArrayList<>();

        for (Map.Entry<String, Integer> entrada : datos.entrySet()) {
            nombres.add(entrada.getKey());
            cantidades.add(entrada.getValue());
        }

        JFileChooser selector = new JFileChooser();
        selector.setSelectedFile(new File("pronosticos_por_usuario.xlsx"));

        int seleccion = selector.showSaveDialog(null);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            String ruta = selector.getSelectedFile().getAbsolutePath();

            if (!ruta.toLowerCase().endsWith(".xlsx")) {
                ruta += ".xlsx";
            }

            ExportadorExcel.exportarGrafico(nombres, cantidades, ruta);
        }
    }
}