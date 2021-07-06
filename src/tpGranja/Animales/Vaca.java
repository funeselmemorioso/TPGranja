package tpGranja.Animales;

import tpGranja.Comportamientos.IRecolectable;
import tpGranja.Productos.Leche;

public class Vaca extends AbstractAnimal implements IRecolectable {	

	public Vaca() {	
		_contadorComida = 0;
		_cuidado = false;
		_pesoMaximo = 100;
		_precio = 30;
	}
	
	@Override
	public Leche Recolectar() {
		// Cada 1 alimentadas y 1 cuidadas, la vaca da leche	
		if(_contadorComida >= 1 && _cuidado) {			
			_contadorComida = 0;
			_cuidado = false;	
			return new Leche();
		}
		return null;
	}	

}
