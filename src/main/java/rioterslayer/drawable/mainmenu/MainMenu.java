package rioterslayer.drawable.mainmenu;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Graphics;

public class MainMenu
{
	private List<MenuElement> liste;

	private int selectedItem = 0;

	public MainMenu()
	{
		super();
		liste = new ArrayList<MenuElement>();
	}

	public MainMenu(List<MenuElement> liste)
	{
		super();
		liste.get(0).setSelected(true);
		this.liste = liste;

	}

	public void draw(Graphics g)
	{

		for (MenuElement element : liste)
		{
			element.draw(g);
		}

	}

	public void next()
	{

		liste.get(selectedItem).setSelected(false);
		if (selectedItem == liste.size() - 1)
		{
			selectedItem = 0;
		} else
		{
			selectedItem += 1;
		}
		liste.get(selectedItem).setSelected(true);
	}

	public void previous()
	{
		liste.get(selectedItem).setSelected(false);
		if (selectedItem == 0)
		{
			selectedItem = liste.size() - 1;
		} else
		{
			selectedItem -= 1;
		}
		liste.get(selectedItem).setSelected(true);
	}

	public List<MenuElement> getListe()
	{

		return liste;
	}

	public void setListe(List<MenuElement> liste)
	{
		this.liste = liste;
	}

	public void ajouter(MenuElement element)
	{
		if (liste.isEmpty())
		{
			element.setSelected(true);
		}

		liste.add(element);
	}

}
