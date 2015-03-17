package co.uk.RandomPanda30.Mowve;

import java.util.Calendar;
import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import co.uk.RandomPanda30.Entities.Cat;
import co.uk.RandomPanda30.Entities.Dog;
import co.uk.RandomPanda30.Entities.Player;
import co.uk.RandomPanda30.Loaders.TextureManager;
import co.uk.RandomPanda30.Map.Map;
import co.uk.RandomPanda30.Music.Music;
import co.uk.RandomPanda30.Tiles.Tile;
import co.uk.RandomPanda30.Utils.Clock;
import co.uk.RandomPanda30.Utils.Time;

public class Mowve {

	/**
	 * Green || 0 = Grass Red || 1 = Dirt Blue || 2 = Water Yellow || 3 =
	 * Ornaments Brown || 4 = Flowers Pink || 5 = Player
	 */

	private static int w;
	private static int h;
	private static int fps;

	private static int startX;
	private static int startY;

	private static int startXDOG;
	private static int startYDOG;

	private static boolean fullscreen = false;

	public static boolean timeup = false;

	public static boolean mm = true;

	public static long time = 0;
	public static int minutes = 1;

	public static Mowve main = new Mowve();
	public static Player player;

	public void run() {
		w = 1024;
		h = 960;
		try {
			Display.setDisplayMode(new DisplayMode(w, h));
			Display.create();
			Display.setResizable(true);
			time = Calendar.getInstance().getTimeInMillis()
					+ (5 * 1000 * 60 * 60);
			// Mouse.setGrabbed(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		System.err.println(time);

		System.err.println(w);
		System.err.println(h);

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
				{ 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 4, 0, 0 },
				{ 0, 0, 0, 0, 0, 4, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0 },
				{ 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 4, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };

		Random random = new Random();
		Map tileMap = new Map(tempMap);

		startX = random.nextInt(15) + 1;
		startY = random.nextInt(14) + 1;
		Tile startTile = tileMap.getTile(startX, startY);
		switch (startTile.getType()) {
		case GRASS:
			break;
		case FLOWER:
			startX = random.nextInt(15) + 1;
			startY = random.nextInt(14) + 1;
			break;
		case PLAYER:
			startX = random.nextInt(15) + 1;
			startY = random.nextInt(14) + 1;
			break;
		case WATER:
			startX = random.nextInt(15) + 1;
			startY = random.nextInt(14) + 1;
			break;
		case ORNAMENT:
			startX = random.nextInt(15) + 1;
			startY = random.nextInt(14) + 1;
			break;
		case DIRT:
			break;
		}

		player = new Player(tileMap.getTile(startX, startY), 64, 64,
				TextureManager.qLoadTexture("ene"), tileMap);
		player.draw();

		startXDOG = random.nextInt(15) + 1;
		startYDOG = random.nextInt(14) + 1;

		Dog dog = new Dog(tileMap.getTile(startXDOG, startYDOG), 64, 64,
				TextureManager.qLoadTexture("ene"), tileMap);
		// dog.draw();

		Cat cat = new Cat(tileMap.getTile(5, 5), 64, 64,
				TextureManager.qLoadTexture("ene"), tileMap);

		while (!Display.isCloseRequested()) {
			Time.checkTime();
			if (!timeup) {
				Clock.update();
				tileMap.draw();
				player.update();
				// dog.update();

				if (Keyboard.isKeyDown(Keyboard.KEY_F2)) {
					if (!fullscreen) {
						fullscreen = true;

					}
				}

				if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
					terminate();
				}

				Display.update();
				Display.sync(60);
			} else {

			}
		}
		Display.destroy();
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
		main.run();
	}

	public static int getFPS() {
		return fps;
	}

	public static void setFPS(int newFPS) {
		fps = newFPS;
	}

	public static int getWidth() {
		return w;
	}

	public static void setWidth(int newWidth) {
		w = newWidth;
	}

	public static int getHeight() {
		return h;
	}

	public static void setHeight(int newHeight) {
		h = newHeight;
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

	public static void resetGame() {
		main = null;
		main = new Mowve();
	}
}