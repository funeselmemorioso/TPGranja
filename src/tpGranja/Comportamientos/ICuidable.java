package tpGranja.Comportamientos;

import tpGranja.Exceptions.AplicableIncompatibleException;

public interface ICuidable {

	// Hace que los seres vivos puedan cuidarse	
	
	
	// Si el aplicable no es para el ser vivo correcto, no se puede aplicar y lanza exception
	// EJ: intentar fertilizar una gallina	
	
	void Cuidar(IAplicable productoAplicable) throws AplicableIncompatibleException;
}
