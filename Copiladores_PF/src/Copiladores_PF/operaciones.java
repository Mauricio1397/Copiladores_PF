package Copiladores_PF;

import java.util.Stack;

public class operaciones {

	
	public static double evaluarExpresion(String expresion) {
        expresion = expresion.replaceAll("\\s+", ""); // Eliminar espacios en blanco

        Stack<Double> operandos = new Stack<>();
        Stack<Character> operadores = new Stack<>();

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
                
                
                
            } else if (c == ')') {
                while (!operadores.isEmpty() && operadores.peek() != '(') {
                    realizarOperacion(operandos, operadores);
                }
                
                if (!operadores.isEmpty() && operadores.peek() == '(') {
                    operadores.pop(); // Eliminar el '(' correspondiente
                }
                if (!operadores.isEmpty() && (operadores.peek() == '*' || operadores.peek() == '/')) {
                    realizarOperacion(operandos, operadores);
                }
                
                
                
            } else if (esOperador(c)) {
                while (!operadores.isEmpty() && obtenerPrioridad(c) <= obtenerPrioridad(operadores.peek())) {
                    realizarOperacion(operandos, operadores);
                }
                operadores.push(c);
            }
        }

        while (!operadores.isEmpty()) {
            realizarOperacion(operandos, operadores);
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
                resultado = primerOperando + segundoOperando;
                break;
            case '-':
                resultado = primerOperando - segundoOperando;
                break;
            case '*':
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
        if (operador == '*' || operador == '/') {
            return 2;
        } else if (operador == '+' || operador == '-') {
            return 1;
        } else {
            return 0;
        }
    }
}
	

