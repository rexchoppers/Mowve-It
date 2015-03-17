package co.uk.RandomPanda30.Entities;

import java.util.Timer;
import java.util.TimerTask;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import co.uk.RandomPanda30.Drawing.QuadDrawing;
import co.uk.RandomPanda30.Loaders.TextureManager;
import co.uk.RandomPanda30.Map.Map;
import co.uk.RandomPanda30.Mowve.Mowve;
import co.uk.RandomPanda30.Tiles.Tile;
import co.uk.RandomPanda30.Tiles.TileType;
import co.uk.RandomPanda30.Utils.HeartHandler;
import co.uk.RandomPanda30.Utils.TextHandler;

public class Player {

	/*
	 * To do - shop system Fix out of bounds due to start position
	 */

	private float x, y;
	private int lives = 3;
	private int points = 0;
	private boolean moved = false;
	private float cX, cY;
	private float width, height;
	Texture texture;
	private Map map;

	private TextHandler th;

	private HeartHandler hh1;
	private HeartHandler hh2;
	private HeartHandler hh3;

	private boolean heart1 = true;
	private boolean heart2 = true;
	private boolean heart3 = true;

	private boolean f = true;
	private boolean pressed = false;

	public Player (Tile startTile, int width, int height, Texture texture,
			Map map) {
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = width;
		this.height = height;
		this.texture = texture;
		this.map = map;
	}

	public void draw() {
		QuadDrawing.drawTexQuad(cX, cY, width, height, texture);

		if (points <= 99) {
			th = new TextHandler(Integer.toString(points), cX + 26, cY - 25,
					"Arial", 20);
			th.draw();
		} else {
			th = new TextHandler(Integer.toString(points), cX + 15, cY - 25,
					"Arial", 20);
			th.draw();
		}

		if (heart1) {
			hh1 = new HeartHandler(TextureManager.qLoadTexture("heart"),
					cX + 24, cY - 50, 16, 16);
			hh1.draw();
		}

		if (heart2) {
			hh2 = new HeartHandler(TextureManager.qLoadTexture("heart"),
					cX + 5, cY - 50, 16, 16);
			hh2.draw();
		}

		if (heart3) {
			hh3 = new HeartHandler(TextureManager.qLoadTexture("heart"),
					cX + 43, cY - 50, 16, 16);
			hh3.draw();
		}
	}

	public void addLife(int no) {
		lives = lives + no;
	}

	public void removeLife(int no) {
		lives = lives - no;

		if (heart2 == true) {
			heart2 = false;
		} else if (heart1 == true) {
			heart1 = false;
		} else {
			heart3 = false;
		}

		if (lives == 0) {
			gameOver();
		}
	}

	public int getLives() {
		return lives;
	}

	public void addPoints(int no) {
		points = points + no;
	}

	public void removePoints(int no) {
		points = points - no;
	}

	public void clearPoints() {
		points = 0;
	}

	public int getPoints() {
		return points;
	}

	public void gameOver() {
		Mowve.exit();
	}

	public void spawn(Tile startingTile, Tile currentTile, boolean removeLife,
			int livesLost) {
		if (removeLife) {
			removeLife(livesLost);
		}
		map.setTile((int) Math.floor(currentTile.getX() / 64),
				(int) Math.floor(currentTile.getY() / 64), TileType.DIRT);
		updateLocation((int) Math.floor(startingTile.getX()),
				(int) Math.floor(startingTile.getY()));
	}

