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
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Oswald David
 */
public class PruebaPDF {

    public static void crearBlog(String nombre, String id, String telefono, int indice) throws BadElementException, IOException {
        Document doc = new Document();
        try {
            
            //PdfWriter.getInstance(doc, new FileOutputStream("Factura.pdf"));
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("Factura.pdf"));
            
            doc.open();
            
            float documentWidth = doc.getPageSize().getWidth();
            float lineaY = 725;
            
            PdfContentByte canvas = writer.getDirectContent();
            canvas.moveTo(0, lineaY); // Mueve el cursor al punto inicial de la línea (en el eje Y)
            canvas.lineTo(documentWidth, lineaY); // Dibuja la línea hasta el punto final (en el eje Y)
            canvas.stroke(); // Dibuja la línea en el lienzo

            Font titleFont = FontFactory.getFont(BaseFont.TIMES_BOLD, 21, BaseColor.RED);
            Font bodyFont = FontFactory.getFont(BaseFont.HELVETICA, 12, BaseColor.BLACK);

            Paragraph title = new Paragraph("Facturación", titleFont);
            Paragraph body = new Paragraph("Nombre: " + nombre + "\nID: " + id + "\nTeléfono: " + telefono, bodyFont);
            
            ExcelLecturaTabla.leerArchivoExcel("C:\\Users\\Oswald David\\Documents\\NetBeansProjects\\CuentoPDF\\Datos.xlsx", indice);
            double precioVehiculo = ExcelLecturaTabla.precioVehiculo;
            Paragraph precio = new Paragraph("\nEl precio del vehículo es: " + precioVehiculo);
            
            double iva = MetodosPDF.calcularIVA(precioVehiculo);
            Paragraph precioIva = new Paragraph("El precio del vehículo con el impuesto IVA es: " + iva);
            
            Paragraph descripcion = new Paragraph("\nConforme a la transacción realizada, se ha procedido a la venta de un vehículo"
                    + " de la marca Toyota, modelo 2023. El monto acordado por esta adquisición asciende a la cantidad de "
                    + precioVehiculo + ". Este acto comercial queda registrado bajo el código de interificación del vehículo"
                    + " en la base de datos del sistema Fasecolda. El vehículo ha sido adquirido por " + nombre + ", quien puede"
                    + " ser contactado mediante el número de teléfono " + telefono + " y está debidamente identificado con el siguiente ID: "
                    + id);
            
            Image image = Image.getInstance("C:/Users/Oswald David/Documents/NetBeansProjects/CuentoPDF/src/main/java/imagenes/Logo.png");
            image.scaleAbsolute(80, 80);

            doc.add(image);
            doc.add(title);
            doc.add(body);
            doc.add(precio);
            doc.add(precioIva);
            doc.add(descripcion);
            
            doc.close();
        } catch (DocumentException | java.io.FileNotFoundException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(null, "Se creo la factura del vehículo en la carpeta del proyecto");
        
        
    }

    
    
    public static void crearTabla(JTable swTable) {
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("tabla.pdf"));
            doc.open();
            PdfPTable tabla = new PdfPTable(swTable.getColumnCount());
            for (int i = 0; i < swTable.getColumnCount(); i++) {
                tabla.addCell(swTable.getColumnName(i));
            }
            for (int x = 0; x < swTable.getRowCount(); x++) {
                for (int y = 0; y < swTable.getColumnCount(); y++) {
                    String valor = (swTable.getValueAt(x, y) != null) ? swTable.getValueAt(x, y).toString() : "";
                    tabla.addCell(valor);
                }
            }
            doc.add(tabla);
            doc.close();

        } catch (DocumentException | java.io.FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    
    public void onEndPage(PdfWriter writer, Document document) {
        try {
            Image background = Image.getInstance("C:\\Users\\Oswald David\\Documents\\NetBeansProjects\\CuentoPDF\\src\\main\\java\\imagenes");
            PdfContentByte canvas = writer.getDirectContentUnder();
            background.scaleAbsolute(document.getPageSize());
            background.setAbsolutePosition(0, 0);
            canvas.addImage(background);
        } catch (DocumentException | IOException e) {
        }
    }
    
}
