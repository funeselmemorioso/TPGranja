package tpGranja.Animales;

import tpGranja.Comportamientos.IAplicable;
import tpGranja.Comportamientos.IComercializable;
import tpGranja.Comportamientos.IComestible;
import tpGranja.Cuidado.AbstractAplicableAnimal;
import tpGranja.Exceptions.AplicableIncompatibleException;
import tpGranja.SeresVivos.AbstractSerVivo;

public abstract class AbstractAnimal extends AbstractSerVivo implements IComercializable {

	private int _peso;
	protected int _pesoMaximo;
	protected double _precio;	
	
	@Override
	protected void Crecer() {
		if(_peso <= _pesoMaximo) {
			_peso++;
		}
	}
	
	protected int Pesar() {
		return _peso;
	}
	
	@Override
	public void Alimentar(IComestible alimento) {			
		_contadorComida++;		
		Crecer();			
	}
	
	@Override
	public void Cuidar(IAplicable aplicable) throws AplicableIncompatibleException {		
		
		// Existen aplicables que son para animales y otros para plantas, por ejemplo no se puede
		// fertilizar un cerdo
		
		boolean esInstanciaDeAplicableAnimal = AbstractAplicableAnimal.class.isAssignableFrom(aplicable.getClass()); 
		
		if(!esInstanciaDeAplicableAnimal) {
			throw new AplicableIncompatibleException();
		}				
		_cuidado = true;		
	}	
	
	@Override
	public double GetPrecio() {
		return _precio;
	}
	
	@Override
	public void SetPrecio(double precio) {
		_precio = precio;
	}
	
	
	
}
