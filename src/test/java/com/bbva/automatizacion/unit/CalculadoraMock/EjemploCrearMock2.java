package com.bbva.automatizacion.unit.CalculadoraMock;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.bbva.automatizacion.unit.CalculadoraMock.Suma;
import com.bbva.automatizacion.unit.CalculadoraMock.ValidarNumero;

public class EjemploCrearMock2 {

	// Clase bajo Test
	@InjectMocks
	private Suma suma;
	// Crear mock de la dependencia
	@Mock
	private ValidarNumero validarNumero;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void sumaTest() {
		suma.suma(3, 5);
		Mockito.verify(validarNumero).validar(3);
	}
}
