package tpGranja.Productos;

import tpGranja.Comportamientos.IComestible;

public class Tomate extends AbstractProducto implements IComestible{

	public Tomate() {
		_precio = 10.0;
	}

}
