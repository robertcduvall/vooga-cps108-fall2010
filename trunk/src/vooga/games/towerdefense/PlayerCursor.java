package vooga.games.towerdefense;

import java.awt.image.BufferedImage;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.util.ImageUtil;

import vooga.engine.player.control.PlayerSprite;
import vooga.engine.resource.Resources;

public class PlayerCursor extends PlayerSprite {

	private static final long serialVersionUID = -8174656868252384431L;
	private SpriteGroup towerGroup;
	private TowerDefense myGame;
	private double creditBalance;
	private double creditsPerMillisecond;
	private double towerCost;
	private String towerType;
	private Class towerDefinition;

	public PlayerCursor(String name, String stateName, Sprite s,
			SpriteGroup towerGroup, TowerDefense game) {
		super(name, stateName, s);
		this.towerGroup = towerGroup;
		myGame = game;
		creditsPerMillisecond = 2;
		changeTowerType("FastTower");
	}

	public void changeTowerType(String newTowerType) {
		towerType = newTowerType;
		updateTowerType();
	}

	private void updateTowerType() {
		try {
			towerDefinition = Class.forName("vooga.games.towerdefense."
					+ towerType);
			Field field = towerDefinition.getDeclaredField("PREVIEW");
			BufferedImage image = (BufferedImage) field.get(null);
			setNewImage(image);
			field = towerDefinition.getDeclaredField("COST");
			towerCost = field.getDouble(null);

		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void buildTower() {
		if (creditBalance >= towerCost) {
			try {
				Class[] argsClass = new Class[] { double.class, double.class,
						TowerDefense.class };
				Object[] args = new Object[] { getX(), getY(), myGame };
				Constructor argsConstructor = towerDefinition
						.getConstructor(argsClass);
				Tower tower = (Tower) createTower(argsConstructor, args);
				towerGroup.add(tower);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			creditBalance -= towerCost;
		}

		// Method perform = this.getClass().getMethod("build" +
		// currentTowerType);
		// perform.invoke(this);
	}

	public static Object createTower(Constructor constructor, Object[] arguments) {

		Object object = null;

		try {
			object = constructor.newInstance(arguments);
			return object;
		} catch (InstantiationException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return object;
	}

	public void buildSniperTower() {
		Tower tower = new SniperTower(getX(), getY(), myGame);
		towerGroup.add(tower);
	}

	public void buildFastTower() {
		Tower tower = new FastTower(getX(), getY(), myGame);
		towerGroup.add(tower);
	}

	public void setCredits(double credits) {
		creditBalance = credits;
	}

	public void addCredits(double credits) {
		creditBalance += credits;
	}

	public void update(long elaspedTime) {
		creditBalance += creditsPerMillisecond * elaspedTime;
	}

}
