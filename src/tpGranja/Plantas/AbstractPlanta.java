package tpGranja.Plantas;

import tpGranja.Comportamientos.IAplicable;
import tpGranja.Comportamientos.IComercializable;
import tpGranja.Comportamientos.IComestible;
import tpGranja.Comportamientos.ICosechable;
import tpGranja.Cuidado.AbstractAplicablePlanta;
import tpGranja.Exceptions.AlimentoIncompatibleException;
import tpGranja.Exceptions.AplicableIncompatibleException;
import tpGranja.Naturaleza.Agua;
import tpGranja.SeresVivos.AbstractSerVivo;

public abstract class AbstractPlanta extends AbstractSerVivo implements IComercializable, ICosechable {

	// Todas las plantas son comercializables
	
	 
	protected int _edad; // Si es cero, es semilla
	protected int _edadMaxima;
	protected double _precio; // La planta antes de cosecharse se puede vender
	
	
	
	@Override
	public void Cuidar(IAplicable aplicable) throws AplicableIncompatibleException {		
		
		boolean esInstanciaDeAplicablePlanta = AbstractAplicablePlanta.class.isAssignableFrom(aplicable.getClass()); 
		
		if(!esInstanciaDeAplicablePlanta) {
			throw new AplicableIncompatibleException();
		}		
		
		_cuidado = true;		
	}	
	
	@Override
	public double GetPrecio() {
		return _precio * _edad;
	}
	
	@Override
	public void SetPrecio(double precio) {
		_precio = precio;
	}
	
	@Override
	public void Alimentar(IComestible alimento) throws AlimentoIncompatibleException {
		// Solo pueden ser alimentadas con agua
		boolean esAgua = Agua.class.isAssignableFrom(alimento.getClass()); 		
		if(!esAgua) {
			throw new AlimentoIncompatibleException();
		}	
		//_contadorComida += 1;	
		Crecer();
	}
	
	@Override
	protected void Crecer() {
		if(_edad < _edadMaxima) {
			_edad++;
		}		
	}
	
	

	
	
}
