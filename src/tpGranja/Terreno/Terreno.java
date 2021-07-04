package tpGranja.Terreno;

import java.util.ArrayList;
import java.util.List;

import tpGranja.Exceptions.DemasiadasParcelasException;

public class Terreno {

	// Paar este juego el terreno solo puede tener 9 parcelas (es para que quede cuadrado cuando tenga UI 3x3)
	
	private List<Parcela> _parcelas;
	private final int CANTIDAD_MAXIMA_PARCELAS = 9;	
		
	public Terreno() {		
		_parcelas = new ArrayList<Parcela>();		
	}
	
	public void AgregarParcela(Parcela parcela) throws DemasiadasParcelasException {
		if(_parcelas.size() <= CANTIDAD_MAXIMA_PARCELAS) {
			_parcelas.add(parcela);
		}
		else
		{
			throw new DemasiadasParcelasException();
		}
	}
	
	public List<Parcela> ListarParcelas(){
		return _parcelas;
	}
	
	public int ContarParcelas(){
		return _parcelas.size();
	}
	
	
}
