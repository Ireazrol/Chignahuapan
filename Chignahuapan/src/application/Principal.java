package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import com.esri.map.ArcGISFeatureLayer;
import com.esri.map.GroupLayer;
import com.esri.map.JMap;
import com.esri.map.MapOverlay;

import classes.LlenarCombos;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JToolBar;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;

import javax.swing.SwingConstants;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPopupMenu;
import javax.swing.ButtonGroup;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.ComponentOrientation;

public class Principal {

	static JFrame frame;
	private JMap map = new JMap();
	private GroupLayer groupLayer = new GroupLayer();
	EventoMapa eventoMapa = new EventoMapa();
	EventoCombos eventoCombos = new EventoCombos();
	Botones botones = new Botones();
	EditarPredios editarPredios = new EditarPredios();
	static List<ArcGISFeatureLayer> listaArcGisFeatureLayer = new ArrayList<ArcGISFeatureLayer>();
	@SuppressWarnings("unused")
	private final ButtonGroup buttonGroup = new ButtonGroup();
	@SuppressWarnings("unused")
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	@SuppressWarnings("unused")
	private final Action action = new SwingAction();
	LlenarCombos llenarCombos = new LlenarCombos();
	
	
	/**
	 * Declaracion de componentes para el menu
	 */
	JPanel panel = new JPanel();  
	JPanel panelPrincipal = new JPanel();
	JMenuBar menuBar = new JMenuBar();
	JMenu menuArchivo = new JMenu("Archivo");
	JMenuItem guardarMapa = new JMenuItem("Guardar mapa");
	JMenuItem configurarImpresion = new JMenuItem("Configurar impresi\u00F3n");
	JMenuItem imprimirMapa = new JMenuItem("Imprimir mapa");
	JMenuItem exportarMapa = new JMenuItem("Exportar mapa");
	JMenuItem salirDelSistema = new JMenuItem("Salir del sistema");
	JMenu vista = new JMenu("Vista");
	JMenuItem cistaGrafica = new JMenuItem("Vista graf\u00EDca");
	JMenuItem vistaDisenio = new JMenuItem("Vista dise\u00F1o");
	JMenuItem rotaLaVista = new JMenuItem("Rota la vista del documento actual");
	JMenuItem terminaHerramientaRotar = new JMenuItem("Termina Herramienta Rotar");
	JMenu edicion = new JMenu("Edici\u00F3n");
	JMenuItem deshacer = new JMenuItem("Deshacer");
	JMenuItem mRehacer = new JMenuItem("Rehacer");
	JMenuItem mCopiar = new JMenuItem("Copiar");
	JMenuItem mPegar = new JMenuItem("Pegar");
	JMenuItem mCortar = new JMenuItem("Cortar");
	JMenuItem mEliminar = new JMenuItem("Eliminar");
	JMenuItem mHerramientaAvanzada = new JMenuItem("Herramienta Avanzada de edici\u00F3n");
	JMenuItem mBarraDeVersiones = new JMenuItem("Barra de versiones");
	JMenuItem mEdicionActiva = new JMenuItem("Edici\u00F3n activa");
	JMenu mInsertar = new JMenu("Insertar");
	JMenuItem mInsertarTitulo = new JMenuItem("Insertar t\u00EDtulo");
	JMenuItem mInsertarTexto = new JMenuItem("Insertar Texto");
	JMenuItem mInsertarLeyenda = new JMenuItem("Insertar leyenda");
	JMenuItem mNorteEnElmapa = new JMenuItem("Norte en el mapa");
	JMenuItem mEscalaEnElMapa = new JMenuItem("Escala en el mapa");
	JMenuItem mTextoDeEscala = new JMenuItem("Texto de escala en el mapa");
	JMenuItem mImagenEnElMapa = new JMenuItem("Imagen en el mapa");
	JMenu mnSeleccin = new JMenu("Selecci\u00F3n");
	JMenuItem mSeleccinNormal = new JMenuItem("Selecci\u00F3n normal");
	JMenuItem mSeleccinPorUbicacin = new JMenuItem("Selecci\u00F3n por ubicaci\u00F3n");
	JMenuItem mSeleccinPorAtributos = new JMenuItem("Selecci\u00F3n por atributos");
	JMenu mnHerramientas = new JMenu("Herramientas");
	JMenuItem mHerramientasEstndar = new JMenuItem("Herramientas est\u00E1ndar");
	JMenuItem mHerramientasVistaLayout = new JMenuItem("Herramientas Vista Layout");
	JMenuItem mHerramientasDeDibujo = new JMenuItem("Herramientas de dibujo en vista Layout");
	JMenuItem mCroquisDeLocalizacin = new JMenuItem("Croquis de localizaci\u00F3n");
	JMenuItem mLocalizarSector = new JMenuItem("Localizar sector");
	JMenuItem mStreetView = new JMenuItem("Street View");
	JMenuItem mCreacinFeatures = new JMenuItem("Creaci\u00F3n features");
	JMenuItem mClculoDeFondo = new JMenuItem("C\u00E1lculo de fondo y frente");
	JMenuItem mValidacionesAnexas = new JMenuItem("Validaciones anexas");
	JMenuItem mCentroides = new JMenuItem("Centroides");
	JMenuItem mColindantes = new JMenuItem("Colindantes");
	JMenuItem mHerramientasAuxiliar = new JMenuItem("Herramientas Auxiliar");
	JMenuItem mNmerosExteriores = new JMenuItem("N\u00FAmeros exteriores");
	JMenu mnVersiones = new JMenu("Versiones");
	JMenuItem mCambiaVersin = new JMenuItem("Cambia Versi\u00F3n");
	JMenuItem mntmConciliacinYPosteo = new JMenuItem("Conciliaci\u00F3n y posteo");
	JMenu mnProcesoCatastral = new JMenu("Proceso Catastral");
	JMenuItem mPrediosPrivativos = new JMenuItem("Predios privativos");
	JMenuItem mPrediosCondominios = new JMenuItem("Predios Condominios");
	JMenu mnTrmiteCatasral = new JMenu("Tr\u00E1mite Catasral");
	JMenuItem mBandejaDeTramites = new JMenuItem("Bandeja de tramites");
	JMenuItem mFusinDePredios = new JMenuItem("Fusi\u00F3n de predios");
	JMenuItem mDivisinDePredios = new JMenuItem("Divisi\u00F3n de predios");
	JMenuItem mTramiteDeFraccionamientos = new JMenuItem("Tramite de fraccionamientos y condominios");
	JMenu mnValidacinCartogrfica = new JMenu("Validaci\u00F3n Cartogr\u00E1fica");
	JMenuItem mFraccionamientosYCondominios = new JMenuItem("Fraccionamientos y condominios");
	JMenu mnConsultaInformacin = new JMenu("Consulta informaci\u00F3n");
	JMenuItem mConsultaDeInformacin = new JMenuItem("Consulta de informaci\u00F3n");
	JMenu mnHistorial = new JMenu("Historial");
	JMenuItem mHistricoDePredios = new JMenuItem("Hist\u00F3rico de predios");
	JMenu mnActualizaCartogrfica = new JMenu("Actualiza Cartogr\u00E1fica");
	JMenuItem mReginCatastral = new JMenuItem("Regi\u00F3n Catastral");
	JMenuItem mZonaCatastral = new JMenuItem("Zona catastral");
	JMenuItem mSectorCatastral = new JMenuItem("Sector catastral");
	JMenuItem mAsentamientos = new JMenuItem("Asentamientos");
	JMenuItem mManzanas = new JMenuItem("Manzanas");
	JMenuItem mVialidades = new JMenuItem("Vialidades");
	JMenuItem mZonasHomogneas = new JMenuItem("Zonas Homog\u00E9neas");
	JMenuItem mBandasDeValor = new JMenuItem("Bandas de valor");
	JMenu mnPlanos = new JMenu("Planos");
	JMenuItem mntmPlanosCatastrales = new JMenuItem("Planos catastrales");
	JMenu mnVentanas = new JMenu("Ventanas");
	JMenuItem mVentanaContenedora = new JMenuItem("Ventana contenedora");
	JMenuItem mVisorDeMapas = new JMenuItem("Visor de mapas");
	JMenuItem mTablaDeContenido = new JMenuItem("Tabla de contenido");
	JMenuItem mntmCatlogo = new JMenuItem("Cat\u00E1logo");
	JMenuItem mntmBuscar = new JMenuItem("Buscar");
	JPanel panelButttons = new JPanel();
	JPanel panelNort = new JPanel();
	JToolBar toolBar_1 = new JToolBar();
	JMenuBar menuBar_3 = new JMenuBar();
	JButton btnZoomMas = new JButton("");
	JButton btnZoommenos = new JButton(""); 
	JButton btnMano = new JButton("");
	JButton btnFull = new JButton("");
	JButton btnMinimizar = new JButton("");
	JButton btnMaximizar = new JButton("");
	JButton btnBack = new JButton("");
	JButton btnNex = new JButton("");
	@SuppressWarnings("rawtypes")
	JComboBox comboBox_2 = new JComboBox(); 
	JButton btnClearSelected = new JButton("");
	JButton btnCursor = new JButton("");
	JButton btnIdentify = new JButton("");
	JButton btnHiperlink = new JButton("");
	JButton btnPopup = new JButton("");
	JButton btnMeasure = new JButton("");
	JButton btnFind = new JButton("");
	JButton btnFindRoute = new JButton("");
	JButton btnXy = new JButton("");
	JButton btnTimeSlider = new JButton("");
	JButton btnNewWindows = new JButton("");
	JPanel panelBotones = new JPanel();
	JToolBar toolBar_3 = new JToolBar();
	JButton btnZoomOut = new JButton("");
	JButton btnAlejar = new JButton("");
	JButton btnPan = new JButton("");
	JButton btnZoomWhole = new JButton("");
	JButton btnZoomTo = new JButton("");
	JButton btnFixedZoomOut = new JButton("");
	JButton btnFixedZoomIn = new JButton("");
	JButton btnGoBack = new JButton("");
	JButton btnGoNextTo = new JButton("");
	@SuppressWarnings("rawtypes")
	JComboBox comboBox_1 = new JComboBox();
	JButton btnTogleDraftMode = new JButton("");
	JButton btnFocusDataframe = new JButton("");
	JButton btnChangeLayout = new JButton("");
	JButton btnDataDrivePages = new JButton("");
	JToolBar toolBar = new JToolBar();
	JMenuBar menuBar_2 = new JMenuBar();
	JButton btnNuevo = new JButton("");
	JButton btnAbrir = new JButton("");
	JButton btnGuardar = new JButton("");
	JButton btnImprimir = new JButton("");
	JButton btnCortar = new JButton("");
	JButton btnCopiar = new JButton("");
	JButton btnPegar = new JButton("");
	JButton btnEliminar = new JButton("");
	JButton btnDeshacer = new JButton("");
	JButton btnSiguiente = new JButton("");
	@SuppressWarnings("rawtypes")
	JComboBox CmbBaseLayer = new JComboBox();
	@SuppressWarnings("rawtypes")
	JComboBox cmbScala = new JComboBox();
	JButton btnEditor = new JButton("");
	JButton btnTablaDeContenido = new JButton("");
	JButton btnEditor_1 = new JButton("");
	JButton btnBuscar = new JButton("");
	JButton btnHerramientas = new JButton("");
	JButton btnPhyton = new JButton("");
	JButton btnBuilder = new JButton("");
	JButton btnMapaOnline = new JButton("");
	JButton btnMapaLocal = new JButton("");
	JToolBar toolBar_2 = new JToolBar();
	JButton btnBoton = new JButton("Unir Predio");
	JButton btnBoton_2 = new JButton("Editar");
	JButton btnBoton_3 = new JButton("Boton_3");
	JButton btnBoton_4 = new JButton("Boton_4");
	JPanel panelFooter = new JPanel();
	JToolBar toolBar_5 = new JToolBar();
	JMenuBar menuBar_1 = new JMenuBar();
	JMenu mnEditor_1 = new JMenu("Editor");
	JMenuItem mntmStart_1 = new JMenuItem("Start Editing");
	JMenuItem mntmStop_1 = new JMenuItem("Stop Editing");
	JMenuItem mntmSave_1 = new JMenuItem("Save Edits");
	JMenuItem mntmMove = new JMenuItem("Move...");
	JMenuItem mntmSplit = new JMenuItem("Split...");
	JMenuItem mntmConstructsPoint = new JMenuItem("Construct Points...");
	JMenuItem mntmCopyParellel = new JMenuItem("Copy Parellel...");
	JMenuItem mntmMerge = new JMenuItem("Merge...");
	JMenuItem mntmBuffer = new JMenuItem("Buffer...");
	JMenuItem mntmUnion = new JMenuItem("Union...");
	JMenuItem mntmClip = new JMenuItem("Clip...");
	JMenuItem mntmValidateFeatures = new JMenuItem("Validate Features");
	JMenuItem mntmSnapping = new JMenuItem("Snapping");
	JMenuItem mntmMoreEdtingTool = new JMenuItem("More Editing Tools");
	JMenuItem mntmEditingWindows = new JMenuItem("Editing Windows");
	JMenuItem mntmOptions = new JMenuItem("Options...");
	JButton btnEditTool_1 = new JButton("");
	JButton btnEditTextTool = new JButton("");
	JButton btnStrainghtSegment = new JButton("");
	JButton btnEndPointArc = new JButton("");
	JButton btnTrace = new JButton("");
	JButton btnPoint = new JButton("");
	JButton btnEditVertices = new JButton("");
	JButton btnReshapeFeatureTool = new JButton("");
	JButton btnCutPolygonsTools = new JButton("");
	JButton btnSplitTools = new JButton("");
	JButton btnAtributes = new JButton("");
	JButton btnSckechtPropietaries = new JButton("");
	JButton btnCreateFeature = new JButton("");
	JButton btnHerramientaRectangulo = new JButton("");
	JButton btnHerramientaPolilinea = new JButton("");
	JButton btnHerramientaManoAlzada = new JButton("");
	JButton btnHerramientaMultipunto = new JButton("");
	JButton btnHerramientaPoligono = new JButton("");
	JPanel panelMapa = new JPanel(); 
	JPanel panelCapas = new JPanel();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@SuppressWarnings("static-access")
			public void run() {
				try {
					Principal window = new Principal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1210, 623);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel.setBackground(new Color(240, 248, 255));
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		panelPrincipal.setBackground(new Color(240, 248, 255));
		panel.add(panelPrincipal, BorderLayout.NORTH);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		panelPrincipal.add(menuBar);
		menuBar.setBackground(Color.WHITE);
		
		menuArchivo.setBackground(Color.WHITE); 
		agregarCaracteristicasMenu(menuArchivo, "Archivo");
		
		menuBar.add(menuArchivo);
		
		guardarMapa.setBackground(Color.WHITE);
		menuArchivo.add(guardarMapa);
		
		configurarImpresion.setBackground(Color.WHITE);
		menuArchivo.add(configurarImpresion);
		
		imprimirMapa.setBackground(Color.WHITE);
		menuArchivo.add(imprimirMapa);
		
		exportarMapa.setBackground(Color.WHITE);
		menuArchivo.add(exportarMapa);
		
		JSeparator separator = new JSeparator();
		menuArchivo.add(separator);
		
		salirDelSistema.setBackground(Color.WHITE);
		menuArchivo.add(salirDelSistema);
		
		menuBar.add(vista);
		
		cistaGrafica.setBackground(Color.WHITE);
		vista.add(cistaGrafica);
		
		vistaDisenio.setBackground(Color.WHITE);
		vista.add(vistaDisenio);
		
		rotaLaVista.setBackground(Color.WHITE);
		vista.add(rotaLaVista);
		
		terminaHerramientaRotar.setBackground(Color.WHITE);
		vista.add(terminaHerramientaRotar);
		
		menuBar.add(edicion);
		
		deshacer.setBackground(Color.WHITE);
		edicion.add(deshacer);
		
		mRehacer.setBackground(Color.WHITE);
		edicion.add(mRehacer);
		
		mCopiar.setBackground(Color.WHITE);
		edicion.add(mCopiar);
		
		mPegar.setBackground(Color.WHITE);
		edicion.add(mPegar);
		
		mCortar.setBackground(Color.WHITE);
		edicion.add(mCortar);
		
		mEliminar.setBackground(Color.WHITE);
		edicion.add(mEliminar);
		
		JSeparator separator_1 = new JSeparator();
		edicion.add(separator_1);
		
		mHerramientaAvanzada.setBackground(Color.WHITE);
		edicion.add(mHerramientaAvanzada);
		
		mBarraDeVersiones.setBackground(Color.WHITE);
		edicion.add(mBarraDeVersiones);
		
		JSeparator separator_2 = new JSeparator();
		edicion.add(separator_2);
		
		mEdicionActiva.setBackground(Color.WHITE);
		edicion.add(mEdicionActiva);
		
		menuBar.add(mInsertar);
		
		mInsertarTitulo.setBackground(Color.WHITE);
		mInsertar.add(mInsertarTitulo);
		
		mInsertarTexto.setBackground(Color.WHITE);
		mInsertar.add(mInsertarTexto);
		
		JSeparator separator_3 = new JSeparator();
		mInsertar.add(separator_3);
		
		mInsertarLeyenda.setBackground(Color.WHITE);
		mInsertar.add(mInsertarLeyenda);
		
		mNorteEnElmapa.setBackground(Color.WHITE);
		mInsertar.add(mNorteEnElmapa);
		
		mEscalaEnElMapa.setBackground(Color.WHITE);
		mInsertar.add(mEscalaEnElMapa);
		
		mTextoDeEscala.setBackground(Color.WHITE);
		mInsertar.add(mTextoDeEscala);
		
		JSeparator separator_4 = new JSeparator();
		mInsertar.add(separator_4);
		
		mImagenEnElMapa.setBackground(Color.WHITE);
		mInsertar.add(mImagenEnElMapa);
		
		menuBar.add(mnSeleccin);
		
		mSeleccinNormal.setBackground(Color.WHITE);
		mnSeleccin.add(mSeleccinNormal);
		
		JSeparator separator_5 = new JSeparator();
		mnSeleccin.add(separator_5);
		
		
		mSeleccinPorUbicacin.setBackground(Color.WHITE);
		mnSeleccin.add(mSeleccinPorUbicacin);
		
		mSeleccinPorAtributos.setBackground(Color.WHITE);
		mnSeleccin.add(mSeleccinPorAtributos);
		
		menuBar.add(mnHerramientas);
		
		mHerramientasEstndar.setBackground(Color.WHITE);
		mnHerramientas.add(mHerramientasEstndar);
		
		mHerramientasVistaLayout.setBackground(Color.WHITE);
		mnHerramientas.add(mHerramientasVistaLayout);
		
		mHerramientasDeDibujo.setBackground(Color.WHITE);
		mnHerramientas.add(mHerramientasDeDibujo);
		
		JSeparator separator_6 = new JSeparator();
		mnHerramientas.add(separator_6);
		
		mCroquisDeLocalizacin.setBackground(Color.WHITE);
		mnHerramientas.add(mCroquisDeLocalizacin);
		
		mLocalizarSector.setBackground(Color.WHITE);
		mnHerramientas.add(mLocalizarSector);
		
		JSeparator separator_7 = new JSeparator();
		mnHerramientas.add(separator_7);
		
		mStreetView.setBackground(Color.WHITE);
		mnHerramientas.add(mStreetView);
		
		JSeparator separator_8 = new JSeparator();
		mnHerramientas.add(separator_8);
		
		mCreacinFeatures.setBackground(Color.WHITE);
		mnHerramientas.add(mCreacinFeatures);
		
		mClculoDeFondo.setBackground(Color.WHITE);
		mnHerramientas.add(mClculoDeFondo);
		
		
		mValidacionesAnexas.setBackground(Color.WHITE);
		mnHerramientas.add(mValidacionesAnexas);
		
		
		mCentroides.setBackground(Color.WHITE);
		mnHerramientas.add(mCentroides);
		
		mColindantes.setBackground(Color.WHITE);
		mnHerramientas.add(mColindantes);
		
		mHerramientasAuxiliar.setBackground(Color.WHITE);
		mnHerramientas.add(mHerramientasAuxiliar);
		
		mNmerosExteriores.setBackground(Color.WHITE);
		mnHerramientas.add(mNmerosExteriores);
		
		menuBar.add(mnVersiones);
		
		mCambiaVersin.setBackground(Color.WHITE);
		mnVersiones.add(mCambiaVersin);
		
		mntmConciliacinYPosteo.setBackground(Color.WHITE);
		mnVersiones.add(mntmConciliacinYPosteo);
		
		menuBar.add(mnProcesoCatastral);
		
		mPrediosPrivativos.setBackground(Color.WHITE);
		mnProcesoCatastral.add(mPrediosPrivativos);
		
		mPrediosCondominios.setBackground(Color.WHITE);
		mnProcesoCatastral.add(mPrediosCondominios);
		
		menuBar.add(mnTrmiteCatasral);
		
		mBandejaDeTramites.setBackground(Color.WHITE);
		mnTrmiteCatasral.add(mBandejaDeTramites);
		
		JSeparator separator_9 = new JSeparator();
		mnTrmiteCatasral.add(separator_9);
		
		mFusinDePredios.setBackground(Color.WHITE);
		mnTrmiteCatasral.add(mFusinDePredios);
		
		mDivisinDePredios.setBackground(Color.WHITE);
		mnTrmiteCatasral.add(mDivisinDePredios);
		
		JSeparator separator_10 = new JSeparator();
		mnTrmiteCatasral.add(separator_10);
		
		mTramiteDeFraccionamientos.setBackground(Color.WHITE);
		mnTrmiteCatasral.add(mTramiteDeFraccionamientos);
		
		
		menuBar.add(mnValidacinCartogrfica);
		
		mFraccionamientosYCondominios.setBackground(Color.WHITE);
		mnValidacinCartogrfica.add(mFraccionamientosYCondominios);
		
		menuBar.add(mnConsultaInformacin);
		
		mConsultaDeInformacin.setBackground(Color.WHITE);
		mnConsultaInformacin.add(mConsultaDeInformacin);
		
		menuBar.add(mnHistorial);
		
		mHistricoDePredios.setBackground(Color.WHITE);
		mnHistorial.add(mHistricoDePredios);
		
		menuBar.add(mnActualizaCartogrfica);
		
		mReginCatastral.setBackground(Color.WHITE);
		mnActualizaCartogrfica.add(mReginCatastral);
		
		mZonaCatastral.setBackground(Color.WHITE);
		mnActualizaCartogrfica.add(mZonaCatastral);
		
		mSectorCatastral.setBackground(Color.WHITE);
		mnActualizaCartogrfica.add(mSectorCatastral);
		
		mAsentamientos.setBackground(Color.WHITE);
		mnActualizaCartogrfica.add(mAsentamientos);
		
		mManzanas.setBackground(Color.WHITE);
		mnActualizaCartogrfica.add(mManzanas);
		
		mVialidades.setBackground(Color.WHITE);
		mnActualizaCartogrfica.add(mVialidades);
		
		mZonasHomogneas.setBackground(Color.WHITE);
		mnActualizaCartogrfica.add(mZonasHomogneas);
		
		mBandasDeValor.setBackground(Color.WHITE);
		mnActualizaCartogrfica.add(mBandasDeValor);
		
		menuBar.add(mnPlanos);
		
		mntmPlanosCatastrales.setBackground(Color.WHITE);
		mnPlanos.add(mntmPlanosCatastrales);
		
		menuBar.add(mnVentanas);
		
		mVentanaContenedora.setBackground(Color.WHITE);
		mnVentanas.add(mVentanaContenedora);
		
		JSeparator separator_11 = new JSeparator();
		mnVentanas.add(separator_11);
		
		mVisorDeMapas.setBackground(Color.WHITE);
		mnVentanas.add(mVisorDeMapas);
		
		mTablaDeContenido.setBackground(Color.WHITE);
		mnVentanas.add(mTablaDeContenido);
		
		mntmCatlogo.setBackground(Color.WHITE);
		mnVentanas.add(mntmCatlogo);
		
		mntmBuscar.setBackground(Color.WHITE);
		mnVentanas.add(mntmBuscar);
		
		panelButttons.setBackground(new Color(240, 248, 255));
		panel.add(panelButttons, BorderLayout.CENTER);
		panelButttons.setLayout(new BorderLayout(0, 0));
		panelNort.setDoubleBuffered(false);
		panelNort.setEnabled(false);
		panelNort.setFocusTraversalKeysEnabled(false);
		panelNort.setFocusable(false);
		panelNort.setAlignmentY(Component.TOP_ALIGNMENT);
		panelNort.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		panelNort.setBackground(new Color(240, 248, 255));
		panelButttons.add(panelNort, BorderLayout.NORTH);
		FlowLayout fl_panelNort = new FlowLayout(FlowLayout.LEFT, 0, 0);
		panelNort.setLayout(fl_panelNort);
		toolBar_1.setAlignmentX(Component.LEFT_ALIGNMENT);
		toolBar_1.setRollover(true);
		toolBar_1.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		toolBar_1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		toolBar_1.setBackground(Color.WHITE);
		panelNort.add(toolBar_1);
		 
		menuBar_3.setBackground(Color.WHITE);
		menuBar_3.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_1.add(menuBar_3);
		
		menuBar_3.add(btnZoomMas);
		btnZoomMas.setSize(new Dimension(80, 25)); 
		btnZoomMas.setBackground(Color.WHITE);
		btnZoomMas.setToolTipText("Acercar");
		btnZoomMas.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/zoomMas.png")));
		btnZoomMas.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		
		menuBar_3.add(btnZoommenos);
		btnZoommenos.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnZoommenos.setBackground(Color.WHITE);
		btnZoommenos.setToolTipText("Alejar");
		btnZoommenos.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/alejar.png")));
		
		menuBar_3.add(btnMano);
		btnMano.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnMano.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/hand.png")));
		btnMano.setBackground(Color.WHITE);
		btnMano.setToolTipText("Pan "
				+ " Pan the map by draddgin. Click to");
		btnMano.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (map.getCursor().getType() == Cursor.HAND_CURSOR) {
					map.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				} else {
					map.setCursor(new Cursor(Cursor.HAND_CURSOR));					
				} 
			}
		});
		
		menuBar_3.add(btnFull);
		btnFull.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnFull.setToolTipText("Full");
		btnFull.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/globo.png")));
		btnFull.setBackground(Color.WHITE);
		
		JSeparator separator_12 = new JSeparator();
		menuBar_3.add(separator_12);
		separator_12.setOrientation(SwingConstants.VERTICAL);
		
		menuBar_3.add(btnMinimizar);
		btnMinimizar.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnMinimizar.setToolTipText("Fixed zoom In");
		btnMinimizar.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/minimizar.png")));
		btnMinimizar.setBackground(Color.WHITE);
		
		menuBar_3.add(btnMaximizar);
		btnMaximizar.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnMaximizar.setToolTipText("Fixed Zoom Out");
		btnMaximizar.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/maximizar.png")));
		btnMaximizar.setBackground(Color.WHITE);
		
		JSeparator separator_13 = new JSeparator();
		menuBar_3.add(separator_13);
		separator_13.setOrientation(SwingConstants.VERTICAL);
		
		menuBar_3.add(btnBack);
		btnBack.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnBack.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/back.png")));
		btnBack.setToolTipText("Go Back");
		btnBack.setBackground(Color.WHITE);
		
		menuBar_3.add(btnNex);
		btnNex.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnNex.setToolTipText("Go to next");
		btnNex.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/next.png")));
		btnNex.setBackground(Color.WHITE);
		
		JSeparator separator_14 = new JSeparator();
		menuBar_3.add(separator_14);
		separator_14.setOrientation(SwingConstants.VERTICAL);
		
		llenarCombos.llenarComboFeatures(comboBox_2);
		comboBox_2.setBackground(Color.WHITE); 
		eventoCombos.eventoCmbFeature(comboBox_2, map);
		menuBar_3.add(comboBox_2);
		
		JSeparator separator_41 = new JSeparator();
		separator_41.setOrientation(SwingConstants.VERTICAL);
		menuBar_3.add(separator_41);
		
		menuBar_3.add(btnClearSelected);
		btnClearSelected.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnClearSelected.setToolTipText("Clear Selected Feature");
		btnClearSelected.setIcon(new ImageIcon(Principal.class.getResource("/com/esri/toolkit/images/SelectionClearSelected16.png")));
		btnClearSelected.setBackground(Color.WHITE);
		
		JSeparator separator_16 = new JSeparator();
		menuBar_3.add(separator_16);
		separator_16.setOrientation(SwingConstants.VERTICAL);
		
		menuBar_3.add(btnCursor);
		btnCursor.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnCursor.setToolTipText("Select Elements");
		btnCursor.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/cursor.png")));
		btnCursor.setBackground(Color.WHITE);
		
		JSeparator separator_15 = new JSeparator();
		menuBar_3.add(separator_15);
		separator_15.setOrientation(SwingConstants.VERTICAL);
		
		menuBar_3.add(btnIdentify);
		btnIdentify.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnIdentify.setToolTipText("Identify");
		btnIdentify.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/identify.png")));
		btnIdentify.setBackground(Color.WHITE);
		
		menuBar_3.add(btnHiperlink);
		btnHiperlink.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnHiperlink.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/hiperlink.png")));
		btnHiperlink.setBackground(Color.WHITE);
		
		menuBar_3.add(btnPopup);
		btnPopup.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnPopup.setToolTipText("Popup");
		btnPopup.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/popup.png")));
		btnPopup.setBackground(Color.WHITE);
		
		JSeparator separator_17 = new JSeparator();
		menuBar_3.add(separator_17);
		separator_17.setOrientation(SwingConstants.VERTICAL);
		
		menuBar_3.add(btnMeasure);
		btnMeasure.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnMeasure.setToolTipText("Measure");
		btnMeasure.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/measure.png")));
		btnMeasure.setBackground(Color.WHITE);
		
		JSeparator separator_18 = new JSeparator();
		menuBar_3.add(separator_18);
		separator_18.setOrientation(SwingConstants.VERTICAL);
		
		menuBar_3.add(btnFind);
		btnFind.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnFind.setToolTipText("Find");
		btnFind.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/find.png")));
		btnFind.setBackground(Color.WHITE);
		
		menuBar_3.add(btnFindRoute);
		btnFindRoute.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnFindRoute.setToolTipText("Find route");
		btnFindRoute.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/findroute.png")));
		btnFindRoute.setBackground(Color.WHITE);
		
		menuBar_3.add(btnXy);
		btnXy.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnXy.setToolTipText("XY");
		btnXy.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/XY.png")));
		btnXy.setBackground(Color.WHITE);
		
		JSeparator separator_19 = new JSeparator();
		menuBar_3.add(separator_19);
		separator_19.setOrientation(SwingConstants.VERTICAL);
		
		menuBar_3.add(btnTimeSlider);
		btnTimeSlider.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnTimeSlider.setToolTipText("Time Slider");
		btnTimeSlider.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/timeslider.png")));
		btnTimeSlider.setBackground(Color.WHITE);
		
		menuBar_3.add(btnNewWindows);
		btnNewWindows.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnNewWindows.setBackground(Color.WHITE);
		btnNewWindows.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/newWindows.png")));
		btnNewWindows.setToolTipText("New Windows");
		panelBotones.setAlignmentY(Component.TOP_ALIGNMENT);
		panelBotones.setAlignmentX(Component.LEFT_ALIGNMENT);
		panelBotones.setInheritsPopupMenu(true);
		panelBotones.setIgnoreRepaint(true);
		panelBotones.setFocusTraversalPolicyProvider(true);
		panelBotones.setFocusCycleRoot(true);
		panelBotones.setAutoscrolls(true);
		
		panelBotones.setBackground(new Color(240, 248, 255));
		panelButttons.add(panelBotones, BorderLayout.WEST);
		panelBotones.setSize(200, 200);
		toolBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		toolBar.setAutoscrolls(true);
		toolBar.setDoubleBuffered(true);
		toolBar.setIgnoreRepaint(true);
		toolBar.setInheritsPopupMenu(true);
		toolBar.setFocusTraversalPolicyProvider(true);
		toolBar.setFocusCycleRoot(true);
		toolBar.setRollover(true);
		toolBar.setBackground(Color.WHITE);
		
		menuBar_2.setBackground(Color.WHITE);
		menuBar_2.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar.add(menuBar_2, BorderLayout.SOUTH);
		
		menuBar_2.add(btnNuevo);
		btnNuevo.setToolTipText("Nuevo");
		btnNuevo.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/new.png")));
		btnNuevo.setBackground(Color.WHITE);
		btnNuevo.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		
		menuBar_2.add(btnAbrir);
		btnAbrir.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnAbrir.setBackground(Color.WHITE);
		btnAbrir.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/open.png")));
		btnAbrir.setToolTipText("Abrir");
		
		menuBar_2.add(btnGuardar);
		btnGuardar.setForeground(Color.BLACK);
		btnGuardar.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/save.png")));
		btnGuardar.setToolTipText("Guardar");
		btnGuardar.setBackground(Color.WHITE);
		btnGuardar.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		
		menuBar_2.add(btnImprimir);
		btnImprimir.setBackground(Color.WHITE);
		btnImprimir.setToolTipText("Imprimir");
		btnImprimir.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/print.png")));
		btnImprimir.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		
		JSeparator separator_20 = new JSeparator();
		menuBar_2.add(separator_20);
		separator_20.setOrientation(SwingConstants.VERTICAL);
		
		menuBar_2.add(btnCortar);
		btnCortar.setToolTipText("Cortar");
		btnCortar.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/cut.png")));
		btnCortar.setBackground(Color.WHITE);
		btnCortar.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		
		menuBar_2.add(btnCopiar);
		btnCopiar.setBackground(Color.WHITE);
		btnCopiar.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/copy.png")));
		btnCopiar.setToolTipText("Copiar");
		btnCopiar.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		
		menuBar_2.add(btnPegar);
		btnPegar.setToolTipText("Pegar");
		btnPegar.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/paste.png")));
		btnPegar.setBackground(Color.WHITE);
		btnPegar.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		
		menuBar_2.add(btnEliminar);
		btnEliminar.setBackground(Color.WHITE);
		btnEliminar.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/delete.png")));
		btnEliminar.setToolTipText("Eliminar");
		btnEliminar.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		
		JSeparator separator_21 = new JSeparator();
		menuBar_2.add(separator_21);
		separator_21.setOrientation(SwingConstants.VERTICAL);
		
		menuBar_2.add(btnDeshacer);
		btnDeshacer.setToolTipText("Deshacer");
		btnDeshacer.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/undo.png")));
		btnDeshacer.setBackground(Color.WHITE);
		btnDeshacer.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		
		menuBar_2.add(btnSiguiente);
		btnSiguiente.setBackground(Color.WHITE);
		btnSiguiente.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/redo.png")));
		btnSiguiente.setToolTipText("Siguiente");
		btnSiguiente.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		
		JSeparator separator_22 = new JSeparator();
		menuBar_2.add(separator_22);
		separator_22.setOrientation(SwingConstants.VERTICAL);
		

		String[] images = {"/com/esri/client/toolkit/images/LayerGeneric16.png", 
				  "/com/esri/client/toolkit/images/LayerPolygon16.png", 
				  "/com/esri/client/toolkit/images/LayerServiceMap16.png"};
		for(int i= 0; i< images.length; i++){
			ImageIcon icon = new ImageIcon(Principal.class.getResource(images[i]));
			CmbBaseLayer.addItem(icon);
		}
		eventoCombos.CmbBaseLayer(CmbBaseLayer, map, groupLayer, frame);
		menuBar_2.add(CmbBaseLayer);
		
		JSeparator separator_23 = new JSeparator();
		menuBar_2.add(separator_23);
		separator_23.setOrientation(SwingConstants.VERTICAL);
		
		menuBar_2.add(cmbScala);
		cmbScala.setBackground(Color.WHITE);
		cmbScala.setModel(new DefaultComboBoxModel(new String[] {"1:1.000", "1:10.000", "1:24.000", "1:100.000", "1:250.000", "1:500.000", "1:750.000", "1:1.000.000", "1:3.000.000", "1:10.000.000"}));
		cmbScala.setSelectedIndex(1); 
		eventoCombos.eventoCmbScala(cmbScala, map); 
		cmbScala.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		
		JSeparator separator_24 = new JSeparator();
		menuBar_2.add(separator_24);
		separator_24.setOrientation(SwingConstants.VERTICAL);
		
		menuBar_2.add(btnEditor);
		btnEditor.setToolTipText("Editor");
		btnEditor.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/edit.png")));
		btnEditor.setBackground(Color.WHITE);
		btnEditor.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		
		JSeparator separator_25 = new JSeparator();
		menuBar_2.add(separator_25);
		separator_25.setOrientation(SwingConstants.VERTICAL);
		
		menuBar_2.add(btnTablaDeContenido);
		btnTablaDeContenido.setToolTipText("Tabla de contenido");
		btnTablaDeContenido.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/table.png")));
		btnTablaDeContenido.setBackground(Color.WHITE);
		btnTablaDeContenido.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		
		menuBar_2.add(btnEditor_1);
		btnEditor_1.setToolTipText("Editor");
		btnEditor_1.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/catalogo.png")));
		btnEditor_1.setBackground(Color.WHITE);
		btnEditor_1.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		
		menuBar_2.add(btnBuscar);
		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/search.png")));
		btnBuscar.setToolTipText("Buscar");
		btnBuscar.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		
		menuBar_2.add(btnHerramientas);
		btnHerramientas.setToolTipText("Herramientas");
		btnHerramientas.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/tools.png")));
		btnHerramientas.setBackground(Color.WHITE);
		btnHerramientas.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		
		menuBar_2.add(btnPhyton);
		btnPhyton.setToolTipText("Phyton");
		btnPhyton.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/cmd.png")));
		btnPhyton.setBackground(Color.WHITE);
		btnPhyton.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		
		JSeparator separator_26 = new JSeparator();
		menuBar_2.add(separator_26);
		separator_26.setOrientation(SwingConstants.VERTICAL);
		
		
		menuBar_2.add(btnBuilder);
		btnBuilder.setBackground(Color.WHITE);
		btnBuilder.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/grafico.png")));
		btnBuilder.setToolTipText("Builder");
		btnBuilder.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		
		menuBar_2.add(btnMapaOnline);
		btnMapaOnline.setToolTipText("Mapa online");
		btnMapaOnline.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/mapLine.png")));
		btnMapaOnline.setBackground(Color.WHITE);
		botones.eventoBotonesMapa(btnMapaOnline, map, groupLayer, 0, eventoMapa);
		btnMapaOnline.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		botones.eventoBotonesMapa(btnMapaOnline, map, groupLayer, 1, eventoMapa);
		
		menuBar_2.add(btnMapaLocal);
		btnMapaLocal.setBackground(Color.WHITE);
		btnMapaLocal.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/mapLocal.png")));
		btnMapaLocal.setToolTipText("Mapa Local");
		btnMapaLocal.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_2.setAlignmentX(Component.LEFT_ALIGNMENT);
		toolBar_2.setAutoscrolls(true);
		toolBar_2.setRollover(true);
		toolBar_2.setBackground(Color.WHITE);
		
		btnBoton.setToolTipText("Union de predio");
		btnBoton.setBackground(Color.WHITE);
		editarPredios.unirPredio(btnBoton, map); 
		btnBoton.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_2.add(btnBoton);
		
		btnBoton_2.setToolTipText("Editar");
		btnBoton_2.setBackground(Color.WHITE);
		editarPredios.unirPredio(btnBoton_2, map);
		btnBoton_2.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_2.add(btnBoton_2);
		
		btnBoton_3.setBackground(Color.WHITE);
		btnBoton_3.setToolTipText("Boton_3");
		btnBoton_3.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_2.add(btnBoton_3);
		
		btnBoton_4.setToolTipText("Boton_4");
		btnBoton_4.setBackground(Color.WHITE);
		btnBoton_4.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_2.add(btnBoton_4);
				panelBotones.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
				toolBar_3.setAlignmentX(Component.LEFT_ALIGNMENT);
				toolBar_3.setInheritsPopupMenu(true);
				toolBar_3.setIgnoreRepaint(true);
				toolBar_3.setFocusTraversalPolicyProvider(true);
				toolBar_3.setFocusCycleRoot(true);
				toolBar_3.setDoubleBuffered(true);
				toolBar_3.setAutoscrolls(true);
				toolBar_3.setRollover(true);
				toolBar_3.setBackground(Color.WHITE);
				
				btnZoomOut.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/zoom_2.png")));
				btnZoomOut.setToolTipText("Zoom Out");
				btnZoomOut.setBackground(Color.WHITE);
				btnZoomOut.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
				toolBar_3.add(btnZoomOut);
				
				btnAlejar.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/alejar_1.png")));
				btnAlejar.setBackground(Color.WHITE);
				btnAlejar.setToolTipText("Zoom In");
				btnAlejar.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
				toolBar_3.add(btnAlejar);
				
				btnPan.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/manoHoja.png")));
				btnPan.setToolTipText("Pan");
				btnPan.setBackground(Color.WHITE);
				btnPan.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
				toolBar_3.add(btnPan);
				
				btnZoomWhole.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/zoomWholePage.png")));
				btnZoomWhole.setToolTipText("Zoom Whole");
				btnZoomWhole.setBackground(Color.WHITE);
				btnZoomWhole.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
				toolBar_3.add(btnZoomWhole);
				
				btnZoomTo.setToolTipText("Zoom To 100%");
				btnZoomTo.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/archivo.png")));
				btnZoomTo.setBackground(Color.WHITE);
				btnZoomTo.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
				toolBar_3.add(btnZoomTo);
				
				JSeparator separator_27 = new JSeparator();
				separator_27.setOrientation(SwingConstants.VERTICAL);
				toolBar_3.add(separator_27);
				
				btnFixedZoomOut.setBackground(Color.WHITE);
				btnFixedZoomOut.setToolTipText("Fixed Zoom Out");
				btnFixedZoomOut.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/FixedZoomOut.png")));
				btnFixedZoomOut.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
				toolBar_3.add(btnFixedZoomOut);
				
				btnFixedZoomIn.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/FixzedZoomIn.png")));
				btnFixedZoomIn.setToolTipText("Fixed Zoom In");
				btnFixedZoomIn.setBackground(Color.WHITE);
				btnFixedZoomIn.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
				toolBar_3.add(btnFixedZoomIn);
				
				JSeparator separator_28 = new JSeparator();
				separator_28.setOrientation(SwingConstants.VERTICAL);
				toolBar_3.add(separator_28);
				
				btnGoBack.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/backPage.png")));
				btnGoBack.setBackground(Color.WHITE);
				btnGoBack.setToolTipText("Go Back to Extent");
				btnGoBack.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
				toolBar_3.add(btnGoBack);
				
				btnGoNextTo.setBackground(Color.WHITE);
				btnGoNextTo.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/goForward.png")));
				btnGoNextTo.setToolTipText("Go Forward to Extent");
				btnGoNextTo.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
				toolBar_3.add(btnGoNextTo);
				
				comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"100%"}));
				comboBox_1.setEnabled(false);
				comboBox_1.setBackground(Color.WHITE);
				comboBox_1.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
				toolBar_3.add(comboBox_1);
				
				JSeparator separator_29 = new JSeparator();
				separator_29.setOrientation(SwingConstants.VERTICAL);
				toolBar_3.add(separator_29);
				
						btnTogleDraftMode.setToolTipText("TogleDraftMode");
						btnTogleDraftMode.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/DatDriven.png")));
						btnTogleDraftMode.setBackground(Color.WHITE);
						btnTogleDraftMode.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
						toolBar_3.add(btnTogleDraftMode);
						
						btnFocusDataframe.setToolTipText("Focus DataFrame");
						btnFocusDataframe.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/focusDataFrame.png")));
						btnFocusDataframe.setBackground(Color.WHITE);
						btnFocusDataframe.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
						toolBar_3.add(btnFocusDataframe);
						
						btnChangeLayout.setToolTipText("Change Layout");
						btnChangeLayout.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/changeLayout.png")));
						btnChangeLayout.setBackground(Color.WHITE);
						btnChangeLayout.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
						toolBar_3.add(btnChangeLayout);
						
						btnDataDrivePages.setToolTipText("Data Driven Pages Toolbar");
						btnDataDrivePages.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/DtaDriven.png")));
						btnDataDrivePages.setBackground(Color.WHITE);
						btnDataDrivePages.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
						toolBar_3.add(btnDataDrivePages);
						panelBotones.add(toolBar_3);
				panelBotones.add(toolBar);
				panelBotones.add(toolBar_2);
		
		panelFooter.setAlignmentY(Component.TOP_ALIGNMENT);
		panelFooter.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		panelFooter.setBackground(new Color(240, 248, 255));
		panelButttons.add(panelFooter, BorderLayout.SOUTH);
		panelFooter.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		toolBar_5.setAlignmentX(Component.LEFT_ALIGNMENT);
		toolBar_5.setAutoscrolls(true);
		toolBar_5.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		toolBar_5.setDoubleBuffered(true);
		toolBar_5.setFocusCycleRoot(true);
		toolBar_5.setFocusTraversalPolicyProvider(true);
		toolBar_5.setIgnoreRepaint(true);
		toolBar_5.setInheritsPopupMenu(true);
		
		toolBar_5.setRollover(true);
		toolBar_5.setBackground(Color.WHITE);
		panelFooter.add(toolBar_5);
		
		menuBar_1.setBackground(Color.WHITE);
		menuBar_1.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_5.add(menuBar_1);
		
		mnEditor_1.setBackground(Color.WHITE);
		mnEditor_1.setHorizontalTextPosition(SwingConstants.LEFT ); 
		mnEditor_1.setIcon(new ImageIcon(Principal.class.getResource("/javax/swing/plaf/metal/icons/sortDown.png")));
		mnEditor_1.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		menuBar_1.add(mnEditor_1);
		
		mntmStart_1.setBackground(Color.WHITE);
		mntmStart_1.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/edit.png")));
		mnEditor_1.add(mntmStart_1);
		
		mntmStop_1.setEnabled(false);
		mntmStop_1.setBackground(Color.WHITE);
		mnEditor_1.add(mntmStop_1);
		
		mntmSave_1.setEnabled(false);
		mntmSave_1.setBackground(Color.WHITE);
		mnEditor_1.add(mntmSave_1);
		
		JSeparator separator_30 = new JSeparator();
		mnEditor_1.add(separator_30);
		
		mntmMove.setEnabled(false);
		mntmMove.setBackground(Color.WHITE);
		mnEditor_1.add(mntmMove);
		
		mntmSplit.setEnabled(false);
		mntmSplit.setBackground(Color.WHITE);
		mnEditor_1.add(mntmSplit);
		
		mntmConstructsPoint.setEnabled(false);
		mntmConstructsPoint.setBackground(Color.WHITE);
		mnEditor_1.add(mntmConstructsPoint);
		
		mntmCopyParellel.setEnabled(false);
		mntmCopyParellel.setBackground(Color.WHITE);
		mnEditor_1.add(mntmCopyParellel);
		
		mntmMerge.setEnabled(false);
		mntmMerge.setBackground(Color.WHITE);
		mnEditor_1.add(mntmMerge);
		
		mntmBuffer.setEnabled(false);
		mntmBuffer.setBackground(Color.WHITE);
		mnEditor_1.add(mntmBuffer);
		
		mntmUnion.setEnabled(false);
		mntmUnion.setBackground(Color.WHITE);
		mnEditor_1.add(mntmUnion);
		
		mntmClip.setEnabled(false);
		mntmClip.setBackground(Color.WHITE);
		mnEditor_1.add(mntmClip);
		
		JSeparator separator_37 = new JSeparator();
		mnEditor_1.add(separator_37);

		mntmValidateFeatures.setEnabled(false);
		mntmValidateFeatures.setBackground(Color.WHITE);
		mnEditor_1.add(mntmValidateFeatures);
		
		JSeparator separator_38 = new JSeparator();
		mnEditor_1.add(separator_38);
		
		mntmSnapping.setBackground(Color.WHITE);
		mnEditor_1.add(mntmSnapping);
		
		JSeparator separator_39 = new JSeparator();
		mnEditor_1.add(separator_39);
		
		mntmMoreEdtingTool.setBackground(Color.WHITE);
		mnEditor_1.add(mntmMoreEdtingTool);
		
		mntmEditingWindows.setBackground(Color.WHITE);
		mnEditor_1.add(mntmEditingWindows);
		
		JSeparator separator_40 = new JSeparator();
		mnEditor_1.add(separator_40);
		
		mntmOptions.setBackground(Color.WHITE);
		mnEditor_1.add(mntmOptions);
		
		JSeparator separator_36 = new JSeparator();
		menuBar_1.add(separator_36);
		separator_36.setOrientation(SwingConstants.VERTICAL);
		
		btnEditTool_1.setEnabled(false);
		btnEditTool_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				eventoMapa.eliminarEventoOverlay(map); 
				eliminarEventoMouseListener (map);
				deshabilitarBotonesEditVertice();
				map.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				eventoMapa.agregarEvento(map, listaArcGisFeatureLayer);
			}
		});
		menuBar_1.add(btnEditTool_1);
		btnEditTool_1.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/editTol.png")));
		btnEditTool_1.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnEditTool_1.setBackground(Color.WHITE);
		
		btnEditTextTool.setEnabled(false);
		btnEditTextTool.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/editTextTool.png")));
		btnEditTextTool.setToolTipText("Edit Text Tool");
		btnEditTextTool.setBackground(Color.WHITE);
		btnEditTextTool.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		menuBar_1.add(btnEditTextTool);
		
		JSeparator separator_31 = new JSeparator();
		separator_31.setOrientation(SwingConstants.VERTICAL);
		menuBar_1.add(separator_31);
		
		btnStrainghtSegment.setEnabled(false);
		btnStrainghtSegment.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/straingSegment.png")));
		btnStrainghtSegment.setToolTipText("Straight Segment");
		btnStrainghtSegment.setBackground(Color.WHITE);
		btnStrainghtSegment.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		menuBar_1.add(btnStrainghtSegment);
		btnStrainghtSegment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				map.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
				eventoMapa.dibujarSegment(btnStrainghtSegment, map, listaArcGisFeatureLayer, frame);
			}
		});
		
		
		btnEndPointArc.setEnabled(false);
		btnEndPointArc.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/arcSegment.png")));
		btnEndPointArc.setToolTipText("End Point Arc Segment");
		btnEndPointArc.setBackground(Color.WHITE);
		btnEndPointArc.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		menuBar_1.add(btnEndPointArc);
		
		btnTrace.setEnabled(false);
		btnTrace.setToolTipText("Trace");
		btnTrace.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/trace1.png")));
		btnTrace.setBackground(Color.WHITE);
		btnTrace.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		menuBar_1.add(btnTrace);
		
		btnPoint.setEnabled(false);
		btnPoint.setBackground(Color.WHITE);
		btnPoint.setToolTipText("Point");
		btnPoint.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/point.png")));
		btnPoint.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		menuBar_1.add(btnPoint);
		
		JSeparator separator_32 = new JSeparator();
		separator_32.setOrientation(SwingConstants.VERTICAL);
		menuBar_1.add(separator_32);
		
		btnEditVertices.setEnabled(false);
		btnEditVertices.setToolTipText("Edit Vertices");
		btnEditVertices.setIcon(new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/EditingEditVertices16.png")));
		btnEditVertices.setBackground(Color.WHITE);
		btnEditVertices.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		menuBar_1.add(btnEditVertices);
		btnEditVertices.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (eventoMapa.validarPredioSeleccionado("", map, listaArcGisFeatureLayer, frame) == 1) {
					deshabilitarBotonesEditVertice () ;					
				}
			}
		});
		
		btnReshapeFeatureTool.setEnabled(false);
		btnReshapeFeatureTool.setToolTipText("Reshape Feature Tool");
		btnReshapeFeatureTool.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/trace.png")));
		btnReshapeFeatureTool.setBackground(Color.WHITE);
		btnReshapeFeatureTool.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		menuBar_1.add(btnReshapeFeatureTool);
		btnReshapeFeatureTool.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (eventoMapa.validarPredioSeleccionado("", map, listaArcGisFeatureLayer, frame) == 1) {
					hablitarBotonesReshapeFeature ();
				}
			}
		});
		
		
		btnCutPolygonsTools.setEnabled(false);
		btnCutPolygonsTools.setToolTipText("Cut polygons Tools");
		btnCutPolygonsTools.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/cutPolygono.png")));
		btnCutPolygonsTools.setBackground(Color.WHITE);
		btnCutPolygonsTools.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		menuBar_1.add(btnCutPolygonsTools);
		
		btnSplitTools.setEnabled(false);
		btnSplitTools.setToolTipText("Split Tool");
		btnSplitTools.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/splitTool.png")));
		btnSplitTools.setBackground(Color.WHITE);
		btnSplitTools.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		menuBar_1.add(btnSplitTools);
		
		JSeparator separator_33 = new JSeparator();
		separator_33.setOrientation(SwingConstants.VERTICAL);
		menuBar_1.add(separator_33);
		
		btnAtributes.setEnabled(false);
		btnAtributes.setToolTipText("Atributes");
		btnAtributes.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/attributes.png")));
		btnAtributes.setBackground(Color.WHITE);
		btnAtributes.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		menuBar_1.add(btnAtributes);
		
		btnSckechtPropietaries.setEnabled(false);
		btnSckechtPropietaries.setToolTipText("Scketch Propietaries");
		btnSckechtPropietaries.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/skecht.png")));
		btnSckechtPropietaries.setBackground(Color.WHITE);
		btnSckechtPropietaries.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		menuBar_1.add(btnSckechtPropietaries);
		
		JSeparator separator_34 = new JSeparator();
		separator_34.setOrientation(SwingConstants.VERTICAL);
		menuBar_1.add(separator_34);
		
		btnCreateFeature.setEnabled(false);
		btnCreateFeature.setToolTipText("Create Feature");
		btnCreateFeature.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/createFeatures.png")));
		btnCreateFeature.setBackground(Color.WHITE);
		btnCreateFeature.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		menuBar_1.add(btnCreateFeature);
		
		JSeparator separator_35 = new JSeparator();
		separator_35.setOrientation(SwingConstants.VERTICAL);
		menuBar_1.add(separator_35);
		
		btnHerramientaRectangulo.setEnabled(false);
		btnHerramientaRectangulo.setToolTipText("Herramienta Rectangulo");
		btnHerramientaRectangulo.setIcon(new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/EditingRectangleTool16.png")));
		btnHerramientaRectangulo.setBackground(Color.WHITE);
		btnHerramientaRectangulo.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		menuBar_1.add(btnHerramientaRectangulo);
		
		btnHerramientaPolilinea.setEnabled(false);
		btnHerramientaPolilinea.setToolTipText("Herramienta Polil\u00EDnea");
		btnHerramientaPolilinea.setIcon(new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/EditingLineTool16.png")));
		btnHerramientaPolilinea.setBackground(Color.WHITE);
		btnHerramientaPolilinea.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		menuBar_1.add(btnHerramientaPolilinea);
		
		btnHerramientaManoAlzada.setEnabled(false);
		btnHerramientaManoAlzada.setIcon(new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/EditingFreehandTool16.png")));
		btnHerramientaManoAlzada.setToolTipText("Herramienta Mano Alzada");
		btnHerramientaManoAlzada.setBackground(Color.WHITE);
		btnHerramientaManoAlzada.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		menuBar_1.add(btnHerramientaManoAlzada);
		
		btnHerramientaMultipunto.setEnabled(false);
		btnHerramientaMultipunto.setIcon(new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/EditingMultiPointTool16.png")));
		btnHerramientaMultipunto.setToolTipText("Herramienta Multipunto");
		btnHerramientaMultipunto.setBackground(Color.WHITE);
		btnHerramientaMultipunto.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		menuBar_1.add(btnHerramientaMultipunto);
		
		btnHerramientaPoligono.setEnabled(false);
		btnHerramientaPoligono.setIcon(new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/EditingPolygonTool16.png")));
		btnHerramientaPoligono.setToolTipText("Herramienta Pol\u00EDgono");
		btnHerramientaPoligono.setBackground(Color.WHITE);
		btnHerramientaPoligono.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		menuBar_1.add(btnHerramientaPoligono);
		
		
		panelMapa.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		panelMapa.setBackground(new Color(240, 248, 255));
		frame.getContentPane().add(panelMapa, BorderLayout.CENTER);
		panelMapa.setLayout(new BorderLayout(0, 0));
		eventoMapa.crearMapaPuebla(groupLayer, map);
		frame.addWindowListener(new WindowAdapter() {
	      @Override
	      public void windowClosing(WindowEvent windowEvent) {
	        super.windowClosing(windowEvent);
	        map.dispose(); 
	        //eventoMapa.agregarEvento(map);
	      }
	    });
		panelMapa.add(map, -1);
		
		panelCapas.setBackground(new Color(240, 248, 255));
		frame.getContentPane().add(panelCapas, BorderLayout.WEST);
		panelCapas.setPreferredSize(new Dimension(300, 660));
		panelCapas.setLayout(new BorderLayout(0, 0));
		eventoMapa.crearMenuCapas(map, panelCapas);
		mntmStart_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				habilitarBotonesStart ();
			}
		});
		
		
	}
	
	public void agregarCaracteristicasMenu (JMenu menu, String decripcion) {
		menu.setMnemonic(KeyEvent.VK_F);
		menu.getAccessibleContext().setAccessibleDescription(decripcion);
	}

	@SuppressWarnings("unused")
	private static void addPopup(Component component, final JPopupMenu popup) {
	}
	@SuppressWarnings("serial")
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	
	
	public void habilitarBotonesStart () {
		mntmStop_1.setEnabled(true);
		mntmSave_1.setEnabled(true);
		mntmMove.setEnabled(true);
		mntmSplit.setEnabled(true);
		mntmConstructsPoint.setEnabled(true);
		mntmCopyParellel.setEnabled(true);
		mntmMerge.setEnabled(true);
		mntmBuffer.setEnabled(true);
		mntmUnion.setEnabled(true);
		mntmClip.setEnabled(true);
		btnEditTool_1.setEnabled(true);
		btnEditVertices.setEnabled(true);
		btnReshapeFeatureTool.setEnabled(true);
		btnCutPolygonsTools.setEnabled(true);
		btnAtributes.setEnabled(true);
		btnSckechtPropietaries.setEnabled(true);
		btnCreateFeature.setEnabled(true);
		
	} 
	
	public void hablitarBotonesReshapeFeature () {
		btnStrainghtSegment.setEnabled(true);
		btnEndPointArc.setEnabled(true);
		btnTrace.setEnabled(true);
	}
	
	public void deshabilitarBotonesEditVertice () {
		btnStrainghtSegment.setEnabled(false);
		btnEndPointArc.setEnabled(false);
		btnTrace.setEnabled(false);
	}
	
	public void eliminarEventoMouseListener (JMap map) { 
		if (map.getMouseListeners().length >1) {
			MouseListener[] listaMouseListener = map.getMouseListeners();
			for (int i=0; i<listaMouseListener.length; i++) {
				if (i>0) {
					map.removeMouseListener(listaMouseListener[i]);
				}
			}
		}
		System.out.println("mouse listener " +map.getMouseListeners().length);
	}
	
	
	
}
