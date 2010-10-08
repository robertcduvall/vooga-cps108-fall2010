package vooga.games.galaxyinvaders;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Levels {

    private ArrayList<File> files;
    private Map<Integer, ArrayList<Point>> map;
    private int levelNum;
   
    public Levels(){
        files = new ArrayList<File>();
        map = new HashMap<Integer, ArrayList<Point>>();
        levelNum = 1;
    }
   
    public void createLevels() throws FileNotFoundException{
        files.add(new File("level1.txt"));
        //files.add(new File("src/level2"));
        //files.add((new File("src/level3"));
        makeMap();
    }
   
    public void makeMap() throws FileNotFoundException{
        for (File file: files){
        	Scanner scanner = new Scanner(file);
            int levelNum = scanner.nextInt();
            map.put(levelNum, new ArrayList<Point>());
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner sc = new Scanner(line);
                int temp1 = sc.nextInt();
                int temp2 = sc.nextInt();
                map.get(levelNum).add(new Point(temp1, temp2));
            }
        }   
    }
   
    public void nextLevel(){
        levelNum++;
    }
   
    public ArrayList<Point> getLevelPath(){
        return map.get(levelNum);
    }
}