package rioterslayer.drawable.powerups;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class HealthUp extends PowerUp
{

	public HealthUp(int x, int y) throws SlickException
	{
		super(x, y);
		this.powerUpImage = new Image("sprites/placeholder64_64.png");
	}

}
