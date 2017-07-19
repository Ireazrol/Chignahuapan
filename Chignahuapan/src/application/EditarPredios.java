package application;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSetInternal;
import javax.sql.rowset.spi.TransactionalWriter;
import javax.swing.ActionMap;
import javax.swing.JButton;

import com.esri.core.geometry.Geometry;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polygon;
import com.esri.core.geometry.Polyline;
import com.esri.core.map.CallbackListener;
import com.esri.core.map.FeatureEditResult;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.SimpleLineSymbol;
import com.esri.map.ArcGISFeatureLayer;
import com.esri.map.GraphicsLayer;
import com.esri.map.GroupLayer;
import com.esri.map.JMap;
import com.esri.map.Layer;
import com.esri.map.MapEvent;
import com.esri.map.MapEventListener;
import com.esri.toolkit.overlays.DrawingCompleteEvent;
import com.esri.toolkit.overlays.DrawingCompleteListener;
import com.esri.toolkit.overlays.DrawingOverlay;
import com.esri.toolkit.overlays.DrawingOverlay.DrawingMode;

import classes.Symbol;

public class EditarPredios {
	
	 GraphicsLayer graphicsLayer = new GraphicsLayer();
	
	public void unirPredio (JButton btnUnion, JMap map) {
		btnUnion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (map.getLayers() != null) {
					List<Layer> lista = map.getLayers();
					if (lista != null) { 
						GroupLayer groupLayer = (GroupLayer) map.getLayers().get(1);
						Layer [] listaLayer =groupLayer.getLayers();
						for(int i=0; i<listaLayer.length; i++) {
							ArcGISFeatureLayer arcGISFeatureLayer = (ArcGISFeatureLayer) listaLayer[i];
							if (arcGISFeatureLayer.getSelectedFeatures().length >= 2 ) {
								Graphic[] graficos = arcGISFeatureLayer.getSelectedFeatures();
								unionPredio (map, graficos, arcGISFeatureLayer);
								break;
							} else if (arcGISFeatureLayer.getSelectedFeatures().length >= 1) {
								Graphic[] graficos = arcGISFeatureLayer.getSelectedFeatures();
								editarPredio (map, graficos, arcGISFeatureLayer) ;
							}
						}
					}
				}
			}
		});
	}
	
	
	
	public void unionPredio(JMap map, Graphic[] graficos, ArcGISFeatureLayer arcGISFeatureLayer) {
	    if (graficos.length < 2) { 
	      return;
	    } else {
	    	 Graphic grafics = graficos[0];
	    	 Geometry[] selectedGeometries = new Geometry[graficos.length];
	    	 int ids[] = new int[graficos.length];
	    	 for (int i=0; i<graficos.length; i++) {
				 Graphic grafic = graficos[i];
				 selectedGeometries[i] = grafic.getGeometry();
				 ids[i]=grafic.getUid();
			 }
	    	Geometry unionGeometry = GeometryEngine.union(selectedGeometries, map.getSpatialReference());
	 	    SimpleLineSymbol lineSymbol = new SimpleLineSymbol(Color.blue, 3, SimpleLineSymbol.Style.SOLID);
	 	    Graphic unionGraphic = new Graphic(unionGeometry, lineSymbol);
	 	    
	 	    Graphic[] graphics = new Graphic[1];
	 	    graphics[0] = unionGraphic;
	 	    
	 	   arcGISFeatureLayer.addGraphic(unionGraphic);
	 	   //arcGISFeatureLayer.addSelectedGraphic(unionGraphic);
	 	   
	 	  for (int i=0; i<graficos.length; i++) {
	 	    	 Graphic grafic = graficos[i]; 
	 	    	arcGISFeatureLayer.removeGraphic(grafic.getUid());
	 	    }
	    }
	}
	
	public void buscarGraficos () {
		
	}
	
	
	public void editarPredio(JMap map, Graphic[] graficos, ArcGISFeatureLayer arcGISFeatureLayer) {
		System.out.println("etitar predio "); 
		if (graficos.length < 2) {
			Graphic grafics = graficos[0];
	    	Geometry geometry = grafics.getGeometry(); 
	    	final Polygon polygon = (Polygon) grafics.getGeometry();
	    	for (int i=0; i<polygon.getPointCount(); i++) {
				Point point= polygon.getPoint(i);
				
				//System.out.println("point " +point);
			}
			DrawingOverlay drawingOverlaya = new DrawingOverlay();
			map.addMapOverlay(drawingOverlaya);
			drawingOverlaya.setUp(DrawingMode.POINT,grafics.getSymbol(),null);
			drawingOverlaya.addDrawingCompleteListener(new DrawingCompleteListener() {
				@Override
				public void drawingCompleted(DrawingCompleteEvent arg0) {
					System.out.println("drawingCompleted ");
					polygon.lineTo((Point) drawingOverlaya.getFeature().getGeometry()); 
					arcGISFeatureLayer.updateGraphic(grafics.getUid(), geometry);
				}
			});
	    	
		}
	}
	
}
