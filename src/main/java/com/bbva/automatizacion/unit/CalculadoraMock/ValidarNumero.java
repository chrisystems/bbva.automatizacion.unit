package com.bbva.automatizacion.unit.CalculadoraMock;

public class ValidarNumero {

	public ValidarNumero() {
	}

	// Validar si es un entero
	public boolean validar(Object obj) {
		if (obj instanceof Integer) {
			return true;
		} else {
			return false;
		}
	}

	// Validar si es un entero y si es Cero, retornar una excepción
	public boolean validarCero(Object obj) {
		if (obj instanceof Integer) {
			if ((Integer) obj == 0) {
				throw new ArithmeticException("No acepta cero");
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	public int doubleToInt(Object obj) {
		if (obj instanceof Double) {
			return ((Double) obj).intValue();
		} else {
			System.out.println("**************");
			return 0;
		}
	}
	
}
