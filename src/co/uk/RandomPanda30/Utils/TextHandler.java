package co.uk.RandomPanda30.Utils;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

public class TextHandler {
	
	private String text;
	private String font;
	private float x;
	private float y;
	private Color color;
	private int size;
	
	private TrueTypeFont ttf;

	public TextHandler(String text, float x, float y, String font, int size,  Color color) {
		this.text = text;
		this.x = x;
		this.y = y;
		this.color = color;
		this.font = font;
		this.size = size;
	}
	
	public void draw() {
		// Check colour that's not working
		Color.red.bind();
		Font awtFont = new Font(font, Font.CENTER_BASELINE, size);
		ttf = new TrueTypeFont(awtFont, false);
		
		ttf.drawString(x, y, text);
	}
	
	public void update() {
		ttf.drawString(x, y, text, color);
	}	
}