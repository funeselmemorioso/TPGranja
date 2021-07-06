/*package tpGranja.Almacenamiento;

import java.util.HashMap;

import tpGranja.Comportamientos.IComercializable;

public class Galpon {

	private HashMap<Integer, IComercializable> _items;
	private int _indice;
	
	public Galpon() {
		_items = new HashMap<Integer, IComercializable>();
		_indice = 0;
	}
	
	public HashMap<Integer, IComercializable> ListarItemsDisponibles(){
		return _items;
	}
	
	public void RemoverItem(int key){		
		_items.remove(key);
	}
	
	public void AgregarItem(IComercializable algo){
		_indice++;
		_items.put(_indice, algo);
	}
	
	public IComercializable GetItemByKey(int key){
		return _items.get(key);
	}
	
	
}*/
