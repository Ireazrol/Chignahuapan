package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import com.esri.map.ArcGISFeatureLayer;
import com.esri.map.ArcGISTiledMapServiceLayer;
import com.esri.map.GroupLayer;
import com.esri.map.JMap;
import com.esri.map.LayerList;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.ScrollPane;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.Component;
import java.awt.Cursor;

import javax.swing.JToolBar;
import java.awt.Scrollbar;
import java.awt.Toolkit;

import javax.swing.JSplitPane;
import java.awt.Panel;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import java.awt.Button;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JTree;
import java.awt.Choice;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Principal {

	private JFrame frame;
	private JMap map = new JMap();
	private GroupLayer groupLayer = new GroupLayer();
	EventoMapa eventoMapa = new EventoMapa();
	EventoCombos eventoCombos = new EventoCombos();
	Botones botones = new Botones();
	EditarPredios editarPredios = new EditarPredios();
	static List<ArcGISFeatureLayer> listaArcGisFeatureLayer = new ArrayList<ArcGISFeatureLayer>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
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
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1126, 623);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();  
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		panel.setBackground(new Color(240, 248, 255));
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(new Color(240, 248, 255));
		panel.add(panelPrincipal, BorderLayout.NORTH);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		JMenuBar menuBar = new JMenuBar();
		panelPrincipal.add(menuBar);
		menuBar.setBackground(Color.WHITE);
		
		JMenu menuArchivo = new JMenu("Archivo");
		menuArchivo.setBackground(Color.WHITE);
		agregarCaracteristicasMenu(menuArchivo, "Archivo");
		
		menuBar.add(menuArchivo);
		
		JMenuItem guardarMapa = new JMenuItem("Guardar mapa");
		guardarMapa.setBackground(Color.WHITE);
		menuArchivo.add(guardarMapa);
		
		JMenuItem configurarImpresion = new JMenuItem("Configurar impresi\u00F3n");
		configurarImpresion.setBackground(Color.WHITE);
		menuArchivo.add(configurarImpresion);
		
		JMenuItem imprimirMapa = new JMenuItem("Imprimir mapa");
		imprimirMapa.setBackground(Color.WHITE);
		menuArchivo.add(imprimirMapa);
		
		JMenuItem exportarMapa = new JMenuItem("Exportar mapa");
		exportarMapa.setBackground(Color.WHITE);
		menuArchivo.add(exportarMapa);
		
		JSeparator separator = new JSeparator();
		menuArchivo.add(separator);
		
		JMenuItem salirDelSistema = new JMenuItem("Salir del sistema");
		salirDelSistema.setBackground(Color.WHITE);
		menuArchivo.add(salirDelSistema);
		
		JMenu vista = new JMenu("Vista");
		menuBar.add(vista);
		
		JMenuItem cistaGrafica = new JMenuItem("Vista graf\u00EDca");
		cistaGrafica.setBackground(Color.WHITE);
		vista.add(cistaGrafica);
		
		JMenuItem vistaDisenio = new JMenuItem("Vista dise\u00F1o");
		vistaDisenio.setBackground(Color.WHITE);
		vista.add(vistaDisenio);
		
		JMenuItem rotaLaVista = new JMenuItem("Rota la vista del documento actual");
		rotaLaVista.setBackground(Color.WHITE);
		vista.add(rotaLaVista);
		
		JMenuItem terminaHerramientaRotar = new JMenuItem("Termina Herramienta Rotar");
		terminaHerramientaRotar.setBackground(Color.WHITE);
		vista.add(terminaHerramientaRotar);
		
		JMenu edicion = new JMenu("Edici\u00F3n");
		menuBar.add(edicion);
		
		JMenuItem deshacer = new JMenuItem("Deshacer");
		deshacer.setBackground(Color.WHITE);
		edicion.add(deshacer);
		
		JMenuItem mRehacer = new JMenuItem("Rehacer");
		mRehacer.setBackground(Color.WHITE);
		edicion.add(mRehacer);
		
		JMenuItem mCopiar = new JMenuItem("Copiar");
		mCopiar.setBackground(Color.WHITE);
		edicion.add(mCopiar);
		
		JMenuItem mPegar = new JMenuItem("Pegar");
		mPegar.setBackground(Color.WHITE);
		edicion.add(mPegar);
		
		JMenuItem mCortar = new JMenuItem("Cortar");
		mCortar.setBackground(Color.WHITE);
		edicion.add(mCortar);
		
		JMenuItem mEliminar = new JMenuItem("Eliminar");
		mEliminar.setBackground(Color.WHITE);
		edicion.add(mEliminar);
		
		JSeparator separator_1 = new JSeparator();
		edicion.add(separator_1);
		
		JMenuItem mHerramientaAvanzada = new JMenuItem("Herramienta Avanzada de edici\u00F3n");
		mHerramientaAvanzada.setBackground(Color.WHITE);
		edicion.add(mHerramientaAvanzada);
		
		JMenuItem mBarraDeVersiones = new JMenuItem("Barra de versiones");
		mBarraDeVersiones.setBackground(Color.WHITE);
		edicion.add(mBarraDeVersiones);
		
		JSeparator separator_2 = new JSeparator();
		edicion.add(separator_2);
		
		JMenuItem mEdicionActiva = new JMenuItem("Edici\u00F3n activa");
		mEdicionActiva.setBackground(Color.WHITE);
		edicion.add(mEdicionActiva);
		
		JMenu mInsertar = new JMenu("Insertar");
		menuBar.add(mInsertar);
		
		JMenuItem mInsertarTitulo = new JMenuItem("Insertar t\u00EDtulo");
		mInsertarTitulo.setBackground(Color.WHITE);
		mInsertar.add(mInsertarTitulo);
		
		JMenuItem mInsertarTexto = new JMenuItem("Insertar Texto");
		mInsertarTexto.setBackground(Color.WHITE);
		mInsertar.add(mInsertarTexto);
		
		JSeparator separator_3 = new JSeparator();
		mInsertar.add(separator_3);
		
		JMenuItem mInsertarLeyenda = new JMenuItem("Insertar leyenda");
		mInsertarLeyenda.setBackground(Color.WHITE);
		mInsertar.add(mInsertarLeyenda);
		
		JMenuItem mNorteEnElmapa = new JMenuItem("Norte en el mapa");
		mNorteEnElmapa.setBackground(Color.WHITE);
		mInsertar.add(mNorteEnElmapa);
		
		JMenuItem mEscalaEnElMapa = new JMenuItem("Escala en el mapa");
		mEscalaEnElMapa.setBackground(Color.WHITE);
		mInsertar.add(mEscalaEnElMapa);
		
		JMenuItem mTextoDeEscala = new JMenuItem("Texto de escala en el mapa");
		mTextoDeEscala.setBackground(Color.WHITE);
		mInsertar.add(mTextoDeEscala);
		
		JSeparator separator_4 = new JSeparator();
		mInsertar.add(separator_4);
		
		JMenuItem mImagenEnElMapa = new JMenuItem("Imagen en el mapa");
		mImagenEnElMapa.setBackground(Color.WHITE);
		mInsertar.add(mImagenEnElMapa);
		
		JMenu mnSeleccin = new JMenu("Selecci\u00F3n");
		menuBar.add(mnSeleccin);
		
		JMenuItem mSeleccinNormal = new JMenuItem("Selecci\u00F3n normal");
		mSeleccinNormal.setBackground(Color.WHITE);
		mnSeleccin.add(mSeleccinNormal);
		
		JSeparator separator_5 = new JSeparator();
		mnSeleccin.add(separator_5);
		
		JMenuItem mSeleccinPorUbicacin = new JMenuItem("Selecci\u00F3n por ubicaci\u00F3n");
		mSeleccinPorUbicacin.setBackground(Color.WHITE);
		mnSeleccin.add(mSeleccinPorUbicacin);
		
		JMenuItem mSeleccinPorAtributos = new JMenuItem("Selecci\u00F3n por atributos");
		mSeleccinPorAtributos.setBackground(Color.WHITE);
		mnSeleccin.add(mSeleccinPorAtributos);
		
		JMenu mnHerramientas = new JMenu("Herramientas");
		menuBar.add(mnHerramientas);
		
		JMenuItem mHerramientasEstndar = new JMenuItem("Herramientas est\u00E1ndar");
		mHerramientasEstndar.setBackground(Color.WHITE);
		mnHerramientas.add(mHerramientasEstndar);
		
		JMenuItem mHerramientasVistaLayout = new JMenuItem("Herramientas Vista Layout");
		mHerramientasVistaLayout.setBackground(Color.WHITE);
		mnHerramientas.add(mHerramientasVistaLayout);
		
		JMenuItem mHerramientasDeDibujo = new JMenuItem("Herramientas de dibujo en vista Layout");
		mHerramientasDeDibujo.setBackground(Color.WHITE);
		mnHerramientas.add(mHerramientasDeDibujo);
		
		JSeparator separator_6 = new JSeparator();
		mnHerramientas.add(separator_6);
		
		JMenuItem mCroquisDeLocalizacin = new JMenuItem("Croquis de localizaci\u00F3n");
		mCroquisDeLocalizacin.setBackground(Color.WHITE);
		mnHerramientas.add(mCroquisDeLocalizacin);
		
		JMenuItem mLocalizarSector = new JMenuItem("Localizar sector");
		mLocalizarSector.setBackground(Color.WHITE);
		mnHerramientas.add(mLocalizarSector);
		
		JSeparator separator_7 = new JSeparator();
		mnHerramientas.add(separator_7);
		
		JMenuItem mStreetView = new JMenuItem("Street View");
		mStreetView.setBackground(Color.WHITE);
		mnHerramientas.add(mStreetView);
		
		JSeparator separator_8 = new JSeparator();
		mnHerramientas.add(separator_8);
		
		JMenuItem mCreacinFeatures = new JMenuItem("Creaci\u00F3n features");
		mCreacinFeatures.setBackground(Color.WHITE);
		mnHerramientas.add(mCreacinFeatures);
		
		JMenuItem mClculoDeFondo = new JMenuItem("C\u00E1lculo de fondo y frente");
		mClculoDeFondo.setBackground(Color.WHITE);
		mnHerramientas.add(mClculoDeFondo);
		
		JMenuItem mValidacionesAnexas = new JMenuItem("Validaciones anexas");
		mValidacionesAnexas.setBackground(Color.WHITE);
		mnHerramientas.add(mValidacionesAnexas);
		
		JMenuItem mCentroides = new JMenuItem("Centroides");
		mCentroides.setBackground(Color.WHITE);
		mnHerramientas.add(mCentroides);
		
		JMenuItem mColindantes = new JMenuItem("Colindantes");
		mColindantes.setBackground(Color.WHITE);
		mnHerramientas.add(mColindantes);
		
		JMenuItem mHerramientasAuxiliar = new JMenuItem("Herramientas Auxiliar");
		mHerramientasAuxiliar.setBackground(Color.WHITE);
		mnHerramientas.add(mHerramientasAuxiliar);
		
		JMenuItem mNmerosExteriores = new JMenuItem("N\u00FAmeros exteriores");
		mNmerosExteriores.setBackground(Color.WHITE);
		mnHerramientas.add(mNmerosExteriores);
		
		JMenu mnVersiones = new JMenu("Versiones");
		menuBar.add(mnVersiones);
		
		JMenuItem mCambiaVersin = new JMenuItem("Cambia Versi\u00F3n");
		mCambiaVersin.setBackground(Color.WHITE);
		mnVersiones.add(mCambiaVersin);
		
		JMenuItem mntmConciliacinYPosteo = new JMenuItem("Conciliaci\u00F3n y posteo");
		mntmConciliacinYPosteo.setBackground(Color.WHITE);
		mnVersiones.add(mntmConciliacinYPosteo);
		
		JMenu mnProcesoCatastral = new JMenu("Proceso Catastral");
		menuBar.add(mnProcesoCatastral);
		
		JMenuItem mPrediosPrivativos = new JMenuItem("Predios privativos");
		mPrediosPrivativos.setBackground(Color.WHITE);
		mnProcesoCatastral.add(mPrediosPrivativos);
		
		JMenuItem mPrediosCondominios = new JMenuItem("Predios Condominios");
		mPrediosCondominios.setBackground(Color.WHITE);
		mnProcesoCatastral.add(mPrediosCondominios);
		
		JMenu mnTrmiteCatasral = new JMenu("Tr\u00E1mite Catasral");
		menuBar.add(mnTrmiteCatasral);
		
		JMenuItem mBandejaDeTramites = new JMenuItem("Bandeja de tramites");
		mBandejaDeTramites.setBackground(Color.WHITE);
		mnTrmiteCatasral.add(mBandejaDeTramites);
		
		JSeparator separator_9 = new JSeparator();
		mnTrmiteCatasral.add(separator_9);
		
		JMenuItem mFusinDePredios = new JMenuItem("Fusi\u00F3n de predios");
		mFusinDePredios.setBackground(Color.WHITE);
		mnTrmiteCatasral.add(mFusinDePredios);
		
		JMenuItem mDivisinDePredios = new JMenuItem("Divisi\u00F3n de predios");
		mDivisinDePredios.setBackground(Color.WHITE);
		mnTrmiteCatasral.add(mDivisinDePredios);
		
		JSeparator separator_10 = new JSeparator();
		mnTrmiteCatasral.add(separator_10);
		
		JMenuItem mTramiteDeFraccionamientos = new JMenuItem("Tramite de fraccionamientos y condominios");
		mTramiteDeFraccionamientos.setBackground(Color.WHITE);
		mnTrmiteCatasral.add(mTramiteDeFraccionamientos);
		
		JMenu mnValidacinCartogrfica = new JMenu("Validaci\u00F3n Cartogr\u00E1fica");
		menuBar.add(mnValidacinCartogrfica);
		
		JMenuItem mFraccionamientosYCondominios = new JMenuItem("Fraccionamientos y condominios");
		mFraccionamientosYCondominios.setBackground(Color.WHITE);
		mnValidacinCartogrfica.add(mFraccionamientosYCondominios);
		
		JMenu mnConsultaInformacin = new JMenu("Consulta informaci\u00F3n");
		menuBar.add(mnConsultaInformacin);
		
		JMenuItem mConsultaDeInformacin = new JMenuItem("Consulta de informaci\u00F3n");
		mConsultaDeInformacin.setBackground(Color.WHITE);
		mnConsultaInformacin.add(mConsultaDeInformacin);
		
		JMenu mnHistorial = new JMenu("Historial");
		menuBar.add(mnHistorial);
		
		JMenuItem mHistricoDePredios = new JMenuItem("Hist\u00F3rico de predios");
		mHistricoDePredios.setBackground(Color.WHITE);
		mnHistorial.add(mHistricoDePredios);
		
		JMenu mnActualizaCartogrfica = new JMenu("Actualiza Cartogr\u00E1fica");
		menuBar.add(mnActualizaCartogrfica);
		
		JMenuItem mReginCatastral = new JMenuItem("Regi\u00F3n Catastral");
		mReginCatastral.setBackground(Color.WHITE);
		mnActualizaCartogrfica.add(mReginCatastral);
		
		JMenuItem mZonaCatastral = new JMenuItem("Zona catastral");
		mZonaCatastral.setBackground(Color.WHITE);
		mnActualizaCartogrfica.add(mZonaCatastral);
		
		JMenuItem mSectorCatastral = new JMenuItem("Sector catastral");
		mSectorCatastral.setBackground(Color.WHITE);
		mnActualizaCartogrfica.add(mSectorCatastral);
		
		JMenuItem mAsentamientos = new JMenuItem("Asentamientos");
		mAsentamientos.setBackground(Color.WHITE);
		mnActualizaCartogrfica.add(mAsentamientos);
		
		JMenuItem mManzanas = new JMenuItem("Manzanas");
		mManzanas.setBackground(Color.WHITE);
		mnActualizaCartogrfica.add(mManzanas);
		
		JMenuItem mVialidades = new JMenuItem("Vialidades");
		mVialidades.setBackground(Color.WHITE);
		mnActualizaCartogrfica.add(mVialidades);
		
		JMenuItem mZonasHomogneas = new JMenuItem("Zonas Homog\u00E9neas");
		mZonasHomogneas.setBackground(Color.WHITE);
		mnActualizaCartogrfica.add(mZonasHomogneas);
		
		JMenuItem mBandasDeValor = new JMenuItem("Bandas de valor");
		mBandasDeValor.setBackground(Color.WHITE);
		mnActualizaCartogrfica.add(mBandasDeValor);
		
		JMenu mnPlanos = new JMenu("Planos");
		menuBar.add(mnPlanos);
		
		JMenuItem mntmPlanosCatastrales = new JMenuItem("Planos catastrales");
		mntmPlanosCatastrales.setBackground(Color.WHITE);
		mnPlanos.add(mntmPlanosCatastrales);
		
		JMenu mnVentanas = new JMenu("Ventanas");
		menuBar.add(mnVentanas);
		
		JMenuItem mVentanaContenedora = new JMenuItem("Ventana contenedora");
		mVentanaContenedora.setBackground(Color.WHITE);
		mnVentanas.add(mVentanaContenedora);
		
		JSeparator separator_11 = new JSeparator();
		mnVentanas.add(separator_11);
		
		JMenuItem mVisorDeMapas = new JMenuItem("Visor de mapas");
		mVisorDeMapas.setBackground(Color.WHITE);
		mnVentanas.add(mVisorDeMapas);
		
		JMenuItem mTablaDeContenido = new JMenuItem("Tabla de contenido");
		mTablaDeContenido.setBackground(Color.WHITE);
		mnVentanas.add(mTablaDeContenido);
		
		JMenuItem mntmCatlogo = new JMenuItem("Cat\u00E1logo");
		mntmCatlogo.setBackground(Color.WHITE);
		mnVentanas.add(mntmCatlogo);
		
		JMenuItem mntmBuscar = new JMenuItem("Buscar");
		mntmBuscar.setBackground(Color.WHITE);
		mnVentanas.add(mntmBuscar);
		
		JPanel panelButttons = new JPanel();
		panelButttons.setBackground(new Color(240, 248, 255));
		panel.add(panelButttons, BorderLayout.CENTER);
		panelButttons.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNort = new JPanel();
		panelNort.setBackground(new Color(240, 248, 255));
		panelButttons.add(panelNort, BorderLayout.NORTH);
		Border bored = BorderFactory.createLineBorder(Color.white,1);
		panelNort.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar_4 = new JToolBar();
		toolBar_4.setBackground(Color.WHITE);
		panelNort.add(toolBar_4, BorderLayout.WEST);
		
		JButton btnZoomMas = new JButton("");
		btnZoomMas.setSize(new Dimension(80, 25)); 
		btnZoomMas.setBackground(Color.WHITE);
		btnZoomMas.setToolTipText("Acercar");
		btnZoomMas.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/zoomMas.png")));
		btnZoomMas.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_4.add(btnZoomMas);
		
		JButton btnZoommenos = new JButton(""); 
		btnZoommenos.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnZoommenos.setBackground(Color.WHITE);
		btnZoommenos.setToolTipText("Alejar");
		btnZoommenos.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/alejar.png")));
		btnZoommenos.setMargin(new Insets(0, 500, 0, 2000)); 
		toolBar_4.add(btnZoommenos);
		
		JButton btnMano = new JButton("");
		btnMano.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnMano.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/hand.png")));
		btnMano.setBackground(Color.WHITE);
		toolBar_4.add(btnMano);
		
		JButton btnFull = new JButton("");
		btnFull.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnFull.setToolTipText("Full");
		btnFull.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/globo.png")));
		btnFull.setBackground(Color.WHITE);
		toolBar_4.add(btnFull);
		
		JSeparator separator_12 = new JSeparator();
		separator_12.setOrientation(SwingConstants.VERTICAL);
		toolBar_4.add(separator_12);
		
		JButton btnMinimizar = new JButton("");
		btnMinimizar.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnMinimizar.setToolTipText("Fixed zoom In");
		btnMinimizar.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/minimizar.png")));
		btnMinimizar.setBackground(Color.WHITE);
		toolBar_4.add(btnMinimizar);
		
		JButton btnMaximizar = new JButton("");
		btnMaximizar.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnMaximizar.setToolTipText("Fixed Zoom Out");
		btnMaximizar.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/maximizar.png")));
		btnMaximizar.setBackground(Color.WHITE);
		toolBar_4.add(btnMaximizar);
		
		JSeparator separator_13 = new JSeparator();
		separator_13.setOrientation(SwingConstants.VERTICAL);
		toolBar_4.add(separator_13);
		
		JButton btnBack = new JButton("");
		btnBack.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnBack.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/back.png")));
		btnBack.setToolTipText("Go Back");
		btnBack.setBackground(Color.WHITE);
		toolBar_4.add(btnBack);
		
		JButton btnNex = new JButton("");
		btnNex.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnNex.setToolTipText("Go to next");
		btnNex.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/next.png")));
		btnNex.setBackground(Color.WHITE);
		toolBar_4.add(btnNex);
		
		JSeparator separator_14 = new JSeparator();
		separator_14.setOrientation(SwingConstants.VERTICAL);
		toolBar_4.add(separator_14);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Iconos"}));
		toolBar_4.add(comboBox);
		
		JButton btnClearSelected = new JButton("");
		btnClearSelected.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnClearSelected.setToolTipText("Clear Selected Feature");
		btnClearSelected.setIcon(new ImageIcon(Principal.class.getResource("/com/esri/toolkit/images/SelectionClearSelected16.png")));
		btnClearSelected.setBackground(Color.WHITE);
		toolBar_4.add(btnClearSelected);
		
		JSeparator separator_16 = new JSeparator();
		separator_16.setOrientation(SwingConstants.VERTICAL);
		toolBar_4.add(separator_16);
		
		JButton btnCursor = new JButton("");
		btnCursor.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnCursor.setToolTipText("Select Elements");
		btnCursor.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/cursor.png")));
		btnCursor.setBackground(Color.WHITE);
		toolBar_4.add(btnCursor);
		
		JSeparator separator_15 = new JSeparator();
		separator_15.setOrientation(SwingConstants.VERTICAL);
		toolBar_4.add(separator_15);
		
		JButton btnIdentify = new JButton("");
		btnIdentify.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnIdentify.setToolTipText("Identify");
		btnIdentify.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/identify.png")));
		btnIdentify.setBackground(Color.WHITE);
		toolBar_4.add(btnIdentify);
		
		JButton btnHiperlink = new JButton("");
		btnHiperlink.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnHiperlink.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/hiperlink.png")));
		btnHiperlink.setBackground(Color.WHITE);
		toolBar_4.add(btnHiperlink);
		
		JButton btnPopup = new JButton("");
		btnPopup.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnPopup.setToolTipText("Popup");
		btnPopup.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/popup.png")));
		btnPopup.setBackground(Color.WHITE);
		toolBar_4.add(btnPopup);
		
		JSeparator separator_17 = new JSeparator();
		separator_17.setOrientation(SwingConstants.VERTICAL);
		toolBar_4.add(separator_17);
		
		JButton btnMeasure = new JButton("");
		btnMeasure.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnMeasure.setToolTipText("Measure");
		btnMeasure.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/measure.png")));
		btnMeasure.setBackground(Color.WHITE);
		toolBar_4.add(btnMeasure);
		
		JSeparator separator_18 = new JSeparator();
		separator_18.setOrientation(SwingConstants.VERTICAL);
		toolBar_4.add(separator_18);
		
		JButton btnFind = new JButton("");
		btnFind.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnFind.setToolTipText("Find");
		btnFind.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/find.png")));
		btnFind.setBackground(Color.WHITE);
		toolBar_4.add(btnFind);
		
		JButton btnFindRoute = new JButton("");
		btnFindRoute.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnFindRoute.setToolTipText("Find route");
		btnFindRoute.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/findroute.png")));
		btnFindRoute.setBackground(Color.WHITE);
		toolBar_4.add(btnFindRoute);
		
		JButton btnXy = new JButton("");
		btnXy.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnXy.setToolTipText("XY");
		btnXy.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/XY.png")));
		btnXy.setBackground(Color.WHITE);
		toolBar_4.add(btnXy);
		
		JSeparator separator_19 = new JSeparator();
		separator_19.setOrientation(SwingConstants.VERTICAL);
		toolBar_4.add(separator_19);
		
		JButton btnTimeSlider = new JButton("");
		btnTimeSlider.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnTimeSlider.setToolTipText("Time Slider");
		btnTimeSlider.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/timeslider.png")));
		btnTimeSlider.setBackground(Color.WHITE);
		toolBar_4.add(btnTimeSlider);
		
		JButton btnNewWindows = new JButton("");
		btnNewWindows.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnNewWindows.setBackground(Color.WHITE);
		btnNewWindows.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/newWindows.png")));
		btnNewWindows.setToolTipText("New Windows");
		toolBar_4.add(btnNewWindows);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(new Color(240, 248, 255));
		panelButttons.add(panelBotones, BorderLayout.CENTER);
		panelBotones.setSize(200, 200);
		panelBotones.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(Color.WHITE);
		panelBotones.add(toolBar, BorderLayout.CENTER);
		
		JButton btnNuevo = new JButton("");
		btnNuevo.setToolTipText("Nuevo");
		btnNuevo.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/new.png")));
		btnNuevo.setBackground(Color.WHITE);
		btnNuevo.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar.add(btnNuevo);
		
		JButton btnAbrir = new JButton("");
		btnAbrir.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		btnAbrir.setBackground(Color.WHITE);
		btnAbrir.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/open.png")));
		btnAbrir.setToolTipText("Abrir");
		toolBar.add(btnAbrir);
		
		JButton btnGuardar = new JButton("");
		btnGuardar.setForeground(Color.BLACK);
		btnGuardar.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/save.png")));
		btnGuardar.setToolTipText("Guardar");
		btnGuardar.setBackground(Color.WHITE);
		btnGuardar.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar.add(btnGuardar);
		
		JButton btnImprimir = new JButton("");
		btnImprimir.setBackground(Color.WHITE);
		btnImprimir.setToolTipText("Imprimir");
		btnImprimir.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/print.png")));
		btnImprimir.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar.add(btnImprimir);
		
		JSeparator separator_20 = new JSeparator();
		separator_20.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_20);
		
		JButton btnCortar = new JButton("");
		btnCortar.setToolTipText("Cortar");
		btnCortar.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/cut.png")));
		btnCortar.setBackground(Color.WHITE);
		btnCortar.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar.add(btnCortar);
		
		JButton btnCopiar = new JButton("");
		btnCopiar.setBackground(Color.WHITE);
		btnCopiar.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/copy.png")));
		btnCopiar.setToolTipText("Copiar");
		btnCopiar.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar.add(btnCopiar);
		
		JButton btnPegar = new JButton("");
		btnPegar.setToolTipText("Pegar");
		btnPegar.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/paste.png")));
		btnPegar.setBackground(Color.WHITE);
		btnPegar.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar.add(btnPegar);
		
		JButton btnEliminar = new JButton("");
		btnEliminar.setBackground(Color.WHITE);
		btnEliminar.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/delete.png")));
		btnEliminar.setToolTipText("Eliminar");
		btnEliminar.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar.add(btnEliminar);
		
		JSeparator separator_21 = new JSeparator();
		separator_21.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_21);
		
		JButton btnDeshacer = new JButton("");
		btnDeshacer.setToolTipText("Deshacer");
		btnDeshacer.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/undo.png")));
		btnDeshacer.setBackground(Color.WHITE);
		btnDeshacer.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar.add(btnDeshacer);
		
		JButton btnSiguiente = new JButton("");
		btnSiguiente.setBackground(Color.WHITE);
		btnSiguiente.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/redo.png")));
		btnSiguiente.setToolTipText("Siguiente");
		btnSiguiente.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar.add(btnSiguiente);
		
		JSeparator separator_22 = new JSeparator();
		separator_22.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_22);
		
		JComboBox CmbBaseLayer = new JComboBox();
		String[] images = {"/com/esri/client/toolkit/images/LayerGeneric16.png", 
				  "/com/esri/client/toolkit/images/LayerPolygon16.png", 
				  "/com/esri/client/toolkit/images/LayerServiceMap16.png"};
		for(int i= 0; i< images.length; i++){
			ImageIcon icon = new ImageIcon(Principal.class.getResource(images[i]));
			CmbBaseLayer.addItem(icon);
		}
		eventoCombos.CmbBaseLayer(CmbBaseLayer, map, groupLayer, frame);
		toolBar.add(CmbBaseLayer);
		
		JSeparator separator_23 = new JSeparator();
		separator_23.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_23);
		
		JComboBox cmbScala = new JComboBox();
		cmbScala.setBackground(Color.WHITE);
		cmbScala.setModel(new DefaultComboBoxModel(new String[] {"1:1.000", "1:10.000", "1:24.000", "1:100.000", "1:250.000", "1:500.000", "1:750.000", "1:1.000.000", "1:3.000.000", "1:10.000.000"}));
		cmbScala.setSelectedIndex(1); 
		eventoCombos.eventoCmbScala(cmbScala, map); 
		cmbScala.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar.add(cmbScala);
		
		JSeparator separator_24 = new JSeparator();
		separator_24.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_24);
		
		JButton btnEditor = new JButton("");
		btnEditor.setToolTipText("Editor");
		btnEditor.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/edit.png")));
		btnEditor.setBackground(Color.WHITE);
		btnEditor.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar.add(btnEditor);
		
		JSeparator separator_25 = new JSeparator();
		separator_25.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_25);
		
		JButton btnTablaDeContenido = new JButton("");
		btnTablaDeContenido.setToolTipText("Tabla de contenido");
		btnTablaDeContenido.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/table.png")));
		btnTablaDeContenido.setBackground(Color.WHITE);
		btnTablaDeContenido.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar.add(btnTablaDeContenido);
		
		JButton btnEditor_1 = new JButton("");
		btnEditor_1.setToolTipText("Editor");
		btnEditor_1.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/catalogo.png")));
		btnEditor_1.setBackground(Color.WHITE);
		btnEditor_1.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar.add(btnEditor_1);
		
		JButton btnBuscar = new JButton("");
		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/search.png")));
		btnBuscar.setToolTipText("Buscar");
		btnBuscar.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar.add(btnBuscar);
		
		JButton btnHerramientas = new JButton("");
		btnHerramientas.setToolTipText("Herramientas");
		btnHerramientas.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/tools.png")));
		btnHerramientas.setBackground(Color.WHITE);
		btnHerramientas.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar.add(btnHerramientas);
		
		JButton btnPhyton = new JButton("");
		btnPhyton.setToolTipText("Phyton");
		btnPhyton.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/cmd.png")));
		btnPhyton.setBackground(Color.WHITE);
		btnPhyton.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar.add(btnPhyton);
		
		JSeparator separator_26 = new JSeparator();
		separator_26.setOrientation(SwingConstants.VERTICAL);
		toolBar.add(separator_26);
		
		JButton btnBuilder = new JButton("");
		btnBuilder.setBackground(Color.WHITE);
		btnBuilder.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/grafico.png")));
		btnBuilder.setToolTipText("Builder");
		btnBuilder.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar.add(btnBuilder);
		
		JButton btnMapaOnline = new JButton("");
		btnMapaOnline.setToolTipText("Mapa online");
		btnMapaOnline.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/mapLine.png")));
		btnMapaOnline.setBackground(Color.WHITE);
		botones.eventoBotonesMapa(btnMapaOnline, map, groupLayer, 0, eventoMapa);
		btnMapaOnline.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar.add(btnMapaOnline);
		
		JButton btnMapaLocal = new JButton("");
		btnMapaLocal.setBackground(Color.WHITE);
		btnMapaLocal.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/mapLocal.png")));
		btnMapaLocal.setToolTipText("Mapa Local");
		botones.eventoBotonesMapa(btnMapaOnline, map, groupLayer, 1, eventoMapa);
		btnMapaLocal.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar.add(btnMapaLocal);
		
		JToolBar toolBar_2 = new JToolBar();
		panelBotones.add(toolBar_2, BorderLayout.EAST);
		toolBar_2.setBackground(Color.WHITE);
		
		JButton btnBoton = new JButton("Unir Predio");
		btnBoton.setToolTipText("Union de predio");
		btnBoton.setBackground(Color.WHITE);
		editarPredios.unirPredio(btnBoton, map); 
		btnBoton.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_2.add(btnBoton);
		
		JButton btnBoton_2 = new JButton("Editar");
		btnBoton_2.setToolTipText("Editar");
		btnBoton_2.setBackground(Color.WHITE);
		editarPredios.unirPredio(btnBoton_2, map);
		btnBoton_2.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_2.add(btnBoton_2);
		
		JButton btnBoton_3 = new JButton("Boton_3");
		btnBoton_3.setBackground(Color.WHITE);
		btnBoton_3.setToolTipText("Boton_3");
		btnBoton_3.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_2.add(btnBoton_3);
		
		JButton btnBoton_4 = new JButton("Boton_4");
		btnBoton_4.setToolTipText("Boton_4");
		btnBoton_4.setBackground(Color.WHITE);
		btnBoton_4.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_2.add(btnBoton_4);
		
		JButton btnBoton_5 = new JButton("Boton_5");
		btnBoton_5.setBackground(Color.WHITE);
		btnBoton_5.setToolTipText("Boton_5");
		btnBoton_5.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_2.add(btnBoton_5);
		
		JButton btnBoton_6 = new JButton("Boton_6");
		btnBoton_6.setToolTipText("Boton_6");
		btnBoton_6.setBackground(Color.WHITE);
		btnBoton_6.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_2.add(btnBoton_6);
		
		JButton btnBoton_7 = new JButton("Boton_7");
		btnBoton_7.setToolTipText("Boton_7");
		btnBoton_7.setBackground(Color.WHITE);
		btnBoton_7.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_2.add(btnBoton_7);
		
		JToolBar toolBar_3 = new JToolBar();
		toolBar_3.setBackground(Color.WHITE);
		panelBotones.add(toolBar_3, BorderLayout.WEST);
		
		
		JButton btnZoomOut = new JButton("");
		btnZoomOut.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/zoom_2.png")));
		btnZoomOut.setToolTipText("Zoom Out");
		btnZoomOut.setBackground(Color.WHITE);
		btnZoomOut.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_3.add(btnZoomOut);
		
		JButton btnAlejar = new JButton("");
		btnAlejar.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/alejar_1.png")));
		btnAlejar.setBackground(Color.WHITE);
		btnAlejar.setToolTipText("Zoom In");
		btnAlejar.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_3.add(btnAlejar);
		
		JButton btnPan = new JButton("");
		btnPan.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/manoHoja.png")));
		btnPan.setToolTipText("Pan");
		btnPan.setBackground(Color.WHITE);
		btnPan.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_3.add(btnPan);
		
		JButton btnZoomWhole = new JButton("");
		btnZoomWhole.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/zoomWholePage.png")));
		btnZoomWhole.setToolTipText("Zoom Whole");
		btnZoomWhole.setBackground(Color.WHITE);
		btnZoomWhole.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_3.add(btnZoomWhole);
		
		JButton btnZoomTo = new JButton("");
		btnZoomTo.setToolTipText("Zoom To 100%");
		btnZoomTo.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/archivo.png")));
		btnZoomTo.setBackground(Color.WHITE);
		btnZoomTo.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_3.add(btnZoomTo);
		
		JSeparator separator_27 = new JSeparator();
		separator_27.setOrientation(SwingConstants.VERTICAL);
		toolBar_3.add(separator_27);
		
		JButton btnFixedZoomOut = new JButton("");
		btnFixedZoomOut.setBackground(Color.WHITE);
		btnFixedZoomOut.setToolTipText("Fixed Zoom Out");
		btnFixedZoomOut.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/FixedZoomOut.png")));
		btnFixedZoomOut.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_3.add(btnFixedZoomOut);
		
		JButton btnFixedZoomIn = new JButton("");
		btnFixedZoomIn.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/FixzedZoomIn.png")));
		btnFixedZoomIn.setToolTipText("Fixed Zoom In");
		btnFixedZoomIn.setBackground(Color.WHITE);
		btnFixedZoomIn.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_3.add(btnFixedZoomIn);
		
		JSeparator separator_28 = new JSeparator();
		separator_28.setOrientation(SwingConstants.VERTICAL);
		toolBar_3.add(separator_28);
		
		JButton btnGoBack = new JButton("");
		btnGoBack.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/backPage.png")));
		btnGoBack.setBackground(Color.WHITE);
		btnGoBack.setToolTipText("Go Back to Extent");
		btnGoBack.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_3.add(btnGoBack);
		
		JButton btnGoNextTo = new JButton("");
		btnGoNextTo.setBackground(Color.WHITE);
		btnGoNextTo.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/goForward.png")));
		btnGoNextTo.setToolTipText("Go Forward to Extent");
		btnGoNextTo.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_3.add(btnGoNextTo);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"100%"}));
		comboBox_1.setEnabled(false);
		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_3.add(comboBox_1);
		
		JSeparator separator_29 = new JSeparator();
		separator_29.setOrientation(SwingConstants.VERTICAL);
		toolBar_3.add(separator_29);
		
		JButton btnTogleDraftMode = new JButton("");
		btnTogleDraftMode.setToolTipText("TogleDraftMode");
		btnTogleDraftMode.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/DatDriven.png")));
		btnTogleDraftMode.setBackground(Color.WHITE);
		btnTogleDraftMode.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_3.add(btnTogleDraftMode);
		
		JButton btnFocusDataframe = new JButton("");
		btnFocusDataframe.setToolTipText("Focus DataFrame");
		btnFocusDataframe.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/focusDataFrame.png")));
		btnFocusDataframe.setBackground(Color.WHITE);
		btnFocusDataframe.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_3.add(btnFocusDataframe);
		
		JButton btnChangeLayout = new JButton("");
		btnChangeLayout.setToolTipText("Change Layout");
		btnChangeLayout.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/changeLayout.png")));
		btnChangeLayout.setBackground(Color.WHITE);
		btnChangeLayout.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_3.add(btnChangeLayout);
		
		JButton btnDataDrivePages = new JButton("");
		btnDataDrivePages.setToolTipText("Data Driven Pages Toolbar");
		btnDataDrivePages.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/DtaDriven.png")));
		btnDataDrivePages.setBackground(Color.WHITE);
		btnDataDrivePages.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_3.add(btnDataDrivePages);
		
		JPanel panelFooter = new JPanel();
		panelFooter.setBackground(new Color(240, 248, 255));
		panelButttons.add(panelFooter, BorderLayout.SOUTH);
		panelFooter.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setBackground(Color.WHITE);
		panelFooter.add(toolBar_1, BorderLayout.WEST);
		
		JComboBox cmbEditor = new JComboBox();
		cmbEditor.setBackground(Color.WHITE);
		cmbEditor.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_1.add(cmbEditor);
		
		JSeparator separator_30 = new JSeparator();
		separator_30.setOrientation(SwingConstants.VERTICAL);
		toolBar_1.add(separator_30);
		
		JButton btnEditTool = new JButton("");
		btnEditTool.setSelectedIcon(new ImageIcon(Principal.class.getResource("/com/esri/map/resources/GenericBlackRightArrowNoTail16.png")));
		btnEditTool.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				if(map.getCursor().getType()==-1) {  
					map.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				}else {
					Image im = Toolkit.getDefaultToolkit().createImage(Principal.class.getResource("/imagenes/img/cursor1.png")); 
					Cursor cur = Toolkit.getDefaultToolkit().createCustomCursor(im, new Point(2,2),"WILL"); 
					map.setCursor(cur ); 
					eventoMapa.agregarEvento(map, listaArcGisFeatureLayer);  
					System.out.println("typo cursor " + map.getCursor().getType());					
				}
			}
		});
		btnEditTool.setToolTipText("Edit Tool");
		btnEditTool.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/editTol.png")));
		btnEditTool.setBackground(Color.WHITE);
		btnEditTool.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_1.add(btnEditTool);
		
		JButton btnEditAnnotationTool = new JButton("");
		btnEditAnnotationTool.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/editTextTool.png")));
		btnEditAnnotationTool.setToolTipText("Edit Annotation Tool");
		btnEditAnnotationTool.setBackground(Color.WHITE);
		btnEditAnnotationTool.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_1.add(btnEditAnnotationTool);
		
		JSeparator separator_31 = new JSeparator();
		separator_31.setOrientation(SwingConstants.VERTICAL);
		toolBar_1.add(separator_31);
		
		JButton btnStrainghtSegment = new JButton("");
		btnStrainghtSegment.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/straingSegment.png")));
		btnStrainghtSegment.setToolTipText("Straight Segment");
		btnStrainghtSegment.setBackground(Color.WHITE);
		btnStrainghtSegment.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_1.add(btnStrainghtSegment);
		
		JButton btnEndPointArc = new JButton("");
		btnEndPointArc.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/arcSegment.png")));
		btnEndPointArc.setToolTipText("End Point Arc Segment");
		btnEndPointArc.setBackground(Color.WHITE);
		btnEndPointArc.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_1.add(btnEndPointArc);
		
		JButton btnTrace = new JButton("");
		btnTrace.setToolTipText("Trace");
		btnTrace.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/trace1.png")));
		btnTrace.setBackground(Color.WHITE);
		btnTrace.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_1.add(btnTrace);
		
		JButton btnPoint = new JButton("");
		btnPoint.setBackground(Color.WHITE);
		btnPoint.setToolTipText("Point");
		btnPoint.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/point.png")));
		btnPoint.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_1.add(btnPoint);
		
		JSeparator separator_32 = new JSeparator();
		separator_32.setOrientation(SwingConstants.VERTICAL);
		toolBar_1.add(separator_32);
		
		JButton btnEditVertices = new JButton("");
		btnEditVertices.setToolTipText("Edit Vertices");
		btnEditVertices.setIcon(new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/EditingEditVertices16.png")));
		btnEditVertices.setBackground(Color.WHITE);
		btnEditVertices.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_1.add(btnEditVertices);
		
		JButton btnReshapeFeatureTool = new JButton("");
		btnReshapeFeatureTool.setToolTipText("Reshape Feature Tool");
		btnReshapeFeatureTool.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/trace.png")));
		btnReshapeFeatureTool.setBackground(Color.WHITE);
		btnReshapeFeatureTool.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_1.add(btnReshapeFeatureTool);
		
		JButton btnCutPolygonsTools = new JButton("");
		btnCutPolygonsTools.setToolTipText("Cut polygons Tools");
		btnCutPolygonsTools.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/cutPolygono.png")));
		btnCutPolygonsTools.setBackground(Color.WHITE);
		btnCutPolygonsTools.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_1.add(btnCutPolygonsTools);
		
		JButton btnSplitTools = new JButton("");
		btnSplitTools.setToolTipText("Split Tool");
		btnSplitTools.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/splitTool.png")));
		btnSplitTools.setBackground(Color.WHITE);
		btnSplitTools.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_1.add(btnSplitTools);
		
		JSeparator separator_33 = new JSeparator();
		separator_33.setOrientation(SwingConstants.VERTICAL);
		toolBar_1.add(separator_33);
		
		JButton btnAtributes = new JButton("");
		btnAtributes.setToolTipText("Atributes");
		btnAtributes.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/attributes.png")));
		btnAtributes.setBackground(Color.WHITE);
		btnAtributes.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_1.add(btnAtributes);
		
		JButton btnSckechtPropietaries = new JButton("");
		btnSckechtPropietaries.setToolTipText("Scketch Propietaries");
		btnSckechtPropietaries.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/skecht.png")));
		btnSckechtPropietaries.setBackground(Color.WHITE);
		btnSckechtPropietaries.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_1.add(btnSckechtPropietaries);
		
		JSeparator separator_34 = new JSeparator();
		separator_34.setOrientation(SwingConstants.VERTICAL);
		toolBar_1.add(separator_34);
		
		JButton btnCreateFeature = new JButton("");
		btnCreateFeature.setToolTipText("Create Feature");
		btnCreateFeature.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/createFeatures.png")));
		btnCreateFeature.setBackground(Color.WHITE);
		btnCreateFeature.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_1.add(btnCreateFeature);
		
		JSeparator separator_35 = new JSeparator();
		separator_35.setOrientation(SwingConstants.VERTICAL);
		toolBar_1.add(separator_35);
		
		JButton btnHerramientaRectangulo = new JButton("");
		btnHerramientaRectangulo.setToolTipText("Herramienta Rectangulo");
		btnHerramientaRectangulo.setIcon(new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/EditingRectangleTool16.png")));
		btnHerramientaRectangulo.setBackground(Color.WHITE);
		btnHerramientaRectangulo.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_1.add(btnHerramientaRectangulo);
		
		JButton btnHerramientaPolilinea = new JButton("");
		btnHerramientaPolilinea.setToolTipText("Herramienta Polil\u00EDnea");
		btnHerramientaPolilinea.setIcon(new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/EditingLineTool16.png")));
		btnHerramientaPolilinea.setBackground(Color.WHITE);
		btnHerramientaPolilinea.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_1.add(btnHerramientaPolilinea);
		
		JButton btnHerramientaManoAlzada = new JButton("");
		btnHerramientaManoAlzada.setIcon(new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/EditingFreehandTool16.png")));
		btnHerramientaManoAlzada.setToolTipText("Herramienta Mano Alzada");
		btnHerramientaManoAlzada.setBackground(Color.WHITE);
		btnHerramientaManoAlzada.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_1.add(btnHerramientaManoAlzada);
		
		JButton btnHerramientaMultipunto = new JButton("");
		btnHerramientaMultipunto.setIcon(new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/EditingMultiPointTool16.png")));
		btnHerramientaMultipunto.setToolTipText("Herramienta Multipunto");
		btnHerramientaMultipunto.setBackground(Color.WHITE);
		btnHerramientaMultipunto.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_1.add(btnHerramientaMultipunto);
		
		JButton btnHerramientaPoligono = new JButton("");
		btnHerramientaPoligono.setIcon(new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/EditingPolygonTool16.png")));
		btnHerramientaPoligono.setToolTipText("Herramienta Pol\u00EDgono");
		btnHerramientaPoligono.setBackground(Color.WHITE);
		btnHerramientaPoligono.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(Color.white, 1), BorderFactory.createLineBorder(Color.white,2)));
		toolBar_1.add(btnHerramientaPoligono);
		
	
		JPanel panelMapa = new JPanel(); 
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
		
		JPanel panelCapas = new JPanel();
		panelCapas.setBackground(new Color(240, 248, 255));
		frame.getContentPane().add(panelCapas, BorderLayout.WEST);
		panelCapas.setPreferredSize(new Dimension(300, 660));
		panelCapas.setLayout(new BorderLayout(0, 0));
		eventoMapa.crearMenuCapas(map, panelCapas);
		
	}
	
	public void agregarCaracteristicasMenu (JMenu menu, String decripcion) {
		menu.setMnemonic(KeyEvent.VK_F);
		menu.getAccessibleContext().setAccessibleDescription(decripcion);
	}

}
