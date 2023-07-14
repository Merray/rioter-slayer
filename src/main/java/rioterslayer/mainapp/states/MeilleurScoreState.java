package rioterslayer.mainapp.states;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import rioterslayer.mainapp.MainApp;
import rioterslayer.utils.FontUtils;

public class MeilleurScoreState extends BasicGameState
{
	public static final int ID = 2;
	private StateBasedGame game;
	private GameContainer container;

	// Images
	private Image backgroundImage;
	private Image gameLogo;
	private Image menuBackground;

	// Fonts
	TrueTypeFont menuFont;

	HashMap<Integer, String> meilleursScores;

	private int timeElapsed = 0;
	private boolean affiche = true;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		this.game = game;
		this.container = container;
		// Images
		this.backgroundImage = new Image("backgrounds/city_dark.png");
		this.gameLogo = new Image("logos/Rioter_Slayer.png");
		this.menuBackground = new Image("logos/menu_background.png");

		this.menuFont = FontUtils.getFont("fonts/arcadePix.TTF", 24f);

		meilleursScores = new HashMap<>();
		meilleursScores.put(40, StringUtils.rightPad("Fanen", 8, " ") + " - 10 000");
		meilleursScores.put(90, StringUtils.rightPad("Merray", 8, " ") + " - 9 300");
		meilleursScores.put(140, StringUtils.rightPad("Bab1", 8, " ") + " - 9 200");
		meilleursScores.put(190, StringUtils.rightPad("Teems1s", 8, " ") + " - 3 200");
		meilleursScores.put(240, StringUtils.rightPad("Nyoho", 8, " ") + " - 1 000");

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException
	{
		timeElapsed += delta;

		if (timeElapsed > 500)
		{
			timeElapsed = 0;
			affiche = !affiche;
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException
	{
		dessinerBackground();
		dessinerMeilleurScore(g);
		dessinerSignature(g);

	}

	@Override
	public void keyReleased(int key, char c)
	{
		switch (key)
		{
		case Input.KEY_ENTER:

			game.enterState(MainMenuState.ID, new FadeOutTransition(), new FadeInTransition());

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

	private void dessinerMeilleurScore(Graphics g)
	{
		g.setFont(menuFont);

		g.setColor(Color.red.darker());
		g.drawString("MEILLEURS SCORES", this.container.getWidth() / 3 + 50, this.container.getHeight() / 2 - 10);
		g.setColor(Color.black);
		meilleursScores.forEach((key, value) -> g.drawString(value, this.container.getWidth() / 3 + 70,
				this.container.getHeight() / 2 + key));

		g.setColor(Color.gray);
		if (affiche)
		{
			g.drawString("Press ENTER to go back", this.container.getWidth() / 3 + 15,
					this.container.getHeight() / 2 + 320);
		}
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
