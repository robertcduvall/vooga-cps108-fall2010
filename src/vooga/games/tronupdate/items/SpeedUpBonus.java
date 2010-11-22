package vooga.games.tronupdate.items;

import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class SpeedUpBonus extends Bonus {
	private boolean consumed=false;
	
	public SpeedUpBonus(BufferedImage image, double initialColPosition,
			double initialRowPosition, int BounsImageWidth) {
		super(image, initialColPosition, initialRowPosition, BounsImageWidth);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void act() {
		setConsumed(true);

	}
	
	public void setConsumed(boolean a){
		consumed=a;
	}

	@Override
	public boolean isConsumed() {
		return consumed;
	}

}
