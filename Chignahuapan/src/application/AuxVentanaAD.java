package application;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.plaf.OptionPaneUI;

import com.esri.arcgis.geodatabase.IGPMessages;
import com.esri.arcgis.geoprocessing.GeoProcessor;
import com.esri.arcgis.geoprocessing.IGpEnumList;
import com.esri.arcgis.geoprocessing.tools.datamanagementtools.Copy;
import com.esri.arcgis.geoprocessing.tools.datamanagementtools.CreateFileGDB;
import com.esri.client.local.LocalMapService;
import com.esri.client.local.WorkspaceInfo;
import com.esri.client.local.WorkspaceInfoSet;
import com.esri.core.map.DrawingInfo;
import com.esri.core.map.DynamicLayerInfo;
import com.esri.core.map.DynamicLayerInfoCollection;
import com.esri.core.map.LayerDataSource;
import com.esri.core.map.TableDataSource;
import com.esri.map.ArcGISDynamicMapServiceLayer;
import com.esri.map.LayerInitializeCompleteEvent;
import com.esri.map.LayerInitializeCompleteListener;
import com.esri.runtime.ArcGISRuntime;


//import com.esri.arcgis.Geometry;

public class AuxVentanaAD {
	int count=0;
	String FSP = System.getProperty("file.separator");
	
	public void chooseFiles(JFileChooser fileChooser, int seleccion){
		
		
		
		System.out.println("Entré al método chooseFiles");
		String cadena="";
		switch (seleccion) {
		case JFileChooser.APPROVE_OPTION:
			System.out.println("Aceptar...");
//			try {
//	            String fileNameRaw = fileChooser.getSelectedFile().getName();
//	            String fileName = fileNameRaw.substring(0, fileNameRaw.length()-4);
//	            String fileDir = fileChooser.getSelectedFile().getParent();
//
//	            addShapefile(fileName, fileDir);
//	          }
//	          catch (Exception e) {
//	        	  JOptionPane.showMessageDialog(fileChooser, "Imposible cargar el Shapefile."+
//	          "Revise que la ruta sea correcta o intente con otro shapefile.","Error",JOptionPane.ERROR_MESSAGE);
//	            e.printStackTrace();
//	          }
			break;
		case JFileChooser.CANCEL_OPTION:
			break;
		case JFileChooser.ERROR_OPTION:
			showMessageError(fileChooser, "Ha ocurrido un error.\nVuelva a intentarlo.");
			break;
		default:
			break;
		}
	}
	
	public void showMessageError(Component comp, String Mensaje){
		JOptionPane.showMessageDialog(comp,Mensaje,"Error",JOptionPane.ERROR_MESSAGE);
	}
	
	public void createGDB(){
		
	}
	
	public void migrateGeodatabases(String inputworkspace, String outputWorkspace, GeoProcessor gp){
		try {
			gp.setEnvironmentValue("workspace", inputworkspace);

			//get a list of personal geodatabases(gdb) and loop through them
			IGpEnumList personalGeodatabases = gp.listWorkspaces("", "Access");
			String pgdb = personalGeodatabases.next();
			while (! "".equals(pgdb)){

				//set the workspace to the personal geodatabase
				gp.setEnvironmentValue("workspace", pgdb);

				//create a file gdb with the same name as the personal gdb
				String fileName = pgdb.substring(inputworkspace.length(), pgdb.lastIndexOf(".mdb"));
				//System.out.println(pgdb + " : " + fileName);

				CreateFileGDB createFileGDB = new CreateFileGDB(outputWorkspace, fileName);
				gp.execute(createFileGDB, null);

				//identify the feature classes in the personal gdb copy them to the file gdb
				Copy copyTool = new Copy();
				IGpEnumList featureClasses = gp.listFeatureClasses("", "", "");
				String fc = featureClasses.next();
				while (! "".equals(fc)){
					System.out.println("Copying feature class " + fc + " to " + fileName + ".gdb");
					copyTool.setInData(fc);
					copyTool.setOutData(outputWorkspace + "/" + fileName + ".gdb" + "/" + fc);
					gp.execute(copyTool, null);
					fc = featureClasses.next();
				}

				//identify the tables and copy as well
				IGpEnumList tables = gp.listTables("", "");
				String table = tables.next();
				while (! "".equals(table)){
					System.out.println("Copying table " + table + " to " + fileName + ".gdb");
					copyTool.setInData(table);
					copyTool.setOutData(outputWorkspace + "/" + fileName + ".gdb" + "/" + table);
					gp.execute(copyTool, null);
					table = tables.next();
				}

				//finally, copy the feature datatsets as well
				IGpEnumList featureDatasets = gp.listDatasets("", "");
				String fds = featureDatasets.next();
				while (! "".equals(fds)){
					System.out.println("Copying feature datatset " + fds + " to " + fileName + ".gdb");
					copyTool.setInData(fds);
					copyTool.setOutData(outputWorkspace + "/" + fileName + ".gdb" + "/" + fds);
					gp.execute(copyTool, null);
					fds = featureDatasets.next();
				}

				pgdb = personalGeodatabases.next();
			}


			System.out.println("finished at " + new Date(System.currentTimeMillis()).toString());
		} catch (Exception e) {
			try {
				//if there was an error then you want to loop the messages
				//returned by the geoprocessor to look for the error
				IGPMessages gpMessages = gp.getReturnMessages();
				for (int i = 0; i < gpMessages.getCount(); i++) {
					System.out.println(gpMessages.getMessage(i).getDescription());
				}
			} catch (Exception e2) {}
			e.printStackTrace();
		}
	}

	
	public void initializeArcGISLicenses(){
		try {
			com.esri.arcgis.system.AoInitialize ao = new com.esri.arcgis.system.AoInitialize();
			if (ao.isProductCodeAvailable(com.esri.arcgis.system.esriLicenseProductCode.esriLicenseProductCodeEngine) == com.esri.arcgis.system.esriLicenseStatus.esriLicenseAvailable)
				ao.initialize(com.esri.arcgis.system.esriLicenseProductCode.esriLicenseProductCodeEngine);
			else if (ao.isProductCodeAvailable(com.esri.arcgis.system.esriLicenseProductCode.esriLicenseProductCodeArcServer) == com.esri.arcgis.system.esriLicenseStatus.esriLicenseAvailable){
				ao.initialize(com.esri.arcgis.system.esriLicenseProductCode.esriLicenseProductCodeArcServer);
			} else {
				System.out.println("Está no es una licencia valida para correr la aplicación.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getOutputDir() {
		String userDir;
		if(System.getProperty("os.name").toLowerCase().indexOf("win")>-1)
			userDir = System.getenv("UserProfile");
		else
			userDir = System.getenv("HOME");
		String outputDir = userDir+"\\Documents\\ArcGIS";
		System.out.println("Creating output directory - "+outputDir);
		new File(outputDir).mkdir();
		return outputDir;
	}
}