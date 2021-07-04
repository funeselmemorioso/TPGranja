package tpGranja.Mercado;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tpGranja.Animales.Abeja;
import tpGranja.Animales.AbstractAnimal;
import tpGranja.Animales.Cerdo;
import tpGranja.Animales.Gallina;
import tpGranja.Animales.Oveja;
import tpGranja.Animales.Vaca;
import tpGranja.Comportamientos.IAplicable;
import tpGranja.Comportamientos.IComercializable;
import tpGranja.Cuidado.AbstractAplicableAnimal;
import tpGranja.Cuidado.Fertilizante;
import tpGranja.Cuidado.Vacuna;
import tpGranja.Dinero.Billetera;
import tpGranja.Exceptions.FondosInsuficientesException;
import tpGranja.Plantas.AbstractPlanta;
import tpGranja.Plantas.PlantaSoja;
import tpGranja.Plantas.PlantaTomate;
import tpGranja.Plantas.PlantaTrigo;
import tpGranja.Productos.Tomate;

public class Mercado {
	
	private List<IComercializable> _items;
	
	public Mercado() {
		_items = new ArrayList();
		CargarProductosDefault(); // El mercado ya tendrá incialmente cosas para comprar
	}
	
	public IComercializable Comprar(IComercializable loQueQuieroComprar, Billetera unaBilletera) throws FondosInsuficientesException {				
		// Buscar lo que quiero comprar 
		for (IComercializable item : _items) {
			if(item.getClass().equals(loQueQuieroComprar.getClass())) {				
				Double costoProducto = item.GetPrecio();
				unaBilletera.GetDinero(costoProducto); // Hago el débito
				return item;
			}
		}
		return null;		
	}
	
	public boolean Vender(IComercializable loQueQuieroVender, Billetera unaBilletera) {		
		// Si lo que quiero vender no existen en el mercado, lo agrego, 
		// retorno true (no hay cantidades en el mercado)		
		if(!_items.contains(loQueQuieroVender)) {
			_items.add(loQueQuieroVender);
		}				
		Double precioVenta = loQueQuieroVender.GetPrecio();
		unaBilletera.SetDinero(precioVenta);
		return true;
	}
	
	public List<IComercializable> ListarItemsDisponibles(){
		return _items;
	}
	
	/*public List<IComercializable> ListarPlantasDisponibles(){
		return _items
				  .stream()
				  .filter(c -> AbstractPlanta.class.isAssignableFrom(c.getClass()))
				  .collect(Collectors.toList());
	}
	
	public List<IComercializable> ListarAnimalesDisponibles(){	
		return _items
				  .stream()
				  .filter(c -> AbstractAnimal.class.isAssignableFrom(c.getClass()))
				  .collect(Collectors.toList());			
	}	
	
	public List<IComercializable> ListarProductosDeCuidadoDisponibles(){	
		return _items
				  .stream()
				  .filter(c -> IAplicable.class.isAssignableFrom(c.getClass()))
				  .collect(Collectors.toList());			
	}*/	
	
	public <T> List<T> ListarGenerico(Class<T> c) {
	    return _items.stream()
	            .filter(c::isInstance)
	            .map(c::cast)
	            .collect(Collectors.toList());
	}
	
	
	private void CargarProductosDefault() {
		// Animales iniciales
		_items.add(new Cerdo());		
		_items.add(new Gallina());
		_items.add(new Oveja());
		_items.add(new Abeja());	
		_items.add(new Vaca());	
		
		// Plantas inciales
		_items.add(new PlantaSoja());
		_items.add(new PlantaTomate());
		_items.add(new PlantaTrigo());
		
		// Productos par el cuidado
		_items.add(new Fertilizante());
		_items.add(new Vacuna());
		
		// Productos para comer
		_items.add(new Tomate());
				
		
	}
	
}
