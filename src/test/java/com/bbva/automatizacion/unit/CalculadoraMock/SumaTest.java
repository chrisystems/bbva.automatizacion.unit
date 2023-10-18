package com.bbva.automatizacion.unit.CalculadoraMock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


public class SumaTest {

	@InjectMocks
	private Suma suma;

	@Mock
	private ValidarNumero validarNumero;
    @Mock
    private Mensaje mensaje;
    @Captor
    private ArgumentCaptor<Integer> captor;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	// Configuración de un comportamiento simple para un Mock
	@Test
	public void sumaTest() {
		when(validarNumero.validar(3)).thenReturn(true);
		boolean checkNumber = validarNumero.validar(3);
		assertEquals(true, checkNumber);

		/*
		 * when(validarNumero.validar("a")).thenReturn(false); checkNumber =
		 * validarNumero.validar("a"); assertEquals(false, checkNumber);
		 */
	}

	// Configuración de un comportamiento que lance una excepción
	@Test
	public void addMockExceptionTest() {
		when(validarNumero.validarCero(0)).thenThrow(new ArithmeticException("No acepta cero"));
		Exception exception = null;
		try {
			validarNumero.validarCero(0);
		} catch (ArithmeticException e) {
			exception = e;
		}
		assertNotNull(exception);
	}

	// Configuración de un comportamiento real
	// No recomendado ya que la idea es eliminar las dependencias
	@Test
	public void addRealMethodTest() {
		when(validarNumero.validar(3)).thenCallRealMethod();
		assertEquals(true, validarNumero.validar(3));

		/*
		 * when(validarNumero.validar("a")).thenCallRealMethod(); assertEquals(false,
		 * validarNumero.validar("a"));
		 */
	}
	
	//Configurar un comportamiento complejo (inteligente) a un Mock
	@Test
    public void addDoubleToIntThenAnswerTest(){
        Answer<Integer> answer = new Answer<Integer>() 
        {
            @Override
            public Integer answer(InvocationOnMock invocationOnMock) throws Throwable {
                return 7;
            }
        };
        when(validarNumero.doubleToInt(7.1)).thenAnswer(answer);
        assertEquals(14, suma.sumaInt(7.1));
    }
	
	//Patrón de pruebas para Mock (BDD)
	//Given
    //When
    //Then
	
    @Test
    public void patronTest(){
        //Given
        when(validarNumero.validar(4)).thenReturn(true);
        when(validarNumero.validar(5)).thenReturn(true);
        //When
        int result = suma.suma(4,5);
        //Then
        assertEquals(9, result);
    }

    //Aplicando patrón BDD: Given, When, Then
    @Test
    public void patronBDDTest(){
        //Given
        given(validarNumero.validar(4)).willReturn(true);
        given(validarNumero.validar(5)).willReturn(true);
        //When
        int result = suma.suma(4,5);
        //Then
        assertEquals(9, result);
    }
    
    //ArgumentMatcher permite una verificación más flexible ya que responde a un rango más amplio de valores o valores previamente desconocidos.
    @Test
    public void argumentMatcherTest(){
        given(validarNumero.validar(anyInt())).willReturn(true);
        int result = suma.suma(4,5);
        assertEquals(9, result);
    }
    
    //Uso de verify (aplica también para métodos Void)
    @Test
    public void addPrintTest(){
        //Given
        given(validarNumero.validar(4)).willReturn(true);
        given(validarNumero.validar(5)).willReturn(true);
        //When
        suma.sumaMensaje(4,5);
        //Then
        verify(validarNumero).validar(4);
        //verify(validNumber, times(2)).validar(4);
        verify(validarNumero, never()).validar(99);
        verify(validarNumero, atLeast(1)).validar(4);
        verify(validarNumero, atMost(3)).validar(4);
        //Métodos Void
        verify(mensaje).showMessage(9);
        verify(mensaje, never()).showMessageError();
    } 
    
  //ArgumentCaptor permite capturar los valores de los argumentos para las asersiones
    @Test
    public void captorTest(){
        //Given
        given(validarNumero.validar(4)).willReturn(true);
        given(validarNumero.validar(5)).willReturn(true);
        //When
        suma.sumaMensaje(4,5);
        //Then
        verify(mensaje).showMessage(captor.capture());
        //se verifica el argumento capturado
        assertEquals(captor.getValue().intValue(), 9);
    }
    
    //Spy igual a mock, sólo que aquellos métodos que no hemos mockeado retornan el resultado real
    @Spy
    List<String> spyList = new ArrayList<>();
    @Mock
    List<String> mockList = new ArrayList<>();

    @Test
    public void spyTest(){
        spyList.add("1");
        spyList.add("2");
        verify(spyList).add("1");
        verify(spyList).add("2");
        assertEquals(2, spyList.size());
    }

    @Test
    public void mockTest(){
        mockList.add("1");
        mockList.add("2");
        verify(mockList).add("1");
        verify(mockList).add("2");
        given(mockList.size()).willReturn(2);
        assertEquals(2, mockList.size());
    }

}
