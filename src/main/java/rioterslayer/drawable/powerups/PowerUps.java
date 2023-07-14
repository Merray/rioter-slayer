package rioterslayer.drawable.powerups;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.SlickException;

public class PowerUps
{
	private List<PowerUp> powerUps;

	private List<PowerUp> displayedPowerUps;

	public int powerUpDelay;

	Random random = new Random();

	public PowerUps() throws SlickException
	{
		super();
		this.powerUps = new ArrayList<>();
		this.powerUps.add(new HealthUp(200 + random.nextInt(900), 0));
		this.powerUps.add(new HealthUp(200 + random.nextInt(900), 0));
		this.powerUps.add(new HealthUp(200 + random.nextInt(900), 0));
		this.powerUps.add(new HealthUp(200 + random.nextInt(900), 0));
		this.powerUps.add(new HealthUp(200 + random.nextInt(900), 0));
		this.powerUps.add(new HealthUp(200 + random.nextInt(900), 0));
		this.powerUps.add(new HealthUp(200 + random.nextInt(900), 0));
		this.powerUps.add(new HealthUp(200 + random.nextInt(900), 0));
		this.powerUps.add(new HealthUp(200 + random.nextInt(900), 0));
		this.powerUps.add(new HealthUp(200 + random.nextInt(900), 0));
		this.displayedPowerUps = new ArrayList<>();
		this.powerUpDelay = 0;

	}

	public void ajouterPowerUp()
	{

		displayedPowerUps.add(powerUps.get(random.nextInt(0, 10)));

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
