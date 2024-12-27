package dev.joseluisgs;

/**
 * Clase que implementa una calculadora
 */
public class Calculadora {

    /**
     * Suma dos números enteros
     *
     * @param a número entero
     * @param b número entero
     * @return la suma de a y b
     */
    public int suma(int a, int b) {
        return a + b;
    }

    /**
     * Resta dos números enteros
     *
     * @param a número entero
     * @param b número entero
     * @return la resta de a y b
     */
    public int resta(int a, int b) {
        return a - b;
    }


    /**
     * Multiplica dos números enteros
     *
     * @param a número entero
     * @param b número entero
     * @return la multiplicación de a y b
     */
    public int multiplicacion(int a, int b) {
        return a * b;
    }

    /**
     * Divide dos números enteros
     *
     * @param a número entero
     * @param b número entero
     * @return la división de a y b
     * @throws ArithmeticException si b es cero
     */

    public int division(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("No se puede dividir por cero");
        }
        return a / b;
    }

}
