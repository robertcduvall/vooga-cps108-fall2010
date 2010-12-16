package arcade.ads.adsbuilder;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileView;

import arcade.ads.adscontent.BasicAd;

public class AdBuilder extends JFrame{

	private static final String XML_TEXT_FILE = "src/arcade/ads/resources/ads";
	private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
	private static final String PROPERTIES_FILE = "arcade/ads/adsbuilder/adbuilder";
	private static final String TEXT_FILE = "arcade/ads/adsbuilder/ads";
	
	private JPanel panel1, panel2, panel3, panel4, subpanel1, subpanel2, subpanel3, subpanel4;
	private JCheckBox[] tagBoxes;
	private JTextField rankField;
	
	private HashMap<String, ArrayList<String>> propertiesMap;
	private HashMap<String, JTextField> attributeMap;
	private HashMap<String, ArrayList<String>> parameterMap;
	private HashMap<String, ArrayList<String>> xmlTagMap;
	private ResourceBundle myTextResources;
	
	private File xmlText;
	private File xmlFile;
	private FileWriter xmlWriter;
	
	//ad properties
	private BasicAd ad;
	private String type = "Image"; //default is an ImageAd
	private File file;
	private ArrayList<String> tags = new ArrayList<String>();
	private boolean related, featured;
	
