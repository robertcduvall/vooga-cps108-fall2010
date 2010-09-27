package engine.resource;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.geom.AffineTransform;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;

import com.brackeen.javagamebook.graphics.Animation;

/**
 * The ImageHandler class manages Images and Animations for the ResourceManager.
 * @author John Kline
 * @date September 26, 2010
 *
 */
public class ImageHandler extends ResourceHandler {

	/**
	 * This constructor invokes the loadFile() method on "directory" to fill myMap.
	 * @param directory The directory to be scanned by loadFile().
	 */
	public ImageHandler(String directory) {
		super();
		try {
			loadFile(directory);
		}catch (IOException e) {
			System.out.println("Could not load file for imagehandler");
		}
	}
	
	/**
	 * This constructor invokes the loadFile() method on all Strings in "directories" to fill myMap.
	 * @param directories The list of directories to be scanned by loadFile().
	 */
	public ImageHandler(List<String> directories) {
		super(directories);
		for (String s: directories) {
			try {
				loadFile(s);
			} catch (IOException e) {
			}
		}
	}
	
	/**
	 * Adds the Image specified at "filepath" to myMap with key="name".
	 * @param name The name to be associated with the image.
	 * @param filepath The filesystem location of the image to be added to the map.
	 */
	public void addImageMapping(String name, String filepath) {
		Image img = new ImageIcon(getClass().getClassLoader().getResource(filepath)).getImage();
		super.addMapping(name, img);
	}
	
	/**
	 * Adds the Image "img" to myMap with key="name".
	 * @param name The name to be associated with the image.
	 * @param img The image to be added to the map.
	 */
	public void addImageMapping(String name, Image img) {
		super.addMapping(name, img);
	}
	
	/**
	 * Adds the Animation "anim" to myMap with key="name".
	 * @param name The name to be associated with the animation.
	 * @param anim The animation to be added to the map.
	 */
	public void addAnimMapping(String name, Animation anim) {
		super.addMapping(name, anim);
	}
		
	/**
	 * Loads a file from a directory string. Adds the directory string to myDirectories,
	 * and puts the filepath->reference mappings into myMap. This method handles both
	 * Images and Animations.
	 * @param directory The location of the file to load.
	 * @throws IOException
	 */
	public void loadFile(String directory) throws IOException {
		super.addDirectory(directory);
		ArrayList<String> lines = new ArrayList<String>();
        int size = 0;
        URL url = getClass().getClassLoader().getResource(directory);
        if (url == null) {
            throw new IOException("No such directory: " + directory);
        }
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(url.openStream()));
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                reader.close();
                break;
            }
            if (!line.equals("") && !line.startsWith("#")) {
                lines.add(line);
            }
        }
        size = lines.size();
        boolean animMode = false;
        for (int y=0; y<size; y++) {
            String line = (String)lines.get(y);
            if (line.equals("$Images")) {
            	animMode = false;
            	y++;
            } else if (line.equals("$Animations")) {
            	animMode = true;
            	y++;
            }
            if (!animMode) {
            	line = lines.get(y);
            	StringTokenizer st = new StringTokenizer(line, ",");
                String filepath = st.nextToken();
                System.out.println(filepath);
                String name = st.nextToken();
                System.out.println(name);
                Image img = null;
                if (name.equals("")) {
                	System.out.println("Filepath " + y + "has no reference name associated with it.");
                } else {
                	img = new ImageIcon(getClass().getClassLoader().getResource(filepath)).getImage(); 
                }
                if (img != null)
                	super.addMapping(name, img);
          
            } else {
            	Animation anim = new Animation();
            	String animName = lines.get(y);
            	y++;
            	line = lines.get(y);
            	StringTokenizer st = new StringTokenizer(line, ",");
                while(st.hasMoreTokens()) {
                	String filepath = st.nextToken();
                	long duration = Long.valueOf(st.nextToken());
                	Image img = new ImageIcon(getClass().getClassLoader().getResource(filepath)).getImage();
                	anim.addFrame(img, duration);
                }
                super.addMapping(animName, anim);
            }
        }
	}
	
	/**
	 * Transforms an image.
	 * @param image The image to work with.
	 * @param x
	 * @param y
	 * @param gc
	 * @return The transformed image.
	 */
	public Image getScaledImage(Image image, float x, float y, GraphicsConfiguration gc) {

        // set up the transform
        AffineTransform transform = new AffineTransform();
        transform.scale(x, y);
        transform.translate(
            (x-1) * image.getWidth(null) / 2,
            (y-1) * image.getHeight(null) / 2);

        // create a transparent (not translucent) image
        Image newImage = gc.createCompatibleImage(
            image.getWidth(null),
            image.getHeight(null),
            Transparency.BITMASK);

        // draw the transformed image
        Graphics2D g = (Graphics2D)newImage.getGraphics();
        g.drawImage(image, transform, null);
        g.dispose();

        return newImage;
    }
}