	@SuppressWarnings("incomplete-switch")
	public void update() {
		if (f) {
			f = false;
		} else {
			if (!moved) {
				cX = x;
				cY = y;
			}
			Tile startingTile = map.getTile((int) (x / 64), (int) (y / 64));
			Tile currentTile = map.getTile((int) Math.floor(cX / 64),
					(int) Math.floor(cY / 64));
			Tile rightTile = map.getTile((int) Math.floor(cX / 64) + 1,
					(int) Math.floor(cY / 64));
			Tile leftTile = map.getTile((int) Math.floor(cX / 64) - 1,
					(int) Math.floor(cY / 64));
			Tile upTile = map.getTile((int) Math.floor(cX / 64),
					(int) Math.floor(cY / 64) - 1);
			Tile downTile = map.getTile((int) Math.floor(cX / 64),
					(int) Math.floor(cY / 64) + 1);

			if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
				if (!pressed) {
					if (!moved) {
						setStartTile();
						moved = true;
					}
					switch (rightTile.getType()) {
					case GRASS:
						map.setTile((int) Math.floor(rightTile.getX() / 64),
								(int) Math.floor(rightTile.getY() / 64),
								TileType.DIRT);
						updateLocation((int) Math.floor(rightTile.getX()),
								(int) Math.floor(rightTile.getY()));
						addPoints(1);
						break;
					case DIRT:
						updateLocation((int) Math.floor(rightTile.getX()),
								(int) Math.floor(rightTile.getY()));
						break;
					case WATER:
						break;
					case ORNAMENT:
						spawn(startingTile, currentTile, true, 1);
						break;
					case FLOWER:
						spawn(startingTile, currentTile, true, 1);
						break;
					}
					pressed = true;
					keyTimer();
				}
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
				if (!pressed) {
					if (!moved) {
						setStartTile();
						moved = true;
					}
					switch (leftTile.getType()) {
					case GRASS:
						map.setTile((int) Math.floor(leftTile.getX() / 64),
								(int) Math.floor(leftTile.getY() / 64),
								TileType.DIRT);
						addPoints(1);
						updateLocation((int) Math.floor(leftTile.getX()),
								(int) Math.floor(leftTile.getY()));
						break;
					case DIRT:
						updateLocation((int) Math.floor(leftTile.getX()),
								(int) Math.floor(leftTile.getY()));
						break;
					case WATER:
						break;
					case ORNAMENT:
						spawn(startingTile, currentTile, true, 1);
						break;
					case FLOWER:
						spawn(startingTile, currentTile, true, 1);
						break;
					}
					pressed = true;
					keyTimer();
				}
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
				if (!pressed) {
					if (!moved) {
						setStartTile();
						moved = true;
					}
					switch (upTile.getType()) {
					case GRASS:
						map.setTile((int) Math.floor(upTile.getX() / 64),
								(int) Math.floor(upTile.getY() / 64),
								TileType.DIRT);
						addPoints(1);
						updateLocation((int) Math.floor(upTile.getX()),
								(int) Math.floor(upTile.getY()));
						break;
					case DIRT:
						updateLocation((int) Math.floor(upTile.getX()),
								(int) Math.floor(upTile.getY()));
						break;
					case WATER:
						break;
					case ORNAMENT:
						spawn(startingTile, currentTile, true, 1);
						break;
					case FLOWER:
						spawn(startingTile, currentTile, true, 1);
						break;
					}
					pressed = true;
					keyTimer();
				}
			}

			if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
				if (!pressed) {
					if (!moved) {
						setStartTile();
						moved = true;
					}
					switch (downTile.getType()) {
					case GRASS:
						map.setTile((int) Math.floor(downTile.getX() / 64),
								(int) Math.floor(downTile.getY() / 64),
								TileType.DIRT);
						addPoints(1);
						updateLocation((int) Math.floor(downTile.getX()),
								(int) Math.floor(downTile.getY()));
						break;
					case DIRT:
						updateLocation((int) Math.floor(downTile.getX()),
								(int) Math.floor(downTile.getY()));
						break;
					case WATER:
						break;
					case ORNAMENT:
						spawn(startingTile, currentTile, true, 1);
						break;
					case FLOWER:
						spawn(startingTile, currentTile, true, 1);
						break;
					}
					pressed = true;
					keyTimer();
				}
			}
			draw();
		}
	}

	private void keyTimer() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				pressed = false;
			}
		}, 150);
	}

	private void updateLocation(int newX, int newY) {
		cX = newX;
		cY = newY;
	}

	private void setStartTile() {
		map.setTile((int) Math.floor(cX / 64), (int) Math.floor(cY / 64),
				TileType.DIRT);
	}

	public float getX() {
		return cX;
	}

	public float getY() {
		return cY;
	}
}
