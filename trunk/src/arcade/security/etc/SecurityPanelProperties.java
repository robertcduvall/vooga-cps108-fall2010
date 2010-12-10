package arcade.security.etc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Properties;

import arcade.security.exceptions.UserConfigurationNotFoundException;
/**
 * This class takes care of reading the .properties file from a specific folder path.
 * All the keys and its values in those .properties file will be returned as a SecurityFrameData
 * Object.  
 * @author Meng Li
 *
 */
public class SecurityPanelProperties {

	public static String loadProperties(String path) throws UserConfigurationNotFoundException{
		//Collection<SecurityFrameData> applications = new LinkedList<SecurityFrameData>();
		File[] files=getFiles(path);
		String result=null;
		for(int i=0;i<files.length;i++){
			if(files[i].getName().contains(".properties")){
				result = getListOfPanelString(getProperties(files[i].getAbsolutePath()));
			}
		}

		return result;
	}

	public static File[] getFiles(String filePath) throws UserConfigurationNotFoundException  {
		File dir = new File(filePath);

		if ((dir != null) && dir.isDirectory()) {
			File[] files = dir.listFiles();
			return files;
		}

		else{
			throw new UserConfigurationNotFoundException("not a Directory or Directory is empty");
		}
	}

	private static Properties getProperties(String absoluteFilePath)throws UserConfigurationNotFoundException {
		FileInputStream fi = null;
		try{
			fi = new FileInputStream(absoluteFilePath);
			Properties p = new Properties();
			p.load(fi);
			return p;
		}
		catch(FileNotFoundException e){
			throw new UserConfigurationNotFoundException(e);
		}
		catch(IOException e){
			throw new UserConfigurationNotFoundException(e);
		}finally{
			if(fi!=null){
				try {
					fi.close();
				} catch (IOException e) {
					throw new UserConfigurationNotFoundException(e);
				}
			}

		}
	}

	public static String getListOfPanelString(Properties p){
	 
		return p.getProperty("listofpanels");

	}
}
