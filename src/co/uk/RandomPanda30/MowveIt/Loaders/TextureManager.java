package co.uk.RandomPanda30.MowveIt.Loaders;

import java.io.IOException;
import java.io.InputStream;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class TextureManager {

	public static Texture loadTexture(String path, String fileType) {
		Texture texture = null;
		InputStream in = ResourceLoader.getResourceAsStream(path);
		try {
			texture = TextureLoader.getTexture(fileType, in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return texture;
	}

	public static Texture qLoadTexture(String name) {
		Texture texture = null;
		texture = loadTexture("images/" + name + ".png", "PNG");
		return texture;
	}
}