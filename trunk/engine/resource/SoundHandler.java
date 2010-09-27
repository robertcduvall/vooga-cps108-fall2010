package engine.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.sound.midi.Sequence;

import com.brackeen.javagamebook.sound.Sound;
import com.brackeen.javagamebook.sound.SoundManager;
import com.brackeen.javagamebook.sound.MidiPlayer;

/**
 * The SoundHandler class manages Sounds for the ResourceManager. It contains a SoundManager and
 * a MidiPlayer to aid in the playing of the Sounds it manages.
 * @author John Kline
 * @date September 26, 2010
 */
public class SoundHandler extends ResourceHandler {

	private SoundManager sm;
	private MidiPlayer mp;
	
	public SoundHandler(SoundManager sm, MidiPlayer mp) {
		this.sm = sm;
		this.mp = mp;
	}

	/**
	 * This constructor invokes the loadFile() method on "directory" to fill myMap.
	 * @param directory The directory to access.
	 */
	public SoundHandler(SoundManager sm, MidiPlayer mp, String directory) {
		this.sm = sm;
		this.mp = mp;
		try {
			loadFile(directory);
		}catch (IOException e) {
			System.out.println("Could not load file for soundhandler");
		}
	}

	/**
	 * This constructor invokes the loadFile() method on all Strings in "directories" to fill myMap.
	 * @param directories The list of directories to access.
	 */
	public SoundHandler(SoundManager sm, MidiPlayer mp, ArrayList<String> directories) {
		super(directories);
		this.sm = sm;
		this.mp = mp;
		for (String s: directories) {
			try {
				loadFile(s);
			} catch (IOException e) {
				System.out.println("Could not load file for soundhandler");
			}
		}
	}

	/**
	 * Adds the Sound specified at "filepath" to myMap with key="name".
	 * @param name The name to be associated with the Sound located at the filepath.
	 * @param filepath The location of the Sound in the filesystem.
	 */
	public void addSoundMapping(String name, String filepath) {
		Sound sound = loadSound(getClass().getClassLoader().getResource(filepath).toString());
		super.addMapping(name, sound);
	}
	
	/**
	 * Adds the Sound "sound" to myMap with key="name".
	 * @param name The name to be associated with the sound.
	 * @param sound The sound to be added to the map.
	 */
	public void addSoundMapping(String name, Sound sound) {
		super.addMapping(name, sound);
	}
	
	/**
	 * Adds the Sequence specified at "filepath" to myMap with key="name".
	 * @param name The name to be associated with the sequence.
	 * @param filepath The location of the Sequence in the filesystem.
	 */
	public void addSequenceMapping(String name, String filepath) {
		Sequence seq = loadSequence(getClass().getClassLoader().getResource(filepath).toString());
		super.addMapping(name, seq);
	}
	
	/**
	 * Adds the Sequence "sequence" to myMap with key="name".
	 * @param name The name to be associated with the sequence.
	 * @param sequence The sequence to be added to the map.
	 */
	public void addSequenceMapping(String name, Sequence sequence) {
		super.addMapping(name, sequence);
	}

	/**
	 * Loads a file from a directory string. Adds the directory string to myDirectories,
	 * and puts the filepath->reference mappings into myMap. This method handles Sounds and Sequences.
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
					super.addMapping(name, snd);
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
                	super.addMapping(name, sequence);
				}
			}
		}
	}

	/**
	 * Uses the SoundManager to load a sound.
	 * @param filepath The location of the sound file.
	 * @return The sound.
	 */
	public Sound loadSound(String filepath) {
        return sm.getSound(getResourceAsStream(filepath));
    }

	/**
	 * Uses the MidiPlayer to load a sequence.
	 * @param filepath The location of the sequence file.
	 * @return The sequence.
	 */
    public Sequence loadSequence(String filepath) {
        return mp.getSequence(getResourceAsStream(filepath));
    }
    
    public InputStream getResourceAsStream(String filename) {
        return getClass().getClassLoader().
            getResourceAsStream(filename);
    }

    /**
     * Returns the SoundManager
     * @return SoundManager.
     */
	public SoundManager getSM() {
		return this.sm;
	}
	
	/**
	 * Returns the MidiPlayer.
	 * @return MidiPlayer.
	 */
	public MidiPlayer getMP() {
		return this.mp;
	}
}
