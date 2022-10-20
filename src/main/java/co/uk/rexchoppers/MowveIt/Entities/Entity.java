package co.uk.rexchoppers.MowveIt.Entities;

import co.uk.rexchoppers.MowveIt.Tiles.Tile;

public abstract interface Entity {

	public void draw();

	public void updateLocation(int newX, int newY);

	public void update();

	public Tile randomTile();

	public void stepTimer();

	public void checkCollisions();

	public float getX();

	public float getY();
	
	public void setX();
	
	public void setY();

}
