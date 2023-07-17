package rioterslayer.drawable.powerups;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

import rioterslayer.drawable.elements.Player;

public class SpeedUp extends PowerUp
{

	public SpeedUp(int x, int y) throws SlickException
	{
		super(x, y);

		this.powerUpAnimation = new Animation(new SpriteSheet("sprites/powerups/speedUp_spritesheet.png", 64, 64), 100);
		this.powerUpSound = new Sound("sounds/speed_up.ogg");
		this.bonusTime = 8000;
	}

	public void appliquerEffet(Player player)
	{
		player.setBuffed(true);
		player.setSpeed(2);
		player.setBonusTime(bonusTime);
	}

}
