package tpGranja.Plantas;

import tpGranja.Comportamientos.IComestible;
import tpGranja.Productos.Trigo;


public class PlantaTrigo extends AbstractPlanta implements IComestible{

	// El trigo es cosechable, lo comen los animales (Es comestible)

	
	public PlantaTrigo() {
		_precio = 2.0;
		_edad = 1;
		_edadMaxima = 10;
	}

	@Override
	public Trigo Cosechar() {
		// Para cosechar debe tener edad suficiente y haber sido cuidada al menos una vez
		if(_edad >= _edadMaxima && _cuidado) {
			return new Trigo();
		}
		return null;
	}
	

}
