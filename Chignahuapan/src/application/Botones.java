package application;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.esri.core.map.Graphic;
import com.esri.core.symbol.SimpleFillSymbol;
import com.esri.core.symbol.SimpleLineSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol.Style;
import com.esri.map.GraphicsLayer;
import com.esri.map.GroupLayer;
import com.esri.map.JMap;
import com.esri.toolkit.overlays.DrawingCompleteEvent;
import com.esri.toolkit.overlays.DrawingCompleteListener;
import com.esri.toolkit.overlays.DrawingOverlay;
import com.esri.toolkit.overlays.DrawingOverlay.DrawingMode;

public class Botones {
	String nameBaseLayer ="";
	
	
	/**
	 * Descripción: Se agrega una nueva Capa en el mapa
	 * El método tiene como parámetros
	 * @param btnAgregarData
	 * @param groupLayer
	 * @param map
	 */
	public void BtnAddLayer(JComboBox btnAgregarData, GroupLayer groupLayer, JMap map){
		btnAgregarData.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int taille=groupLayer.size()+1;
				
				for(int i=1; i<map.getLayers().size(); i++){
					map.getLayers().remove(i);
				}
//				ArcGISFeatureLayer arcGISFeatureLayer = new ArcGISFeatureLayer("");
				GraphicsLayer graphicsLayer = new GraphicsLayer();
				String nameLayer="Subcapa "+ taille;
				graphicsLayer.setName(nameLayer);
				groupLayer.add(graphicsLayer);
				map.getLayers().add(groupLayer);
				System.out.println("BtnAddLayer---->groupLayer:  \n"+groupLayer);
				taille++;
			}
		});
	}
	
	public String addBaseLayer(int num){
		String nameBaseLayer ="";
		String [] arrayBaseLayer={"https://services.arcgisonline.com/arcgis/rest/services/World_Imagery/MapServer",
				"https://services.arcgisonline.com/arcgis/rest/services/World_Physical_Map/MapServer",
				"https://services.arcgisonline.com/arcgis/rest/services/World_Shaded_Relief/MapServer",
				"https://services.arcgisonline.com/arcgis/rest/services/World_Street_Map/MapServer",
				"https://services.arcgisonline.com/arcgis/rest/services/World_Terrain_Base/MapServer",
				"https://services.arcgisonline.com/arcgis/rest/services/World_Topo_Map/MapServer"};
		nameBaseLayer= arrayBaseLayer[num];
		return nameBaseLayer;
	}
	
	public void BtnRectangle(DrawingOverlay drawingOverlayu,JButton rectangleButton){
		System.out.println("Entr� al m�todo para pintar un rectangulo");
		rectangleButton.addActionListener(new ActionListener() {
	          @Override
	          public void actionPerformed(ActionEvent e) {
	        	  System.out.println("Pintar� un rectangulo");
	        	  drawingOverlayu.setUp(DrawingMode.POLYGON_RECTANGLE, 
	        			  new SimpleFillSymbol(new Color(200, 0, 0, 180), new SimpleLineSymbol(new Color(200, 0, 0), 3)),
	        			  null);
	          }
	    });
	}
	
	public void BtnPolyline(DrawingOverlay drawingOverlaya, JButton polylineButton){
		System.out.println("Entr� al m�todo para pintar una polil�nea");
		polylineButton.addActionListener(new ActionListener() {
	          @Override
	          public void actionPerformed(ActionEvent e) {
	        	  System.out.println("Pintar� una polil�nea");
	        	  drawingOverlaya.setUp(
	                DrawingMode.POLYLINE,
	                new SimpleLineSymbol(Color.BLUE, 3),
	                null);
	          }
	    });
	}
	
	public void BtnFreehandLine(DrawingOverlay drawingOverFreehand, JButton freehandLineButton){
		freehandLineButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawingOverFreehand.setUp(DrawingMode.POLYLINE_FREEHAND,new SimpleLineSymbol(Color.GREEN, 2), null);
			}
		});
	}
	
	public void BtnPoint(DrawingOverlay drawingOverPoint,JButton pointButton){
		pointButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawingOverPoint.setUp(DrawingMode.POINT,new SimpleMarkerSymbol(Color.cyan, 20, Style.CIRCLE), null);
			}
		});
	}
	
	public void BtnMultipoint(DrawingOverlay drawingOverMultipoint, JButton multipointButton) {
		multipointButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawingOverMultipoint.setUp(DrawingMode.MULTIPOINT, new SimpleMarkerSymbol(Color.DARK_GRAY, 20, Style.CIRCLE), null);
			}
		});
	}
	
	public void BtnPolygon(DrawingOverlay drawingOverPolygon, JButton polygonButton,SimpleLineSymbol dottedLine){
		polygonButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				drawingOverPolygon.setUp(DrawingMode.POLYGON, new SimpleFillSymbol(new Color(0, 0, 0, 80), dottedLine), null);
			}
		});
	}
	
	public void graphicLayer(DrawingOverlay drawingOverlay, GraphicsLayer graphicsLayer){
		drawingOverlay.addDrawingCompleteListener(new DrawingCompleteListener() {
			 @Override
			 public void drawingCompleted(DrawingCompleteEvent arg0) {
		        graphicsLayer.addGraphic((Graphic) drawingOverlay.getAndClearFeature());
		      }
		 });
	}
	/**********DrawingOverlay************/
	public DrawingOverlay DORectangle(JMap map){
		final DrawingOverlay drawingOverlayu = new DrawingOverlay();
	    map.addMapOverlay(drawingOverlayu);
	    drawingOverlayu.setActive(true);
	    return drawingOverlayu;
	}
	
	
	
	public void eventoBotonesMapa (JButton btnButton, JMap map, GroupLayer groupLayer, int localOnline, EventoMapa eventoMapa) {
		btnButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				eventoMapa.cargarMapasLayer(map, localOnline, groupLayer); 
			}
		});
	}
	
}