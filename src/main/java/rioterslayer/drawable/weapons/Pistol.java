package rioterslayer.drawable.weapons;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Pistol extends Weapon
{

	public Pistol() throws SlickException
	{
		this.damage = 1;
		this.ammoSize = 6;
		this.currentAmmo = ammoSize;
		this.weaponSprite = new Image("sprites/weapons/pistol/pistolet.png");
		this.reloadSound = new Sound("sounds/pistolReload.ogg");
		chargerBulletSprites();
	}

	private void chargerBulletSprites() throws SlickException
	{
		this.bulletSprites = new Image[ammoSize + 1];
		this.bulletSprites[0] = new Image("sprites/weapons/pistol/0bullet_pistol.png");
		this.bulletSprites[1] = new Image("sprites/weapons/pistol/1bullet_pistol.png");
		this.bulletSprites[2] = new Image("sprites/weapons/pistol/2bullet_pistol.png");
		this.bulletSprites[3] = new Image("sprites/weapons/pistol/3bullet_pistol.png");
		this.bulletSprites[4] = new Image("sprites/weapons/pistol/4bullet_pistol.png");
		this.bulletSprites[5] = new Image("sprites/weapons/pistol/5bullet_pistol.png");
		this.bulletSprites[6] = new Image("sprites/weapons/pistol/6bullet_pistol.png");

	}

}
