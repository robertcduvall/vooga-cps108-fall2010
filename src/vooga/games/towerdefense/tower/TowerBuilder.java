package vooga.games.towerdefense.tower;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.event.IEvent;
import vooga.games.towerdefense.Tower;
import vooga.games.towerdefense.TowerDefense;
import vooga.games.towerdefense.events.*;

public class TowerBuilder implements BuildTowerListener{
	
	private TowerDefense game;
	public final int TOWER_EDGE = 16;

	public void setGame(TowerDefense game){
		this.game = game;
	}
	
	@Override
	public void actionPerformed(IEvent event) {
		BuildTowerEvent buildEvent = (BuildTowerEvent) event;
		Class towerDefinition = null;
		try {
			towerDefinition = Class.forName("vooga.games.towerdefense."
					+ buildEvent.getTowerType());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		SpriteGroup towers = game.getTowerGroup();
		Class[] argsClass = new Class[] { double.class, double.class,
				TowerDefense.class };
		Object[] args = new Object[] { buildEvent.getX()-TOWER_EDGE/2, buildEvent.getY()-TOWER_EDGE/2,game };
		Constructor argsConstructor = null;
		try {
			argsConstructor = towerDefinition
					.getConstructor(argsClass);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Tower tower = (Tower) createTower(argsConstructor, args);
		towers.add(tower);
		
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

}
