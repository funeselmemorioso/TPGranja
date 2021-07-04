package tpGranja.UI.Ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tpGranja.Almacenamiento.Galpon;
import tpGranja.Comportamientos.ICosechable;
import tpGranja.Comportamientos.IRecolectable;
import tpGranja.Dinero.Billetera;
import tpGranja.Exceptions.AlimentoIncompatibleException;
import tpGranja.Mercado.Mercado;
import tpGranja.Naturaleza.Agua;
import tpGranja.Plantas.AbstractPlanta;
import tpGranja.Productos.AbstractProducto;
import tpGranja.SeresVivos.AbstractSerVivo;
import tpGranja.Terreno.Parcela;

class PanelBotones extends JPanel {
	
	public PanelBotones() {
		this.setLayout(new GridLayout(1, 3));		
		
		JButton btnMercado = new JButton("Ver Mercado");	
		JButton btnBilletera = new JButton("Ver Billetera");
		
		btnMercado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaMercado v = new VentanaMercado("todo",0, "compra");				
				v.setVisible(true);				
				v.setSize(500, 500);
				v.setTitle("Granja - Mercado");	
			}			
		});
		
		btnBilletera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaBilletera v = new VentanaBilletera();				
				v.setVisible(true);				
				v.setSize(600, 200);
				v.setTitle("Granja - Billetera");	
			}			
		});		
		
		this.add(btnMercado);
		this.add(btnBilletera);
	}
}



class PanelParcela extends JPanel{
	
	public PanelParcela(Parcela parcela) {
		
		// La parcela no tiene nada
		if(parcela.GetcontenidoParcela() == null) {
			JButton b1 = new JButton("Agregar");
			this.add(b1, BorderLayout.NORTH);	
			
			b1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
					try 
					{
						/*VentanaAgregar v = new VentanaAgregar(parcela.GetId());				
						v.setVisible(true);						
						v.setSize(500, 500);*/
						
						VentanaMercado v = new VentanaMercado("agregarParcela",parcela.GetId(), "compra");				
						v.setVisible(true);				
						v.setSize(500, 500);
						v.setTitle("Granja - Mercado - Parcela # " +parcela.GetId());	
						
						
					}
					catch(Exception ex) 
					{						
					}					
				}			
			});	
		}
		else
		{
			// La parcela tiene algún contenido (animal o planta)
			JButton btnAlimentar = new JButton("Alimentar");
			JButton btnCuidar = new JButton("Cuidar");
			JButton btnVender = new JButton("Vender");
			this.add(btnAlimentar, BorderLayout.NORTH);	
			this.add(btnCuidar, BorderLayout.NORTH);	
			this.add(btnVender, BorderLayout.NORTH);	
			
			if(parcela.GetcontenidoParcela() instanceof AbstractPlanta) {
				// Si es una planta, el unico alimento es agua
				btnAlimentar.setText("Regar");
				
				btnAlimentar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {					
						AbstractPlanta p = (AbstractPlanta)parcela.GetcontenidoParcela();
						try {
							p.Alimentar(new Agua());
							JOptionPane.showMessageDialog(null, "La planta se regó correctamente");
						} 
						catch (AlimentoIncompatibleException e1) {					
							e1.printStackTrace();
						}
						parcela.SetcontenidoParcela(p);				
					}			
				});	
				
				
			}
			else
			{
				btnAlimentar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {					
						VentanaMercado v = new VentanaMercado("comida", parcela.GetId(), "compra");				
						v.setVisible(true);				
						v.setSize(500, 500);					
					}			
				});	
			}
			
			btnCuidar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
					VentanaMercado v = new VentanaMercado("productosDeCuidado", parcela.GetId(), "compra");				
					v.setVisible(true);				
					v.setSize(500, 500);
				}			
			});	
			btnVender.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
					VentanaMercado v = new VentanaMercado("", parcela.GetId(), "venta");				
					//v.setVisible(true);				
					//v.setSize(500, 500);				
					
				}			
			});	
			
			
			if(parcela.GetcontenidoParcela() instanceof ICosechable) {
				btnVender.setText("Cosechar");
			}
			
			
			
			// Para mostrar el botón de recolectar, debo preguntar si la parcela tiene un
			// animal o planta que implemente IRecolectable
			if(parcela.GetcontenidoParcela() instanceof IRecolectable) {
				JButton b4 = new JButton("Recolectar");
				this.add(b4, BorderLayout.NORTH);	
				b4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {					
						
						IRecolectable serVivoParcela = (IRecolectable)parcela.GetcontenidoParcela();						
						AbstractProducto p = serVivoParcela.Recolectar();
						
						if(p == null){
							JOptionPane.showMessageDialog(null, "No hay nada que recolectar, prueba alimentando o cuidando");							
						}
						else
						{
							String nombreProducto = p.getClass().getName();
							Double precioProducto = p.GetPrecio();
							VentanaMain.mercado.Vender(p, VentanaMain.billetera);
							JOptionPane.showMessageDialog(null, "Felicidades, se venderá en el "
									+ "mercado: " + nombreProducto + " por $ " +precioProducto.toString());
						}
					}			
				});	
			}
			
			
			
			// Mostrar la imagen
			try 
			{ 
				String nombreClase = parcela.GetcontenidoParcela().getClass().getName().split("\\.")[2].toLowerCase();
			    System.out.println(nombreClase);
				BufferedImage image = ImageIO.read(new File("imagenes/"+nombreClase+".png"));	
			    JLabel picLabel = new JLabel(new ImageIcon(image));
			    picLabel.setMaximumSize(new Dimension(10, 10));
			    this.add(picLabel, BorderLayout.CENTER);		          
	        } 
		    catch (IOException ex) 
		    {
		    	System.out.println(ex.toString());
	        }
			
		}
		
		this.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));		
	}
}


class PanelPrincipal extends JPanel{	
	
	public PanelPrincipal(List<Parcela> parcelas) {
		this.setLayout(new GridLayout(3, 3));		
		for(int a = 0; a < parcelas.size(); a++) {			
			PanelParcela pp = new PanelParcela(parcelas.get(a));			
			this.add(pp);
		}		
	}
}


public class VentanaMain extends JFrame {
	
	private static Double DINERO_INICIAL = 2000.00;
	public static Mercado mercado = new Mercado();
	public static Galpon galpon = new Galpon();
	public static Billetera billetera = new Billetera(DINERO_INICIAL);
	public static PanelPrincipal panelPrincipal;
	public static List<Parcela> parcelas = new ArrayList<Parcela>();
	public static JPanel panel;
	
	
	public static void Refresh() {
		
		panel.revalidate();
		panel.repaint();
		
		
		panelPrincipal = new PanelPrincipal(parcelas);
		
		panel.removeAll();
		
		panel.add(panelPrincipal);
	}
	
	public VentanaMain() {		
		
		for(int a = 0; a < 9; a++) {			
			parcelas.add(new Parcela(a+1));
		}
		
		panel = new JPanel();							
		panel.setLayout(new GridLayout(1, 1));
		
		panelPrincipal = new PanelPrincipal(parcelas);
		panel.add(panelPrincipal);
		
		this.add(panel);		
		
		PanelBotones pb = new PanelBotones();					
		this.add(pb, BorderLayout.SOUTH);	
	}
	
	
	
	
	public static void main(String[] args) {
		VentanaMain p1 = new VentanaMain();
		p1.setVisible(true);
		p1.setDefaultCloseOperation(EXIT_ON_CLOSE);
		p1.setSize(1450, 1000);
		p1.setTitle("Granja - Pantalla Principal");
		
	}
}