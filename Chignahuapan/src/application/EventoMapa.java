package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import com.esri.client.local.ArcGISLocalDynamicMapServiceLayer;
import com.esri.client.local.ArcGISLocalFeatureLayer;
import com.esri.client.local.ArcGISLocalTiledLayer;
import com.esri.core.geodatabase.Geodatabase;
import com.esri.core.geodatabase.GeodatabaseFeature;
import com.esri.core.geometry.Envelope;
import com.esri.core.map.Feature;
import com.esri.core.map.LayerLegendInfo;
import com.esri.core.map.LayerLegendInfoCollection;
import com.esri.core.map.LegendItemInfo;
import com.esri.core.renderer.SimpleRenderer;
import com.esri.core.renderer.UniqueValueRenderer;
import com.esri.map.ArcGISDynamicMapServiceLayer;
import com.esri.map.ArcGISFeatureLayer;
import com.esri.map.ArcGISTiledMapServiceLayer;
import com.esri.map.GroupLayer;
import com.esri.map.JMap;
import com.esri.map.Layer;
import com.esri.map.LayerInfo;
import com.esri.map.LayerInitializeCompleteEvent;
import com.esri.map.LayerInitializeCompleteListener;
import com.esri.map.LayerList;
import com.esri.map.MapEvent;
import com.esri.map.MapEventListenerAdapter;
import com.esri.runtime.ArcGISRuntime;
import com.esri.map.Layer.LayerStatus;
import com.esri.toolkit.legend.JLegend;
import com.esri.toolkit.overlays.HitTestEvent;
import com.esri.toolkit.overlays.HitTestListener;
import com.esri.toolkit.overlays.HitTestOverlay;
import com.google.gson.Gson;

import classes.StyleLayer;
import classes.UniqueValueInfo;
import classes.UniqueValueStyle;

public class EventoMapa {
	String urlMapOnline = "http://192.168.116.124:6080/arcgis/rest/services/chignahuapanSDE/MapServer";
	String urlMapLocal = "http://192.168.116.124:6080/arcgis/rest/services/chignahuapanSDE/FeatureServer";
	HashMap<String, LayerInfo> listaLayers = new HashMap<String, LayerInfo>();
	List<ArcGISFeatureLayer> listaArcGisFeatureLayer = new ArrayList<ArcGISFeatureLayer>();
	
	public void crearMapaPuebla(GroupLayer groupLayer, JMap map) {
		ArcGISTiledMapServiceLayer baseLayer = new ArcGISTiledMapServiceLayer("https://services.arcgisonline.com/arcgis/rest/services/World_Street_Map/MapServer");
		LayerList layers = map.getLayers();
		baseLayer.setName("Mapa Base");
		layers.add(baseLayer);
//		map.setAutoscrolls(false);
//		map.setFocusable(false);
//		map.setFocusCycleRoot(false);
		cargarMapasLayer(map, 0, groupLayer);
		//seleccionarPredio(map);
	}
	
	public void changeBaseLayer(GroupLayer groupLayer, JMap map, String nameBaseLayer){
		System.out.println("Entré al método changeBaseLayer");
		ArcGISTiledMapServiceLayer baseLayer = new ArcGISTiledMapServiceLayer(nameBaseLayer);
		baseLayer.setName("Mapa Base");
		
		System.out.println("Base Layer status : "+baseLayer.getStatus());
//		for (int i = 0; i < map.getLayers().size(); i++) {
//			
//			if (map.getLayers().get(i) != null) {
//				if(map.getLayers().get(i).getName() != null){
//					if(map.getLayers().get(i).getName().equals("Mapa Base")){
//						System.out.println("Encontré el mapa base : "+map.getLayers().get(i));
//						map.getLayers().remove(i);
//					}
//					break;
//				}
//			}
//		}
		
		System.out.println("Antes de agregar Mapsdfffdsssssss : \n"+map.getLayers());
		map.getLayers().add(baseLayer);
		System.out.println("Mapsdfffdsssssss : \n"+map.getLayers());
	}
	
