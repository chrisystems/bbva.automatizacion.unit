package com.bbva.automatizacion.unit.CalculadoraJunit;

import static org.junit.jupiter.api.Assertions.assertAll;

public class Calculadora {
	private int result;

	public int suma(int n1, int n2) {
		return result = n1 + n2;
	}
	
    public int resta(int n1, int n2){
        return result = n1 - n2;
    }

    public int division(int n1, int n2){
        return result = n1 / n2;
    }

    public int divisionPorCero(int n1, int n2){
        if(n2 == 0){
            throw new ArithmeticException("No se puede dividir por cero");
        }
        return result = n1 / n2;
    }
    
}


