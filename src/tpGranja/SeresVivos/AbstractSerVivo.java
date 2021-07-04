package tpGranja.SeresVivos;

import tpGranja.Comportamientos.IAlimentable;
import tpGranja.Comportamientos.IAplicable;
import tpGranja.Comportamientos.IComestible;
import tpGranja.Comportamientos.ICuidable;
import tpGranja.Exceptions.AlimentoIncompatibleException;
import tpGranja.Exceptions.AplicableIncompatibleException;


public abstract class AbstractSerVivo implements ICuidable, IAlimentable {

	// Para evitar cuestiones éticas, los seres vivos no tienen precio, el precio queda
	// para las clases que heredan de esta, como planta o animal. Lo mismo para el método Vender()
	// Esto es en el caso que se agreguen humanos al juego
	
	protected int _contadorComida;
	protected boolean _cuidado;

	@Override
	public abstract void Alimentar(IComestible alimento) throws AlimentoIncompatibleException;

	@Override
	public abstract void Cuidar(IAplicable productoAplicable) throws AplicableIncompatibleException;

	
	protected abstract void Crecer();
}
