package co.uk.rexchoppers.MowveIt.Drawing;

import org.lwjgl.opengl.GL11;

public class LineDrawing {

	private static float x;
	private static float x2;
	private static float y;
	private static float y2;

	public LineDrawing (float x, float y, float x2, float y2) {
		LineDrawing.x = x;
		LineDrawing.y = y;
	}

	public void drawLine() {
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex2f(x, y);
		GL11.glVertex2f(x2, y2);
		GL11.glEnd();
	}
}