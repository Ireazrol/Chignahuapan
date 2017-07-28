package application;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.ScrollPane;
import java.awt.Shape;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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

import com.esri.arcgisruntime.tasks.geodatabase.GenerateGeodatabaseJob;
import com.esri.client.local.ArcGISLocalDynamicMapServiceLayer;
import com.esri.client.local.ArcGISLocalFeatureLayer;
import com.esri.client.local.ArcGISLocalTiledLayer;
import com.esri.core.geodatabase.Geodatabase;
import com.esri.core.geodatabase.GeodatabaseFeature;
import com.esri.core.geometry.Envelope;
import com.esri.core.geometry.Geometry;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Line;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polygon;
import com.esri.core.geometry.Polyline;
import com.esri.core.geometry.Segment;
import com.esri.core.map.CallbackListener;
import com.esri.core.map.Feature;
import com.esri.core.map.FeatureEditResult;
import com.esri.core.map.FeatureTemplate.DRAWING_TOOL;
import com.esri.core.map.Graphic;
import com.esri.core.map.LayerLegendInfo;
import com.esri.core.map.LayerLegendInfoCollection;
import com.esri.core.map.LegendItemInfo;
import com.esri.core.renderer.SimpleRenderer;
import com.esri.core.renderer.UniqueValueRenderer;
import com.esri.core.symbol.SimpleFillSymbol;
import com.esri.core.symbol.SimpleLineSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol.Style;
import com.esri.core.symbol.Symbol;
import com.esri.core.tasks.ags.geoprocessing.GPParameter;
import com.esri.core.tasks.geodatabase.GenerateGeodatabaseParameters;
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
import com.esri.map.MapOptions;
import com.esri.map.MapOverlay;
import com.esri.map.MapOptions.MapType;
import com.esri.runtime.ArcGISRuntime;
import com.esri.map.Layer.LayerStatus;
import com.esri.toolkit.legend.JLegend;
import com.esri.toolkit.overlays.DrawingCompleteEvent;
import com.esri.toolkit.overlays.DrawingCompleteListener;
import com.esri.toolkit.overlays.DrawingOverlay;
import com.esri.toolkit.overlays.DrawingOverlay.DrawingMode;
import com.esri.toolkit.overlays.HitTestEvent;
import com.esri.toolkit.overlays.HitTestListener;
import com.esri.toolkit.overlays.HitTestOverlay;
import com.google.gson.Gson;

import classes.StyleLayer;
import classes.UniqueValueInfo;
import classes.UniqueValueStyle;
import javafx.scene.control.ToolBar;

public class EventoMapa {
	// http://192.168.116.124:6080/arcgis/rest/services/chignahuapanSDE/FeatureServer
	// http://192.168.116.124:6080/arcgis/rest/services/chignahuapanSDE/MapServer
	String urlMapOnline = "http://192.168.116.124:6080/arcgis/rest/services/chignahuapanSDE/FeatureServer";
	String urlMapLocal = "http://192.168.116.124:6080/arcgis/rest/services/chignahuapanSDE/MapServer";
	HashMap<String, LayerInfo> listaLayers = new HashMap<String, LayerInfo>();
	List<ArcGISFeatureLayer> listaArcGisFeatureLayer = new ArrayList<ArcGISFeatureLayer>();

	public void crearMapaPuebla(GroupLayer groupLayer, JMap map) {
		ArcGISTiledMapServiceLayer baseLayer = new ArcGISTiledMapServiceLayer(
				"https://services.arcgisonline.com/arcgis/rest/services/World_Street_Map/MapServer");
		LayerList layers = map.getLayers();
		baseLayer.setName("Mapa Base");
		layers.add(baseLayer);
		cargarMapasLayer(map, 0, groupLayer);
	}

