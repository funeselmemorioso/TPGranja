package tpGranja.Terreno;

import tpGranja.SeresVivos.AbstractSerVivo;

public class Parcela {
	
	// Una parcela solo puede contener un tipo de ser vivo
	
	private AbstractSerVivo _contenidoParcela;
	private int _id;
	
	public Parcela(int id) {
		_id = id;
	}
	
	public int GetId() {
		return _id;
	}
	
	
	public AbstractSerVivo GetcontenidoParcela() {
		return _contenidoParcela;
	}


	public void SetcontenidoParcela(AbstractSerVivo contenidoParcela) {
		_contenidoParcela = contenidoParcela;
	}
}