	public void cargarMapasLayer(JMap map, int url, GroupLayer groupLayer) {
		 String urlMapa = urlMapOnline;
		if (url == 1) {
			urlMapa = urlMapLocal;
			try {
				mapaLocal (map);
			} catch (Exception e) { 
				System.out.println(" exception " + e);
			}
			
	        map.setExtent(new Envelope(-1.0914563461473859E7, 2251658.6830414305, -1.0909068825257503E7, 2255260.8421374366));
		} else {
			map.setExtent(new Envelope(-1.0914563461473859E7, 2251658.6830414305, -1.0909068825257503E7, 2255260.8421374366));
			final ArcGISDynamicMapServiceLayer onlineDynamicLayer = new ArcGISDynamicMapServiceLayer(urlMapa);
			onlineDynamicLayer.setName("Onlinehgtythyt");
			map.getLayers().add(onlineDynamicLayer);
			onlineDynamicLayer.addLayerInitializeCompleteListener(new LayerInitializeCompleteListener() {
				@Override
				public void layerInitializeComplete(LayerInitializeCompleteEvent e) {
					System.out.println(" status " + onlineDynamicLayer.getStatus());
					if (onlineDynamicLayer.getStatus() == LayerStatus.INITIALIZED) {
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								//seleccionarPredio(map, onlineDynamicLayer);
								listaLayers = onlineDynamicLayer.getLayers();
//								GroupLayer groupLayer = new GroupLayer();
								if (listaLayers != null) {
									for (int i = 0; i < listaLayers.size(); i++) {
										LayerInfo layerInfo = (LayerInfo) listaLayers.values().toArray()[i];
										ArcGISFeatureLayer arcGISFeatureLayer = new ArcGISFeatureLayer( urlMapOnline + "/" + layerInfo.getId());
										map.getLayers().add(arcGISFeatureLayer);
										arcGISFeatureLayer.addLayerInitializeCompleteListener(new LayerInitializeCompleteListener() {
											@Override
											public void layerInitializeComplete(LayerInitializeCompleteEvent e) {
												if (arcGISFeatureLayer.getStatus() == LayerStatus.INITIALIZED) {
													groupLayer.add(arcGISFeatureLayer);
													listaArcGisFeatureLayer.add(arcGISFeatureLayer);
													if (listaArcGisFeatureLayer.size() == listaLayers.size()) {
														agregarEvento(map, listaArcGisFeatureLayer);
													}
												}
											}
										});
									}
								}

								do{
									map.getLayers().remove(map.getLayers().size()-1);
								}while(map.getLayers().size() > 1);
								
								if(map.getLayers().size()==1){
									map.getLayers().add(groupLayer);
								}
							}
						});
					}
				}
			});
		}
	}
	
	public void mapaLocal (JMap map) {
		ArcGISLocalTiledLayer tiledLayer = new ArcGISLocalTiledLayer("C:\\Program Files (x86)\\ArcGIS SDKs\\java10.2.4\\sdk\\samples\\data\\tpks\\chignahuapanSDE.tpk");
		ArcGISRuntime arcGISRuntime = new ArcGISRuntime();
		map.getLayers().add(tiledLayer);
		System.out.println("tiledLayerdsdsd"+tiledLayer);
		tiledLayer.addLayerInitializeCompleteListener(new LayerInitializeCompleteListener() {
			@Override
			public void layerInitializeComplete(LayerInitializeCompleteEvent e) {
				if (tiledLayer.getStatus() == LayerStatus.INITIALIZED) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {  
							
							LayerLegendInfoCollection layerLegendInfoCollection = tiledLayer.getLegend();
							List<LayerLegendInfo> listaLayer= layerLegendInfoCollection.getLayerLegendInfos();
							if (listaLayer != null) {
								for (LayerLegendInfo layerLegendInfo: listaLayer) { 
									System.out.println(" id "+layerLegendInfo.getLayerId()+ " name " + layerLegendInfo.getLayerName()+ " type " +layerLegendInfo.getLayerType() + " url "+layerLegendInfo.getServiceUrl());
									ArcGISLocalFeatureLayer arcGISLocalFeatureLayer = new ArcGISLocalFeatureLayer("C:\\Program Files (x86)\\ArcGIS SDKs\\java10.2.4\\sdk\\samples\\data\\tpks\\chignahuapanSDE.tpk", layerLegendInfo.getLayerId());
									System.out.println("tiledLayer "+arcGISLocalFeatureLayer);	
									map.getLayers().add(arcGISLocalFeatureLayer);
									arcGISLocalFeatureLayer.addLayerInitializeCompleteListener(new LayerInitializeCompleteListener() {
										@Override
										public void layerInitializeComplete(LayerInitializeCompleteEvent e) {
											System.out.println("arcGISLocalFeatureLayer.getStatus() " +arcGISLocalFeatureLayer.getStatus());
											if (arcGISLocalFeatureLayer.getStatus() == LayerStatus.INITIALIZED) {
												
											}
										}
									});
								}
							}
						}
					});
				}
			}
		});
	}
	
	public void agregarEvento(JMap map, List<ArcGISFeatureLayer> lista ) {
		System.out.println(" prueba " + map.getLayers());
		for (int i = 0; i < lista.size(); i++) {  
			seleccionarPredio(map, lista.get(i));
			ArcGISFeatureLayer arcGISFeatureLayer = lista.get(i);
		}
	}
	
	public void seleccionarPredio(JMap map, ArcGISFeatureLayer arcGISFeatureLayer) {
		LayerList layerList = map.getLayers();
		map.addMapEventListener(new MapEventListenerAdapter() {
			@Override
			public void mapReady(MapEvent mapEvent) {
				arcGISFeatureLayer.setSelectionColor(Color.YELLOW);
			}
		});
		HitTestListener listener = SeleccionarPredio();
		final HitTestOverlay selectionOverlay = new HitTestOverlay(arcGISFeatureLayer, listener);
		map.addMapOverlay(selectionOverlay);
	}
	
	public HitTestListener SeleccionarPredio() {
		HitTestListener listener = new HitTestListener() {
			@Override
			public void featureHit(HitTestEvent event) {
				HitTestOverlay overlay = (HitTestOverlay) event.getSource();
				List<Feature> hitFeatures = overlay.getHitFeatures();
				ArcGISFeatureLayer arcGISFeatureLayer = (ArcGISFeatureLayer) overlay.getLayer();
				for (Feature feature : hitFeatures) {
					if (arcGISFeatureLayer.isGraphicSelected((int) feature.getId())) {
						arcGISFeatureLayer.unselect((int) feature.getId());
					} else {
						arcGISFeatureLayer.select((int) feature.getId());
					}
				}
			}
		};
		return listener;
	}
	
	public void crearMenuCapas (JMap map, JPanel panelMenuCapas) {
		JLegend legend = new JLegend(map);
		for (Component component : legend.getComponents()) {
			JScrollPane jScrollPane = (JScrollPane) component;
			for (int i =0; i<jScrollPane.getComponents().length; i++) {
				if (i==0) {
					JViewport jViewport = (JViewport) jScrollPane.getComponent(i);
					JTree jTree = (JTree) jViewport.getComponent(0); 
					//jTree.setBackground(Color.CYAN);
					jTree.addMouseListener(new MouseListener() {
						@Override
						public void mouseReleased(MouseEvent arg0) {
//							System.out.println("mouseReleased ");
						}
						@Override
						public void mousePressed(MouseEvent arg0) {
//							System.out.println("mousePressed ");
						}
						
						@Override
						public void mouseExited(MouseEvent arg0) {
//							System.out.println("mouseExited ");							
						}
						@Override
						public void mouseEntered(MouseEvent arg0) {
							//System.out.println("mouseEntered ");							
						}
						@Override
						public void mouseClicked(MouseEvent arg0) {
							DefaultTreeModel defaultTreeModel = (DefaultTreeModel) jTree.getModel();
							if (jTree.getPathForLocation(arg0.getX(), arg0.getY()) != null) {
								TreePath path = jTree.getPathForLocation(arg0.getX(), arg0.getY());
								Object[] objects = path.getPath();
								LayerLegendInfo layerLegendInfo = null;
								ArcGISLocalTiledLayer arcGISLocalTiledLayer = null;
								LegendItemInfo legendItemInfo= null;
								ArcGISFeatureLayer arcGISFeatureLayer = null;
								int localOnline=1;
								if (objects.length > 3) {
									for (int i=0; i<objects.length; i++) { 
										System.out.println(" lenhgt " + objects[i]);
										if (i==1) {
											try {
												DefaultMutableTreeNode defaultMutableTreeNode  = (DefaultMutableTreeNode) objects[i];
												arcGISLocalTiledLayer = (ArcGISLocalTiledLayer) defaultMutableTreeNode.getUserObject();
											} catch (Exception e) { 
												localOnline=0;
											}
										}
										if (localOnline ==0) {
											if (i==2 ) {
												DefaultMutableTreeNode defaultMutableTreeNode  = (DefaultMutableTreeNode) objects[i];
												arcGISFeatureLayer =  (ArcGISFeatureLayer) defaultMutableTreeNode.getUserObject();
											}
										}else {
											if (i==2 ) {
												DefaultMutableTreeNode defaultMutableTreeNode  = (DefaultMutableTreeNode) objects[i];
												layerLegendInfo =  (LayerLegendInfo) defaultMutableTreeNode.getUserObject();
											}
										}
										//System.out.println("path " + objects[i]);
										if ((i+1) == objects.length) {
											//System.out.println(" i " + i + " objects[i] " +objects[i]);
											DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) objects[i];
											legendItemInfo = (LegendItemInfo) defaultMutableTreeNode.getUserObject();
										}
									}
									if (localOnline ==0) {
										seleccionarLayer(map, arcGISFeatureLayer, legendItemInfo);
									} else {
										
										System.out.println(" map.getLayers().get(1) " + map.getLayers().get(1) );
										
									}
									
									if (defaultTreeModel != null) {
										defaultTreeModel.reload();
									}
								}
								
							}
						}
					});
				} 
			}
		}
		legend.setPreferredSize(new Dimension(300, 749));
		legend.setBorder(new LineBorder(new Color(205, 205, 255), 3));
		panelMenuCapas.add(legend, BorderLayout.WEST);
	}
	
	public void seleccionarLayer(JMap map, ArcGISFeatureLayer arcGISFeatureLayer, LegendItemInfo legendItemInfo) {
		if (arcGISFeatureLayer != null && legendItemInfo != null) {
			try {// SimpleRender, si marca error entra a catch
				SimpleRenderer simpleRenderer = (SimpleRenderer) arcGISFeatureLayer.getRenderer();
				System.out.println(" legendItemInfo simple: "+legendItemInfo);
				cambiarColorLayerSimple(simpleRenderer.toJson(), map, arcGISFeatureLayer, legendItemInfo); 
			} catch (Exception e) {
				try {
					UniqueValueRenderer uniqueValueRenderer = (UniqueValueRenderer) arcGISFeatureLayer.getRenderer();
					System.out.println(" uniqueValueRenderer: "+ legendItemInfo );
					cambiarColorLayerUnique(uniqueValueRenderer.toJson(), map, arcGISFeatureLayer, legendItemInfo);
					
				} catch (Exception e2) {
					// se pondran objetos del local
				}

			}

		}
			//System.out.println(" arcGISFeatureLayer.getRenderer() " + arcGISFeatureLayer.getRenderer() +" arcGISFeatureLayer.getSelectionColor() "+arcGISFeatureLayer.getSelectionColor());
	}
	
	public void cambiarColorLayerUnique (String Json, JMap map, ArcGISFeatureLayer arcGISFeatureLayer, LegendItemInfo legendItemInfo) {
		Gson gson = new Gson();
		UniqueValueStyle uniqueValueStyle = (UniqueValueStyle) gson.fromJson(Json, UniqueValueStyle.class);
		if (uniqueValueStyle != null) {
			List<UniqueValueInfo> listaValues = uniqueValueStyle.getUniqueValueInfos();
			if (listaValues != null) {
				for (UniqueValueInfo uniqueValueInfo : listaValues) {
					if (uniqueValueInfo.getLabel().equals(legendItemInfo.getLabel())) {
						int[] colors = {};
						colors = uniqueValueInfo.getSymbol().getColor();
						uniqueValueInfo.getSymbol().setColor(obtenerColorNuevo(map, colors));
						Gson nuevoJson = new Gson(); 
						String jsonActual = nuevoJson.toJson(uniqueValueStyle); 
						//System.out.println(" js " + jsonActual);
						actualizarColorLayerUnique(jsonActual, arcGISFeatureLayer, legendItemInfo);
						break;
					}
				}
			}
		}
	}
	
	public void actualizarColorLayerUnique (String jsonActual, ArcGISFeatureLayer arcGISFeatureLayer, LegendItemInfo legendItemInfo) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(jsonActual);
			//System.out.println("nuevoJson " + jsonNode);
			UniqueValueRenderer uniqueValueRenderer = new UniqueValueRenderer(jsonNode);
			arcGISFeatureLayer.setRenderer(uniqueValueRenderer);
			arcGISFeatureLayer.refresh();
		} catch (Exception e) {
			System.out.println("actualizarColorLayer e: "  +e);
		}
		
	}
	
	public void cambiarColorLayerSimple (String Json,JMap map, ArcGISFeatureLayer arcGISFeatureLayer, LegendItemInfo legendItemInfo) {
		try { 
			Gson gson = new Gson();
			StyleLayer styleLayer = gson.fromJson(Json, StyleLayer.class);
			if (styleLayer != null) {
				int[] colors = {};
				if (styleLayer.getSymbol().getOutline() != null) {
					colors =styleLayer.getSymbol().getOutline().getColor(); 
					styleLayer.getSymbol().getOutline().setColor(obtenerColorNuevo(map, colors));
				} else  {
					colors =styleLayer.getSymbol().getColor(); 
					styleLayer.getSymbol().setColor(obtenerColorNuevo(map, colors));
				}
				Gson nuevoJson = new Gson();
				String jsonActual = nuevoJson.toJson(styleLayer); 
				actualizarColorLayer(jsonActual, arcGISFeatureLayer, legendItemInfo);
			} 
			
		} catch (Exception e) {
			System.out.println("Exception " + e);
			e.printStackTrace();
		}
	}
	
	public void actualizarColorLayer (String jsonActual, ArcGISFeatureLayer arcGISFeatureLayer, LegendItemInfo legendItemInfo) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(jsonActual);
			//System.out.println("nuevoJson " + jsonNode);
			SimpleRenderer simpleRenderer2 = new SimpleRenderer(jsonNode);
			arcGISFeatureLayer.setRenderer(simpleRenderer2);
			arcGISFeatureLayer.refresh();
		} catch (Exception e) {
			System.out.println("actualizarColorLayer e: "  +e);
		}
	}
	
	public int[] obtenerColorNuevo (JMap map, int[] colors) {
		int r =0, g=0, b=0, a=0;
		int[] colorNuevos = colors;
		for (int i=0; i<colors.length; i++) { 
			if (i==0) {
				r =colors[i];
			} else if (i==1) {
				g =colors[i];
			} else if (i==2) {
				b =colors[i];
			} else if (i==3) {
				a =colors[i];
			}
		}
		Color color = new Color(r, g, b, a);
		Color fromColor = JColorChooser.showDialog(  map, "Choose 'from' color", color);
		if (fromColor != null) {
			int[] colorNuevoss = {fromColor.getRed(), fromColor.getGreen(), fromColor.getBlue(), fromColor.getAlpha()};
			colorNuevos = colorNuevoss;
		}
		return colorNuevos;
	}

}
