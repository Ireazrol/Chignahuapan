package application;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.CellRendererPane;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.plaf.metal.MetalComboBoxButton;

import com.esri.arcgis.datasourcesGDB.FileGDBWorkspaceFactory;
import com.esri.arcgis.geodatabase.FeatureClass;
import com.esri.arcgis.geodatabase.IDataset;
import com.esri.arcgis.geodatabase.IDatasetName;
import com.esri.arcgis.geodatabase.IEnumDataset;
import com.esri.arcgis.geodatabase.IEnumDatasetName;
import com.esri.arcgis.geodatabase.Workspace;
import com.esri.arcgis.geodatabase.esriDatasetType;
import com.esri.arcgis.system.AoInitialize;
import com.esri.arcgis.system.esriLicenseProductCode;
import com.esri.arcgis.system.esriLicenseStatus;
import com.esri.map.GroupLayer;
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
	
	public void CmbBaseLayer (JComboBox CmbBaseLayer, JMap map, GroupLayer groupLayer, JFrame frameMain) {
		CmbBaseLayer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
				int numItem= CmbBaseLayer.getSelectedIndex();
				switch (numItem) {
				case 0:
					try {
						UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
						VentanaAgregarDatos addDatos = new VentanaAgregarDatos();
						int seleccion = addDatos.fileChooser.showOpenDialog(frameMain);
						AuxVentanaAD auxVentanaAD =new AuxVentanaAD();
						auxVentanaAD.chooseFiles(addDatos.fileChooser, seleccion);
					} catch (Exception error) {
						System.out.println("Error : "+error.getMessage());
					}
					break;
				case 1:
					VentanasDinamicas frame = new VentanasDinamicas(map, groupLayer, frameMain);
					frameMain.setEnabled(false);
					frame.setVisible(true);
					break;
				case 2:
					System.out.println("Dos");
					break;
				default:
					break;
				}
			} 
		});
	}

	private static void initializeArcGISLicenses(AoInitialize aoInit) {
		try {
			if (aoInit.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeEngine) 
					== esriLicenseStatus.esriLicenseAvailable)
				aoInit.initialize(esriLicenseProductCode.esriLicenseProductCodeEngine);
			else if (aoInit.isProductCodeAvailable(esriLicenseProductCode.esriLicenseProductCodeEngine) 
					== esriLicenseStatus.esriLicenseAvailable)
				//            aoInit.li
				aoInit.initialize(esriLicenseProductCode.esriLicenseProductCodeEngine);
			else{
				System.err.println("Could not initialize an Engine or ArcView license. Exiting application.");
				System.exit(-1);
			}
		} catch (Exception e) {e.printStackTrace();}
	}

	private void browseFileGDB(String inFGDB){
		try{
			FileGDBWorkspaceFactory factory = new FileGDBWorkspaceFactory();
			Workspace workspace = new Workspace(factory.openFromFile(inFGDB, 0));

			//Get all dataset names in the workspace
			IEnumDatasetName enumDatasetName = workspace.getDatasetNames(esriDatasetType.esriDTAny);

			//Get the first name in the dataset
			IDatasetName dsName = enumDatasetName.next();
			while(dsName != null){
				//Print out the dataset name to the console
				System.out.println("Dataset Name: " + dsName.getName());

				//Get the next name in the enumeration of dataset names
				dsName = enumDatasetName.next();
			}

			//Get all the datasets
			IEnumDataset enumDataset = workspace.getDatasets(esriDatasetType.esriDTFeatureClass);

			//Get the first dataset
			IDataset ds = enumDataset.next();
			while(ds != null){
				FeatureClass fClass = new FeatureClass(ds);

				int fCount = fClass.featureCount(null);

				System.out.println("FeatureClass " + fClass.getAliasName() + " has "
						+ fCount + " features.");


				ds = enumDataset.next();
			}
		}catch(Exception e){e.printStackTrace();}
	}
	
	public void eventoCmbFeature (JComboBox jComboBox, JMap map) { 
		jComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {    
				for (int i=0; i<jComboBox.getComponents().length; i++) {
					//System.out.println("jComboBox " +i +" - "+ jComboBox.getComponents()[i]);
					if (i>0) {
						CellRendererPane cellRendererPane = (CellRendererPane) jComboBox.getComponents()[i];
						//MetalComboBoxButton metalComboBoxButton = (MetalComboBoxButton) jComboBox.getComponents()[i];
						//System.out.println("jComboBox " +i +" - "+ cellRendererPane.);
					}
					
				}
			}
		});
	}

}