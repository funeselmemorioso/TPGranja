package tpGranja.Comportamientos;

import tpGranja.Exceptions.AlimentoIncompatibleException;

public interface IAlimentable {

	// Hace que los seres vivos se puedan alimentar	
	
	void Alimentar(IComestible alimento) throws AlimentoIncompatibleException;
}
