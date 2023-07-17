package rioterslayer.mainapp.states;

import java.util.ArrayList;
import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import rioterslayer.drawable.elements.Bullet;
import rioterslayer.drawable.elements.Enemy;
import rioterslayer.drawable.elements.Player;
import rioterslayer.drawable.powerups.PowerUp;
import rioterslayer.drawable.powerups.PowerUps;
import rioterslayer.mainapp.MainApp;
import rioterslayer.utils.FontUtils;

public class MainGameState extends BasicGameState
{

	public static final int ID = 1;
	private StateBasedGame game;
	private GameContainer container;
	private boolean enemiesSpawn = true;
	Random random;

	// Font
	TrueTypeFont font;

	// Sons / Musiques
	private Music gameMusic;
	private Sound fireSound;
	private Sound speedDownSound;
	private Sound playerHurtSound;
	private Sound enemyDeathSound1;
	private Sound enemyDeathSound2;
	private Sound enemyDeathSound3;
	private ArrayList<Sound> deathSounds;

	// Images
	private Image backgroundImage;
	private Image leftHud;

	// Conteneurs
	private Player player;
	ArrayList<Bullet> bullets;
	private int nbBullets;

	ArrayList<Enemy> enemies;
	private int nbEnemies;

	PowerUps powerUps;
	private int nbPowerUps;

	int timePassed = 0;
	int hurtTime = 0;
	int healedTime = 0;
	int reloadTime = 0;
	boolean reloading = false;
	boolean collision = false;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		this.container = container;
		this.game = game;
		font = FontUtils.getFont("fonts/arcadePix.TTF", 22f);
		gameMusic = new Music("musics/game_music.ogg");
		backgroundImage = new Image("backgrounds/road.png");
		leftHud = new Image("backgrounds/left_hud.png");
		player = new Player(650, 700);
		bullets = new ArrayList<>();
		enemies = new ArrayList<>();
		powerUps = new PowerUps();
		deathSounds = new ArrayList<>();
		random = new Random();
		fireSound = new Sound("sounds/pew_pew.ogg");
		enemyDeathSound1 = new Sound("sounds/death_sound1.ogg");
		enemyDeathSound2 = new Sound("sounds/death_sound2.ogg");
		enemyDeathSound3 = new Sound("sounds/death_sound3.ogg");
		playerHurtSound = new Sound("sounds/playerHurtSound.ogg");
		speedDownSound = new Sound("sounds/speed_down.ogg");
		deathSounds.add(enemyDeathSound1);
		deathSounds.add(enemyDeathSound2);
		deathSounds.add(enemyDeathSound3);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		timePassed += delta;
		powerUps.powerUpDelay += delta;

		updatePlayerStats(delta);
		updateDeplacementJoueur(container);
		updateDeplacementBullets(delta);
		updateDeplacementEnemies(delta);
		updateDeplacementPowerUps(delta);
		updateCollision();
		updateListeEnemies();
		updateListeBullets();
		updateListePowerUps();

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		dessinerBackground();
		dessinerHUD(g);
		dessinerJoueur(g);
		dessinerBullets();
		dessinerEnemies();
		dessinerPowerUps();

	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException
	{
		gameMusic.loop(1f, MainMenuState.volume);

		bullets.clear();
		enemies.clear();
		powerUps.getDisplayedPowerUps().clear();
		nbBullets = 0;
		nbEnemies = 0;
		nbPowerUps = 0;
		powerUps.powerUpDelay = 0;
		player = new Player(650, 700);

	}

	@Override
	public void leave(GameContainer container, StateBasedGame game) throws SlickException
	{
		gameMusic.pause();
	}

	private void dessinerBackground()
	{
		this.backgroundImage.draw(200, 0, this.container.getWidth() - 200, this.container.getHeight());
	}

	@Override
	public void keyPressed(int key, char c)
	{
		switch (key)
		{
		case Input.KEY_SPACE:
			try
			{
				if (player.getPlayerWeapon().getCurrentAmmo() > 0)
				{

					fireSound.play();
					bullets.add(new Bullet(player.getPlayerX() + 20, 680));
					player.getPlayerWeapon().setCurrentAmmo(player.getPlayerWeapon().getCurrentAmmo() - 1);
					nbBullets++;
				}

			} catch (SlickException e)
			{
				e.printStackTrace();
			}
			break;

		default:
			break;
		}
	}

	private void dessinerPowerUps()
	{
		for (PowerUp p : powerUps.getDisplayedPowerUps())
		{
			p.draw();
		}
	}

	private void updateDeplacementPowerUps(int delta) throws SlickException
	{
		if (powerUps.powerUpDelay > 10000)
		{
			powerUps.powerUpDelay = 0;
			powerUps.ajouterPowerUp();
			nbPowerUps++;
		}

		for (PowerUp p : powerUps.getDisplayedPowerUps())
		{
			p.setY(p.getY() + delta / 5);
		}
	}

