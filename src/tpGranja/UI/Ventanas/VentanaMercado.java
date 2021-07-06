package tpGranja.UI.Ventanas;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import tpGranja.Animales.AbstractAnimal;
import tpGranja.Comportamientos.IAplicable;
import tpGranja.Comportamientos.IComercializable;
import tpGranja.Comportamientos.IComestible;
import tpGranja.Cuidado.AbstractAplicable;
import tpGranja.Exceptions.AlimentoIncompatibleException;
import tpGranja.Exceptions.AplicableIncompatibleException;
import tpGranja.Exceptions.FondosInsuficientesException;
import tpGranja.Mercado.Mercado;
import tpGranja.Plantas.AbstractPlanta;
import tpGranja.Productos.AbstractProducto;
import tpGranja.SeresVivos.AbstractSerVivo;
import tpGranja.Terreno.Parcela;

public class VentanaMercado extends JFrame {

	private int _idParcela;
	
	public VentanaMercado(String modoMercado, int idParcela, String operacion) {	
		
		_idParcela = idParcela;	
		
		if(operacion.equals("compra")) 
		{
			GenerarMercadoCompra(modoMercado, idParcela);
			return;
		}
		GenerarMercadoVenta(modoMercado, idParcela);	
	}	
	
	private void GenerarMercadoVenta(String modoMercado, int  idParcela) {
		
		// se muestra boton de confirmar
		JOptionPane.showMessageDialog(null, "Se liquidará la parcela " + idParcela + " en el mercado..." );
		
		
		
		Parcela pa = VentanaMain.parcelas.get(_idParcela-1);
		IComercializable contenidoParcela = (IComercializable)pa.GetcontenidoParcela(); 	
		VentanaMain.mercado.Vender(contenidoParcela, VentanaMain.billetera);
		
		pa.SetcontenidoParcela(null);
		
		VentanaMain.parcelas.set(_idParcela-1, pa);		
		VentanaMain.Refresh();
		this.dispose(); 
		
		
		
		
	}
	
	private void GenerarMercadoCompra(String modoMercado, int  idParcela) {
		
		List<String[]> animales = ListarMercado(AbstractAnimal.class);
		List<String[]> plantas = ListarMercado(AbstractPlanta.class);
		List<String[]> aplicables = ListarMercado(AbstractAplicable.class);	
		List<String[]> comidas = ListarMercado(AbstractProducto.class);	
				
		String column[]={"Nombre","Precio","Categoria","Clase"}; 				
		List<String[]> values = new ArrayList<String[]>();
		
		
		switch(modoMercado) {
			case "agregarParcela":
				    this.setTitle("Granja - Mercado - Agregar a parcela # " + idParcela);	
					values.addAll(animales);
					values.addAll(plantas);
				break;
			case "todo":
				    this.setTitle("Granja - Mercado - General");	
					values.addAll(animales);
					values.addAll(plantas);				
					values.addAll(aplicables);
					values.addAll(comidas);
				break;
			case "productosDeCuidado":
				    this.setTitle("Granja - Mercado - Cuidar parcela # " + idParcela);
					values.addAll(aplicables);
				break;
			case "comida":
			    this.setTitle("Granja - Mercado - Comprar comida para parcela # " + idParcela);
				values.addAll(comidas);
			break;
			default:
				break;
		}
		
		GenerarTabla(values, column);
	}
	
	// Metodo generico para trae cualquier cosa del mercado
	private <T> List<String[]> ListarMercado(Class<T> c) {
		List<IComercializable> items = (List<IComercializable>) VentanaMain.mercado.ListarGenerico(c);
		List<String[]> values = new ArrayList<String[]>();
		
		for (int i = 0; i < items.size(); i++) {
			IComercializable generico = (IComercializable)items.get(i);         	
        	String nombre = generico.getClass().getName().split("\\.")[2];
        	String tipo = generico.getClass().getName().split("\\.")[1];
        	String precio = String.valueOf(generico.GetPrecio());
        	String clase = generico.getClass().getName();
            values.add(new String[] {nombre, precio, tipo, clase}); 
        }		
		return values;		
	}
	
