package Copiladores_PF;

import java.util.Stack;

public class operaciones {

	
	public  double evaluarExpresion(String expresion,  Stack<Double> operandos, Stack<Character> operadores, int controlP) {
        expresion = expresion.replaceAll("\\s+", ""); // Eliminar espacios en blanco

        for (int i = 0; i < expresion.length(); i++) {
            char c = expresion.charAt(i);

                if (Character.isDigit(c) || c == '.') {
                    StringBuilder token = new StringBuilder();
                token.append(c);

                // Continuar recorriendo para obtener el número completo
                while (i + 1 < expresion.length() && (Character.isDigit(expresion.charAt(i + 1)) || expresion.charAt(i + 1) == '.')) {
                	token.append(expresion.charAt(i + 1));
                    i++;
                }

                double numero = Double.parseDouble(token.toString());
                operandos.push(numero);
                
            } else if (c == '(') {
                operadores.push(c);
                Double numero=null;
                
                int d = expresion.indexOf(')', i);
                
                if  (d != -1) {
                    String parentesis=null;
                    if (expresion.substring(i+1,d ).indexOf('(') != -1){
                     parentesis=expresion.substring(i+1, expresion.indexOf(')')+1);
                    }else{
                       parentesis=expresion.substring(i+1, d);
                      
                    }
                      
                     numero= evaluarExpresion(parentesis, operandos, operadores, controlP+1);
                     if (operadores.peek()=='(') {
                        operadores.pop();
                    }
                     operandos.push(numero);
                     if (!operandos.empty()) {
                         
                       
                         char antP=expresion.charAt(i-1);
                         if(antP!='+'){
                             if (antP!='-') {
                                  if (antP=='/' || antP=='*') {
                                    realizarOperacion(operandos, operadores);
                                  }else{
                                    operadores.push('*');
                                    realizarOperacion(operandos, operadores);
                                  }
                           }  
                         }
     
                    }
                     i=d;
                 }else {
                    System.out.println("Error de sintaxis");
                }
                
            } else if (c == ')') {
                    if (!esOperador(expresion.charAt(i+1))) {
                        operadores.push('*');
                        StringBuilder token = new StringBuilder();
                            while (i + 1 < expresion.length() && (Character.isDigit(expresion.charAt(i + 1)) || expresion.charAt(i + 1) == '.')) {
                            token.append(expresion.charAt(i + 1));
                        i++;
                        }
                            Double numero=Double.parseDouble(token.toString());
                             operandos.push(numero);
                             realizarOperacion(operandos, operadores);
 
                    } 
            } else if (esOperador(c)) {
                operadores.push(c);
                if (c=='*' || c=='/') {
                    if (expresion.charAt(i + 1) !='(') {
                        StringBuilder token = new StringBuilder();
                            while (i + 1 < expresion.length() && (Character.isDigit(expresion.charAt(i + 1)) || expresion.charAt(i + 1) == '.')) {
                            token.append(expresion.charAt(i + 1));
                        i++;
                        }
                            Double numero=Double.parseDouble(token.toString());
                             operandos.push(numero);
                             realizarOperacion(operandos, operadores);
   
                    }
                         
                }

            }
        }
        
       
            if (!operadores.isEmpty() && controlP==0 && operadores.peek()!='/') {
                Stack<Double> operandossum = new Stack<>();
                Stack<Character> operadoressum = new Stack<>();
            
                
                while (!operandos.isEmpty()) {
                    Double elemento = operandos.pop();
                    operandossum.push(elemento);
                }
                while (!operadores.isEmpty()) {
                    char elemento = operadores.pop();
                    operadoressum.push(elemento);
                }
            operadores=operadoressum;
            operandos=operandossum;
            
            while (!operadores.isEmpty() && controlP==0) {
            
                realizarOperacion(operandos, operadores);
            
                }
            
           }else{
                while (!operadores.isEmpty() && (operadores.peek()!='+') && operadores.peek()!='-' && operadores.peek()!='(' ) {
                realizarOperacion(operandos, operadores);
                }
                while (!operadores.isEmpty() && operadores.peek()!='(') {
                    realizarOperacion(operandos, operadores);
                }
           }
 
        
        
            return operandos.pop();
    }

	
    public static void realizarOperacion(Stack<Double> operandos, Stack<Character> operadores) {
        char operador = operadores.pop();
        double segundoOperando = operandos.pop();
        double primerOperando = operandos.pop();

            double resultado = 0.0;

        switch (operador) {
            case '+':
                resultado = segundoOperando + primerOperando;
                break;
            case '-':
                resultado = segundoOperando - primerOperando;
                break;
            case '*':
            case '(' :   
                resultado = primerOperando * segundoOperando;
                break;
            case '/':
                resultado = primerOperando / segundoOperando;
                break;
        }

        operandos.push(resultado);
    }

    public static boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    public static int obtenerPrioridad(char operador) {
        if (operador == '*' || operador == '/' || operador =='(') {
            return 2;
        } else if (operador == '+' || operador == '-') {
            return 1;
        } else {
            return 0;
        }
    }
    
    
    
        
}