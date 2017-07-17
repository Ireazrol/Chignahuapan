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

}
