package main.java.co.uk.rexchoppers.MowveIt.Entities;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import main.java.co.uk.rexchoppers.MowveIt.Drawing.R;
import main.java.co.uk.rexchoppers.MowveIt.Map.Map;
import main.java.co.uk.rexchoppers.MowveIt.Mowve.Mowve;
import main.java.co.uk.rexchoppers.MowveIt.Tiles.Tile;
import org.newdawn.slick.opengl.Texture;

public class Dog implements Entity {

	private float x, y, cX, cY, width, height;

	private boolean moved = false;
	private boolean playerNear = false;
	Texture texture;
	private Map map;
	private boolean f = true;
	private boolean i = false;

	Tile startingTile;
	Tile currentTile;
	Tile rightTile;
	Tile leftTile;
	Tile upTile;
	Tile downTile;
	
	private R rotation = R.RIGHT;

	public Dog(Tile startTile, int width, int height, Texture texture, Map map) {
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.cX = startTile.getX();
		this.cY = startTile.getY();
		this.width = width;
		this.height = height;
		this.texture = texture;
		this.map = map;
	}

	public void draw() {
		// QuadDrawing.drawTexQuad(cX, cY, width, height, texture, rotation);
	}

	public void updateLocation(int newX, int newY) {
		cX = newX;
		cY = newY;
	}

	public void update() {
		if (f) {
			f = false;
		} else {
			if (!moved) {
				cX = x;
				cY = y;
			}
			startingTile = map.getTile((int) (x / 64), (int) (y / 64));
			currentTile = map.getTile((int) Math.floor(cX / 64),
					(int) Math.floor(cY / 64));
			rightTile = map.getTile((int) Math.floor(cX / 64) + 1,
					(int) Math.floor(cY / 64));
			leftTile = map.getTile((int) Math.floor(cX / 64) - 1,
					(int) Math.floor(cY / 64));
			upTile = map.getTile((int) Math.floor(cX / 64),
					(int) Math.floor(cY / 64) - 1);
			downTile = map.getTile((int) Math.floor(cX / 64),
					(int) Math.floor(cY / 64) + 1);
			if (!i) {
				if (!playerNear) {
					moved = true;
					i = true;
					Tile random = randomTile();
					updateLocation((int) Math.floor(random.getX()),
							(int) Math.floor(random.getY()));
					checkCollisions();
					stepTimer();
				} else {
					for(int i = 0; i < 5; i++) {
						runTimer();
					}
					playerNear = false;
				}
			}
		}
		draw();
	}

	public Tile randomTile() {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		tiles.add(upTile);
		tiles.add(downTile);
		tiles.add(leftTile);
		tiles.add(rightTile);

		Random random = new Random();
		int no = 0;
		no = random.nextInt(3) + 1;
		Tile st = null;

		st = tiles.get(no);
		switch (st.getType()) {
		case GRASS:
			break;
		case DIRT:
			break;
		case PLAYER:
			break;
		case FLOWER:
			no = random.nextInt(3) + 1;
			st = tiles.get(no);
			break;
		case ORNAMENT:
			no = random.nextInt(3) + 1;
			st = tiles.get(no);
			break;
		case WATER:
			break;
		}
		return st;
	}

	// Sets I to true and won't allow the entity to move in the loop

	public void stepTimer() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				i = false;
			}
		}, 1000);
	}

	public void runTimer() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Tile tile = randomTile();
				updateLocation((int) Math.floor(tile.getX()),
						(int) Math.floor(tile.getY()));
			}
		}, 700);
	}

	public void checkCollisions() {
		if (Mowve.player.getX() + 64 == this.getX()
				|| Mowve.player.getX() - 64 == this.getX()
				|| Mowve.player.getY() + 64 == this.getY()
				|| Mowve.player.getY() - 64 == this.getY()) {
			playerNear = true;
		}
	}

	public float getX() {
		return cX;
	}

	public float getY() {
		return cY;
	}

	public void setX() {
		// TODO Auto-generated method stub

	}

	public void setY() {
		// TODO Auto-generated method stub

	}
}