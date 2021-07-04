package tpGranja.Cuidado;

import tpGranja.Comportamientos.IAplicable;
import tpGranja.Comportamientos.IComercializable;

public abstract class AbstractAplicable implements IAplicable, IComercializable {

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
