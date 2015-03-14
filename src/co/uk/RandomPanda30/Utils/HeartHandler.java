package co.uk.RandomPanda30.Utils;

import org.newdawn.slick.opengl.Texture;

import co.uk.RandomPanda30.Drawing.QuadDrawing;

public class HeartHandler {

	private float baseX;
	private float baseY;
	private float baseWidth;
	private float baseHeight;
	private Texture baseTexture;

	Texture heart1;
	boolean t1 = true;
	float x1, y1, w1, h1;

	Texture heart2;
	boolean t2 = true;
	float x2, y2, w2, h2;

	Texture heart3;
	boolean t3 = true;
	float x3, y3, w3, h3;

	public HeartHandler(Texture texture, float x, float y, float width,
			float height) {
		baseX = x;
		baseY = y;
		baseWidth = width;
		baseHeight = height;
		baseTexture = texture;
	}

	public void updateHearts() {
		heartMath();
		draw();
	}

	public void heartMath() {
		if (t1) {
			heart1 = baseTexture;
			x1 = baseX - 20;
			y1 = baseY;
			w1 = baseWidth;
			h1 = baseHeight;
		}

		if (t2) {
			heart2 = baseTexture;
			x2 = baseX;
			y2 = baseY;
			w2 = baseWidth;
			h2 = baseHeight;
		}

		if (t3) {
			heart3 = baseTexture;
			x3 = baseX + 20;
			y3 = baseY;
			w3 = baseWidth;
			h3 = baseHeight;
		}
	}

	public void draw() {
		if (t1) {
			QuadDrawing.drawTexQuad(x1, y1, w1, h1, heart1);
		}

		if (t2) {
			QuadDrawing.drawTexQuad(x2, y2, w2, h2, heart2);
		}

		if (t3) {
			QuadDrawing.drawTexQuad(x3, y3, w3, h3, heart3);
		}
	}

	public void removeLife() {
		if (t3) {
			System.out.println("11 " + t3);
			t3 = false;
			System.out.println("11 " + t3);
			heart3.release();
		} else if (t2) {
			t2 = false;
			heart2.release();
		} else if (t1) {
			t1 = false;
			heart1.release();
		}
	}
}
