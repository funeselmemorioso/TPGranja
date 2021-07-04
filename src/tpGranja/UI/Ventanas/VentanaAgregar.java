/*package tpGranja.UI.Ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import tpGranja.Animales.AbstractAnimal;
import tpGranja.Animales.Vaca;
import tpGranja.Comportamientos.IComercializable;
import tpGranja.Exceptions.FondosInsuficientesException;
import tpGranja.Mercado.Mercado;
import tpGranja.Plantas.AbstractPlanta;
import tpGranja.SeresVivos.AbstractSerVivo;
import tpGranja.Terreno.Parcela;

public class VentanaAgregar extends JFrame {

	private int _idParcela;
	
	public VentanaAgregar(int idParcela) throws FondosInsuficientesException {		
		
		this.setTitle("Granja - Agregar a parcela # " + idParcela);	
		_idParcela = idParcela;		
		BuscarItemsMercado(); 
	}
	
	private void Adquirir(String nombreClase) throws FondosInsuficientesException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		// Convierte el string en un objeto
		Object o = Class.forName(nombreClase).newInstance(); 		
		IComercializable objetoAComprar  = (IComercializable)o;			
		
		// Compra
		AbstractSerVivo compra = (AbstractSerVivo)VentanaMain.mercado.Comprar(objetoAComprar, VentanaMain.billetera);
		
		// Llena la parcela
		Parcela pa = VentanaMain.parcelas.get(_idParcela-1);
		pa.SetcontenidoParcela(compra);		
		VentanaMain.parcelas.set(_idParcela-1, pa);		
		VentanaMain.Refresh();
		this.dispose(); // Cierra la ventana de agregar
	}
	
	private void BuscarItemsMercado() {
		
		List<IComercializable> animales = VentanaMain.mercado.ListarAnimalesDisponibles();
		List<IComercializable> plantas = VentanaMain.mercado.ListarPlantasDisponibles();
		
		String column[]={"Nombre","Precio","Categoria","Clase"}; 		
		List<String[]> values = new ArrayList<String[]>();

        for (int i = 0; i < animales.size(); i++) {
        	AbstractAnimal animal = (AbstractAnimal)animales.get(i);         	
        	String nombre = animal.getClass().getName().split("\\.")[2];
        	String tipo = animal.getClass().getName().split("\\.")[1];
        	String precio = String.valueOf(animal.GetPrecio());
        	String clase = animal.getClass().getName();
            values.add(new String[] {nombre, precio, tipo, clase}); 
        }
		
        for (int i = 0; i < plantas.size(); i++) {
        	AbstractPlanta planta = (AbstractPlanta)plantas.get(i);
        	String nombre = planta.getClass().getName().split("\\.")[2];
        	String tipo = planta.getClass().getName().split("\\.")[1];
        	String precio = String.valueOf(planta.GetPrecio());
        	String clase = planta.getClass().getName();
            values.add(new String[] {nombre, precio, tipo, clase}); 
          
        }
        
        GenerarTabla(values, column);   
	}
	
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
		            catch (FondosInsuficientesException | InstantiationException | IllegalAccessException | ClassNotFoundException e) 
		            {						
						e.printStackTrace();
					}
		        }
		    }
		});
}
	
	
}*/
