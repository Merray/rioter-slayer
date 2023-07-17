package rioterslayer.drawable.powerups;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;

import rioterslayer.drawable.elements.Player;

public class HealthUp extends PowerUp
{

	public HealthUp(int x, int y) throws SlickException
	{
		super(x, y);
		this.powerUpAnimation = new Animation(new SpriteSheet("sprites/powerups/healthUp_spritesheet.png", 64, 64),
				100);
		this.powerUpSound = new Sound("sounds/health_up.ogg");
		this.bonusTime = 0;
	}

	public void appliquerEffet(Player player)
	{
		if (player.getPlayerCurrentHealth() < player.getPlayerMaxHealth())
		{
			player.setPlayerCurrentHealth(player.getPlayerCurrentHealth() + 1);
			player.setHealed(true);
		}
	}

}
