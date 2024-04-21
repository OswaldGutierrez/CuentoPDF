/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Oswald David
 */
public class ExcelLecturaTabla {
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File archivo = new File("C:/Users/Oswald David/Documents/NetBeansProjects/CuentoPDF/Datos.xlsx");
        
        try {
            InputStream input = new FileInputStream(archivo);
            XSSFWorkbook libro = new XSSFWorkbook(input);                           // Abrimos el documento
            XSSFSheet hoja = libro.getSheetAt(1);                                   // Accedemos a la hoja que contiene los datos
            
            Iterator<Row> filas = hoja.rowIterator();
            Iterator<Cell> columnas = null;
            
            Row filaActual = null;
            Cell columnaActual = null;
            double precioVehiculo = 0;
            
            while (filas.hasNext()) {
                filaActual = filas.next();
                columnas = filaActual.cellIterator();
                
                while (columnas.hasNext()) {
                    columnaActual = columnas.next();
                    
                    if (filaActual.getRowNum() == 11202 && columnaActual.getColumnIndex() == 83) {
                        if (columnaActual.getCellType() == CellType.NUMERIC) {
                            precioVehiculo = columnaActual.getNumericCellValue();
                        }
                    }
                    
                    if (columnaActual.getCellType() == CellType.STRING) {
                        String valor = columnaActual.getStringCellValue();
                        System.out.println(valor);
                    }
                    if (columnaActual.getCellType() == CellType.NUMERIC) {
                        double valor = columnaActual.getNumericCellValue();
                        System.out.println(valor);
                    }
                    if (columnaActual.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(columnaActual)) {
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                        Date fecha = columnaActual.getDateCellValue();
                        System.out.println(formato.format(fecha));
                    }
                }
            }
            
            System.out.println("El precio del vehículo es: " + precioVehiculo);
            input.close();
            libro.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}