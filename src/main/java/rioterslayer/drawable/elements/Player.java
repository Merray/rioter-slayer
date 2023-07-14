package rioterslayer.drawable.elements;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;

import rioterslayer.drawable.weapons.Pistol;
import rioterslayer.drawable.weapons.Weapon;
import rioterslayer.utils.FontUtils;

public class Player
{
	private int playerX, playerY;

	private int playerMaxHealth, playerCurrentHealth;

	private Image playerImage;

	private Image playerPortraitFine;
	private Image playerPortraitNotFine;
	private Image playerPortraitHurt;
	private Animation playerHealthAnimation;

	private Weapon playerWeapon;

	private boolean isHurt = false;

	private TrueTypeFont font = FontUtils.getFont("fonts/arcadePix.TTF", 24f);

	public Player(int playerX, int playerY) throws SlickException
	{
		super();
		this.playerWeapon = new Pistol();
		this.playerX = playerX;
		this.playerY = playerY;
		this.playerMaxHealth = 3;
		this.playerCurrentHealth = this.playerMaxHealth;
		this.playerImage = new Image("sprites/player.png").getScaledCopy(.3f);
		this.playerPortraitFine = new Image("sprites/playerPortraitFine.png");
		this.playerPortraitNotFine = new Image("sprites/playerPortraitNotFine.png");
		this.playerPortraitHurt = new Image("sprites/playerPortraitHurt.png");

		this.playerHealthAnimation = new Animation(new SpriteSheet("sprites/coeur_spritesheet.png", 216, 216), 150);

	}

	public void draw(Graphics g)
	{
		playerImage.draw(playerX, playerY);
		if (this.isHurt)
		{
			playerPortraitHurt.draw(10, 15);
		} else if (this.playerCurrentHealth < this.playerMaxHealth)
		{
			playerPortraitNotFine.draw(10, 15);
		} else
		{
			playerPortraitFine.draw(10, 15);
		}
		playerHealthAnimation.draw(-10, 130);

		playerWeapon.draw();

		g.setFont(font);
		g.setColor(Color.red);
		g.drawString(playerCurrentHealth + "/" + playerMaxHealth, 70, 270);
		g.resetFont();
	}

	public int getPlayerX()
	{
		return playerX;
	}

	public void setPlayerX(int playerX)
	{
		this.playerX = playerX;
	}

	public int getPlayerY()
	{
		return playerY;
	}

	public void setPlayerY(int playerY)
	{
		this.playerY = playerY;
	}

	public Image getPlayerImage()
	{
		return playerImage;
	}

	public void setPlayerImage(Image playerImage)
	{
		this.playerImage = playerImage;
	}

	public int getPlayerMaxHealth()
	{
		return playerMaxHealth;
	}

	public void setPlayerMaxHealth(int playerMaxHealth)
	{
		this.playerMaxHealth = playerMaxHealth;
	}

	public int getPlayerCurrentHealth()
	{
		return playerCurrentHealth;
	}

	public void setPlayerCurrentHealth(int playerCurrentHealth)
	{
		this.playerCurrentHealth = playerCurrentHealth;
	}

	public Animation getPlayerHealthAnimation()
	{
		return playerHealthAnimation;
	}

	public void setPlayerHealthAnimation(Animation playerHealthAnimation)
	{
		this.playerHealthAnimation = playerHealthAnimation;
	}

	public Image getPlayerPortraitFine()
	{
		return playerPortraitFine;
	}

	public void setPlayerPortraitFine(Image playerPortraitFine)
	{
		this.playerPortraitFine = playerPortraitFine;
	}

	public Image getPlayerPortraitNotFine()
	{
		return playerPortraitNotFine;
	}

	public void setPlayerPortraitNotFine(Image playerPortraitNotFine)
	{
		this.playerPortraitNotFine = playerPortraitNotFine;
	}

	public Image getPlayerPortraitRed()
	{
		return playerPortraitHurt;
	}

	public void setPlayerPortraitRed(Image playerPortraitRed)
	{
		this.playerPortraitHurt = playerPortraitRed;
	}

	public boolean isHurt()
	{
		return isHurt;
	}

	public void setHurt(boolean isHurt)
	{
		this.isHurt = isHurt;
	}

	public Image getPlayerPortraitHurt()
	{
		return playerPortraitHurt;
	}

	public void setPlayerPortraitHurt(Image playerPortraitHurt)
	{
		this.playerPortraitHurt = playerPortraitHurt;
	}

	public Weapon getPlayerWeapon()
	{
		return playerWeapon;
	}

	public void setPlayerWeapon(Weapon playerWeapon)
	{
		this.playerWeapon = playerWeapon;
	}

}
