package dev.joseluisgs;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hola Caracola");
        var Calculadora = new Calculadora();
        System.out.println("Suma: " + Calculadora.suma(2, 3));
        System.out.println("Resta: " + Calculadora.resta(2, 3));
        System.out.println("Multiplicación: " + Calculadora.multiplicacion(2, 3));
        System.out.println("División: " + Calculadora.division(2, 3));
        try {
            System.out.println("División: " + Calculadora.division(2, 0));
        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
    }

}