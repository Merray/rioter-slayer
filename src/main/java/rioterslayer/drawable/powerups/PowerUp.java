package rioterslayer.drawable.powerups;

import org.newdawn.slick.Image;

public abstract class PowerUp
{
	protected int x, y;

	protected Image powerUpImage;

	protected boolean isColisioned;

	public PowerUp(int x, int y)
	{
		super();
		this.x = x;
		this.y = y;
		this.isColisioned = false;
	}

	public void draw()
	{
		powerUpImage.draw(x, y);
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

	public Image getPowerUpImage()
	{
		return powerUpImage;
	}

	public void setPowerUpImage(Image powerUpImage)
	{
		this.powerUpImage = powerUpImage;
	}

	public boolean isColisioned()
	{
		return isColisioned;
	}

	public void setColisioned(boolean isColisioned)
	{
		this.isColisioned = isColisioned;
	}

}