	public static void changeBaseLayer(GroupLayer groupLayer, JMap map, String nameBaseLayer) {
		ArcGISTiledMapServiceLayer baseLayer = new ArcGISTiledMapServiceLayer(nameBaseLayer);
		baseLayer.setName("Mapa Base");
		GroupLayer groupLayerAux = new GroupLayer();
		for (int i = 0; i < map.getLayers().size(); i++) {
			if (map.getLayers().get(i) != null) {
				if (map.getLayers().get(i).getName().equals("Capas")) {
					groupLayerAux.add(map.getLayers().get(i));
				}
			}
		}
		map.getLayers().clear();
		map.getLayers().add(baseLayer);
		map.getLayers().add(groupLayerAux.getLayer("Capas"));
		System.out.println("Map.getLayers() (after add) : \n" + map.getLayers());
	}

	public void cargarMapasLayer(JMap map, int url, GroupLayer groupLayer) {
		String urlMapa = urlMapOnline;
		if (url == 1) {
			urlMapa = urlMapLocal;
			try {
				mapaLocal(map);
			} catch (Exception e) {
				System.out.println(" exception " + e);
			}

			map.setExtent(
					new Envelope(-1.0914563461473859E7, 2251658.6830414305, -1.0909068825257503E7, 2255260.8421374366));
		} else {
			map.setExtent(
					new Envelope(-1.0914563461473859E7, 2251658.6830414305, -1.0909068825257503E7, 2255260.8421374366));
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
								// seleccionarPredio(map, onlineDynamicLayer);
								listaLayers = onlineDynamicLayer.getLayers();
								// GroupLayer groupLayer = new GroupLayer();
								if (listaLayers != null) {
									for (int i = 0; i < listaLayers.size(); i++) {
										LayerInfo layerInfo = (LayerInfo) listaLayers.values().toArray()[i];
										ArcGISFeatureLayer arcGISFeatureLayer = new ArcGISFeatureLayer(
												urlMapOnline + "/" + layerInfo.getId());
										map.getLayers().add(arcGISFeatureLayer);

										arcGISFeatureLayer.addLayerInitializeCompleteListener(
												new LayerInitializeCompleteListener() {
													@Override
													public void layerInitializeComplete(
															LayerInitializeCompleteEvent e) {
														if (arcGISFeatureLayer.getStatus() == LayerStatus.INITIALIZED) {
															cambiarGraficos(map, arcGISFeatureLayer);
															groupLayer.add(arcGISFeatureLayer);
															groupLayer.setName("Capas");
															listaArcGisFeatureLayer.add(arcGISFeatureLayer);
															if (listaArcGisFeatureLayer.size() == listaLayers.size()) {
																Principal.listaArcGisFeatureLayer = listaArcGisFeatureLayer;
															}
														}
													}
												});
									}
								}

								do {
									map.getLayers().remove(map.getLayers().size() - 1);
								} while (map.getLayers().size() > 1);

								if (map.getLayers().size() == 1) {
									map.getLayers().add(groupLayer);
								}
							}
						});
					}
				}
			});
		}
	}

	public void mapaLocal(JMap map) {
		ArcGISLocalTiledLayer tiledLayer = new ArcGISLocalTiledLayer(
				"C:\\Program Files (x86)\\ArcGIS SDKs\\java10.2.4\\sdk\\samples\\data\\tpks\\chignahuapanSDE.tpk");
		ArcGISRuntime arcGISRuntime = new ArcGISRuntime();
		map.getLayers().add(tiledLayer);
		System.out.println("tiledLayerdsdsd" + tiledLayer);
		tiledLayer.addLayerInitializeCompleteListener(new LayerInitializeCompleteListener() {
			@Override
			public void layerInitializeComplete(LayerInitializeCompleteEvent e) {
				if (tiledLayer.getStatus() == LayerStatus.INITIALIZED) {
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							LayerLegendInfoCollection layerLegendInfoCollection = tiledLayer.getLegend();
							List<LayerLegendInfo> listaLayer = layerLegendInfoCollection.getLayerLegendInfos();
							if (listaLayer != null) {
								for (LayerLegendInfo layerLegendInfo : listaLayer) {
									System.out.println(" id " + layerLegendInfo.getLayerId() + " name "
											+ layerLegendInfo.getLayerName() + " type " + layerLegendInfo.getLayerType()
											+ " url " + layerLegendInfo.getServiceUrl());
									ArcGISLocalFeatureLayer arcGISLocalFeatureLayer = new ArcGISLocalFeatureLayer(
											"C:\\Program Files (x86)\\ArcGIS SDKs\\java10.2.4\\sdk\\samples\\data\\tpks\\chignahuapanSDE.tpk",
											layerLegendInfo.getLayerId());
									System.out.println("tiledLayer " + arcGISLocalFeatureLayer);
									map.getLayers().add(arcGISLocalFeatureLayer);
									arcGISLocalFeatureLayer
											.addLayerInitializeCompleteListener(new LayerInitializeCompleteListener() {
												@Override
												public void layerInitializeComplete(LayerInitializeCompleteEvent e) {
													System.out.println("arcGISLocalFeatureLayer.getStatus() "
															+ arcGISLocalFeatureLayer.getStatus());
													if (arcGISLocalFeatureLayer
															.getStatus() == LayerStatus.INITIALIZED) {

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

	public void cambiarGraficos(JMap map, ArcGISFeatureLayer arcGISFeatureLayer) {
		try {
			UniqueValueRenderer uniqueValueRenderer = (UniqueValueRenderer) arcGISFeatureLayer.getRenderer();
		} catch (Exception e) {
			try {
				System.out.println("getDescription " + arcGISFeatureLayer.getDefinitionExpression());
				Symbol symbol = obtenerSymbol(arcGISFeatureLayer);
				if (symbol != null) {
					UniqueValueRenderer uniqueValueRenderer = new UniqueValueRenderer();
					uniqueValueRenderer.setDefaultSymbol(symbol);
					uniqueValueRenderer.addValue(new com.esri.core.renderer.UniqueValueInfo(
							new Object[] { arcGISFeatureLayer.getName() }, symbol));
					arcGISFeatureLayer.setRenderer(uniqueValueRenderer);
					arcGISFeatureLayer.refresh();
				}
			} catch (Exception e2) {
				System.out.println("Exception2: " + e2);
			}
		}
	}

	public Symbol obtenerSymbol(ArcGISFeatureLayer arcGISFeatureLayer) {
		SimpleFillSymbol symbol = null;
		try {
			if (arcGISFeatureLayer != null) {
				SimpleRenderer simpleRenderer = (SimpleRenderer) arcGISFeatureLayer.getRenderer();
				Gson gson = new Gson();
				StyleLayer styleLayer = gson.fromJson(simpleRenderer.toJson(), StyleLayer.class);
				Gson nuevoJson = new Gson();
				String jsonActual = nuevoJson.toJson(styleLayer.getSymbol().getOutline());
				ObjectMapper objectMapper = new ObjectMapper();
				JsonNode jsonNode = objectMapper.readTree(simpleRenderer.toJson());
				ObjectMapper objectMappers = new ObjectMapper();
				JsonNode jsonNodes = objectMappers.readTree(jsonActual);
				SimpleFillSymbol simpleFillSymbol = new SimpleFillSymbol(jsonNode);
				Color color = new Color(255, 0, 255);
				simpleFillSymbol.setColor(color);
				simpleFillSymbol.setAlpha(1);
				simpleFillSymbol.setOutline(new SimpleLineSymbol(jsonNodes));
				symbol = simpleFillSymbol;
			}
		} catch (Exception e) {
			System.out.println("arcGISFeatureLayer -------" + e.getMessage());
		}
		return symbol;
	}

	public void agregarEvento(JMap map, List<ArcGISFeatureLayer> lista) {
		System.out.println(" prueba " + map.getLayers());
		for (int i = 0; i < lista.size(); i++) {
			ArcGISFeatureLayer arcGISFeatureLayer = lista.get(i);
			if (arcGISFeatureLayer.getName().equals("P")) {
				seleccionarPredio(map, arcGISFeatureLayer);
			}
			
		}
	}

	public void seleccionarPredio(JMap map, ArcGISFeatureLayer arcGISFeatureLayer) {
		LayerList layerList = map.getLayers();
		HitTestListener listener = SeleccionarPredio();
		final HitTestOverlay selectionOverlay = new HitTestOverlay(arcGISFeatureLayer, listener);
		map.addMapOverlay(selectionOverlay);

	}

	public HitTestListener SeleccionarPredio() {
		HitTestListener listener = new HitTestListener() {
			@Override
			public void featureHit(HitTestEvent event) {
				// System.out.println(" event " +event.getSource() );
				HitTestOverlay overlay = (HitTestOverlay) event.getSource();
				// System.out.println(" overlay " +overlay.getHitFeatures() );
				List<Feature> hitFeatures = overlay.getHitFeatures();
				ArcGISFeatureLayer arcGISFeatureLayer = (ArcGISFeatureLayer) overlay.getLayer();
				arcGISFeatureLayer.clearSelection();
				for (Feature feature : hitFeatures) {
					if (arcGISFeatureLayer.isGraphicSelected((int) feature.getId())) {
						arcGISFeatureLayer.unselect((int) feature.getId());
					} else {
						arcGISFeatureLayer.select((int) feature.getId());
						arcGISFeatureLayer.setSelectionColor(Color.YELLOW);
					}
				}
			}
		};
		return listener;
	}

	public void crearMenuCapas(JMap map, JPanel panelMenuCapas) {
		JLegend legend = new JLegend(map);
		for (Component component : legend.getComponents()) {
			JScrollPane jScrollPane = (JScrollPane) component;
			for (int i = 0; i < jScrollPane.getComponents().length; i++) {
				if (i == 0) {
					JViewport jViewport = (JViewport) jScrollPane.getComponent(i);
					JTree jTree = (JTree) jViewport.getComponent(0);
					// jTree.setBackground(Color.CYAN);
					jTree.addMouseListener(new MouseListener() {
						@Override
						public void mouseReleased(MouseEvent arg0) {
							// System.out.println("mouseReleased ");
						}

						@Override
						public void mousePressed(MouseEvent arg0) {
							// System.out.println("mousePressed ");
						}

						@Override
						public void mouseExited(MouseEvent arg0) {
							// System.out.println("mouseExited ");
						}

						@Override
						public void mouseEntered(MouseEvent arg0) {
							// System.out.println("mouseEntered ");
						}

						@Override
						public void mouseClicked(MouseEvent arg0) {
							DefaultTreeModel defaultTreeModel = (DefaultTreeModel) jTree.getModel();
							if (jTree.getPathForLocation(arg0.getX(), arg0.getY()) != null) {
								TreePath path = jTree.getPathForLocation(arg0.getX(), arg0.getY());
								Object[] objects = path.getPath();
								LayerLegendInfo layerLegendInfo = null;
								ArcGISLocalTiledLayer arcGISLocalTiledLayer = null;
								LegendItemInfo legendItemInfo = null;
								ArcGISFeatureLayer arcGISFeatureLayer = null;
								int localOnline = 1;
								if (objects.length > 3) {
									for (int i = 0; i < objects.length; i++) {
										System.out.println(" lenhgt " + objects[i]);
										if (i == 1) {
											try {
												DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) objects[i];
												arcGISLocalTiledLayer = (ArcGISLocalTiledLayer) defaultMutableTreeNode
														.getUserObject();
											} catch (Exception e) {
												localOnline = 0;
											}
										}
										if (localOnline == 0) {
											if (i == 2) {
												DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) objects[i];
												arcGISFeatureLayer = (ArcGISFeatureLayer) defaultMutableTreeNode
														.getUserObject();
											}
										} else {
											if (i == 2) {
												DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) objects[i];
												layerLegendInfo = (LayerLegendInfo) defaultMutableTreeNode
														.getUserObject();
											}
										}
										// System.out.println("path " +
										// objects[i]);
										if ((i + 1) == objects.length) {
											// System.out.println(" i " + i + "
											// objects[i] " +objects[i]);
											DefaultMutableTreeNode defaultMutableTreeNode = (DefaultMutableTreeNode) objects[i];
											legendItemInfo = (LegendItemInfo) defaultMutableTreeNode.getUserObject();
										}
									}
									if (localOnline == 0) {
										seleccionarLayer(map, arcGISFeatureLayer, legendItemInfo);
									} else {

										System.out.println(" map.getLayers().get(1) " + map.getLayers().get(1));

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
				System.out.println(" legendItemInfo simple: " + legendItemInfo);
				cambiarColorLayerSimple(simpleRenderer.toJson(), map, arcGISFeatureLayer, legendItemInfo);
			} catch (Exception e) {
				try {
					UniqueValueRenderer uniqueValueRenderer = (UniqueValueRenderer) arcGISFeatureLayer.getRenderer();
					System.out.println(" uniqueValueRenderer: " + legendItemInfo);
					cambiarColorLayerUnique(uniqueValueRenderer.toJson(), map, arcGISFeatureLayer, legendItemInfo);

				} catch (Exception e2) {
					// se pondran objetos del local
				}

			}

		}
		// System.out.println(" arcGISFeatureLayer.getRenderer() " +
		// arcGISFeatureLayer.getRenderer() +"
		// arcGISFeatureLayer.getSelectionColor()
		// "+arcGISFeatureLayer.getSelectionColor());
	}

	public void cambiarColorLayerUnique(String Json, JMap map, ArcGISFeatureLayer arcGISFeatureLayer,
			LegendItemInfo legendItemInfo) {
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
						// System.out.println(" js " + jsonActual);
						actualizarColorLayerUnique(jsonActual, arcGISFeatureLayer, legendItemInfo);
						break;
					}
				}
			}
		}
	}

	public void actualizarColorLayerUnique(String jsonActual, ArcGISFeatureLayer arcGISFeatureLayer,
			LegendItemInfo legendItemInfo) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(jsonActual);
			// System.out.println("nuevoJson " + jsonNode);
			UniqueValueRenderer uniqueValueRenderer = new UniqueValueRenderer(jsonNode);
			arcGISFeatureLayer.setRenderer(uniqueValueRenderer);
			arcGISFeatureLayer.refresh();
		} catch (Exception e) {
			System.out.println("actualizarColorLayer e: " + e);
		}

	}

	public void cambiarColorLayerSimple(String Json, JMap map, ArcGISFeatureLayer arcGISFeatureLayer,
			LegendItemInfo legendItemInfo) {
		try {
			Gson gson = new Gson();
			StyleLayer styleLayer = gson.fromJson(Json, StyleLayer.class);
			if (styleLayer != null) {
				int[] colors = {};
				if (styleLayer.getSymbol().getOutline() != null) {
					colors = styleLayer.getSymbol().getOutline().getColor();
					styleLayer.getSymbol().getOutline().setColor(obtenerColorNuevo(map, colors));
				} else {
					colors = styleLayer.getSymbol().getColor();
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

	public void actualizarColorLayer(String jsonActual, ArcGISFeatureLayer arcGISFeatureLayer,
			LegendItemInfo legendItemInfo) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode = objectMapper.readTree(jsonActual);
			// System.out.println("nuevoJson " + jsonNode);
			SimpleRenderer simpleRenderer2 = new SimpleRenderer(jsonNode);
			arcGISFeatureLayer.setRenderer(simpleRenderer2);
			arcGISFeatureLayer.refresh();
		} catch (Exception e) {
			System.out.println("actualizarColorLayer e: " + e);
		}
	}

	public int[] obtenerColorNuevo(JMap map, int[] colors) {
		int r = 0, g = 0, b = 0, a = 0;
		int[] colorNuevos = colors;
		for (int i = 0; i < colors.length; i++) {
			if (i == 0) {
				r = colors[i];
			} else if (i == 1) {
				g = colors[i];
			} else if (i == 2) {
				b = colors[i];
			} else if (i == 3) {
				a = colors[i];
			}
		}
		Color color = new Color(r, g, b, a);
		Color fromColor = JColorChooser.showDialog(map, "Choose 'from' color", color);
		if (fromColor != null) {
			int[] colorNuevoss = { fromColor.getRed(), fromColor.getGreen(), fromColor.getBlue(),
					fromColor.getAlpha() };
			colorNuevos = colorNuevoss;
		}
		return colorNuevos;
	}

	public int validarPredioSeleccionado(String titulo, JMap map, List<ArcGISFeatureLayer> lista, JFrame jFrame) {
		int validar = 0;
		if (lista != null) {
			for (ArcGISFeatureLayer arcGISFeatureLayer : lista) {
				Graphic[] listaGraphic = arcGISFeatureLayer.getSelectedFeatures();
				if (listaGraphic != null && listaGraphic.length > 0) {
					validar = 1;
					System.out.println(" listaGraphic " + listaGraphic.length);
				}
			}
			if (validar == 0) {
				JOptionPane.showMessageDialog(jFrame, "Seleccione un predio.");
			}
		}
		return validar;
	}

	Polyline polyLine = new Polyline();
	Point prevPoint;
	boolean startOver = false;
	Geometry bufferedArea = null;
	ArcGISFeatureLayer arcGISFeatureLayer = null;
	Graphic graphic;
	Graphic[] graphicsSeleccionados;

	public void dibujarSegment(JButton jButton, JMap map, List<ArcGISFeatureLayer> lista, JFrame frame) {
		eliminarEventoOverlay(map);
		 startOver = true;
		 prevPoint = null;
		 polyLine.setEmpty();
		 
		if (lista != null) {
			for (ArcGISFeatureLayer arcGISFeatureLayer2 : lista) {
				Graphic[] graphics = arcGISFeatureLayer2.getSelectedFeatures();
				if (graphics != null && graphics.length > 0) {
					graphicsSeleccionados = graphics;
					arcGISFeatureLayer = arcGISFeatureLayer2;
					break;
				}

			}
			final SimpleMarkerSymbol SYM_POINT = new SimpleMarkerSymbol(Color.BLACK, 6, Style.DIAMOND);
			SimpleLineSymbol SYM_LINE = new SimpleLineSymbol(Color.black, 1);
			SimpleFillSymbol SYM_BUFFER = new SimpleFillSymbol(new Color(0, 0, 255, 80), SYM_LINE);
			map.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent event) {
					if (validarPredioSeleccionado("", map, lista, frame) ==1) {
						if (event.getClickCount() == 2) {
							unirPredio(graphicsSeleccionados, map, arcGISFeatureLayer, polyLine, bufferedArea);
							// start new on next click
							 startOver = true;
							 prevPoint = null;
							 polyLine.setEmpty();
							 return;
						}

						Point currPoint = map.toMapPoint(event.getX(), event.getY());
						Graphic currPointGraphic = new Graphic(currPoint, SYM_POINT);
						arcGISFeatureLayer.addGraphic(currPointGraphic);
						if (prevPoint != null) {
							Segment segment = new Line();
							segment.setStart(prevPoint);
							segment.setEnd(currPoint);
							polyLine.addSegment(segment, false);
							Graphic lineGraphic = new Graphic(polyLine, SYM_LINE);
							arcGISFeatureLayer.addGraphic(lineGraphic);
							bufferedArea = lineGraphic.getGeometry();
						}
						prevPoint = currPoint;
						pintarLinea (map, currPoint);
					}
				}
			});

		}
	}
	
	private Point inicioArrastre;
	private Point finArrastre;
	private ArrayList<Shape> lineas = new ArrayList<Shape>();
	private ArrayList<Polyline> listaLineas = new ArrayList<Polyline>();

	public void pintarLinea (JMap map, Point currPoint) {
		map.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) { // cuando se presiona el  mouse
				inicioArrastre = new Point(e.getX(), e.getY());
				finArrastre = inicioArrastre;
				paint();
			}

			public void mouseReleased(MouseEvent e) { // cuando se deja de presionar el mouse
				System.out.println(" mouseReleased  "+inicioArrastre);
				if (inicioArrastre ==null) {
					inicioArrastre = currPoint; 					
				}
				Point point = new Point();
				point.setX(e.getX());
				point.setY(e.getY()); 
				Segment segment = new Line();
				segment.setStart(inicioArrastre);
				segment.setEnd(point);
				Polyline polyline = new Polyline();
				polyline.addSegment(segment, false);
				listaLineas.add(polyline);
				inicioArrastre = null;
				finArrastre = null;
				paint();
			}
		});
		map.addMouseMotionListener(new  MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) { // cuando se esta arrastrando el mouse
            	finArrastre = new Point(e.getX(), e.getY());
                paint();
            }
        });
		
	}
	
	  public void paint() {
//		  polyLine.addSegment(segment, false);
		  SimpleLineSymbol SYM_LINE = new SimpleLineSymbol(Color.red, 1);
//			Graphic lineGraphic = new Graphic(polyLine, SYM_LINE);
//			arcGISFeatureLayer.addGraphic(lineGraphic);
//			
//	        Graphics2D g2 = (Graphics2D) g;
//	        g2.setColor(Color.RED);
	       
	        for (Polyline linea : listaLineas) { // dibuja todos las elipses
	        	 Graphic lineGraphic = new Graphic(linea, SYM_LINE);
	        	 arcGISFeatureLayer.addGraphic(lineGraphic);
	        }
	        if (inicioArrastre != null && finArrastre != null) { // se esta arrastrando el raton?
	        	Polyline linea = new Polyline();
	        	Segment segment = new Line();
				segment.setStart(inicioArrastre);
				segment.setEnd(finArrastre);
				linea.addSegment(segment, false);
				Graphic lineGraphic = new Graphic(linea, SYM_LINE);
	        	arcGISFeatureLayer.addGraphic(lineGraphic);
	            //g2.draw(linea);
	        }
	        
	    }

	    private Line2D.Double crearLinea(double x1, double y1, double x2, double y2) {
	        return new Line2D.Double(x1, y1, x2, y2);
	    }
	
	public void unirPredio(Graphic[] graphicSeleccionado, JMap map, ArcGISFeatureLayer arcGISFeatureLayer, Polyline polyline, Geometry graphic2) {
		SimpleFillSymbol SYM_FILL_DIFF = new SimpleFillSymbol(new Color(0, 0, 200, 120), new SimpleLineSymbol(Color.RED, 6));
		Geometry geometryNuevo = obtenerNuevosGrafico(polyline);
		Symbol symbol = ObtenerSymbol(graphicSeleccionado);
		Geometry[] geometrys = obtenerListaGeometries(graphicSeleccionado, geometryNuevo);
		Polygon polygon = obtenerPolygono(graphicSeleccionado);
		
		
		
		Geometry diferencia = GeometryEngine.difference(polygon, geometryNuevo, map.getSpatialReference());
		Geometry[] geometries =listarGeometries(diferencia, geometryNuevo);
		
		Geometry geometryUnion = GeometryEngine.union(geometries, map.getSpatialReference());
		
//		Graphic graphic = new Graphic(geometryUnion, SYM_FILL_DIFF);
//		arcGISFeatureLayer.addGraphic(graphic);
		
//		Geometry geometry = obtenerGeometry(geometries);
//		Geometry shape1 =  GeometryEngine.project(geometry, map.getSpatialReference(), map.getSpatialReference());
//		Graphic graphics = new Graphic(shape1, SYM_FILL_DIFF);
//		arcGISFeatureLayer.addGraphic(graphics);
		
		//Geometry intersection = GeometryEngine.intersect(polygon,geometryNuevo, map.getSpatialReference());
		
		
		// add the result as a graphic to the map
		Graphic graphics = new Graphic(geometryUnion, symbol);
		Graphic[] graphics2 = new Graphic[1];
		graphics2[0] = graphics;
		
		arcGISFeatureLayer.applyEdits(graphics2, graphicSeleccionado, null, new CallbackListener<FeatureEditResult[][]>() {
			@Override
			public void onError(Throwable e) {
				System.out.println("error " + e.getMessage());
			}
			@Override
			public void onCallback(FeatureEditResult[][] objs) {
				System.out.println("onCallback " + objs);
				for (FeatureEditResult[] outputParameter : objs) {
					//System.out.println("outputParameter " + outputParameter);
				}
			}
		});
	}
	
	public Geometry obtenerGeometry (Geometry[] geometries) {
		Geometry geometry = null;
		Polygon polygonUnion = new Polygon();
		for (int i=0; i<geometries.length; i++) {
			Polygon polygon = (Polygon) geometries[i];
			for (int x=0; x<polygon.getPointCount(); x++) {
				Point point = polygon.getPoint(x);
				if (x== 0) {
					polygonUnion.startPath(point);
				} else {
					polygonUnion.lineTo(point);
				}
			}
		}
		geometry = polygonUnion;
		return geometry;		
	}
	
	public Geometry obtenerNuevosGrafico (Polyline polyline) {
		Polygon polygon2 = new Polygon();
		for (int i=0; i<polyline.getPointCount(); i++) { 
			Point point = polyline.getPoint(i);
			if (i ==0) {
				polygon2.startPath(point); 
			} else {
				polygon2.lineTo(point); 
			}
		}
		Geometry geometry = polygon2;
		return geometry;
	}
	
	public Symbol ObtenerSymbol (Graphic[] graphics) {
		Symbol symbol = null;
		if (graphics != null) {
			for (int i=0; i<graphics.length; i++) {
				if (i ==0 ) {
					Graphic graphic = graphics[i];
					symbol = graphic.getSymbol();
					break;
				}
			}
		}
		return symbol;
	}
	
	public Geometry[] obtenerListaGeometries (Graphic[] listaGraphics, Geometry geometrySeleccionado) {
		Geometry[] geometrys = new Geometry[listaGraphics.length+1];
		if (listaGraphics != null) {
			for (int i=0; i<listaGraphics.length; i++) {
				Graphic graphic = listaGraphics[i];
				Geometry geometry = graphic.getGeometry();
				geometrys[i] =geometry;
			}
			geometrys[listaGraphics.length]= geometrySeleccionado;
		} 
		return geometrys;
	}
	
	
	public Polygon obtenerPolygono (Graphic[] listaGraphics) {
		Polygon polygon = new Polygon();
		if (listaGraphics != null) {
			for (int i=0; i<listaGraphics.length; i++) {
				Graphic graphic = listaGraphics[i];
				Geometry geometry = graphic.getGeometry();
				Polygon polygon2 = (Polygon) geometry;
				for (int x=0; x<polygon2.getPointCount(); x++) {
					Point point = polygon2.getPoint(x);
					if (x== 0) {
						polygon.startPath(point);
					} else {
						polygon.lineTo(point);
					}
				}
			}
		}
		return polygon;
	}
	
	
	public Geometry[] listarGeometries (Geometry geometryDiferencia, Geometry geometrySeleccionado) {
		Geometry[] geometries = new Geometry[2];
		geometries[0] = geometrySeleccionado;
		geometries[1] = geometryDiferencia;
		return geometries;
	}
	
	
	public void eliminarEventoOverlay(JMap map) {
		List<MapOverlay> listaOverlay = new ArrayList<MapOverlay>(map.getMapOverlays());
		for (int i = 0; i < listaOverlay.size(); i++) {
			MapOverlay mapOverlay = listaOverlay.get(i);
			map.removeMapOverlay(mapOverlay);
		}
	}

}
