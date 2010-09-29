package engine.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.sound.midi.Sequence;
import javax.sound.sampled.AudioFormat;

import com.brackeen.javagamebook.sound.Sound;
import com.brackeen.javagamebook.sound.SoundManager;
import com.brackeen.javagamebook.sound.MidiPlayer;

/**
 * The SoundHandler class manages Sounds for the ResourceManager. It contains a SoundManager and
 * a MidiPlayer to aid in the playing of the Sounds it manages.
 * @author John Kline
 * @date September 26, 2010
 */
public class SoundHandler {

	private static List<String> myDirectories = new ArrayList<String>();
	private static Map<String, Object> myMap = new HashMap<String, Object>();
	
	// uncompressed, 44100Hz, 16-bit, mono, signed, little-endian
	private static SoundManager sm = new SoundManager(new AudioFormat(44100,
			16, 1, true, false), 8);
	
	private static MidiPlayer mp = new MidiPlayer();

	/**
	 * Adds the Sound specified at "filepath" to myMap with key="name".
	 * @param name The name to be associated with the Sound located at the filepath.
	 * @param filepath The location of the Sound in the filesystem.
	 */
	public static void addSoundMapping(String name, String filepath) {
		Sound sound = loadSound(filepath);
		addMapping(name, sound);
	}
	
	/**
	 * Adds the Sound "sound" to myMap with key="name".
	 * @param name The name to be associated with the sound.
	 * @param sound The sound to be added to the map.
	 */
	public static void addSoundMapping(String name, Sound sound) {
		addMapping(name, sound);
	}
	
	/**
	 * Adds the Sequence specified at "filepath" to myMap with key="name".
	 * @param name The name to be associated with the sequence.
	 * @param filepath The location of the Sequence in the filesystem.
	 */
	public static void addSequenceMapping(String name, String filepath) {
		Sequence seq = loadSequence(filepath);
		addMapping(name, seq);
	}
	
	/**
	 * Adds the Sequence "sequence" to myMap with key="name".
	 * @param name The name to be associated with the sequence.
	 * @param sequence The sequence to be added to the map.
	 */
	public static void addSequenceMapping(String name, Sequence sequence) {
		addMapping(name, sequence);
	}

	/**
	 * Adds a directory to myDirectories.
	 * @param newDir The directory location as a string.
	 */
	public static void addDirectory(String newDir) {
		myDirectories.add(newDir);
	}
	
	/**
	 * Adds a mapping to myMap.
	 * @param key The String key to add.
	 * @param object The Object associated with the key.
	 */
	public static void addMapping(String key, Object object) {
		myMap.put(key, object);
	}
	
	/**
	 * Return an Object value associated with the String key.
	 * @param key The String key to check for.
	 * @return The Object value associated with key.
	 */
	public static Object getMapping(String key) {
		return myMap.get(key);
	}
	
	/**
	 * Loads a file from a directory string. Adds the directory string to myDirectories,
	 * and puts the filepath->reference mappings into myMap. This method handles Sounds and Sequences.
	 * @param directory The location of the file to load.
	 * @throws IOException
	 */
	public static void loadFile(String directory) throws IOException {
		addDirectory(directory);
		ArrayList<String> lines = new ArrayList<String>();
		int size = 0;
		URL url = SoundHandler.class.getClassLoader().getResource(directory);
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
		boolean sequenceMode = false;
		for (int y=0; y<size; y++) {
			String line = (String)lines.get(y);
			if (line.equals("$Sounds")) {
				sequenceMode = false;
				y++;
			} else if (line.equals("$Sequences")) {
				sequenceMode = true;
				y++;
			}
			if (!sequenceMode) {
				line = lines.get(y);
				StringTokenizer st = new StringTokenizer(line, ",");
				String filepath = st.nextToken();
				String name = st.nextToken();
				Sound snd = null;
				if (name.equals("")) {
					System.out.println("Filepath " + y + "has no reference name associated with it.");
				} else {
					snd = loadSound(filepath); 
				}
				if (snd != null)
					addMapping(name, snd);
			} else {
				line = lines.get(y);
				StringTokenizer st = new StringTokenizer(line, ",");
				String filepath = st.nextToken();
				String name = st.nextToken();
				Sequence sequence = null;
				if (name.equals("")) {
					System.out.println("Filepath" + y + "has no reference name associated with it.");
				} else {
					sequence = loadSequence(filepath);
				}
				if (sequence != null) {
                	addMapping(name, sequence);
				}
			}
		}
	}

	/**
	 * Uses the SoundManager to load a sound.
	 * @param filepath The location of the sound file.
	 * @return The sound.
	 */
	public static Sound loadSound(String filepath) {
        return sm.getSound(getResourceAsStream(filepath));
    }

	/**
	 * Uses the MidiPlayer to load a sequence.
	 * @param filepath The location of the sequence file.
	 * @return The sequence.
	 */
    public static Sequence loadSequence(String filepath) {
        return mp.getSequence(getResourceAsStream(filepath));
    }
    
    /**
     * Returns as an InputStream the file specified by filename.
     * @param filename The String representation of the filename.
     * @return
     */
    public static InputStream getResourceAsStream(String filename) {
        return SoundHandler.class.getClassLoader().
            getResourceAsStream(filename);
    }

    /**
     * Returns the SoundManager
     * @return SoundManager.
     */
	public static SoundManager getSM() {
		return sm;
	}
	
	/**
	 * Returns the MidiPlayer.
	 * @return MidiPlayer.
	 */
	public static MidiPlayer getMP() {
		return mp;
	}
}
