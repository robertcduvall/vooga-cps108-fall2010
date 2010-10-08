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
	private NonSetGameStateManager stateManager;
	public final int TOWER_EDGE = 16;
	public final double PLAY_AREA_WIDTH = 745;
	private boolean build;

	public PlayerCursor(String name, String stateName, Sprite s,
			SpriteGroup towerGroup, TowerDefense game, Stat<Integer> balance,NonSetGameStateManager states) {
		super(name, stateName, s);
		this.towerGroup = towerGroup;
		myGame = game;
		creditBalance = balance;
		changeTowerType("NormalTower");
		stateManager = states;
		build = false;
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

	public void onClick() {
		if(myGame.play.isActive()){
			if(build){
				buildTower();
			}else{
				build = true;
			}
			switchTower();
		}else if(myGame.startMenu.isActive()){
			menuAction();
		}
	}
	
	private void menuAction(){
		if(checkButtons(107,314,241,385)){
			stateManager.switchTo(myGame.play);
		}
		else if(checkButtons(400,311,577,391)){
			stateManager.switchTo(myGame.play);
		}
		else if(checkButtons(724,304,881,398)){
			stateManager.switchTo(myGame.play);
		}
		
	}

	
	private boolean checkButtons(int x1, int y1, int x2, int y2){
		int myX = myGame.getMouseX();
		int myY = myGame.getMouseY();
		
		if(myX >= x1 && myX <= x2){
			if(myY >= y1 && myY <= y2){
				return true;
			}
		}
		return false;
	}
	
	private void switchTower(){
		int mouseX = myGame.getMouseX();
		int mouseY = myGame.getMouseY();
		
		if(mouseY>215 && mouseY<500){
			if(mouseX > 760 && mouseX<1000){
				if(mouseY<300){
					changeTowerType("NormalTower");
				}
				if(mouseY>315 && mouseY<400){
					changeTowerType("FastTower");
				}
				if(mouseY>415){
					changeTowerType("SniperTower");
				}
				
			}
		}
	}
	
	private void buildTower(){
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
		if(inPlayArea() && myGame.play.isActive()){
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
