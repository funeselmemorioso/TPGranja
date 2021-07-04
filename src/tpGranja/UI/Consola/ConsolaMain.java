package tpGranja.UI.Consola;

import java.util.List;
import tpGranja.Animales.Cerdo;
import tpGranja.Animales.Gallina;
import tpGranja.Animales.Vaca;
import tpGranja.Comportamientos.IAplicable;
import tpGranja.Comportamientos.IComercializable;
import tpGranja.Comportamientos.IComestible;
import tpGranja.Comportamientos.ICuidable;
import tpGranja.Comportamientos.IRecolectable;
import tpGranja.Cuidado.Fertilizante;
import tpGranja.Cuidado.Vacuna;
import tpGranja.Dinero.Billetera;
import tpGranja.Exceptions.AplicableIncompatibleException;
import tpGranja.Exceptions.DemasiadasParcelasException;
import tpGranja.Exceptions.FondosInsuficientesException;
import tpGranja.Mercado.Mercado;
import tpGranja.MiGranja.Granja;
import tpGranja.Naturaleza.AbstractNaturaleza;
import tpGranja.Naturaleza.Agua;
import tpGranja.Plantas.PlantaSoja;
import tpGranja.Plantas.PlantaTrigo;
import tpGranja.Productos.AbstractProducto;
import tpGranja.Productos.Soja;
import tpGranja.Productos.Trigo;
import tpGranja.Terreno.Parcela;
import tpGranja.Terreno.Terreno;

public class ConsolaMain {	
	
