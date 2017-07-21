package application;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import com.esri.map.GroupLayer;
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
	
	public void CmbBaseLayer (JComboBox CmbBaseLayer, JMap map, GroupLayer groupLayer, JFrame frameMain) {
		CmbBaseLayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
				int numItem= CmbBaseLayer.getSelectedIndex();
				switch (numItem) {
				case 0:
					System.out.println("Cero");
					break;
				case 1:
					VentanasDinamicas frame = new VentanasDinamicas(map, groupLayer, frameMain);
					frameMain.setEnabled(false);
					frame.setVisible(true);
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