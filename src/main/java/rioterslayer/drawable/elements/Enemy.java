package rioterslayer.drawable.elements;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Enemy
{
	private int enemyX, enemyY;

	private Image enemyImage;
	private boolean isCollisioned;

	public Enemy(int enemyX, int enemyY) throws SlickException
	{
		super();
		this.enemyX = enemyX;
		this.enemyY = enemyY;
		this.isCollisioned = false;
		this.enemyImage = new Image("sprites/orc.png");
	}

	public void draw()
	{
		enemyImage.draw(this.enemyX, this.enemyY);
	}

	public int getEnemyX()
	{
		return enemyX;
	}

	public void setEnemyX(int enemyX)
	{
		this.enemyX = enemyX;
	}

	public int getEnemyY()
	{
		return enemyY;
	}

	public void setEnemyY(int enemyY)
	{
		this.enemyY = enemyY;
	}

	public Image getEnemyImage()
	{
		return enemyImage;
	}

	public void setEnemyImage(Image enemyImage)
	{
		this.enemyImage = enemyImage;
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
