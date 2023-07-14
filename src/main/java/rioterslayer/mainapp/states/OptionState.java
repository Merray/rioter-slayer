package rioterslayer.mainapp.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import rioterslayer.drawable.mainmenu.MainMenu;
import rioterslayer.drawable.mainmenu.MenuElement;
import rioterslayer.mainapp.MainApp;
import rioterslayer.utils.FontUtils;

public class OptionState extends BasicGameState
{

	public static final int ID = 3;
	private StateBasedGame game;
	private GameContainer container;

	// Images
	private Image backgroundImage;
	private Image gameLogo;
	private Image menuBackground;

	// Fonts
	TrueTypeFont menuFont;

	// Musiques
	private Sound selectionSound;
	private Sound validationSound;

	// Liste des éléments du menu
	private MainMenu mainMenu;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		this.container = container;
		this.game = game;
		// Images
		this.backgroundImage = new Image("backgrounds/city_dark.png");
		this.gameLogo = new Image("logos/Rioter_Slayer.png");
		this.menuBackground = new Image("logos/menu_background.png");

		// Musiques et Sons
		selectionSound = new Sound("sounds/selection_sound.ogg");
		validationSound = new Sound("sounds/validation_sound.ogg");

		this.menuFont = FontUtils.getFont("fonts/arcadePix.TTF", 24f);

		this.mainMenu = new MainMenu();
		mainMenu.ajouter(
				new MenuElement("-  Musique  +", container.getWidth() / 3 + 90, container.getHeight() / 2 + 30, true));
		mainMenu.ajouter(
				new MenuElement("Retour", container.getWidth() / 3 + 150, container.getHeight() / 2 + 170, false));
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		dessinerBackground();
		dessinerMenu(g);
		dessinerSon(g);
		dessinerSignature(g);
	}

	@Override
	public void keyReleased(int key, char c)
	{

		switch (key)
		{
		case Input.KEY_LEFT:
			if (mainMenu.getListe().get(0).isSelected())
			{
				MainMenuState.volume -= .1f;
				if (MainMenuState.volume < .1f)
				{
					MainMenuState.volume = 0f;
				}

				MainMenuState.mainMenuBackgroundMusic.setVolume(MainMenuState.volume);
			}
			break;
		case Input.KEY_RIGHT:
			if (mainMenu.getListe().get(0).isSelected())
			{
				MainMenuState.volume += .1f;
				if (MainMenuState.volume > 1f)
				{
					MainMenuState.volume = 1f;
				}
				MainMenuState.mainMenuBackgroundMusic.setVolume(MainMenuState.volume);
			}
			break;

		case Input.KEY_DOWN:
			selectionSound.play(1f, .2f);
			mainMenu.next();
			break;

		case Input.KEY_UP:
			selectionSound.play(1f, .2f);
			mainMenu.previous();
			break;
		case Input.KEY_ENTER:
			if (mainMenu.getListe().get(1).isSelected())
			{
				validationSound.play(1f, .2f);
				game.enterState(MainMenuState.ID, new FadeOutTransition(), new FadeInTransition());
			}
			break;
		default:
			break;
		}
	}

	private void dessinerSignature(Graphics g)
	{
		g.resetFont();
		g.setColor(Color.white);
		g.drawString(MainApp.VERSION + " By Fanen & Merray", 20, this.container.getHeight() - 30);
	}

	private void dessinerSon(Graphics g)
	{
		if (mainMenu.getListe().get(0).isSelected())
		{
			g.setColor(Color.black);
		} else
		{
			g.setColor(Color.gray);
		}
		if (MainMenuState.volume < .1f)
		{
			g.drawString("OFF", this.container.getWidth() / 3 + 170, this.container.getHeight() / 2 + 80);
		} else
		{
			g.drawString(String.valueOf((int) (MainMenuState.volume * 10)), this.container.getWidth() / 3 + 187,
					this.container.getHeight() / 2 + 80);
		}
	}

	private void dessinerMenu(Graphics g)
	{
		g.setFont(menuFont);
		g.setColor(Color.gray);

		this.mainMenu.draw(g);
	}

	private void dessinerBackground()
	{
		this.backgroundImage.draw(0, 0, this.container.getWidth(), this.container.getHeight());
		this.gameLogo.draw(this.container.getWidth() / 6 + 20, this.container.getHeight() / 10);
		this.menuBackground.draw(this.container.getWidth() / 3, this.container.getHeight() / 2 - 50, 400, 370);
	}

	@Override
	public int getID()
	{
		return ID;
	}

}
