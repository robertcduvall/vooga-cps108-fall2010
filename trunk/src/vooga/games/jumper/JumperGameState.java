package vooga.games.jumper;

import com.golden.gamedev.object.SpriteGroup;

import vooga.engine.state.GameState;

public class JumperGameState extends GameState {
	private int gameStateType;

	

	public JumperGameState(SpriteGroup sg, int stateType){
		super(sg);
		gameStateType = stateType;	
		System.out.println("cons.. " +gameStateType);
		initialize(); //why do I have to include this? --devon
	}
	
	@Override
	public void initialize() {
        switch (gameStateType) {
            case 1: { //normal gamestate
            	System.out.println("normalGS");
            	break;
            }
            case 2:	{ //jetpack gamestate
            	Jumper.setJetpackOn(true);
            	System.out.println("SET JETPACK ON --- TRUE");
            	break;
            }
        }

		

	}
	
	

}
