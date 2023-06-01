package Copiladores_PF;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.FileWriter;
import java.io.BufferedWriter;


public class OperacionesArchivo {
	
	
	public static void main(String[] args) throws IOException {
		

	
		ArrayList<String> lineas = lineasArchivo("operaciones.txt");
		
		generaArchivo(lineas);
		
	
		
		
			 

	}
	
	
	
	
	public static ArrayList<String> lineasArchivo(String file) throws IOException {
		ArrayList<String> lineas = new ArrayList<String>();
		File archivo = new File (file);
		
		FileReader fr = null;
		try {
			fr = new FileReader (archivo);
			BufferedReader br = new BufferedReader(fr);
			
			String linea;	         
			while((linea=br.readLine())!=null) {
				lineas.add(linea); 
			}
		} catch (FileNotFoundException e) {			
			System.out.println("El archivo "+file+" no pudo ser encontrado.");
			
		}catch (IOException e) {
			
		}finally {
			if (fr != null) {
				fr.close();
			}
		}
		
		return lineas;
	}
	
	
	public static String LeeTokens(String linea){
		String instruccion="error";
		try {
			 instruccion = linea.substring(0,linea.indexOf("("));
		} catch (Exception e) {
			
		}
		return instruccion;
	
		
	}
	
	public static void generaArchivo(ArrayList<String> lineas) {
		
		try {
			FileWriter archivo = new FileWriter("salida.txt");
			BufferedWriter salida = new BufferedWriter(archivo);
			
			for (String OP : lineas) {
				
				salida.write(OP+"  Leido");	
				salida.newLine();
				
			}
			salida.close();
			
		} catch (Exception e) {
			System.out.println("Error al generar el archivo: " + e.getMessage());
		}
		
		
	}
	
	public static void calculo(ArrayList<String> lineas) {
		
		
	}
	
}








