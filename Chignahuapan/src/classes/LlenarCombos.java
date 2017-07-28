package classes;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;

import application.JChomboRenderer;
import application.Principal;

public class LlenarCombos {
	public void llenarComboFeatures (JComboBox jComboBox) {
		ImageIcon[] itemsFeatures = {            
	            new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/SelectionSelectTool16.png"), "Select By Rectangle"),
	            new ImageIcon(Principal.class.getResource("/com/esri/toolkit/images/SelectionSelectUnselect16.png"),"Select by Polygon"),
	            new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/LayerServiceMap16.png"),"Select by Lasso"),
	            new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/LayerSelect16.png"),"Select by Circle"),
	            new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/LayerLine16.png"),"Select by Line") 
		};
		JMenuItem [] listaItems = {
				new JMenuItem( "Select By Rectangle", new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/SelectionSelectTool16.png"))),
				new JMenuItem( "Select by Polygon", new ImageIcon(Principal.class.getResource("/com/esri/toolkit/images/SelectionSelectUnselect16.png"))),
				new JMenuItem( "Select by Lasso", new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/LayerServiceMap16.png"))),
				new JMenuItem( "Select by Circle", new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/LayerSelect16.png"))),
				new JMenuItem( "Select by Line", new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/LayerLine16.png")))
		};
		
		for( int i=0; i<listaItems.length; i++) {
			jComboBox.addItem(i);
        }
		JChomboRenderer render = new JChomboRenderer( listaItems );
		jComboBox.setRenderer(render);
	}
}
