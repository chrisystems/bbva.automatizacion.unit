package com.bbva.automatizacion.unit.CalculadoraMock;

public class Suma {

	ValidarNumero validarNumero;
	Mensaje mensaje;

	public Suma(ValidarNumero validarNumero) {
		this.validarNumero = validarNumero;
	}
	
    public Suma(ValidarNumero validarNumero, Mensaje mensaje){
    	this.validarNumero = validarNumero;
        this.mensaje = mensaje;
    }

	public int suma(Object n1, Object n2) {
		if (validarNumero.validar(n1) && validarNumero.validar(n2)) {
			return (Integer) n1 + (Integer) n2;
		} else {
			return 0;
		}
	}

	public int sumaInt(Object a) {
		return validarNumero.doubleToInt(a) + validarNumero.doubleToInt(a);
	}
	
    public void sumaMensaje(Object a, Object b){
        if(validarNumero.validar(a) && validarNumero.validar(b)){
            int result = (Integer)a + (Integer)b;
            mensaje.showMessage(result);
        }else {
            mensaje.showMessageError();
        }
    }
}