	public AdBuilder(String xmlTextFile){
		setTitle("Ad Builder");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		propertiesMap = readFile(PROPERTIES_FILE);
		
		JFileChooser chooser = new JFileChooser(System.getProperties().getProperty("user.dir"));
		chooser.setDialogTitle("Save XML File");
		//chooser.
		int state = chooser.showSaveDialog(null);
		if (state == chooser.APPROVE_OPTION);
			xmlFile = chooser.getSelectedFile();
		try {
			xmlWriter = new FileWriter(xmlFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JFileChooser chooser2 = new JFileChooser(System.getProperties().getProperty("user.dir"));
		chooser2.setDialogTitle("Select Text File of XML Files");
		//chooser.
		int state2 = chooser2.showOpenDialog(null);
		if (state2 == chooser2.APPROVE_OPTION);
			xmlText = chooser2.getSelectedFile();
		
		attributeMap = new HashMap<String, JTextField>();
		myTextResources = ResourceBundle.getBundle(TEXT_FILE);
		
		addTag(XML_HEADER + "\n<AdGroup>\n");
		
		createPanels();
		
		makeMenu(subpanel1, propertiesMap.get("types").toArray(new String[propertiesMap.get("types").size()]));
		makeCheckBoxes(subpanel1);
		makeFileBrowser(subpanel2);
		makeTextFields(panel2, (String[]) propertiesMap.get("labels").toArray(new String[propertiesMap.get("labels").size()]));
		//rankField = addField("Rank", panel2); //if featured
		//updateField(false, rankField);
		tagBoxes = addCheckMenu(subpanel3, "Tags", (String[]) propertiesMap.get("tags").toArray(new String[propertiesMap.get("tags").size()])); //if related
		updateField(false, tagBoxes);
		addButton("Submit", subpanel4);

		//getContentPane().setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		getContentPane().add(panel1, BorderLayout.PAGE_START);
		getContentPane().add(panel2, BorderLayout.CENTER);
		getContentPane().add(panel3, BorderLayout.PAGE_END);
		
		pack();
		setVisible(true);
	}
	
	private HashMap<String, ArrayList<String>> readFile(String file){
		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		ResourceBundle myResources = ResourceBundle.getBundle(file);
		for (String key : myResources.keySet()) {
			map.put(key, new ArrayList<String>());
			for (String value : myResources.getString(key).split(","))
				map.get(key).add(value);
		}
		return map;
	}
	
	private void addTag(String tag){
		try {
			xmlWriter.write(tag);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void createPanels(){
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,1));
		subpanel1 = new JPanel();
		subpanel1.setLayout(new FlowLayout());
		subpanel2 = new JPanel();
		panel1.add(subpanel1);
		panel1.add(subpanel2);
		
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(5,1));
		
		panel3 = new JPanel();
		panel3.setLayout(new BorderLayout());
		subpanel3 = new JPanel();
		subpanel3.setLayout(new GridLayout(5,2));
		subpanel4 = new JPanel();
		panel3.add(subpanel3, BorderLayout.NORTH);
		panel3.add(subpanel4, BorderLayout.SOUTH);
		
		panel4 = new JPanel();
	}
	
	private void makeMenu(JPanel panel, String[] items){
		JComboBox typeMenu = new JComboBox(items);
	    typeMenu.setSelectedIndex(0);
		typeMenu.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
			    JComboBox cb = (JComboBox)e.getSource();
		        type = (String)cb.getSelectedItem();
			}
		});
		
		panel.add(typeMenu);
	}
	
	private void makeCheckBoxes(JPanel panel){
		JCheckBox relatedBox = new JCheckBox("Related");
		relatedBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				related = !related;
				updateField(related, tagBoxes);
				subpanel3.updateUI();
			}
			
		});
		JCheckBox featuredBox = new JCheckBox("Featured");
		/*featuredBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				featured = !featured;
				//updateField(featured, rankField);
				panel2.updateUI();
			}
		
		});
		featuredBox.setEnabled(false);*/
		panel.add(relatedBox);
		panel.add(featuredBox);
	}
	
	private void makeFileBrowser(JPanel panel){
		JLabel label = new JLabel("Choose Ad File:");
		panel.add(label);
		JButton button = new JButton("Browse");
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser(System.getProperties().getProperty("user.dir"));
				int state = chooser.showOpenDialog(null);
				if (state == chooser.APPROVE_OPTION)
					setFile(chooser.getSelectedFile());
			}
			
		});
		panel.add(button);
	}
	
	private void setFile(File f){
		file = f;
		JLabel label;
		try {
			label = new JLabel("Chosen File: " + file.getName());
			subpanel2.add(label);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		panel1.updateUI();
	}
	
	private void makeTextFields(JPanel panel, String[] items){
		for (String item: items){
			attributeMap.put(item, addField(item, panel));
		}
	}
	
	private JCheckBox[] addCheckMenu(JPanel panel, String label, String[] items){
		ArrayList<JCheckBox> boxes = new ArrayList<JCheckBox>();
		for (String item: items){
			JCheckBox box = new JCheckBox(item);
			box.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					updateTag(tags, ((JCheckBox) e.getSource()).getText());
				}	
			});
			panel.add(box);
			boxes.add(box);
		}
		return boxes.toArray(new JCheckBox[boxes.size()]);
	}
	
	private void updateTag(ArrayList<String> list, String tag){
		if (list.contains(tag)){
			list.remove(tag);
		}
		else
			list.add(tag);
	}
	
	
	private JTextField addField(String text, JPanel panel){
		JLabel label = new JLabel(text);
		JTextField field = new JTextField(20);
		field.setText(myTextResources.getString(text));
		panel.add(label);
		panel.add(field);
		return field;
	}

	private void addButton(String text, JPanel panel){
		JButton button = new JButton(text);
		//button.setPreferredSize(new Dimension(6,1));
		button.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				getAllInfo();
				int n = JOptionPane.showConfirmDialog(null, "Do you want to create another ad?", "", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				respond(n);
			}
			
		});
		panel.add(button);
	}
	
	private void getAllInfo(){
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			if (!related && !featured) {
				addTag("<Ads>\n");
				writeBasicAd();
				addTag("</Ads>\n");
			}
			else if (related) {
				addTag("<RelatedAds>\n");
				addTag("<RelatedAd type=\"");
				for (int i=0; i<tags.size()-1; i++) {
					addTag(tags.get(i) + ",");
				}
				addTag(tags.get(tags.size()-1) + "\">\n");
				writeBasicAd();
				addTag("</RelatedAd>\n</RelatedAds>\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		addTag("</AdGroup>");
		writeXMLtoText();
		try {
			xmlWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void writeBasicAd(){
		addTag("<Ad");
		addTag(" class=\""+ propertiesMap.get(type).get(0) + "\" ");
		try {
			String path = file.getCanonicalPath().replace(System.getProperties().getProperty("user.dir"),"");
			addTag(type.toLowerCase() + "=" + path.substring(1) + " ");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String label : propertiesMap.get("labels")){
			addTag(label + "=\"" + attributeMap.get(label).getText() +"\" ");
		}
		addTag("/>\n");
	}
	
	private void writeXMLtoText(){
		FileWriter textWriter;
		try {
			textWriter = new FileWriter(xmlText, true);
			String path = xmlFile.getCanonicalPath().replace(System.getProperties().getProperty("user.dir"),"");
			textWriter.append("\n" + path.substring(1));
			textWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void updateField(boolean val, Component... comp){
		for (Component c: comp)
			c.setEnabled(val);
	}
	
	private void respond(int n){
		if (n==JOptionPane.YES_OPTION) {
			//this.
			new AdBuilder(XML_TEXT_FILE);
		}
		else if (n==JOptionPane.NO_OPTION)
			System.exit(0);
	}
	
	public static void main(String args[]){
		new AdBuilder(XML_TEXT_FILE);
	}
	
}
