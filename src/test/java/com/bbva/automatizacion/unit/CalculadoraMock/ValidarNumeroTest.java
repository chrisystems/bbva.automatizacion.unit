package com.bbva.automatizacion.unit.CalculadoraMock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bbva.automatizacion.unit.CalculadoraMock.ValidarNumero;

public class ValidarNumeroTest {

	ValidarNumero validarNumero;

	@Before
	public void setUp() {
		validarNumero = new ValidarNumero();
	}

	@After
	public void tearDown() {
		validarNumero = null;
	}

	// Tests Método validarNumero
	@Test
	public void validarTest() {
		assertEquals(true, validarNumero.validar(5));
	}

	@Test
	public void validarNegativoTest() {
		assertEquals(true, validarNumero.validar(-5));
	}

	@Test
	public void validarStringTest() {
		assertEquals(false, validarNumero.validar("a"));
	}

	// Tests Método validarCero
	@Test
	public void validarCeroStringTest() {
		assertEquals(false, validarNumero.validarCero("5"));
	}

	@Test
	public void validarCero0Test() {
		assertThrows(ArithmeticException.class, () -> validarNumero.validarCero(0));
	}
	
	@Test
	public void doubleToIntTest() {
		assertEquals(9, validarNumero.doubleToInt(9.999));
	}

	@Test
	public void doubleToIntErrorTest() {
		assertEquals(0, validarNumero.doubleToInt("a"));
	}

}
