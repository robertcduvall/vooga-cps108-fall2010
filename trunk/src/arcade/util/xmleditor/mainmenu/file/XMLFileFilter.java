package arcade.util.xmleditor.mainmenu.file;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

public class XMLFileFilter extends FileFilter {

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}

		String extension = getExtension(f);
		if (extension != null) {
			if (extension.equals(".xml")) {
				return true;
			} else {
				return false;
			}
		}

		return false;
	}

	// The description of this filter
	public String getDescription() {
		return "*.xml";
	}

	private String getExtension(File file) {
		String path = file.getPath();
		int lastIndex = path.lastIndexOf('.');
		if(lastIndex>0){
			return path.substring(lastIndex);
		}
		return "";
	}
}
