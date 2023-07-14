package rioterslayer.mainapp;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import rioterslayer.mainapp.states.GameOverState;
import rioterslayer.mainapp.states.MainGameState;
import rioterslayer.mainapp.states.MainMenuState;
import rioterslayer.mainapp.states.MeilleurScoreState;
import rioterslayer.mainapp.states.OptionState;

public class MainApp extends StateBasedGame
{

	public static int GAME_WIDTH = 1200;
	public static int GAME_HEIGH = 800;
	public static String GAME_NAME = "Rioter Slayer";
	public static boolean DEBUG = true;
	public static String VERSION = "v0.0.2";

	public MainApp()
	{
		super(GAME_NAME);
	}

	public static void main(String[] args) throws SlickException
	{

		AppGameContainer app = new AppGameContainer(new MainApp());

		app.setDisplayMode(GAME_WIDTH, GAME_HEIGH, false);
		app.setTargetFrameRate(60);

		app.start();
	}

	@Override
	public void initStatesList(GameContainer arg0) throws SlickException
	{
		addState(new MainMenuState());
		addState(new MeilleurScoreState());
		addState(new OptionState());
		addState(new MainGameState());
		addState(new GameOverState());

	}

}
