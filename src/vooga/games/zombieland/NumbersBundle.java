package vooga.games.zombieland;

import java.util.ListResourceBundle;

public class NumbersBundle extends ListResourceBundle{

	@Override
	protected Object[][] getContents() {
		return contents;
	}
	
	static final Object[][] contents ={
		
		{"delim" , ","},
		{"playerImageGroups" , "Down,Up,Left,Right"},
		
		{"Down" , "Down1,Down2,Down3,Down4"},
		{"Up" , "Up1,Up2,Up3,Up4"},
		{"Left" , "Left1,Left2,Left3,Left4"},
		{"Right" , "Right1,Right2,Right3,Right4"},

		{"zombieImageGroups" , "ZombieDown,ZombieUp,ZombieLeft,ZombieRight,ZombieAttackUp,ZombieAttackDown,ZombieAttackLeft,ZombieAttackRight"},
		{"ZombieDown" , "ZombieDown1,ZombieDown2,ZombieDown3"},
		{"ZombieUp " , "ZombieUp1,ZombieUp2,ZombieUp3"},
		{"ZombieLeft" , "ZombieLeft1,ZombieLeft2,ZombieLeft3"},
		{"ZombieRight" , "ZombieRight1,ZombieRight2,ZombieRight3"},

		{"ZombieAttackUp" , "ZombieAttackUp1,ZombieAttackUp2,ZombieAttackUp3"},
		{"ZombieAttackDown" , "ZombieAttackDown1,ZombieAttackDown2,ZombieAttackDown3"},
		{"ZombieAttackLeft" , "ZombieAttackLeft1,ZombieAttackLeft2,ZombieAttackLeft3"},
		{"ZombieAttackRight" , "ZombieAttackRight1,ZombieAttackRight2,ZombieAttackRight3"},
		{"ZombieDeath" , "ZombieDeath1,ZombieDeath2,ZombieDeath3"},


		{"Down1" , "resources/Down1.png"},
		{"Down2" , "resources/Down2.png"},
		{"Down3" , "resources/Down3.png"},
		{"Down4" , "resources/Down4.png"},
		{"Left1" , "resources/left1.png"},
		{"Left2" , "resources/left2.png"},
		{"Left3" , "resources/left3.png"},
		{"Left4" , "resources/left4.png"},
		{"Up1" , "resources/up1.png"},
		{"Up2" , "resources/up2.png"},
		{"Up3" , "resources/up3.png"},
		{"Up4" , "resources/up4.png"},
		{"Right1" , "resources/right1.png"},
		{"Right2" , "resources/right2.png"},
		{"Right3" , "resources/right3.png"},
		{"Right4" , "resources/right4.png"},

		{"ZombieDown1" , "resources/ZombieDown1.png"},
		{"ZombieDown2" , "resources/ZombieDown2.png"},
		{"ZombieDown3" , "resources/ZombieDown3.png"},
				
		{"ZombieUp1" , "resources/ZombieUp1.png"},
		{"ZombieUp2" , "resources/ZombieUp2.png"},
		{"ZombieUp3" , "resources/ZombieUp3.png"},

		{"ZombieLeft1" , "resources/ZombieLeft1.png"},
		{"ZombieLeft2" , "resources/ZombieLeft2.png"},
		{"ZombieLeft3" , "resources/ZombieLeft3.png"},

		{"ZombieRight1" , "resources/ZombieRight1.png"},
		{"ZombieRight2" , "resources/ZombieRight2.png"},
		{"ZombieRight3" , "resources/ZombieRight3.png"},

		{"ZombieAttackUp1" , "resources/ZombieAttackUp1.png"},
		{"ZombieAttackUp2" , "resources/ZombieAttackUp2.png"},
		{"ZombieAttackUp3" , "resources/ZombieAttackUp3.png"},
		
		{"ZombieAttackDown1" , "resources/ZombieAttackDown1.png"},
		{"ZombieAttackDown2" , "resources/ZombieAttackDown2.png"},
		{"ZombieAttackDown3" , "resources/ZombieAttackDown3.png"},

		{"ZombieAttackLeft1" , "resources/ZombieAttackLeft1.png"},
		{"ZombieAttackLeft2" , "resources/ZombieAttackLeft2.png"},
		{"ZombieAttackLeft3" , "resources/ZombieAttackLeft3.png"},

		{"ZombieAttackRight1" , "resources/ZombieAttackRight1.png"},
		{"ZombieAttackRight2" , "resources/ZombieAttackRight2.png"},
		{"ZombieAttackRight3" , "resources/ZombieAttackRight3.png"},

		{"ZombieDeath1" , "resources/ZombieDeath1.png"},
		{"ZombieDeath2" , "resources/ZombieDeath2.png"},
		{"ZombieDeath3" , "resources/ZombieDeath3.png"},


		{"playerImageHeight" , "350"},
		{"playerImageWidth" , "250"},
		{"SCREEN_WIDTH" , "640"},
		{"SCREEN_HEIGHT" , "480"},
		{"overlayHealthStringX" , "5"},
		{"overlayHealthStringY" , "10"},
		{"overlayHealthBarX" , "80"},
		{"overlayHealthBarY" , "18"},
		{"overlayScoreStringX" , "385"},
		{"overlayScoreStringY" , "12"},
		{"overlayAmmoStringX" , "470"},
		{"overlayAmmoStringY" , "12"},
		{"overlayLevelStringX" , "640*1/2-60"},
		{"overlayLevelStringY" , "480*1/2-60"},
		{"timer" , "2000"},
		{"startLevel" , "1"},
		{"startZombieNumber" , "25"},
		{"startZombieDamage" , "5"},
		{"startlevel" , "1"},
		{"startzombiesAppeared" , "0"},
		{"startzombieDamage" , "5"},
		{"maxHealth" , "200"},
		{"timer" , "2000"},
		{"zombieFrameSize" , "3"},
		{"playerFrameSize" , "4"}
	};
}
