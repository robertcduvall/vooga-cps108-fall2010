package vooga.games.doodlejump;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import javax.imageio.ImageIO;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;
import vooga.engine.factory.*;

public class DoodleLevel implements LevelFactory {
	private ImageBackground background;

	private SpriteGroup platformGroup, monsterGroup, brownPlatformGroup,
			whitePlatformGroup, springGroup, trampolineGroup;

	private int score, nextLevel;

	public DoodleLevel() {
		platformGroup = new SpriteGroup("Platform Group");
		monsterGroup = new SpriteGroup("Monster Group");
		brownPlatformGroup = new SpriteGroup("Brown Platform Group");
		whitePlatformGroup = new SpriteGroup("White Platform Group");
		springGroup = new SpriteGroup("Spring Group");
		trampolineGroup = new SpriteGroup("Trampoline Group");
	}

	@Override
	public PlayField getPlayfield(File levelFactoryFile) {
		Scanner levelFile = null;
		try {
			levelFile = new Scanner(levelFactoryFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String backgroundPath = levelFile.nextLine();
		background = new ImageBackground(getImage(backgroundPath));
		PlayField playField = new PlayField(background);
		while (levelFile.hasNextLine()) {
			String sprite = levelFile.nextLine();
			String spriteName = sprite.substring(0, sprite.indexOf(","));
			double x = Double.parseDouble(sprite.substring(
					sprite.indexOf(",") + 1, sprite.lastIndexOf(",")));
			double y = Double.parseDouble(sprite.substring(sprite
					.lastIndexOf(",") + 1));
			if (spriteName.equals("green_platform")
					|| spriteName.equals("dark_blue_platform"))
				platformGroup.add(new Sprite(getImage("images/" + spriteName
						+ ".png"), x, y));
			else if (spriteName.equals("gray_platform"))
				platformGroup.add(new GrayPlatform(getImage("images/"
						+ spriteName + ".png"), x, y));
			else if (spriteName.equals("light_blue_platform"))
				platformGroup.add(new LightBluePlatform(getImage("images/"
						+ spriteName + ".png"), x, y));
			else if (spriteName.equals("white_platform"))
				whitePlatformGroup.add(new Sprite(getImage("images/"
						+ spriteName + ".png"), x, y));
			else if (spriteName.equals("brown_platform")) {
				BufferedImage[] breaking_brown_images = new BufferedImage[4];
				breaking_brown_images[0] = getImage("images/brown_platform.png");
				breaking_brown_images[1] = getImage("images/brown_platform_breaking_1.png");
				breaking_brown_images[2] = getImage("images/brown_platform_breaking_2.png");
				breaking_brown_images[3] = getImage("images/brown_platform_breaking_3.png");
				brownPlatformGroup.add(new AnimatedSprite(
						breaking_brown_images, x, y));
			} else if (spriteName.equals("blue_flying_monster")) {
				BufferedImage[] blue_flying_images = new BufferedImage[5];
				blue_flying_images[0] = getImage("images/blue_flying_monster_1.png");
				blue_flying_images[1] = getImage("images/blue_flying_monster_2.png");
				blue_flying_images[2] = getImage("images/blue_flying_monster_3.png");
				blue_flying_images[3] = getImage("images/blue_flying_monster_4.png");
				blue_flying_images[4] = getImage("images/blue_flying_monster_5.png");
				monsterGroup.add(new JigglingFlyingMonster(blue_flying_images,
						x, y));
			} else if (spriteName.equals("blue_monstser"))
				monsterGroup.add(new MovingMonster(
						getImage("images/blue_monster_left.png"), x, y));
			else if (spriteName.equals("green_flying_monster")) {
				BufferedImage[] green_flying_images = new BufferedImage[5];
				green_flying_images[0] = getImage("images/green_flying_monster_1.png");
				green_flying_images[1] = getImage("images/green_flying_monster_2.png");
				green_flying_images[2] = getImage("images/green_flying_monster_3.png");
				green_flying_images[3] = getImage("images/green_flying_monster_4.png");
				green_flying_images[4] = getImage("images/green_flying_monster_5.png");
				monsterGroup.add(new FlyingMonster(green_flying_images, x, y));
			} else if (spriteName.equals("green_monster")
					|| spriteName.equals("big_blue_monster")
					|| spriteName.equals("red_monster")
					|| spriteName.equals("purple_monster"))
				monsterGroup.add(new JigglingMonster(getImage("images/"
						+ spriteName + ".png"), x, y));
			else if (spriteName.equals("green_jumping_monster"))
				monsterGroup.add(new JumpingMonster(
						getImage("green_jumping_monster.png"), x, y));
			else if (spriteName.equals("spring")) {
				BufferedImage[] spring_images = new BufferedImage[2];
				spring_images[0] = getImage("images/spring_compressed.png");
				spring_images[1] = getImage("images/spring_full.png");
				springGroup.add(new AnimatedSprite(spring_images, x, y));
			} else if (spriteName.equals("trampoline")) {
				BufferedImage[] trampoline_images = new BufferedImage[2];
				trampoline_images[0] = getImage("images/trampoline.png");
				trampoline_images[1] = getImage("images/trampoline_down.png");
				trampolineGroup
						.add(new AnimatedSprite(trampoline_images, x, y));
			} else if (spriteName.equals("finish_line")) {
				platformGroup.add(new Sprite(
						getImage("images/finish_line.png"), x, y));
			} else if (spriteName.equals("score")) {
				score = (int) x;
				nextLevel = (int) y;
			}
		}
		playField.addGroup(brownPlatformGroup);
		playField.addGroup(whitePlatformGroup);
		playField.addGroup(trampolineGroup);
		playField.addGroup(platformGroup);
		playField.addGroup(monsterGroup);
		playField.addGroup(springGroup);
		return playField;
	}

	private BufferedImage getImage(String imagePath) {
		try {
			return ImageIO.read(new File("src/vooga/games/doodlejump/"
					+ imagePath));
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public ImageBackground getBackground() {
		return background;
	}

	public SpriteGroup getPlatformGroup() {
		return platformGroup;
	}

	public SpriteGroup getMonsterGroup() {
		return monsterGroup;
	}

	public SpriteGroup getBrownPlatformGroup() {
		return brownPlatformGroup;
	}

	public SpriteGroup getWhitePlatformGroup() {
		return whitePlatformGroup;
	}

	public SpriteGroup getSpringGroup() {
		return springGroup;
	}

	public SpriteGroup getTrampolineGroup() {
		return trampolineGroup;
	}

	public int getScore() {
		return score;
	}

	public int getNextLevel() {
		return nextLevel;
	}
}
