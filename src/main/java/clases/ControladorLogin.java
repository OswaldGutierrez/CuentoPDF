/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import forms.FormularioPDF;
import forms.Ventana2;
import model.Usuario;
import model.JsonUtil;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author Oswald David
 */
public class ControladorLogin {

    private FormularioPDF formularioPDF;
    private Usuario usuario;

    public ControladorLogin(FormularioPDF formularioPDF) {
        this.formularioPDF = formularioPDF;
        this.formularioPDF.setLoginListener(e -> autenticarUsuario());
    }

    private void autenticarUsuario() {
        String username = formularioPDF.getUsername();
        String password = formularioPDF.getPassword();

        try {
            usuario = JsonUtil.cargarUsuarioDesdeJson();
            if (usuario.getNombreUsuario().equals(username) && usuario.getContrasena().equals(password)) {
                JOptionPane.showMessageDialog(formularioPDF, "Login exitoso", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                formularioPDF.dispose();
                Ventana2 ventana2 = new Ventana2();
                ventana2.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(formularioPDF, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(formularioPDF, "Error al leer el archivo de configuración", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
