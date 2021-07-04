package tpGranja.Animales;

import tpGranja.Comportamientos.IRecolectable;
import tpGranja.Productos.Lana;

public class Oveja extends AbstractAnimal implements IRecolectable {

	public Oveja() {			
		_contadorComida = 0;
		_cuidado = false;
		_pesoMaximo = 50;
		_precio = 90;
	}
	
	@Override
	public Lana Recolectar() {		
		// Cada cuatro alimentadas y una cuidada la oveja da lana
		if(_contadorComida == 4 && _cuidado) {			
			_contadorComida = 0;
			_cuidado = false;
			return new Lana();
		}
		return null;
	}

	
		
	
}
