package co.uk.RandomPanda30.Utils;

import java.awt.Font;

import org.newdawn.slick.TrueTypeFont;

public class TextHandler {

	private String text;
	private String font;
	private float x;
	private float y;
	private int size;

	private TrueTypeFont ttf;

	public TextHandler (String text, float x, float y, String font, int size) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.font = font;
		this.size = size;
	}

	public void draw() {
		Font awtFont = new Font(font, Font.CENTER_BASELINE, size);
		ttf = new TrueTypeFont(awtFont, false);

		ttf.drawString(x, y, text);
	}

	public void update() {
		ttf.drawString(x, y, text);
	}
}