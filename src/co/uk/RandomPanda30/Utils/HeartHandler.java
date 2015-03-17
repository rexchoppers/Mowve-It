package co.uk.RandomPanda30.Utils;

import org.newdawn.slick.opengl.Texture;

import co.uk.RandomPanda30.Drawing.QuadDrawing;

public class HeartHandler {

	private float x;
	private float y;
	private float width;
	private float height;
	private Texture texture;

	public HeartHandler(Texture texture, float x, float y, float width,
			float height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.texture = texture;
	}

	public void draw() {
		QuadDrawing.drawTexQuad(x, y, width, height, texture);
	}

	public void remove() {
		texture.release();
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
}
