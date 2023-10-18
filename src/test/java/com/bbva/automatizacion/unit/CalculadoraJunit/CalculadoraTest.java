package com.bbva.automatizacion.unit.CalculadoraJunit;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.bbva.automatizacion.unit.CalculadoraJunit.Calculadora;

class CalculadoraTest {
	private Calculadora calculadora;
	private static Calculadora calculadoraAll;
	
    @BeforeAll
    public static void beforeAllTests(){
    	calculadoraAll = new Calculadora();
    }

    @AfterAll
    public static void afterAllTests(){
    	calculadoraAll = null;
    }
    
	@BeforeEach
    public void setUp(){
    	calculadora = new Calculadora();
    }	
    
    @AfterEach
    public void tearDown(){
    	calculadora = null;
    }
	
    @Test
    public void tiposAssertTest(){
        assertTrue(1 == 1);
        assertNotNull(calculadora);

        Calculadora calculator1 = new Calculadora();
        Calculadora calculator2 = new Calculadora();
        Calculadora calculator3 = calculator1;

        assertSame(calculator1, calculator3);
        assertNotSame(calculator1, calculator2);
        assertEquals("prueba", "prueba");
        assertEquals(1, 1.4, 0.5);
    }
    
    @Test
    public void sumaTest(){
        assertEquals(30, calculadora.suma(10,20));
    }
    
    @Test
    public void restaTest(){
        assertEquals(11, calculadora.resta(15,4));
    }

    @Test
    public void restaNegativaTest(){
        assertEquals(-10, calculadora.resta(10,20));
    }
    
    @Test
    public void divisionPorCero_ExpectedException_Test(){
        assertThrows(ArithmeticException.class, ()->calculadora.divisionPorCero(5,0), "No se puede dividir por cero");
    }
    
    @Test
    public void sumaConAssertAll_Test(){
        assertAll(
                ()-> assertEquals(9, calculadora.suma(4,5)),
                ()-> assertEquals(5, calculadora.suma(3,2)),
                ()-> assertEquals(2, calculadora.suma(1,1))
        );
    }
    
    @Nested
    class SumaTest{
        @Test
        public void suma_Positiva_Test(){
            assertEquals(30, calculadora.suma(15,15));
        }

        @Test
        public void suma_Negativa_Test(){
            assertEquals(-30, calculadora.suma(-15,-15));
        }

        @Test
        public void suma_Cero_Test(){
            assertEquals(0, calculadora.suma(0,0));
        }
    }
    
    private static Stream<Arguments> addProviderData(){
        return Stream.of(
                Arguments.of(6,2,8),
                Arguments.of(-6,-2,-8),
                Arguments.of(6,-2,4),
                Arguments.of(-6,2,-4),
                Arguments.of(6,0,6)
        );
    }
    
    @ParameterizedTest(name = "{index} => a={0}, b={1}, result={2}")
    @MethodSource("addProviderData")
    public void sumaParametrizadaTest(int a, int b, int result){
        assertEquals(result, calculadora.suma(a, b));
    }
    
    @Test
    public void timeOut_Test(){
        assertTimeout(Duration.ofMillis(2000), () ->{
        	assertEquals(30, calculadora.suma(10,20));
        });
    }
    
    @Disabled("Deshabilitat Test")
    @Test
    public void dividirEnConstruccionTest(){
    	assertEquals(2, calculadora.division(20,30));
    }

    @DisplayName("Metodo Dividir")
    @Test
    public void dividirTest(){
    	assertEquals(2, calculadora.division(20,10));
    }
}
