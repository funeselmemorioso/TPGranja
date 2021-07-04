package tpGranja.Plantas;



import tpGranja.Productos.Soja;

public class PlantaSoja extends AbstractPlanta {

	// No es comible por los animales por eso no implementa IComestible

	public PlantaSoja() {
		_precio = 1.0;
		_edad = 1;
		_edadMaxima = 2;
	}
	
	@Override
	public Soja Cosechar() {
		// Para cosechar debe tener edad suficiente y haber sido cuidada al menos una vez
		if(_edad >= _edadMaxima && _cuidado) {
			return new Soja();
		}
		return null;
	}
	
	
	

}
