/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Oswald David
 */
public class JsonUtil {
    private static final String JSON_FILE_PATH = "C:\\Users\\Oswald David\\Documents\\NetBeansProjects\\CuentoPDF\\src\\main\\java\\resources\\config.json";

    public static Usuario cargarUsuarioDesdeJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(JSON_FILE_PATH), Usuario.class);
    }

    public static void guardarUsuarioEnJson(Usuario usuario) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(JSON_FILE_PATH), usuario);
    }
}
