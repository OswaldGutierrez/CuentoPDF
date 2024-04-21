/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author Oswald David
 */
public class MetodosPDF {
    
    public static double calcularIVA(double precio) {
        double nuevoPrecio = precio + (precio * 0.19);
        return nuevoPrecio;
    }
    
}
