package rioterslayer.drawable.elements;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Bullet
{
	private int bulletX, bulletY;
	private Image bulletImage;
	private boolean isCollisioned;

	public Bullet(int bulletX, int bulletY) throws SlickException
	{
		super();
		this.bulletX = bulletX;
		this.bulletY = bulletY;
		this.isCollisioned = false;
		this.bulletImage = new Image("sprites/bullet.png");
	}

	public boolean collision(Enemy e)
	{

		if ((bulletX >= e.getEnemyX() && bulletX <= e.getEnemyX() + 60)
				&& (bulletY >= e.getEnemyY() && bulletY <= e.getEnemyY() + 70))
		{
			return true;
		} else
		{
			return false;
		}

	}

	public void draw()
	{
		bulletImage.draw(this.bulletX, this.bulletY);
	}

	public int getBulletX()
	{
		return bulletX;
	}

	public void setBulletX(int bulletX)
	{
		this.bulletX = bulletX;
	}

	public int getBulletY()
	{
		return bulletY;
	}

	public void setBulletY(int bulletY)
	{
		this.bulletY = bulletY;
	}

	public boolean isCollisioned()
	{
		return isCollisioned;
	}

	public void setCollisioned(boolean isCollisioned)
	{
		this.isCollisioned = isCollisioned;
	}

}
