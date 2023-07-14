package rioterslayer.utils;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class FontUtils
{
	public static TrueTypeFont getFont(String chemin, float taille)
	{
		Font laFont = null;
		try
		{
			InputStream inputStream = ResourceLoader.getResourceAsStream(chemin);
			laFont = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(taille);

		} catch (FontFormatException | IOException e)
		{

			e.printStackTrace();
		}

		return new TrueTypeFont(laFont, false);
	}
}
