package tpGranja.MiGranja;

import tpGranja.Terreno.Terreno;

public class Granja {
	
	// Esta clase todavía no se usa, tengo que ver si la dejo o la saco
	
	// Singleton granja
	
	private static Granja _granja;	
	private Terreno _terreno;	
	
	private Granja() {		
	}
	
	public static Granja Crear() {	
		return (_granja == null? new Granja() : _granja); 
	}
	
	public void SetTerreno(Terreno terreno) {
		_terreno = terreno;
	}
	
	public Terreno GetTerreno() {
		return _terreno;
	}	
	
	
	
}
