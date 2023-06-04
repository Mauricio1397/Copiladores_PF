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
import java.util.Stack;



public class OperacionesArchivo {
	
	
	public static void main(String[] args) throws IOException {
            System.out.println("HOLA");
		ArrayList<String> lineas = lineasArchivo("operaciones.txt");
		
		//generaArchivo(lineas);
		calculo(lineas);
                

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
	
	public static void calculo(ArrayList<String> lineas) {
            try {
                
                
                
                
                int controlP=0;
                String error=null;
                String exp=null;
                Double calculo=null;
                
                SalidaArchivo salida = new SalidaArchivo("salida.txt");
                salida.crearArchivo();
               for (String linea : lineas) {
                   //String linea="43(17*25*19(95+86+93*7)58=";
                   Stack<Character> Cparent = new Stack<>();
                   Stack<Double> operandos = new Stack<>();
                   Stack<Character> operadores = new Stack<>();
                   
                    
                    exp=linea;
                    
                    try {
                        calculo=new operaciones().evaluarExpresion(exp, operandos, operadores, controlP, Cparent);
                        
                        if (calculo==-0.0 || !Cparent.empty()) {
                            error="Error de sintaxis";
                            salida.agregarLinea(linea+" "+error);
                            
                        }else{
                            salida.agregarLinea(exp+" "+calculo);
                            
                        }
                        
                    } catch (Exception e) {
                        error="Error de sintaxis";
                        salida.agregarLinea(exp+" "+error);
                       
                    }
                    
                    
                    
               }
                System.out.println("\n\n!!!!!! FINALIZADO !!!!!!");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
		
		
		
		
	}
        
        
        public static void generaArchivo(String linea) {
                    try {
                        File archivo = new File("salida.txt");


                        FileWriter archivoWriter = new FileWriter(archivo);
                        BufferedWriter salida = new BufferedWriter(archivoWriter);

                        salida.write(linea);
                        salida.newLine();

                        salida.close();
                        

                    } catch (Exception e) {
                        System.out.println("Error al generar el archivo: " + e.getMessage());
                    }
}

        
        
	
}








