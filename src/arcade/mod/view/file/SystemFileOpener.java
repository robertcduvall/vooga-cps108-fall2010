package arcade.mod.view.file;

import java.io.File;

import javax.imageio.metadata.IIOMetadataNode;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import vooga.engine.util.XMLDocumentCreator;
import vooga.engine.util.XMLFileParser;

public class SystemFileOpener implements IFileOpener{
	
	public static final String RECENT_FILE_LIST = "src/arcade/mod/recentlyOpened.xml";
	public static final String FILE_TAG = "File";
	public static final String FILE_PATH_ATTRIBUTE = "path";
	private JComponent parent;
	
	public SystemFileOpener(JComponent parent){
		this.parent = parent;
	}

	@Override
	public File openFile() {
		JFileChooser loadChooser = new JFileChooser();

		loadChooser.showOpenDialog(parent);
		File file = loadChooser.getSelectedFile();
		addToRecentlyOpened(file);
		return file;
	}
	
	private void addToRecentlyOpened(File file){
		String filepath = file.getPath();
		filepath = filepath.substring(filepath.indexOf("src"));
		System.out.println(filepath);
		XMLDocumentCreator xmlParser = new XMLFileParser(RECENT_FILE_LIST);
		Document doc;
		try {
			doc = xmlParser.getDocument();
			Element newChild = doc.createElement(FILE_TAG);
			newChild.setAttribute(FILE_PATH_ATTRIBUTE, filepath);
			Node root = doc.getFirstChild();
			root.insertBefore(newChild, root.getFirstChild());
			   // Prepare the DOM document for writing
		        Source source = new DOMSource(doc);

		        // Prepare the output file
		        File recentFile = new File(RECENT_FILE_LIST);
		        Result result = new StreamResult(recentFile);

		        // Write the DOM document to the file
		        Transformer xformer = TransformerFactory.newInstance().newTransformer();
		        xformer.transform(source, result);
		} catch (Exception e){
			//TODO better error handling
			e.printStackTrace();
		}	
		
	}

}
