package tpGranja.Animales;

import tpGranja.Comportamientos.IRecolectable;
import tpGranja.Productos.Miel;

public class Abeja extends AbstractAnimal  implements IRecolectable {

	public Abeja() {
		_contadorComida = 0;
		_cuidado = false;	
		_precio = 39;
	}
	
	@Override
	public Miel Recolectar() {
		// Cada cinco alimentadas y una cuidada, da miel
		if(_contadorComida == 5 && _cuidado) {			
			_contadorComida = 0;
			_cuidado = false;
			return new Miel();
		}
		return null;
	}
	
	
	

}
