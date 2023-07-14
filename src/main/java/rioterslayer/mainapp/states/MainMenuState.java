package rioterslayer.mainapp.states;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import rioterslayer.drawable.mainmenu.MainMenu;
import rioterslayer.drawable.mainmenu.MenuElement;
import rioterslayer.mainapp.MainApp;
import rioterslayer.utils.FontUtils;

public class MainMenuState extends BasicGameState
{

	public static final int ID = 0;
	private StateBasedGame game;
	private GameContainer container;

	// Images/Sprites
	private Animation hayAnimation;
	private int hayX = 1050;
	private Image backgroundImageBack;
	private Image backgroundImageFront;
	private Image gameLogo;
	private Image menuBackground;

	// Fonts
	TrueTypeFont menuFont;

	// Musiques
	public static Music mainMenuBackgroundMusic;
	public static float volume = 1f;
	private boolean musicPlaying;
	private Sound selectionSound;
	private Sound validationSound;

	// Liste des éléments du menu
	private MainMenu mainMenu;

	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		this.container = container;
		this.game = game;
		// Images
		this.hayAnimation = new Animation(new SpriteSheet("backgrounds/hay_sprite.png", 50, 50), 50);
		this.backgroundImageBack = new Image("backgrounds/city_dark_back.png");
		this.backgroundImageFront = new Image("backgrounds/city_dark_front.png");
		this.gameLogo = new Image("logos/Rioter_Slayer.png");
		this.menuBackground = new Image("logos/menu_background.png");

		// Musiques et Sons
		mainMenuBackgroundMusic = new Music("musics/menu_music.ogg");

		selectionSound = new Sound("sounds/selection_sound.ogg");
		validationSound = new Sound("sounds/validation_sound.ogg");

		this.menuFont = FontUtils.getFont("fonts/arcadePix.TTF", 24f);

		this.mainMenu = new MainMenu();
		mainMenu.ajouter(new MenuElement("Nouvelle Partie", this.container.getWidth() / 3 + 90,
				this.container.getHeight() / 2 + 30, true));
		mainMenu.ajouter(new MenuElement("Meilleurs scores", this.container.getWidth() / 3 + 80,
				this.container.getHeight() / 2 + 90, false));
		mainMenu.ajouter(new MenuElement("Options", this.container.getWidth() / 3 + 150,
				this.container.getHeight() / 2 + 150, false));
		mainMenu.ajouter(new MenuElement("Quitter", this.container.getWidth() / 3 + 155,
				this.container.getHeight() / 2 + 210, false));
	}

	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{

		hayX -= delta / 10;

		if (hayX < 850)
		{
			hayX = 1150;
		}
	}

	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		dessinerBackground();
		dessinerMenu(g);
		dessinerSignature(g);
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException
	{
		if (!musicPlaying)
		{
			mainMenuBackgroundMusic.loop(1f, volume);
			musicPlaying = true;
		}
	}

	@Override
	public void keyReleased(int key, char c)
	{
		switch (key)
		{
		case Input.KEY_DOWN:
			selectionSound.play(1f, .2f);
			mainMenu.next();
			break;
		case Input.KEY_UP:
			selectionSound.play(1f, .2f);
			mainMenu.previous();
			break;

		case Input.KEY_ENTER:
			validationSound.play(1f, .2f);
			if (mainMenu.getListe().get(3).isSelected())
			{
				container.exit();
			} else if (mainMenu.getListe().get(2).isSelected())
			{
				game.enterState(OptionState.ID, new FadeOutTransition(), new FadeInTransition());
			} else if (mainMenu.getListe().get(1).isSelected())
			{
				game.enterState(MeilleurScoreState.ID, new FadeOutTransition(), new FadeInTransition());
			} else if (mainMenu.getListe().get(0).isSelected())
			{
				mainMenuBackgroundMusic.stop();
				musicPlaying = false;
				game.enterState(MainGameState.ID, new FadeOutTransition(), new FadeInTransition());
			}

		default:
			break;
		}
	}

	private void dessinerBackground()
	{
		this.backgroundImageBack.draw(0, 0, this.container.getWidth(), this.container.getHeight());
		this.gameLogo.draw(this.container.getWidth() / 6 + 20, this.container.getHeight() / 10);
		this.hayAnimation.draw(hayX, 600);
		this.backgroundImageFront.draw(0, 0, this.container.getWidth(), this.container.getHeight());
		this.menuBackground.draw(this.container.getWidth() / 3, this.container.getHeight() / 2 - 50, 400, 370);
	}

	private void dessinerMenu(Graphics g)
	{
		g.setFont(menuFont);
		g.setColor(Color.gray);

		this.mainMenu.draw(g);
	}

	private void dessinerSignature(Graphics g)
	{
		g.resetFont();
		g.setColor(Color.white);
		g.drawString(MainApp.VERSION + " By Fanen & Merray", 20, this.container.getHeight() - 30);
	}

	@Override
	public int getID()
	{
		return ID;
	}

}
