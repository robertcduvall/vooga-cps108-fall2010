package arcade.mod.view.file;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import vooga.engine.util.XMLDocumentCreator;
import vooga.engine.util.XMLFileParser;

public class RecentFileOpener implements IFileOpener{
	
	public static final String RECENT_FILE_LIST = "src/arcade/mod/recentlyOpened.xml";
	public static final String FILE_TAG = "File";
	public static final String FILE_PATH_ATTRIBUTE = "path";
	private String selectedFile;
	JComboBox fileList;
	
	@Override
	public File openFile() {
		JFrame openFrame = new JFrame();
		JPanel mainPanel = new JPanel(new BorderLayout());
		fileList = new JComboBox(getRecentFiles().toArray());
		JPanel buttonPanel = new JPanel(new FlowLayout());
		JButton openButton = new JButton("Open");
		openButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setFile(getDropDownSelection());
			}

		});
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setFile(null);
			}

		});
		
		buttonPanel.add(openButton);
		buttonPanel.add(cancelButton);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);		
		mainPanel.add(fileList, BorderLayout.CENTER);
		openFrame.add(mainPanel);
		openFrame.pack();
		openFrame.setVisible(true);
		
		
		return null;
	}
	
	public List<String> getRecentFiles(){
		List<String> recentFileList = new ArrayList<String>();
		
		XMLDocumentCreator xmlParser = new XMLFileParser(RECENT_FILE_LIST);
		Document doc;
		try {
			doc = xmlParser.getDocument();
		NodeList files = doc.getElementsByTagName(FILE_TAG);
		for(int i=0; i<files.getLength(); i++){
			Node fileNode = files.item(i);
			if(fileNode.getNodeType()==Node.ELEMENT_NODE){
				Element fileElement = (Element) fileNode;
				recentFileList.add(fileElement.getAttribute(FILE_PATH_ATTRIBUTE));
			}
		}
		} catch (Exception e){
			//TODO better error handling
			e.printStackTrace();
		}
		
		return recentFileList;		
	}
	
	private void setFile(String filePath){
		selectedFile=filePath;
	}
	
	private String getDropDownSelection(){
		return (String) fileList.getSelectedItem();
	}
	
}
