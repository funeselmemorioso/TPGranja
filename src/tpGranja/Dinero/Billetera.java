package tpGranja.Dinero;

import tpGranja.Exceptions.FondosInsuficientesException;

public class Billetera {

	private static double _dinero;
	
	public Billetera(double dineroInicial) {
		_dinero = dineroInicial;
	}
	
	public void SetDinero(double dinero) {
		_dinero = _dinero + dinero;
	}
	
	public double GetTotalDinero() {
		return _dinero;
	}
	
	public double GetDinero(double cuantoDinero) throws FondosInsuficientesException {
		if(cuantoDinero<=_dinero) {
			_dinero = _dinero - cuantoDinero;
			return cuantoDinero;
		}
		throw new FondosInsuficientesException();
	}
}