	private void updatePlayerStats(int delta)
	{

		// Buff
		if (player.isBuffed())
		{
			player.bonusTime -= delta;

			if (player.bonusTime < 0)
			{
				player.bonusTime = 0;
				player.setBuffed(false);

				if (player.getSpeed() != 1)
				{
					player.setSpeed(1);
					speedDownSound.play();
				}
			}
		}

		// Reload
		if (player.getPlayerWeapon().getCurrentAmmo() == 0)
		{
			reloadTime += delta;

			if (!reloading)
			{
				player.getPlayerWeapon().getReloadSound().play();
				reloading = true;
			}

			if (reloadTime > 1500)
			{
				reloadTime = 0;
				player.getPlayerWeapon().setCurrentAmmo(player.getPlayerWeapon().getAmmoSize());
				reloading = false;
			}
		}

		// Hurt
		if (player.isHurt())
		{
			hurtTime += delta;

			if (hurtTime > 1000)
			{
				hurtTime = 0;
				player.setHurt(false);
			}
		}

		// Heal
		if (player.isHealed())
		{
			healedTime += delta;

			if (healedTime > 1000)
			{
				healedTime = 0;
				player.setHealed(false);
			}
		}

		// Game over
		if (player.getPlayerCurrentHealth() <= 0)
		{
			this.game.enterState(GameOverState.ID, new FadeOutTransition(), new FadeInTransition());
		}

	}

	private void dessinerEnemies()
	{
		for (Enemy e : enemies)
		{
			e.draw();
		}
	}

	private void dessinerBullets()
	{
		for (Bullet b : bullets)
		{
			b.draw();
		}
	}

	private void dessinerJoueur(Graphics g)
	{
		player.draw(g);
	}

	private void dessinerHUD(Graphics g)
	{

		this.leftHud.draw(0, 0, 200, this.container.getHeight());

		if (MainApp.DEBUG)
		{
			g.setColor(Color.red);
			g.drawString("DEBUG MODE", 20, 660);
			g.setColor(Color.black);
			g.drawString("Bullets : " + nbBullets, 20, 680);
			g.drawString("Enemies : " + nbEnemies, 20, 700);
			g.drawString("Power ups : " + nbPowerUps, 20, 720);
			g.drawString("Collision : " + collision, 20, 740);
		}
	}

	private void updateDeplacementJoueur(GameContainer container)
	{
		// Deplacement
		Input input = container.getInput();
		if (input.isKeyDown(Input.KEY_LEFT))
		{
			player.setPlayerX(player.getPlayerX() - 5 * player.getSpeed());
		} else if (input.isKeyDown(Input.KEY_RIGHT))
		{
			player.setPlayerX(player.getPlayerX() + 5 * player.getSpeed());
		}

		// Pour pas sortir de l'écran
		if (player.getPlayerX() < 200)
		{
			player.setPlayerX(200);
		} else if (player.getPlayerX() > 1100)
		{
			player.setPlayerX(1100);
		}
	}

	private void updateDeplacementEnemies(int delta) throws SlickException
	{
		if (enemiesSpawn && timePassed > 1200)
		{
			timePassed = 0;

			enemies.add(new Enemy(200 + random.nextInt(900), 0));
			nbEnemies++;
		}

		for (Enemy e : enemies)
		{
			e.setEnemyY(e.getEnemyY() + delta / 10);
		}
	}

	private void updateDeplacementBullets(int delta)
	{
		for (Bullet b : bullets)
		{
			b.setBulletY(b.getBulletY() - delta / 3);

		}
	}

	private void updateCollision()
	{
		for (Bullet b : bullets)
		{
			for (Enemy e : enemies)
			{

				if (b.collision(e))
				{
					collision = true;
					b.setCollisioned(true);
					e.setCollisioned(true);

					deathSounds.get(random.nextInt(3)).play();
				}

			}
		}

		for (PowerUp p : powerUps.getDisplayedPowerUps())
		{
			if (p.collision(player))
			{
				p.setColisioned(true);
			}
		}
	}

	private void updateListeBullets()
	{
		for (int i = bullets.size() - 1; i >= 0; i--)
		{
			if (bullets.get(i).getBulletY() < 0 || bullets.get(i).isCollisioned())
			{
				bullets.remove(i);
				nbBullets--;
				collision = false;
			}
		}
	}

	private void updateListeEnemies()
	{
		for (int i = enemies.size() - 1; i >= 0; i--)
		{

			if (enemies.get(i).getEnemyY() > 750 || enemies.get(i).isCollisioned())
			{
				if (enemies.get(i).getEnemyY() > 750)
				{
					playerHurtSound.play();
					player.setPlayerCurrentHealth(player.getPlayerCurrentHealth() - 1);

					player.setHurt(true);

				}

				enemies.remove(i);
				nbEnemies--;
				collision = false;
			}

		}
	}

	private void updateListePowerUps()
	{
		for (int i = powerUps.getDisplayedPowerUps().size() - 1; i >= 0; i--)
		{

			if (powerUps.getDisplayedPowerUps().get(i).getY() > 750
					|| powerUps.getDisplayedPowerUps().get(i).isColisioned())
			{

				if (powerUps.getDisplayedPowerUps().get(i).isColisioned())
				{
					powerUps.getDisplayedPowerUps().get(i).setColisioned(false);
					powerUps.getDisplayedPowerUps().get(i).getPowerUpSound().play(1f, .3f);
					powerUps.getDisplayedPowerUps().get(i).appliquerEffet(player);
				}
				powerUps.getDisplayedPowerUps().remove(i);
				nbPowerUps--;
			}

		}
	}

	@Override
	public int getID()
	{
		return ID;
	}

}
