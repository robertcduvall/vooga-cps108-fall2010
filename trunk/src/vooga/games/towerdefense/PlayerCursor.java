package vooga.games.towerdefense;

import java.awt.Graphics2D;

import vooga.engine.core.Sprite;
import vooga.engine.overlay.Stat;
import vooga.engine.state.GameStateManager;
import vooga.games.towerdefense.events.BuildTowerEvent;
import vooga.games.towerdefense.tower.Fast;
import vooga.games.towerdefense.tower.Normal;
import vooga.games.towerdefense.tower.Sniper;
import vooga.games.towerdefense.tower.Tower;


/**
 * Since the player does not have a physical representation, the cursor 
 * is effectively the player in the game. This cursor has the capability 
 * to build Towers and update itself according to what kind of Tower 
 * is going to built next using Reflection
 * @author Derek Zhou, Daniel Koverman, Justin Goldsmith
 *
 */
public class PlayerCursor extends Sprite {

	private static final long serialVersionUID = -8174656868252384431L;
	private DropThis myGame;
	private Stat<Integer> creditBalance;
	private GameStateManager stateManager;
	public final int TOWER_EDGE = 16;
	public final double PLAY_AREA_WIDTH = 745;
	private Tower currentTower;
	
	public PlayerCursor(String a)

	public PlayerCursor(String name, String stateName, Sprite s, Stat<Integer> balance,GameStateManager states) {
		super(name, stateName, s);
		myGame = game;
		creditBalance = balance;
		changeTowerType(new Normal(0,0));
		stateManager = states;
	}
	
	public void changeTowerType(Tower newTower){
		currentTower = newTower;
		setNewImage(currentTower.getPreviewImage());
	}
	public void onClick() {
		if(myGame.play.isActive()){
			buildTower();
			switchTower();
		}else if(myGame.startMenu.isActive()){
			menuAction();
		}else if(myGame.gameOver.isActive()){
			gameOverActions();
		}
	}
	
	private void gameOverActions() {
		if(checkButtons(129,370,283,432)){
			stateManager.switchTo(myGame.startMenu);
		}
		else if(checkButtons(585,367,707,431)){
			myGame.finish();
		}
	}

	private void menuAction(){
		if(checkButtons(107,314,241,385)){
			myGame.setDifficulty(0);
			myGame.begin();
		}
		else if(checkButtons(400,311,577,391)){
			myGame.setDifficulty(1);
			myGame.begin();
		}
		else if(checkButtons(724,304,881,398)){
			myGame.setDifficulty(2);
			myGame.begin();
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
					changeTowerType(new Normal(0,0));
				}
				if(mouseY>315 && mouseY<400){
					changeTowerType(new Fast(0,0));
				}
				if(mouseY>415){
					changeTowerType(new Sniper(0,0));
				}
				
			}
		}
	}
	
	private void buildTower(){
		
		if (creditBalance.getStat() >= currentTower.getCost() && offPath() && inPlayArea()) {
			SingletonEventManager.fireEvent("BuildTowerEvent", new BuildTowerEvent(this, "BuildTowerEvent", currentTower,  getX(), getY()));
			creditBalance.setStat(creditBalance.getStat() - currentTower.getCost());
			changeTowerType(currentTower.clone());
			
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

	
	public void render(Graphics2D g) {
		if(inPlayArea() && myGame.play.isActive()){
			getCurrentSprite().forceX(getX()-getCurrentSprite().getWidth()/2+7);
			getCurrentSprite().forceY(getY()-getCurrentSprite().getHeight()/2+7);
			getCurrentSprite().render(g);
		}
	}
	
	private boolean inPlayArea(){
		return getX()<PLAY_AREA_WIDTH;
	}
	

}
