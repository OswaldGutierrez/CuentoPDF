/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import javax.swing.JTable;
import com.itextpdf.text.Document;

/**
 *
 * @author Oswald David
 */
public class PruebaPDF {
    public static void crearBlog(String nombre, String id, String telefono){
        try{
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("Factura.pdf"));
            doc.open();
        
            Font titleFont = FontFactory.getFont(BaseFont.TIMES_BOLD, 21, BaseColor.RED);
            Font bodyFont = FontFactory.getFont(BaseFont.HELVETICA, 12, BaseColor.BLACK);
        
            Paragraph title = new Paragraph("Información del Usuario", titleFont);
            Paragraph body = new Paragraph("Nombre: " + nombre + "\nID: " + id + "\nTeléfono: " + telefono, bodyFont);
        
            doc.add(title); 
            doc.add(body);
        
            doc.close();
        } catch(DocumentException | java.io.FileNotFoundException e){
            e.printStackTrace();
        }
    }
    
    public static void crearTabla(JTable swTable){
                try{
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream("tabla.pdf"));
        doc.open();
        PdfPTable tabla = new PdfPTable(swTable.getColumnCount());
        for(int i = 0; i < swTable.getColumnCount(); i++)
        {
            tabla.addCell(swTable.getColumnName(i));
        }
        for(int x = 0; x < swTable.getRowCount(); x++)
        {
            for(int y = 0; y < swTable.getColumnCount(); y++)
            {
                String valor = (swTable.getValueAt(x, y) != null) ? swTable.getValueAt(x, y).toString() : "";
                tabla.addCell(valor);
            }
        }
        doc.add(tabla);
        doc.close();

        
        }catch(DocumentException | java.io.FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
