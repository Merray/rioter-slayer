package rioterslayer.drawable.powerups;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.SlickException;

public class PowerUps
{
	private List<PowerUp> displayedPowerUps;

	public int powerUpDelay;

	Random random = new Random();

	public PowerUps() throws SlickException
	{
		super();
		this.displayedPowerUps = new ArrayList<>();
		this.powerUpDelay = 0;

	}

	public void ajouterPowerUp() throws SlickException
	{

		int resultat = random.nextInt(100);

		if (resultat > 0 && resultat <= 49)
		{

			displayedPowerUps.add(new HealthUp(200 + random.nextInt(900), 0));
		} else
		{
			displayedPowerUps.add(new SpeedUp(200 + random.nextInt(900), 0));
		}

	}

	public List<PowerUp> getDisplayedPowerUps()
	{
		return displayedPowerUps;
	}

	public void setDisplayedPowerUps(List<PowerUp> displayedPowerUps)
	{
		this.displayedPowerUps = displayedPowerUps;
	}

}