	public static void main(String[] args) throws AplicableIncompatibleException {	
				
		
		
		
		// ideas pasar una tarjeta de credito al mercado en lugar de billetera
		// en la segunda parte hacer que se debite y en la priemra tambien
		
		
		
		
		System.out.println("INICIO");
		try 
		{
			// Creo un mercado donde poder comprar y vender 
			Mercado elMercado = new Mercado();	
			
			// Creo el terreno de la granja
			Terreno terrenoDeLaGranja = new Terreno();		
			
			// Creo una billetera que es donde guardaré el dinero (con saldo incial 1000)
			Billetera miBilletera = new Billetera(1000);
			System.out.println("Mi saldo es -> " + miBilletera.GetTotalDinero());
			
			// Creo una parcela (podría crear mas, en este caso creo una para poner una vaca)
			Parcela parcelaVaca = new Parcela(1);		
			
			
			// Listo los productos del mercado (se supone que los veo en una lista y elijo)			
			List<IComercializable> items = elMercado.ListarItemsDisponibles();
			System.out.println("Lista de productos que hay en el mercado");
			for(IComercializable p : items) {
				System.out.println(p);
			}
			
			// Elijo algo que comprar de la lista			
			try 
			{				
				
				// En el mercado se compra diciendo que objeto quiero y 
				// enviando la billetera (cuenta) para que se debite,
				// si está en la lista me lo retornará
				Vaca vaca = (Vaca)elMercado.Comprar(new Vaca(), miBilletera);				
				System.out.println("Mi saldo es -> " + miBilletera.GetTotalDinero());
				
				
				// Agrego la vaca a la parcela				
				parcelaVaca.SetcontenidoParcela(vaca);				
				
			} 
			catch (FondosInsuficientesException e) 
			{
				
				e.printStackTrace();
			}		
			
			// Agrego la parcela al terreno, esta parcela ya tiene una vaca que compré, pero
			// podría agregarse luego también
			terrenoDeLaGranja.AgregarParcela(parcelaVaca);
			
			
			
			
			// **************************************************************************
			// En este punto tengo mi terreno con una parcela y una vaca en la parcela
			// **************************************************************************
			
			
			
			// Voy a recolectar la leche de la vaca, pero como nunca la alimenté ni cuidé, 
			// no me dará nada			
		    Vaca miVaca = (Vaca)terrenoDeLaGranja.ListarParcelas().get(0).GetcontenidoParcela();			
		    AbstractProducto leche = (AbstractProducto)miVaca.Recolectar();			
			System.out.println("La vaca me dio -> " + leche); // La vaca da null leche
			
			
			// Ahora la alimentaré, para eso debo ir a comprar un comestible al mercado
			// Pido trigo en el mercado		
			IComestible comidaParaLaVaca = (IComestible)elMercado.Comprar(new Trigo(), miBilletera);			
			System.out.println("Compré -> " + comidaParaLaVaca); // Que compré ?			
			System.out.println("Mi saldo es -> " + miBilletera.GetTotalDinero());
			
			
			// Le doy el trigo a la vaca para que coma
			miVaca.Alimentar(comidaParaLaVaca);
			
			
			// Ahora tengo que cuidar a la vaca, me voy al mercado y accidentalmente compro
			// un fertilizante y se lo doy			
			IAplicable productoParaCuidarLaVaca = (IAplicable)elMercado.Comprar(new Fertilizante(), miBilletera);			
			System.out.println("Compré -> " + productoParaCuidarLaVaca); // Que compré ?			
			System.out.println("Mi saldo es -> " + miBilletera.GetTotalDinero());
			
			
			//miVaca.Cuidar(productoParaCuidarLaVaca); // <-- Esto me dará exception ya que no se puede
			// dar un fertilizante a una vaca
			
			
			
			// Nuevamente voy al mercado y compro una vacuna para la vaca			
			productoParaCuidarLaVaca = (IAplicable)elMercado.Comprar(new Vacuna(), miBilletera);			
			System.out.println("Compré -> " + productoParaCuidarLaVaca); // Que compré ?			
			System.out.println("Mi saldo es -> " + miBilletera.GetTotalDinero());
			
			miVaca.Cuidar(productoParaCuidarLaVaca); // <- ahora si está cuidada
			
			
			// Vuelvo a pedirle leche a la vaca
			leche = null; // Limpio la variable por las dudas (igual ya estaba en null)
			leche = (AbstractProducto)miVaca.Recolectar();			
			System.out.println("La vaca me dio -> " + leche); // Esta vez si me dio leche
			
			
			// *************************************************************************************
			// La vaca no me dará leche de nuevo si no le doy alimento y cuidado una vez mas,
			// Sin embargo irá ganando peso hasta alcanzar el máximo y podré venderla eventualmente
			// *************************************************************************************
			
			
			// Ahora podría ir al mercado a vender la leche que me dio la vaca
			elMercado.Vender(leche, miBilletera);	
			System.out.println("Mi saldo es -> " + miBilletera.GetTotalDinero());
			
			
			// En este punto en el mercado hay un producto mas que es la leche y podría ser comprado nuevamente
		    items = null;
			items = elMercado.ListarItemsDisponibles();
			System.out.println("Lista de productos que hay en el mercado");
			for(IComercializable p : items) {
				System.out.println(p);
			}

			
			
			// Podría darle agua mi vaca, la cual es gratis y es un comestible
			miVaca.Alimentar(new Agua());
			
			
			
			// Finalmente podria vender la vaca
			elMercado.Vender(miVaca, miBilletera);
			System.out.println("Mi saldo es -> " + miBilletera.GetTotalDinero());
			
			
			
			//********************************************************************
			// Hasta aqui todo el ciclo que puede tener un animal
			//********************************************************************
			
			
			// *************************************************
			// Ahora plantas
			// *************************************************
			
			
			PlantaSoja unaPlantaDeSoja = (PlantaSoja)elMercado.Comprar(new PlantaSoja(), miBilletera);		
			System.out.println("Mi saldo es -> " + miBilletera.GetTotalDinero());
			
			
			Soja soja = unaPlantaDeSoja.Cosechar();			
			System.out.println("soj -> " + soja); // No hay cosecha porque no regue ni cuide
			
			
						
			unaPlantaDeSoja.Alimentar(new Agua());
			
			Fertilizante f = (Fertilizante)elMercado.Comprar(new Fertilizante(), miBilletera);
			
			unaPlantaDeSoja.Cuidar(f);
			
			
			soja = unaPlantaDeSoja.Cosechar();	
			System.out.println("soj -> " + soja); // Ahora si hay cosecha
			
			elMercado.Vender(soja, miBilletera); // Vendo en el mercado la cosecha de soja
			System.out.println("Mi saldo es -> " + miBilletera.GetTotalDinero());
			
			
			
			
	
			
		} 
		catch (DemasiadasParcelasException e) 
		{			
			e.printStackTrace();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		
		
	}
	
}

