package vooga.engine.util;

import vooga.engine.core.Game;

/**
 * Singleton class designed to allow the ability to play sounds and music from
 * anywhere in the game. The game must be set first, after that calling
 * SoundPlayer.playMusic() or SoundPlayer.playSound will be the equivalent of
 * calling those methods in your game.
 * 
 * @author Daniel Koverman
 * 
 */
public class SoundPlayer {

	private static Game game;

	/**
	 * Sets the game for playing music. Do this first.
	 * 
	 * @param game
	 *            the current game which you would like to play sounds in
	 */
	public static void setGame(Game game) {
		SoundPlayer.game = game;
	}

	/**
	 * Plays background music for your game. Make sure that the game is set
	 * before calling this.
	 * 
	 * @param audioFilePath
	 *            File path to the music file. Resources supplies the filepath.
	 */
	public static void playMusic(String audioFilePath) {
		game.bsMusic.play(audioFilePath);
	}

	/**
	 * Play a sound effect for your game. Make sure that the game is set before
	 * calling this.
	 * 
	 * @param audioFilePath
	 *            File path to the music file. Resources supplies the filepath.
	 */
	public static void playSound(String audioFilePath) {
		game.playSound(audioFilePath);
	}

}
