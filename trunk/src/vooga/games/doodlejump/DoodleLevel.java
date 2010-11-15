package vooga.games.doodlejump;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;

import com.golden.gamedev.object.AnimatedSprite;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;

import vooga.engine.core.VoogaPlayField;
import vooga.engine.factory.*;
import vooga.engine.resource.Resources;

import vooga.games.doodlejump.monsters.*;
import vooga.games.doodlejump.platforms.*;

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
	public VoogaPlayField getPlayfield(File levelFactoryFile) {
		Scanner levelFile = null;
		try {
			levelFile = new Scanner(levelFactoryFile);
		} catch (Exception e) {
			System.exit(0);
		}
		String backgroundName = levelFile.nextLine();
		Resources.loadImage(backgroundName, "");
		background = new ImageBackground(Resources.getImage(backgroundName));
		VoogaPlayField playField = new VoogaPlayField(background);
		while (levelFile.hasNextLine()) {
			String sprite = levelFile.nextLine();
			String spriteName = sprite.substring(0, sprite.indexOf(","));
			double x = Double.parseDouble(sprite.substring(sprite.indexOf(",") + 1, sprite.lastIndexOf(",")));
			double y = Double.parseDouble(sprite.substring(sprite.lastIndexOf(",") + 1));
			addPlatforms(spriteName, x, y);
			addMonsters(spriteName, x, y);
			addPowerUps(spriteName, x, y);
			addLevelInfo(spriteName, x, y);
		}
		playField.addGroup(brownPlatformGroup);
		playField.addGroup(whitePlatformGroup);
		playField.addGroup(trampolineGroup);
		playField.addGroup(platformGroup);
		playField.addGroup(monsterGroup);
		playField.addGroup(springGroup);
		return playField;
	}
	
	public void addPlatforms(String spriteName, double x, double y){
		if (spriteName.equals("green_platform") || spriteName.equals("dark_blue_platform"))
			platformGroup.add(new Sprite(Resources.getImage(spriteName), x, y));
		else if (spriteName.equals("gray_platform"))
			platformGroup.add(new GrayPlatform(Resources.getImage(spriteName), x, y));
		else if (spriteName.equals("light_blue_platform"))
			platformGroup.add(new LightBluePlatform(Resources.getImage(spriteName), x, y));
		else if (spriteName.equals("white_platform"))
			whitePlatformGroup.add(new Sprite(Resources.getImage(spriteName), x, y));
		else if (spriteName.equals("brown_platform_breaking")) {
			BufferedImage[] breaking_brown_images = new BufferedImage[4];
			breaking_brown_images[0] = Resources.getImage(spriteName + "_1");
			breaking_brown_images[1] = Resources.getImage(spriteName + "_2");
			breaking_brown_images[2] = Resources.getImage(spriteName + "_3");
			breaking_brown_images[3] = Resources.getImage(spriteName + "_4");
			brownPlatformGroup.add(new AnimatedSprite(breaking_brown_images, x, y));
		} 
	}
	
	public void addMonsters(String spriteName, double x, double y){
		if (spriteName.equals("blue_flying_monster")) {
			BufferedImage[] blue_flying_images = new BufferedImage[5];
			blue_flying_images[0] = Resources.getImage(spriteName + "_1");
			blue_flying_images[1] = Resources.getImage(spriteName + "_2");
			blue_flying_images[2] = Resources.getImage(spriteName + "_3");
			blue_flying_images[3] = Resources.getImage(spriteName + "_4");
			blue_flying_images[4] = Resources.getImage(spriteName + "_5");
			monsterGroup.add(new JigglingFlyingMonster(blue_flying_images, x, y));
		} else if (spriteName.equals("blue_monster"))
			monsterGroup.add(new MovingMonster(Resources.getImage("blue_monster_left"), x, y));
		else if (spriteName.equals("green_flying_monster")) {
			BufferedImage[] green_flying_images = new BufferedImage[5];
			green_flying_images[0] = Resources.getImage(spriteName + "_1");
			green_flying_images[1] = Resources.getImage(spriteName + "_2");
			green_flying_images[2] = Resources.getImage(spriteName + "_3");
			green_flying_images[3] = Resources.getImage(spriteName + "_4");
			green_flying_images[4] = Resources.getImage(spriteName + "_5");
			monsterGroup.add(new FlyingMonster(green_flying_images, x, y));
		} else if (spriteName.equals("green_monster") || spriteName.equals("big_blue_monster") || spriteName.equals("red_monster") || spriteName.equals("purple_monster"))
			monsterGroup.add(new JigglingMonster(Resources.getImage(spriteName), x, y));
		else if (spriteName.equals("green_jumping_monster"))
			monsterGroup.add(new JumpingMonster(Resources.getImage(spriteName), x, y));
	}
	
	public void addPowerUps(String spriteName, double x, double y){
		if (spriteName.equals("spring")) {
			BufferedImage[] spring_images = new BufferedImage[2];
			spring_images[0] = Resources.getImage(spriteName + "_compressed");
			spring_images[1] = Resources.getImage(spriteName + "_full");
			springGroup.add(new AnimatedSprite(spring_images, x, y));
		} else if (spriteName.equals("trampoline")) {
			BufferedImage[] trampoline_images = new BufferedImage[2];
			trampoline_images[0] = Resources.getImage(spriteName);
			trampoline_images[1] = Resources.getImage(spriteName + "_down");
			trampolineGroup.add(new AnimatedSprite(trampoline_images, x, y));
		}
	}
	
	public void addLevelInfo(String spriteName, double x, double y){
		if (spriteName.equals("finish_line")) {
			platformGroup.add(new Sprite(Resources.getImage("finish_line"), x, y));
		} else if (spriteName.equals("score")) {
			score = (int) x;
			nextLevel = (int) y;
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
