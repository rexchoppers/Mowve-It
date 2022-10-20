package co.uk.rexchoppers.MowveIt.Drawing;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;

public class QuadDrawing {

	public static void drawQuad(float x, float y, float width, float height) {
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x, y);
		GL11.glVertex2f(x + width, y);
		GL11.glVertex2f(x + width, y + height);
		GL11.glVertex2f(x, y + height);
		GL11.glEnd();
	}

	public static void drawTexQuad(float x, float y, float width, float height,
			Texture texture) {
		texture.bind();
		GL11.glPushMatrix();
		GL11.glTranslatef(x, y, 0);
		GL11.glRotatef(0, x, y, 0);
		GL11.glBegin(GL11.GL_QUADS);

		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(0, 0);

		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(width, 0);

		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(width, height);

		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(0, height);

		GL11.glEnd();
		GL11.glPopMatrix();
		GL11.glLoadIdentity();
	}
}