	// Genera la JTable
	private void GenerarTabla(List<String[]> values, String column[]) {
	     TableModel tableModel = new DefaultTableModel(values.toArray(new Object[][] {}), column);
         JTable table = new JTable(tableModel);
		 JScrollPane sp = new JScrollPane(table);    
		 this.add(table);
		 
		 // Doble click, obtengo la clase que quiero comprar
		 table.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent mouseEvent) {
		        JTable table =(JTable) mouseEvent.getSource();
		        Point point = mouseEvent.getPoint();
		        int row = table.rowAtPoint(point);
		        if (mouseEvent.getClickCount() == 2 && table.getSelectedRow() != -1) {
		            String nombreClase = (String)table.getModel().getValueAt(row, 3);
		            try 
		            {
		            	System.out.println("Quiere comprar -> " + nombreClase);
						Adquirir(nombreClase);						
					} 
		            catch (FondosInsuficientesException | InstantiationException | IllegalAccessException | ClassNotFoundException | AlimentoIncompatibleException | AplicableIncompatibleException e) 
		            {				
						JOptionPane.showMessageDialog(null, "Excepción -> " + e.toString() );
						
					}
		        }
		    }
		});
	}
	
	// Compra un producto del mercado
	private void Adquirir(String nombreClase) throws FondosInsuficientesException, InstantiationException, IllegalAccessException, ClassNotFoundException, AlimentoIncompatibleException, AplicableIncompatibleException {
				
		// Convierte el string en un objeto
		Object o = Class.forName(nombreClase).newInstance(); 		
		IComercializable objetoAComprar  = (IComercializable)o;			
				
		
		// Tiene que darse cuenta si es una comida o un ser vivo o un aplicable
		if(AbstractSerVivo.class.isAssignableFrom(objetoAComprar.getClass())) {
			// Compra
			AbstractSerVivo compra = (AbstractSerVivo)VentanaMain.mercado.Comprar(objetoAComprar, VentanaMain.billetera);
			
			// Llena la parcela
			Parcela pa = VentanaMain.parcelas.get(_idParcela-1);
			pa.SetcontenidoParcela(compra);		
			VentanaMain.parcelas.set(_idParcela-1, pa);		
			VentanaMain.Refresh();
			this.dispose(); // Cierra la ventana de agregar
		}
		else if(IComestible.class.isAssignableFrom(objetoAComprar.getClass())) {
		
			IComestible compra = (IComestible)VentanaMain.mercado.Comprar(objetoAComprar, VentanaMain.billetera);
			
			Parcela pa = VentanaMain.parcelas.get(_idParcela-1);
			AbstractSerVivo asv = pa.GetcontenidoParcela();
			asv.Alimentar(compra);
			pa.SetcontenidoParcela(asv);
			VentanaMain.parcelas.set(_idParcela-1, pa);		
			VentanaMain.Refresh();
			JOptionPane.showMessageDialog(null, "Alimentado correctamente !");
			this.dispose(); // Cierra la ventana de agregar
			
		}
		else if(IAplicable.class.isAssignableFrom(objetoAComprar.getClass())) {
			
			IAplicable compra = (IAplicable)VentanaMain.mercado.Comprar(objetoAComprar, VentanaMain.billetera);
			
			Parcela pa = VentanaMain.parcelas.get(_idParcela-1);
			AbstractSerVivo asv = pa.GetcontenidoParcela();
			asv.Cuidar(compra);
			pa.SetcontenidoParcela(asv);
			VentanaMain.parcelas.set(_idParcela-1, pa);		
			VentanaMain.Refresh();
			JOptionPane.showMessageDialog(null, "Cuidado correctamente !");
			this.dispose(); // Cierra la ventana de agregar
		}
		else
		{
			JOptionPane.showMessageDialog(null, "El producto no es el adecuado " );
		}
		
		
	}
	
	
	
}
