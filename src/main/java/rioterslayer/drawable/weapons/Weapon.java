package rioterslayer.drawable.weapons;

import org.newdawn.slick.Image;
import org.newdawn.slick.Sound;

public abstract class Weapon
{
	protected int damage, ammoSize, currentAmmo;

	protected Image weaponSprite;

	protected Image[] bulletSprites;

	protected Sound reloadSound;

	public void draw()
	{
		weaponSprite.draw(10, 300);
		bulletSprites[currentAmmo].draw(10, 500);
	}

	public void reload()
	{

	}

	public int getDamage()
	{
		return damage;
	}

	public void setDamage(int damage)
	{
		this.damage = damage;
	}

	public int getAmmoSize()
	{
		return ammoSize;
	}

	public void setAmmoSize(int ammoSize)
	{
		this.ammoSize = ammoSize;
	}

	public int getCurrentAmmo()
	{
		return currentAmmo;
	}

	public void setCurrentAmmo(int currentAmmo)
	{
		this.currentAmmo = currentAmmo;
	}

	public Image getWeaponSprite()
	{
		return weaponSprite;
	}

	public void setWeaponSprite(Image weaponSprite)
	{
		this.weaponSprite = weaponSprite;
	}

	public Sound getReloadSound()
	{
		return reloadSound;
	}

	public void setReloadSound(Sound reloadSound)
	{
		this.reloadSound = reloadSound;
	}

}
