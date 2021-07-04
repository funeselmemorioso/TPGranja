package tpGranja.Plantas;


import tpGranja.Productos.Tomate;

public class PlantaTomate extends AbstractPlanta {

	// no es comible por los animales por eso no implementa IComestible

	public PlantaTomate() {
		_precio = 3.0;
		_edad = 1;
		_edadMaxima = 8;
	}

	@Override
	public Tomate Cosechar() {
		// Para cosechar debe tener edad suficiente y haber sido cuidada al menos una vez
		if(_edad >= _edadMaxima && _cuidado) {
			return new Tomate();
		}
		return null;
	}
	

	

}
