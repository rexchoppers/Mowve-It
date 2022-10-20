package co.uk.rexchoppers.MowveIt.Mowve;

import java.util.Random;

import co.uk.rexchoppers.MowveIt.Map.Map;
import co.uk.rexchoppers.MowveIt.Tiles.Tile;
import co.uk.rexchoppers.MowveIt.Tiles.TileType;
import co.uk.rexchoppers.MowveIt.Utils.Clock;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import co.uk.rexchoppers.MowveIt.Entities.Cat;
import co.uk.rexchoppers.MowveIt.Entities.Dog;
import co.uk.rexchoppers.MowveIt.Entities.Player;
import co.uk.rexchoppers.MowveIt.Loaders.TextureManager;
import co.uk.rexchoppers.MowveIt.Music.Music;

public class Mowve {

	private static int w;
	private static int h;

	private static boolean fullscreen = false;

	public static Mowve main;
	public static Player player;

	public void run() {
		w = 1024;
		h = 960;
		try {
			Display.setDisplayMode(new DisplayMode(w, h));
			Display.create();
			Display.setResizable(false);
			// Mouse.setGrabbed(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		Music music = new Music();
		music.initMusic();
		// music.playSong();

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 1024, 960, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		update();
	}

	public static void update() {
		int[][] tempMap = { { 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 2, 0, 0, 0, 0, 3, 0, 0, 2, 0, 1, 0 },
				{ 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 3, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 4, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 4 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 6, 0, 4, 0, 0 },
				{ 0, 0, 0, 0, 0, 4, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0 },
				{ 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 4, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };

		Random random = new Random();
		Map tileMap = new Map(tempMap);

		int startX = random.nextInt(15) + 1;
		int startY = random.nextInt(14) + 1;
		Tile startTile = tileMap.getTile(startX, startY);

		if (!isGrass(startTile)) {
			startX = random.nextInt(15) + 1;
			startY = random.nextInt(14) + 1;
			isGrass(startTile);
		}

		player = new Player(tileMap.getTile(startX, startY), 64, 64, tileMap);
		player.draw();

		Dog dog = new Dog(tileMap.getTile(10, 10), 64, 64,
				TextureManager.qLoadTexture("ene"), tileMap);
		// dog.draw();

		Cat cat = new Cat(tileMap.getTile(5, 5), 64, 64,
				TextureManager.qLoadTexture("ene"), tileMap);

		while (!Display.isCloseRequested()) {
			tileMap.draw();
			Clock.update();
			player.update();

			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				terminate();
			}

			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}

	public static boolean isGrass(Tile tile) {
		if (tile.getType().equals(TileType.GRASS)) {
			return true;
		}
		return false;
	}

	public static void exit() {
		Display.destroy();
		AL.destroy();
		System.exit(-1);
		main = null;
	}

	public static void terminate() {
		Display.destroy();
		AL.destroy();
		System.exit(-1);
		main = null;
	}

	public static void main(String[] args) {
		main = new Mowve();
		main.run();
	}

	public static int getWidth() {
		return w;
	}

	public static int getHeight() {
		return h;
	}

	public static boolean isFullscreen() {
		if (fullscreen) {
			return true;
		}
		return false;
	}

	public static void setFullscreen(boolean newFullscreen) {
		fullscreen = newFullscreen;
	}
}