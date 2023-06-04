/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Copiladores_PF;

/**
 *
 * @author mejia
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SalidaArchivo {
    private File archivo;

    public SalidaArchivo(String nombreArchivo) {
        this.archivo = new File(nombreArchivo);
    }

    public boolean validarArchivo() {
        return archivo.exists() && archivo.isFile() && archivo.getName().endsWith(".txt");
    }

    public void crearArchivo() throws IOException {
        if (!validarArchivo()) {
            archivo.createNewFile();
            
        }
    }

    public void agregarLinea(String linea) throws IOException {
        FileWriter writer = new FileWriter(archivo, true);
        writer.write(linea + "\n");
        writer.close();
    }
}
