package vooga.games.towerdefense;

import java.awt.Graphics2D;
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

import vooga.engine.overlay.Stat;
import vooga.engine.player.control.PlayerSprite;
import vooga.engine.resource.Resources;

public class PlayerCursor extends PlayerSprite {

	private static final long serialVersionUID = -8174656868252384431L;
	private SpriteGroup towerGroup;
	private TowerDefense myGame;
	private Stat<Integer> creditBalance;
	private int towerCost;
	private String towerType;
	private Class towerDefinition;
	public final int TOWER_EDGE = 16;
	public final double PLAY_AREA_WIDTH = 745;

	public PlayerCursor(String name, String stateName, Sprite s,
			SpriteGroup towerGroup, TowerDefense game, Stat<Integer> balance) {
		super(name, stateName, s);
		this.towerGroup = towerGroup;
		myGame = game;
		creditBalance = balance;
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
			towerCost = field.getInt(null);

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
		System.out.println('b');
		System.out.println(creditBalance.getStat() + " : " + towerCost);
		if (creditBalance.getStat() >= towerCost && offPath() && inPlayArea()) {
			try {
				Class[] argsClass = new Class[] { double.class, double.class,
						TowerDefense.class };
				Object[] args = new Object[] { getX()-TOWER_EDGE/2, getY()-TOWER_EDGE/2, myGame };
				Constructor argsConstructor = towerDefinition
						.getConstructor(argsClass);
				Tower tower = (Tower) createTower(argsConstructor, args);
				towerGroup.add(tower);
				creditBalance.setStat(creditBalance.getStat() - towerCost);
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Method perform = this.getClass().getMethod("build" +
		// currentTowerType);
		// perform.invoke(this);
	}
	
	public boolean offPath(){
		for(PathPoint point : myGame.getPathPoints())
		{
			if(getDistance(point.getX(), point.getY(), getX()+TOWER_EDGE/2, getY()+TOWER_EDGE/2)<2*TOWER_EDGE){
				return false;
			}
		}
		return true;
	}
	
	private double getDistance(double x1, double y1, double x2, double y2){
		return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
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
	
	public void render(Graphics2D g) {
		if(inPlayArea()){
			getCurrentSprite().forceX(getX()-getCurrentSprite().getWidth()/2+7);
			getCurrentSprite().forceY(getY()-getCurrentSprite().getHeight()/2+7);
			getCurrentSprite().render(g);
		}
	}
	
	public void buildNormalTower() {
		Tower tower = new NormalTower(getX(), getY(), myGame);
		towerGroup.add(tower);
	}
	
	private boolean inPlayArea(){
		return getX()<PLAY_AREA_WIDTH;
	}

}
