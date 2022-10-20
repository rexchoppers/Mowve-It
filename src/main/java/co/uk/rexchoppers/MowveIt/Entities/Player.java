package main.java.co.uk.rexchoppers.MowveIt.Entities;

import java.util.Timer;
import java.util.TimerTask;

import main.java.co.uk.rexchoppers.MowveIt.Drawing.R;
import main.java.co.uk.rexchoppers.MowveIt.Loaders.TextureManager;
import main.java.co.uk.rexchoppers.MowveIt.Map.Map;
import main.java.co.uk.rexchoppers.MowveIt.Mowve.Mowve;
import main.java.co.uk.rexchoppers.MowveIt.Tiles.Tile;
import main.java.co.uk.rexchoppers.MowveIt.Tiles.TileType;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import main.java.co.uk.rexchoppers.MowveIt.Drawing.QuadDrawing;
import main.java.co.uk.rexchoppers.MowveIt.Utils.HeartHandler;
import main.java.co.uk.rexchoppers.MowveIt.Utils.TextHandler;

public class Player {

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

	private boolean aR = true;
	private boolean aL = true;
	private boolean aU = true;
	private boolean aD = true;

	private boolean f = true;
	private boolean pressed = false;

	private R rotation = R.LEFT;

	public Player(Tile startTile, int width, int height, Map map) {
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = width;
		this.height = height;
		this.map = map;
	}

	public void draw() {
		switch (rotation.toString()) {
		case "LEFT":
			this.texture = TextureManager.qLoadTexture("playerL");
			break;
		case "RIGHT":
			this.texture = TextureManager.qLoadTexture("playerR");
			break;
		case "UP":
			this.texture = TextureManager.qLoadTexture("playerU");
			break;
		case "DOWN":
			this.texture = TextureManager.qLoadTexture("playerD");
			break;
		}
		
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

	public void spawn(Tile startingTile, Tile currentTile, int livesLost,
			int pointsRemove) {
		removeLife(livesLost);
		if ((points - pointsRemove) < 0) {
			points = 0;
		} else {
			removePoints(pointsRemove);
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

			Tile rightTile = null;
			if (map.getTile((int) Math.floor(cX / 64) + 1,
					(int) Math.floor(cY / 64)) != null) {
				rightTile = map.getTile((int) Math.floor(cX / 64) + 1,
						(int) Math.floor(cY / 64));
			} else {
				System.err.println("test");
			}

			Tile leftTile = map.getTile((int) Math.floor(cX / 64) - 1,
					(int) Math.floor(cY / 64));
			Tile upTile = map.getTile((int) Math.floor(cX / 64),
					(int) Math.floor(cY / 64) - 1);
			Tile downTile = map.getTile((int) Math.floor(cX / 64),
					(int) Math.floor(cY / 64) + 1);

			if (aL) {
				if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
					if (!pressed) {
						if (!moved) {
							setStartTile();
							moved = true;
						}
						rotation = R.RIGHT;
						switch (rightTile.getType()) {
						case GRASS:
							map.setTile(
									(int) Math.floor(rightTile.getX() / 64),
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
							spawn(startingTile, currentTile, 1, 10);
							break;
						case FLOWER:
							spawn(startingTile, currentTile, 1, 10);
							break;
						case GNOME:
							map.setTile(
									(int) Math.floor(rightTile.getX() / 64),
									(int) Math.floor(rightTile.getY() / 64),
									TileType.DIRT);
							updateLocation((int) Math.floor(rightTile.getX()),
									(int) Math.floor(rightTile.getY()));
							addPoints(10);
							break;
						}
						pressed = true;
						keyTimer();
					}
				}
			}

			if (aR) {
				if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
					if (!pressed) {
						if (!moved) {
							setStartTile();
							moved = true;
						}
						rotation = R.LEFT;
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
							spawn(startingTile, currentTile, 1, 10);
							break;
						case FLOWER:
							spawn(startingTile, currentTile, 1, 10);
							break;
						case GNOME:
							map.setTile((int) Math.floor(leftTile.getX() / 64),
									(int) Math.floor(leftTile.getY() / 64),
									TileType.DIRT);
							updateLocation((int) Math.floor(leftTile.getX()),
									(int) Math.floor(leftTile.getY()));
							addPoints(10);
							break;
						}
						pressed = true;
						keyTimer();
					}
				}
			}

			if (aU) {
				if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
					if (!pressed) {
						if (!moved) {
							setStartTile();
							moved = true;
						}
						rotation = R.UP;
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
							spawn(startingTile, currentTile, 1, 10);
							break;
						case FLOWER:
							spawn(startingTile, currentTile, 1, 10);
							break;
						case GNOME:
							map.setTile((int) Math.floor(upTile.getX() / 64),
									(int) Math.floor(upTile.getY() / 64),
									TileType.DIRT);
							updateLocation((int) Math.floor(upTile.getX()),
									(int) Math.floor(upTile.getY()));
							addPoints(10);
							break;
						}
						pressed = true;
						keyTimer();
					}
				}
			}

			if (aD) {
				if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
					if (!pressed) {
						if (!moved) {
							setStartTile();
							moved = true;
						}
						rotation = R.DOWN;
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
							spawn(startingTile, currentTile, 1, 10);
							break;
						case FLOWER:
							spawn(startingTile, currentTile, 1, 10);
							break;
						case GNOME:
							map.setTile((int) Math.floor(downTile.getX() / 64),
									(int) Math.floor(downTile.getY() / 64),
									TileType.DIRT);
							updateLocation((int) Math.floor(downTile.getX()),
									(int) Math.floor(downTile.getY()));
							addPoints(10);
							break;
						}
						pressed = true;
						keyTimer();
					}
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
