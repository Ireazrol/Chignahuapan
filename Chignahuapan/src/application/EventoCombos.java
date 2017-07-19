package application;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;

import com.esri.map.JMap;

public class EventoCombos {
	
	
	public void eventoCmbScala (JComboBox cmbScala, JMap map) {
		cmbScala.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) { 
	            	 String cad1=(String)cmbScala.getSelectedItem();
	            	 cad1 = cad1.substring(2);
	            	 String scala = cad1.replace(".", "");   
	            	 System.out.println("scala " + scala);
	            	 map.setScale(Double.parseDouble(scala));
	            } 
	        });
	}
	
	public void CmbBaseLayer (JComboBox CmbBaseLayer) {
		CmbBaseLayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
				int numItem= CmbBaseLayer.getSelectedIndex();
				switch (numItem) {
				case 0:
					System.out.println("Cero");
					break;
				case 1:
					VentanasDinamicas.main(null);
					break;
				case 2:
					System.out.println("Dos");
					break;
				default:
					break;
				}
			} 
		});
	}
}