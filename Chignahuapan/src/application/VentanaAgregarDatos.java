package application;

import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.esri.arcgis.geoprocessing.GeoProcessor;
import com.esri.arcgis.system.AoInitialize;
import com.esri.arcgis.system.EngineInitializer;
import com.esri.core.tasks.ags.geoprocessing.Geoprocessor;
import com.l2fprod.common.swing.JDirectoryChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;


//import jas.util.gui.*;

@SuppressWarnings("serial")
public class VentanaAgregarDatos extends JFrame {
    static JFileChooser fileChooser;
    private JButton btnLevelup, btnNewFolder, btnView;
    AuxVentanaAD auxVentanaAD = new AuxVentanaAD();
    
	/**
	 * Create the frame.
	 */
	public VentanaAgregarDatos() {
		/**JFileChooser**/
		fileChooser =  new JFileChooser("C:\\Users\\Job\\Documents\\ArcGIS");
		fileChooser.setDialogTitle("Agregar datos");
		fileChooser.setApproveButtonText("Agregar");
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Conjunto de datos, capas y resultados", "gpk");
		fileChooser.addChoosableFileFilter(filter);
		
		/***JDirectoryChooser***/
		JDirectoryChooser directoryChooser = new JDirectoryChooser("C:\\Users\\Job\\Escritorio");
		directoryChooser.setDialogTitle("Conectar a una Carpeta");
		directoryChooser.setMultiSelectionEnabled(false);
		
		JToolBar jtoolBar = null;
		JComponent auxComponent = null;
		
		/**Botones y separadores**/
		JButton btnHome = new JButton("");
		btnHome.setToolTipText("Ir a la página principal");
		btnHome.setIcon(new ImageIcon(VentanaAgregarDatos.class.getResource("/imagenes/img/home.png")));
			
		JButton btnDefaultGeoDB = new JButton("");
		btnDefaultGeoDB.setToolTipText("Ir a la GeoDataBase por defecto");
		btnDefaultGeoDB.setIcon(new ImageIcon(VentanaAgregarDatos.class.getResource("/imagenes/img/bd.png")));
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		
		JSeparator separator1 = new JSeparator();
		separator1.setOrientation(SwingConstants.VERTICAL);
		
		JButton btnConnectFolder = new JButton("");
		btnConnectFolder.setToolTipText("Conectar a la Carpeta");
		btnConnectFolder.setIcon(new ImageIcon(VentanaAgregarDatos.class.getResource("/imagenes/img/loadFile.png")));
		
		JSeparator separator2 = new JSeparator();
		separator2.setOrientation(SwingConstants.VERTICAL);
		
		JButton btnNewGeoDB = new JButton("");
		btnNewGeoDB.setToolTipText("Nuevo archivo de GeoDataBase");
		btnNewGeoDB.setIcon(new ImageIcon(VentanaAgregarDatos.class.getResource("/imagenes/img/database.png")));
		
		JButton btnToolBoox = new JButton("");
		btnToolBoox.setToolTipText("Nueva caja de herramientas");
		btnToolBoox.setIcon(new ImageIcon(VentanaAgregarDatos.class.getResource("/imagenes/img/toolbox.png")));
		/**Fin de botones y separadores**/
		
		for (int i = 0; i < fileChooser.getComponentCount()-1; i++) {
			if (i==1) {
				jtoolBar = (JToolBar) fileChooser.getComponent(i);
				for (int j = 0; j < jtoolBar.getComponentCount(); j++) {
					auxComponent = (JComponent) jtoolBar.getComponent(j);
					switch (j) {
					case 4:
						btnLevelup = (JButton) auxComponent;
						btnLevelup.setIcon(new ImageIcon(VentanaAgregarDatos.class.getResource("/imagenes/img/levelUp.png")));
						break;
					case 5:
						btnNewFolder = (JButton) auxComponent;
						btnNewFolder.setIcon(new ImageIcon(VentanaAgregarDatos.class.getResource("/imagenes/img/folder.png")));
						break;
					case 6:
						btnView = (JButton) auxComponent;
						break;
					default:
						break;
					}
				}
				jtoolBar.add(btnLevelup);
				jtoolBar.add(btnHome);
				jtoolBar.add(btnDefaultGeoDB);
				jtoolBar.add(separator);
				jtoolBar.add(btnView);
				jtoolBar.add(separator1);
				jtoolBar.add(btnConnectFolder);
				jtoolBar.add(separator2);
				jtoolBar.add(btnNewFolder);
				jtoolBar.add(btnNewGeoDB);
				jtoolBar.add(btnToolBoox);
			}
		}
		
		/***Eventos de los botones**/
		btnHome.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fileChooser.setCurrentDirectory(new File("C:\\Users\\Job\\Documents\\ArcGIS"));
			}
		});
		
		btnDefaultGeoDB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fileChooser.setCurrentDirectory(new File("C:\\Users\\Job\\Documents\\ArcGIS\\Default.gdb"));
			}
		});
		
		btnConnectFolder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = directoryChooser.showDialog(fileChooser, "Aceptar");
				switch (result) {
				case JDirectoryChooser.APPROVE_OPTION:
					File f = directoryChooser.getSelectedFile();
					fileChooser.setCurrentDirectory(new File(f.getAbsolutePath()));
					break;
				case JDirectoryChooser.CANCEL_OPTION:
					break;
				case JDirectoryChooser.ERROR_OPTION:
					auxVentanaAD.showMessageError(directoryChooser, "Ha ocurrido un error.\nVuelva a Intentarlo.");
					break;
				default:
					break;
				}
			}
		});
		
		btnNewGeoDB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				File f = fileChooser.getCurrentDirectory();
//				EngineInitializer.initializeEngine();
//				auxVentanaAD.initializeArcGISLicenses();

				System.out.println("Geoprocessing Database Conversion - An ArcObjects Java SDK Developer Sample");
				System.out.println("Creando nueva Geodatabase");
				String arcGISHome = f.getAbsolutePath();
				/**** Change the following lines if you want to use different data****/
				String inputworkspace = arcGISHome;
				String outputWorkspace = auxVentanaAD.getOutputDir();

				Geoprocessor geoProcessor= new Geoprocessor("");
				
				GeoProcessor gp=null;
				try {
					System.out.println("Entre al try ---->Gp : "+gp);
					System.out.println("inputworkspace : "+inputworkspace+" outputWorkspace : "+outputWorkspace+ " gp : "+gp);
					gp = new GeoProcessor();
					System.out.println("Gp : "+gp);
					gp.setOverwriteOutput(true);
					
				} catch (Exception e1) {
//					auxVentanaAD.showMessageError(fileChooser, "Lanzó una excepción creando el geoprocesador\n"+e1.getMessage());
					System.out.println("Lanzó una excepción creando el geoprocesador\n"+e1.getMessage());
					e1.printStackTrace();
				}
				System.out.println("inputworkspace : "+inputworkspace+" outputWorkspace : "+outputWorkspace+ " gp : "+gp);
				auxVentanaAD.migrateGeodatabases(inputworkspace, outputWorkspace, gp);

				try { new AoInitialize().shutdown();} catch (Exception e1) {}
			}
		});
	}
}