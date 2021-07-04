package tpGranja.Productos;

import tpGranja.Comportamientos.IComercializable;

public abstract class AbstractProducto implements IComercializable {	
	
	protected double _precio;	
	
	@Override
	public double GetPrecio() {
		return _precio;
	}
	
	@Override
	public void SetPrecio(double precio) {
		_precio = precio;
	}
	
}
