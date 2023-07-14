package rioterslayer.mainapp.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import rioterslayer.utils.FontUtils;

public class GameOverState extends BasicGameState
{

	public static final int ID = 4;
	private StateBasedGame game;
	private GameContainer container;

	// Images
	private Image backgroundImage;
	private Image gameOverImage;

	// Sons/Musiques
	private Sound indigniteSound;
	private Music gameOverMusic;

	private int timeElapsed = 0;
	private boolean affiche = true;

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException
	{
		this.container = container;
		this.game = game;
		this.indigniteSound = new Sound("sounds/indignite.ogg");

		this.backgroundImage = new Image("backgrounds/city_dark.png");
		this.gameOverImage = new Image("logos/game_over.png");

		this.gameOverMusic = new Music("musics/game_over.ogg");

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

		dessinerBackground(g);
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException
	{
		this.indigniteSound.play();
		this.gameOverMusic.loop(1f, MainMenuState.volume);
	}

	private void dessinerBackground(Graphics g)
	{
		this.backgroundImage.draw(0, 0, this.container.getWidth(), this.container.getHeight());
		this.gameOverImage.draw(this.container.getWidth() / 5 + 40, this.container.getHeight() / 3);
		if (affiche)
		{
			g.setFont(FontUtils.getFont("fonts/arcadePix.TTF", 24f));
			g.setColor(Color.red);
			g.drawString("Press ENTER to go back", this.container.getWidth() / 3 + 15,
					this.container.getHeight() / 2 + 320);
			g.resetFont();
		}

	}

	@Override
	public void keyPressed(int key, char c)
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

	@Override
	public int getID()
	{
		return ID;
	}

}
