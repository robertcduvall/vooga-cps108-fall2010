package vooga.games.galaxyinvaders;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PathPointParser {
	
	private static int timerNum;
	
	public static ArrayList<Point> getPathPoints(String filename) {
		Scanner scanner;
		try {
			scanner = new Scanner(new File(filename));
		} catch (FileNotFoundException e) {
			scanner = null;
			System.out.println("File not found error!");
		}
		timerNum = scanner.nextInt();
		int enemyNum = scanner.nextInt();
		scanner.nextLine();

		ArrayList<Point> pathmap = new ArrayList<Point>();
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			Scanner sc = new Scanner(line);
			int temp1 = sc.nextInt();
			int temp2 = sc.nextInt();
			pathmap.add(new Point(temp1, temp2));
		}
		return pathmap;
	}
	
	public static int getTimerNum() {
		return timerNum;
	}
}
