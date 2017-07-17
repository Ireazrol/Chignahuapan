package application;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;

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

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import java.awt.Component;
import javax.swing.JToolBar;
import java.awt.Scrollbar;
import javax.swing.JSplitPane;
import java.awt.Panel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Button;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class Principal {

	private JFrame frame;
	private JMap map = new JMap();
	private GroupLayer groupLayer = new GroupLayer();
	EventoMapa eventoMapa = new EventoMapa();
	EventoCombos eventoCombos = new EventoCombos();
	Botones botones = new Botones();
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
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBackground(Color.WHITE);
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
		
		JPanel panelBotones = new JPanel();
		panel.add(panelBotones);
		panelBotones.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(Color.WHITE);
		panelBotones.add(toolBar, BorderLayout.WEST);
		
		JButton btnNuevo = new JButton("");
		btnNuevo.setToolTipText("Nuevo");
		btnNuevo.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/new.png")));
		btnNuevo.setBackground(Color.WHITE);
		toolBar.add(btnNuevo);
		
		JButton btnAbrir = new JButton("");
		btnAbrir.setBackground(Color.WHITE);
		btnAbrir.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/open.png")));
		btnAbrir.setToolTipText("Abrir");
		toolBar.add(btnAbrir);
		
		JButton btnGuardar = new JButton("");
		btnGuardar.setForeground(Color.BLACK);
		btnGuardar.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/save.png")));
		btnGuardar.setToolTipText("Guardar");
		btnGuardar.setBackground(Color.WHITE);
		toolBar.add(btnGuardar);
		
		JButton btnImprimir = new JButton("");
		btnImprimir.setBackground(Color.WHITE);
		btnImprimir.setToolTipText("Imprimir");
		btnImprimir.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/print.png")));
		toolBar.add(btnImprimir);
		
		JButton btnCortar = new JButton("");
		btnCortar.setToolTipText("Cortar");
		btnCortar.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/cut.png")));
		btnCortar.setBackground(Color.WHITE);
		toolBar.add(btnCortar);
		
		JButton btnCopiar = new JButton("");
		btnCopiar.setBackground(Color.WHITE);
		btnCopiar.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/copy.png")));
		btnCopiar.setToolTipText("Copiar");
		toolBar.add(btnCopiar);
		
		JButton btnPegar = new JButton("");
		btnPegar.setToolTipText("Pegar");
		btnPegar.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/paste.png")));
		btnPegar.setBackground(Color.WHITE);
		toolBar.add(btnPegar);
		
		JButton btnEliminar = new JButton("");
		btnEliminar.setBackground(Color.WHITE);
		btnEliminar.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/delete.png")));
		btnEliminar.setToolTipText("Eliminar");
		toolBar.add(btnEliminar);
		
		JButton btnDeshacer = new JButton("");
		btnDeshacer.setToolTipText("Deshacer");
		btnDeshacer.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/undo.png")));
		btnDeshacer.setBackground(Color.WHITE);
		toolBar.add(btnDeshacer);
		
		JButton btnSiguiente = new JButton("");
		btnSiguiente.setBackground(Color.WHITE);
		btnSiguiente.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/redo.png")));
		btnSiguiente.setToolTipText("Siguiente");
		toolBar.add(btnSiguiente);
		
		JButton btnAgregarCapa = new JButton("");
		btnAgregarCapa.setToolTipText("Agregar capa");
		btnAgregarCapa.setIcon(new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/LayerSelect16.png")));
		btnAgregarCapa.setBackground(Color.WHITE);
		botones.BtnAddLayer(btnAgregarCapa, map);
		toolBar.add(btnAgregarCapa);
		
		JComboBox cmbScala = new JComboBox();
		cmbScala.setEditable(true);
		cmbScala.setBackground(Color.WHITE);
		cmbScala.setModel(new DefaultComboBoxModel(new String[] {"1:1.000", "1:10.000", "1:24.000", "1:100.000", "1:250.000", "1:500.000", "1:750.000", "1:1.000.000", "1:3.000.000", "1:10.000.000"}));
		cmbScala.setSelectedIndex(1);
		eventoCombos.eventoCmbScala(cmbScala, map); 
		toolBar.add(cmbScala);
		
		JButton btnEditor = new JButton("");
		btnEditor.setToolTipText("Editor");
		btnEditor.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/edit.png")));
		btnEditor.setBackground(Color.WHITE);
		toolBar.add(btnEditor);
		
		JButton btnTablaDeContenido = new JButton("");
		btnTablaDeContenido.setToolTipText("Tabla de contenido");
		btnTablaDeContenido.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/table.png")));
		btnTablaDeContenido.setBackground(Color.WHITE);
		toolBar.add(btnTablaDeContenido);
		
		JButton btnEditor_1 = new JButton("");
		btnEditor_1.setToolTipText("Editor");
		btnEditor_1.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/catalogo.png")));
		btnEditor_1.setBackground(Color.WHITE);
		toolBar.add(btnEditor_1);
		
		JButton btnBuscar = new JButton("");
		btnBuscar.setBackground(Color.WHITE);
		btnBuscar.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/search.png")));
		btnBuscar.setToolTipText("Buscar");
		toolBar.add(btnBuscar);
		
		JButton btnHerramientas = new JButton("");
		btnHerramientas.setToolTipText("Herramientas");
		btnHerramientas.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/tools.png")));
		btnHerramientas.setBackground(Color.WHITE);
		toolBar.add(btnHerramientas);
		
		JButton btnBuilder = new JButton("");
		btnBuilder.setBackground(Color.WHITE);
		btnBuilder.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/grafico.png")));
		btnBuilder.setToolTipText("Builder");
		toolBar.add(btnBuilder);
		
		JButton btnMapaOnline = new JButton("");
		btnMapaOnline.setToolTipText("Mapa online");
		btnMapaOnline.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/mapLine.png")));
		btnMapaOnline.setBackground(Color.WHITE);
		toolBar.add(btnMapaOnline);
		
		JButton btnMapaLocal = new JButton("");
		btnMapaLocal.setBackground(Color.WHITE);
		btnMapaLocal.setIcon(new ImageIcon(Principal.class.getResource("/imagenes/img/mapLocal.png")));
		btnMapaLocal.setToolTipText("Mapa Local");
		toolBar.add(btnMapaLocal);
		
		JToolBar toolBar_2 = new JToolBar();
		panelBotones.add(toolBar_2, BorderLayout.CENTER);
		toolBar_2.setBackground(Color.WHITE);
		
		JButton btnBoton = new JButton("Boton_1");
		btnBoton.setToolTipText("Boton_1");
		btnBoton.setBackground(Color.WHITE);
		toolBar_2.add(btnBoton);
		
		JButton btnBoton_2 = new JButton("Boton_2");
		btnBoton_2.setToolTipText("Boton_2");
		btnBoton_2.setBackground(Color.WHITE);
		toolBar_2.add(btnBoton_2);
		
		JButton btnBoton_3 = new JButton("Boton_3");
		btnBoton_3.setBackground(Color.WHITE);
		btnBoton_3.setToolTipText("Boton_3");
		toolBar_2.add(btnBoton_3);
		
		JButton btnBoton_4 = new JButton("Boton_4");
		btnBoton_4.setToolTipText("Boton_4");
		btnBoton_4.setBackground(Color.WHITE);
		toolBar_2.add(btnBoton_4);
		
		JButton btnBoton_5 = new JButton("Boton_5");
		btnBoton_5.setBackground(Color.WHITE);
		btnBoton_5.setToolTipText("Boton_5");
		toolBar_2.add(btnBoton_5);
		
		JButton btnBoton_6 = new JButton("Boton_6");
		btnBoton_6.setToolTipText("Boton_6");
		btnBoton_6.setBackground(Color.WHITE);
		toolBar_2.add(btnBoton_6);
		
		JButton btnBoton_7 = new JButton("Boton_7");
		btnBoton_7.setToolTipText("Boton_7");
		btnBoton_7.setBackground(Color.WHITE);
		toolBar_2.add(btnBoton_7);
		
		JToolBar toolBar_3 = new JToolBar();
		toolBar_3.setBackground(Color.WHITE);
		panelBotones.add(toolBar_3, BorderLayout.EAST);
		
		JButton btnBoton_8 = new JButton("Boton_8");
		btnBoton_8.setToolTipText("Boton_8");
		btnBoton_8.setBackground(Color.WHITE);
		toolBar_3.add(btnBoton_8);
		
		JButton btnBoton_9 = new JButton("Boton_9");
		btnBoton_9.setBackground(Color.WHITE);
		btnBoton_9.setToolTipText("Boton_9");
		toolBar_3.add(btnBoton_9);
		
		JButton btnBoton_10 = new JButton("Boton_10");
		btnBoton_10.setToolTipText("Boton_10");
		btnBoton_10.setBackground(Color.WHITE);
		toolBar_3.add(btnBoton_10);
		
		JButton btnBoton_11 = new JButton("Boton_11");
		btnBoton_11.setToolTipText("Boton_11");
		btnBoton_11.setBackground(Color.WHITE);
		toolBar_3.add(btnBoton_11);
		
		JPanel panelFooter = new JPanel();
		panel.add(panelFooter, BorderLayout.SOUTH);
		panelFooter.setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar_1 = new JToolBar();
		toolBar_1.setBackground(Color.WHITE);
		panelFooter.add(toolBar_1, BorderLayout.NORTH);
		
		JButton btnHerramientaRectangulo = new JButton("");
		btnHerramientaRectangulo.setToolTipText("Herramienta Rectangulo");
		btnHerramientaRectangulo.setIcon(new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/EditingRectangleTool16.png")));
		btnHerramientaRectangulo.setBackground(Color.WHITE);
		toolBar_1.add(btnHerramientaRectangulo);
		
		JButton btnHerramientaPolilinea = new JButton("");
		btnHerramientaPolilinea.setToolTipText("Herramienta Polil\u00EDnea");
		btnHerramientaPolilinea.setIcon(new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/EditingLineTool16.png")));
		btnHerramientaPolilinea.setBackground(Color.WHITE);
		toolBar_1.add(btnHerramientaPolilinea);
		
		JButton btnHerramientaManoAlzada = new JButton("");
		btnHerramientaManoAlzada.setIcon(new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/EditingFreehandTool16.png")));
		btnHerramientaManoAlzada.setToolTipText("Herramienta Mano Alzada");
		btnHerramientaManoAlzada.setBackground(Color.WHITE);
		toolBar_1.add(btnHerramientaManoAlzada);
		
		JButton btnHerramientaMultipunto = new JButton("");
		btnHerramientaMultipunto.setIcon(new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/EditingMultiPointTool16.png")));
		btnHerramientaMultipunto.setToolTipText("Herramienta Multipunto");
		btnHerramientaMultipunto.setBackground(Color.WHITE);
		toolBar_1.add(btnHerramientaMultipunto);
		
		JButton btnHerramientaPoligono = new JButton("");
		btnHerramientaPoligono.setIcon(new ImageIcon(Principal.class.getResource("/com/esri/client/toolkit/images/EditingPolygonTool16.png")));
		btnHerramientaPoligono.setToolTipText("Herramienta Pol\u00EDgono");
		btnHerramientaPoligono.setBackground(Color.WHITE);
		toolBar_1.add(btnHerramientaPoligono);
		
	
		JPanel panelMapa = new JPanel();
		panelMapa.setBackground(Color.WHITE);
		frame.getContentPane().add(panelMapa, BorderLayout.CENTER);
		panelMapa.setLayout(new BorderLayout(0, 0));
		eventoMapa.crearMapaPuebla(groupLayer, map);
		frame.addWindowListener(new WindowAdapter() {
	      @Override
	      public void windowClosing(WindowEvent windowEvent) {
	        super.windowClosing(windowEvent);
	        map.dispose();
	      }
	    });
		panelMapa.add(map, -1);
		
		JPanel panelCapas = new JPanel();
		panelCapas.setBackground(Color.WHITE);
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
