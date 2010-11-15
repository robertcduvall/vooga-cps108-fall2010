package vooga.widget;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.util.ImageUtil;

import vooga.engine.resource.Resources;
import vooga.engine.state.Button;

public class LevelSelector {

	public LevelSelector (String path){
		
		Sprite menuSprite = new Sprite(ImageUtil.resize(Resources
				.getImage(path), WIDTH, HEIGHT));
		menuGroup = startMenu.addAndReturnGroup(new SpriteGroup("Menu Group"));
		menuGroup.add(menuSprite);
	}
	
	@Override
	public void initialize() {
		
	}
	
	public void addButton(Button button){

		myUpdateField.add(button);
		myRenderField.add(button);
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
}
