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
import java.io.File;
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

	private static final String PROPERTIES_FILE = "arcade/ads/adsbuilder/adbuilder";
	private static final String TEXT_FILE = "arcade/ads/adsbuilder/ads";
	private static final String PARAMETERS_FILE = "arcade/ads/adsbuilder/parameters";
	private static final String PATH = "";
	
	private JPanel panel1, panel2, panel3, panel4, subpanel1, subpanel2, subpanel3, subpanel4;
	private JCheckBox[] tagBoxes;
	private JTextField rankField;
	
	private HashMap<String, ArrayList<String>> propertiesMap;
	private HashMap<String, JTextField> attributeMap;
	private HashMap<String, ArrayList<String>> parameterMap;
	private ResourceBundle myTextResources;
	
	//ad properties
	private BasicAd ad;
	private String type = "Image"; //default is an ImageAd
	private File file;
	private ArrayList<String> tags = new ArrayList<String>();
	private boolean related, featured;
	
	public AdBuilder(){
		propertiesMap = readFile(PROPERTIES_FILE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		//attributeMap = new HashMap<String, String>();
		attributeMap = new HashMap<String, JTextField>();
		myTextResources = ResourceBundle.getBundle(TEXT_FILE);
		parameterMap = readFile(PARAMETERS_FILE);
		
		setTitle("Ad Builder");
		
		createPanels();
		
		makeMenu(subpanel1, propertiesMap.get("types").toArray(new String[propertiesMap.get("types").size()]));
		makeCheckBoxes(subpanel1);
		makeFileBrowser(subpanel2);
		makeTextFields(panel2, (String[]) propertiesMap.get("labels").toArray(new String[propertiesMap.get("labels").size()]));
		rankField = addField("Rank", panel2); //if featured
		updateField(false, rankField);
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
	
	private void createPanels(){
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,1));
		subpanel1 = new JPanel();
		subpanel1.setLayout(new FlowLayout());
		subpanel2 = new JPanel();
		panel1.add(subpanel1);
		panel1.add(subpanel2);
		// this.add(panel1);
		
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
		featuredBox.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				featured = !featured;
				updateField(featured, rankField);
				panel2.updateUI();
			}
		
		});
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
				//chooser.setFileView(new AdFileView());
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
					tags.add(((JCheckBox) e.getSource()).getLabel());
				}	
			});
			panel.add(box);
			boxes.add(box);
		}
		return boxes.toArray(new JCheckBox[boxes.size()]);
	}
	
	
	private JTextField addField(String text, JPanel panel){
		JLabel label = new JLabel(text);
		JTextField field = new JTextField(20);
		String[] arr = text.split(" ");
		String s = "";
		for (String str : arr){
			s += str;
		}
		field.setText(myTextResources.getString(s));
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
			Class<?> c = Class.forName(propertiesMap.get(type).get(0));
			//for (String s: propertiesMap.get("labels")){
			Class[] parameters = new Class[parameterMap.get(type).size()];
			for (int i=0; i<parameters.length; i++){
				parameters[i] = Class.forName(parameterMap.get(type).get(i));
				if (parameterMap.get(type).get(i).endsWith("Integer"))
					parameters[i] = parameters[i].getMethod("intValue", null).getReturnType();
				if (parameterMap.get(type).get(i).endsWith("Long"))
					parameters[i] = parameters[i].getMethod("longValue", null).getReturnType();
					
			}
//			parameters[0] = String.class;
//			parameters[1] = Image.class;
//			parameters[2] = String.class;
//			parameters[3] = Integer.TYPE;
//			parameters[4] = Integer.TYPE;
//			parameters[5] = Date.class;
//			parameters[6] = Date.class; 
//			parameters[7] = long.class; 
			
			Constructor constructor = c.getConstructor(parameters);
			ArrayList<String> labels = propertiesMap.get("labels");
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Object[] arguments = new Object[8];
			arguments[0] = attributeMap.get(labels.get(0)).getText();
			arguments[1] = ImageIO.read(file);
			arguments[2] = attributeMap.get(labels.get(1)).getText();
			arguments[3] = Integer.parseInt(attributeMap.get(labels.get(2)).getText());
			arguments[4] = Integer.parseInt(attributeMap.get(labels.get(3)).getText());
			arguments[5] = dateFormat.parse(attributeMap.get(labels.get(4)).getText());
			arguments[6] = dateFormat.parse(attributeMap.get(labels.get(5)).getText());
			arguments[7] = Long.parseLong(attributeMap.get(labels.get(6)).getText());
			ad = (BasicAd) constructor.newInstance(arguments);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
//	public void set(String field, Object value){
//		try {
//			Class.class.getField(field).set(field, value);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//	}
	/*ry {
        Class cls = Class.forName("constructor2");
        Class partypes[] = new Class[2];
         partypes[0] = Integer.TYPE;
         partypes[1] = Integer.TYPE;
         Constructor ct 
           = cls.getConstructor(partypes);
         Object arglist[] = new Object[2];
         arglist[0] = new Integer(37);
         arglist[1] = new Integer(47);
         Object retobj = ct.newInstance(arglist);
      }
      catch (Throwable e) {
         System.err.println(e)
	*/
	
	private void updateField(boolean val, Component... comp){
		for (Component c: comp)
			c.setEnabled(val);
	}
	
	private void respond(int n){
		if (n==JOptionPane.YES_OPTION) {
			new AdBuilder();
			System.exit(0);
		}
		else if (n==JOptionPane.NO_OPTION)
			System.exit(0);
	}
	
	public static void main(String args[]){
		new AdBuilder();
	}
	
}
