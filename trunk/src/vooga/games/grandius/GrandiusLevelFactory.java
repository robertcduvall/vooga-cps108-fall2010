package vooga.games.grandius;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.background.ImageBackground;

import vooga.engine.factory.LevelFactory;
import vooga.engine.resource.Resources;
import vooga.games.grandius.enemy.boss.BossPart;
import vooga.games.grandius.enemy.boss.GrandiusBoss;
import vooga.games.grandius.enemy.boss.reacher.Reacher;
import vooga.games.grandius.enemy.boss.reacher.ReacherEye;
import vooga.games.grandius.enemy.common.Boomer;
import vooga.games.grandius.enemy.common.Zipster;

public class GrandiusLevelFactory implements LevelFactory{
	private PlayField playfield;	
	private Background background;
	//Lists (or Collection) vs. ArrayList
	private ArrayList<Sprite> bossPartList;
	private ArrayList<Sprite> bossList;
	private ArrayList<Sprite> spritesList;
	
	public GrandiusLevelFactory() {
		bossPartList = new ArrayList<Sprite>();
		bossList = new ArrayList<Sprite>();
		spritesList = new ArrayList<Sprite>();
	}
	
		
	@Override
	public PlayField getPlayfield(File levelFactoryFile) {
		playfield = new PlayField();
		
		ArrayList<String> lines = new ArrayList<String>();
		int size = 0;
		boolean bossMode = false;
		StringTokenizer st;
		
		try {
			Scanner scanner = new Scanner(levelFactoryFile);
			while(scanner.hasNextLine()){
				String line = scanner.nextLine();
				if(line.startsWith("#Background")){
					Scanner details = new Scanner(line);
					details.useDelimiter(", *");
					details.next();
					String image = details.next();
					int width = details.nextInt();
					int height = details.nextInt();
					background = new ImageBackground(Resources.getImage(image));
					background.setSize(width, height);
					//playfield.setBackground(background);
				}
				else if (line.equals("") || line.startsWith("#")){
					continue;
				}
				else {
					lines.add(line);
				}
			}
			
			size = lines.size();
			for (int y=2; y<size; y++) {
				String line = lines.get(y);
				if (line.equals("$Bosses")) {
					bossMode = true;
					System.out.println("Going into boss mode");
					y++;
					line = lines.get(y);
				}
				if (bossMode) {
					st = new StringTokenizer(line, ",");
					String bossName = st.nextToken();
					line = lines.get(++y);
					st = new StringTokenizer(line, ",");
					int[] bossBreakpoints = new int[2];
					bossBreakpoints[0] = Integer.parseInt(st.nextToken());
					bossBreakpoints[1] = Integer.parseInt(st.nextToken());
					line = lines.get(++y);
					st = new StringTokenizer(line, ",");
					double bossXPosition = Double.parseDouble(st.nextToken());
					double bossYPosition = Double.parseDouble(st.nextToken());
					line = lines.get(++y);
					st = new StringTokenizer(line, ",");
					int bossHealth = Integer.parseInt(st.nextToken());
					GrandiusBoss newBoss = null;
					if (lines.get(++y).equals("$Parts")) {
						System.out.println("Parts mode");
						line = lines.get(++y);
						//System.out.println(line);
						while(!line.isEmpty()) {
							//System.out.println(line);
							st = new StringTokenizer(line, ",");
							String bossPartName = st.nextToken();
							line = lines.get(++y);
							//System.out.println(line);
							st = new StringTokenizer(line, ",");
							int[] breakpoints = new int[2];
							breakpoints[0] = Integer.parseInt(st.nextToken());
							breakpoints[1] = Integer.parseInt(st.nextToken());
							line = lines.get(++y);
							//System.out.println(line);
							st = new StringTokenizer(line, ",");
							double xPosition = Double.parseDouble(st.nextToken());
							double yPosition = Double.parseDouble(st.nextToken());
							line = lines.get(++y);
							//System.out.println(line);
							st = new StringTokenizer(line, ",");
							int health = Integer.parseInt(st.nextToken());
							int shields = Integer.parseInt(st.nextToken());
							BossPart newBossPart = null;
							if (bossPartName.equals("ReacherEye")) {
								//System.out.println("making new ReacherEye");
								newBossPart = new ReacherEye(Resources.getAnimation(bossPartName), breakpoints, xPosition, yPosition, health, shields);
							}
							bossPartList.add(newBossPart);
							//System.out.println("y="+y);
							//System.out.println("size="+lines.size());
							if ((y+1)==lines.size())
								break;
							line = lines.get(++y);
						}
					}
					if (bossName.equals("Reacher")) {
						System.out.println("creating Reacher at" + bossXPosition + "," + bossYPosition);
						newBoss = new Reacher(Resources.getAnimation(bossName), bossBreakpoints, bossXPosition, bossYPosition, bossHealth, bossPartList);
					}
					bossList.add(newBoss);
					playfield.add(newBoss);
				} else {
					st = new StringTokenizer(line, ",");
					String spriteName = st.nextToken();
					double xPosition = Double.parseDouble(st.nextToken());
					double yPosition = Double.parseDouble(st.nextToken());
					if (spriteName.equals("Zipster")) {
						Zipster newZipster = new Zipster(Resources.getAnimation(spriteName), xPosition, yPosition);
						spritesList.add(newZipster);
						playfield.add(newZipster);
					}
					if (spriteName.equals("Boomer")) {
						Boomer newBoomer = new Boomer(Resources.getAnimation(spriteName), xPosition, yPosition);
						spritesList.add(newBoomer);
						playfield.add(newBoomer);
					}
				}
			}		
		} catch (FileNotFoundException e) {
			System.out.println("LevelFactoryFile not found!");
		}
		return playfield;
	}
	
	/**
	 * Returns a list of all the SpriteLists created by the Factory.
	 * @return
	 */
	public ArrayList<ArrayList<Sprite>> getGrandiusSpritesList() {
		ArrayList<ArrayList<Sprite>> returnCollection = new ArrayList<ArrayList<Sprite>>();
		returnCollection.add(spritesList);
		returnCollection.add(bossPartList);
		returnCollection.add(bossList);
		return returnCollection;
	}
	
	/**
	 * Returns the background
	 * @return
	 */
	public Background getBackground(){
		return background;
	}
}
