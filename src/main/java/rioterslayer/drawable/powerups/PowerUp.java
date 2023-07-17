package rioterslayer.drawable.powerups;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Sound;

import rioterslayer.drawable.elements.Player;

public abstract class PowerUp
{
	protected int x, y;

	protected Animation powerUpAnimation;

	protected Sound powerUpSound;

	protected boolean isColisioned;

	protected int bonusTime;

	public PowerUp(int x, int y)
	{
		super();
		this.x = x;
		this.y = y;
		this.isColisioned = false;
	}

	public void appliquerEffet(Player player)
	{

	}

	public boolean collision(Player player)
	{
		if ((x >= player.getPlayerX() - 50 && x <= player.getPlayerX() + 50)
				&& (y >= player.getPlayerY() && y <= player.getPlayerY() + 80))
		{
			return true;
		} else
		{
			return false;
		}
	}

	public void draw()
	{
		powerUpAnimation.draw(x, y);
	}

	public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public boolean isColisioned()
	{
		return isColisioned;
	}

	public void setColisioned(boolean isColisioned)
	{
		this.isColisioned = isColisioned;
	}

	public Sound getPowerUpSound()
	{
		return powerUpSound;
	}

	public void setPowerUpSound(Sound powerUpSound)
	{
		this.powerUpSound = powerUpSound;
	}

	public Animation getPowerUpAnimation()
	{
		return powerUpAnimation;
	}

	public void setPowerUpAnimation(Animation powerUpAnimation)
	{
		this.powerUpAnimation = powerUpAnimation;
	}

	public int getBonusTime()
	{
		return bonusTime;
	}

	public void setBonusTime(int bonusTime)
	{
		this.bonusTime = bonusTime;
	}

}
