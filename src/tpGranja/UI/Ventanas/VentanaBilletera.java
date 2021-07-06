package tpGranja.UI.Ventanas;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextArea;

import tpGranja.Dinero.Billetera;

public class VentanaBilletera extends JFrame {
	
	
	
	public VentanaBilletera() {
		
		Billetera b = VentanaMain.billetera;		
		Double saldo = b.GetTotalDinero();
		
		JTextArea text = new JTextArea();
		text.disable();
		Font font = new Font("Segoe Script", Font.BOLD, 40);
		text.setFont(font);
		text.setForeground(Color.GREEN);
		text.setCaretColor(Color.BLACK);
		text.setText("TU SALDO ES $ " + saldo.toString());
		
		text.setBackground(new Color(229, 57, 53));
		
		this.add(text);
		
	}
	
	
}
