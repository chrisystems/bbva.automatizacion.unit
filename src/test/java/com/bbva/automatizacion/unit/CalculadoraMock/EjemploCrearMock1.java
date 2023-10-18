package com.bbva.automatizacion.unit.CalculadoraMock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.bbva.automatizacion.unit.CalculadoraMock.Suma;
import com.bbva.automatizacion.unit.CalculadoraMock.ValidarNumero;

public class EjemploCrearMock1 {
	private Suma suma;
	private ValidarNumero validarNumero;

	@Before
	public void setUp() {
		// Crear mock de la dependencia
		validarNumero = Mockito.mock(ValidarNumero.class);
		// Clase bajo Test
		suma = new Suma(validarNumero);
	}

	@Test
	public void sumaTest() {
		suma.suma(3, 5);
		Mockito.verify(validarNumero).validar(3);
	}
}
