package util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExportadorExcel {

    // Exporta una JTable (usada por VistaRankingAciertos) a un archivo .xlsx
    public static void exportarTabla(JTable tabla, String nombreArchivo) {
        TableModel modelo = tabla.getModel();

        try (Workbook libro = new XSSFWorkbook()) {

            Sheet hoja = libro.createSheet("Reporte");

            // Estilo para el encabezado
            CellStyle estiloEncabezado = libro.createCellStyle();
            Font fuenteEncabezado = libro.createFont();
            fuenteEncabezado.setBold(true);
            estiloEncabezado.setFont(fuenteEncabezado);
            estiloEncabezado.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            estiloEncabezado.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // Fila de encabezados (nombres de columnas)
            Row filaEncabezado = hoja.createRow(0);
            for (int col = 0; col < modelo.getColumnCount(); col++) {
                Cell celda = filaEncabezado.createCell(col);
                celda.setCellValue(modelo.getColumnName(col));
                celda.setCellStyle(estiloEncabezado);
            }

            // Filas de datos
            for (int fila = 0; fila < modelo.getRowCount(); fila++) {
                Row filaExcel = hoja.createRow(fila + 1);

                for (int col = 0; col < modelo.getColumnCount(); col++) {
                    Object valor = modelo.getValueAt(fila, col);
                    Cell celda = filaExcel.createCell(col);

                    if (valor instanceof Integer) {
                        celda.setCellValue((Integer) valor);
                    } else if (valor instanceof Double) {
                        celda.setCellValue((Double) valor);
                    } else if (valor != null) {
                        celda.setCellValue(valor.toString());
                    }
                }
            }

            // Ajusta el ancho de cada columna al contenido
            for (int col = 0; col < modelo.getColumnCount(); col++) {
                hoja.autoSizeColumn(col);
            }

            try (FileOutputStream salida = new FileOutputStream(nombreArchivo)) {
                libro.write(salida);
            }

            JOptionPane.showMessageDialog(null, "Archivo exportado correctamente:\n" + nombreArchivo);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al exportar: " + ex.getMessage());
        }
    }

    // Exporta datos de gráfico (usada por VistaPronosticosUsuario) a un archivo .xlsx
    public static void exportarGrafico(List<String> nombres, List<Integer> cantidades, String nombreArchivo) {

        try (Workbook libro = new XSSFWorkbook()) {

            Sheet hoja = libro.createSheet("Pronosticos por Usuario");

            CellStyle estiloEncabezado = libro.createCellStyle();
            Font fuenteEncabezado = libro.createFont();
            fuenteEncabezado.setBold(true);
            estiloEncabezado.setFont(fuenteEncabezado);
            estiloEncabezado.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
            estiloEncabezado.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            Row filaEncabezado = hoja.createRow(0);
            Cell c1 = filaEncabezado.createCell(0);
            c1.setCellValue("Usuario");
            c1.setCellStyle(estiloEncabezado);

            Cell c2 = filaEncabezado.createCell(1);
            c2.setCellValue("Cantidad de Pronósticos");
            c2.setCellStyle(estiloEncabezado);

            for (int i = 0; i < nombres.size(); i++) {
                Row fila = hoja.createRow(i + 1);
                fila.createCell(0).setCellValue(nombres.get(i));
                fila.createCell(1).setCellValue(cantidades.get(i));
            }

            hoja.autoSizeColumn(0);
            hoja.autoSizeColumn(1);

            try (FileOutputStream salida = new FileOutputStream(nombreArchivo)) {
                libro.write(salida);
            }

            JOptionPane.showMessageDialog(null, "Archivo exportado correctamente:\n" + nombreArchivo);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al exportar: " + ex.getMessage());
        }
    }
}