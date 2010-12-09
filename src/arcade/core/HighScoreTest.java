package arcade.core;

import java.util.Random;

import arcade.util.database.Constants;

public class HighScoreTest {
	public static void main(String[] args) {
		HighScoreControl hsu = new HighScoreControl(Constants.HOST,
				Constants.DBNAME, Constants.USER, Constants.PASSWORD,
				"HighScores");
		 generateRandomScores(hsu,100);
		System.out.println(hsu.getGameHighScores("galaxyinvaders", 5));
		System.out.println(hsu.getPlayerHighScores("PlayerA", 5));
	}

	private static void generateRandomScores(HighScoreControl hsu, int n) {
		String[] gameNames = { "asteroids", "cyberion", "doodlejump",
				"galaxyinvaders", "grandius", "jumper", "mariogame",
				"towerdefense", "zombieland" };
		String[] playerNames = { "PlayerA", "PlayerB", "PlayerC", "Guest" };
		Random r = new Random();
		for (int i = 0; i < n; i++) {
			int gameIndex = r.nextInt(gameNames.length);
			int playerIndex = r.nextInt(playerNames.length);
			double score = r.nextInt(13544);
			System.out.println(playerNames[playerIndex] + ","
					+ gameNames[gameIndex] + "," + score);
			hsu.addScore(playerNames[playerIndex], gameNames[gameIndex],
					score + "");
		}
	}

}
