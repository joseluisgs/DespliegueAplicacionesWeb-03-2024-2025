package dev.joseluisgs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculadoraTest {

    Calculadora calculadora = new Calculadora();

    @Test
    void suma() {
        assertEquals(5, calculadora.suma(2, 3));
    }

    @Test
    void resta() {
        assertEquals(-1, calculadora.resta(2, 3));
    }

    @Test
    void multiplicacion() {
        assertEquals(6, calculadora.multiplicacion(2, 3));
    }

    @Test
    void division() {
        assertEquals(3, calculadora.division(6, 2));
    }

    @Test
    void divisionPorCero() {
        var exception = assertThrows(ArithmeticException.class, () -> {
            calculadora.division(2, 0);
        });
        assertEquals("No se puede dividir por cero", exception.getMessage());
    }
}