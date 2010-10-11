package vooga.games.jumper;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.state.GameState;

public class JumperGameState extends GameState {
	private int gameStateType;
	private final int jetpackGameState = 2;
	private final int normalGameState = 1;

	

	public JumperGameState(SpriteGroup sg, int stateType){
		super(sg);
		gameStateType = stateType;	
	}
	
	@Override
	public void initialize() {
        switch (gameStateType) {
            case 1: { //normal gamestate
            }
            case 2:	{ //jetpack gamestate
            	Jumper.setJetpackOn(true);
            }
            /*default: {
            	
            }*/
        }

	}
	
	

}
