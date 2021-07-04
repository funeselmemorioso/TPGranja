package tpGranja.Animales;

import tpGranja.Comportamientos.IRecolectable;
import tpGranja.Productos.Huevo;

public class Gallina extends AbstractAnimal implements IRecolectable {
	
	public Gallina() {		
		_contadorComida = 0;
		_cuidado = false;
		_pesoMaximo = 3;
		_precio = 25;
	}
	
	@Override
	public Huevo Recolectar() {	
		// Cada tres alimentadas y una cuidada, da un huevo
		if(_contadorComida == 3 && _cuidado) {			
			_contadorComida = 0;
			_cuidado = false;
			return new Huevo();
		}
		return null;
	}
	
	

	

}
