package co.uk.rexchoppers.MowveIt.Display;

import co.uk.rexchoppers.MowveIt.Mowve.Mowve;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Fullscreen {

	DisplayMode displayMode = null;
	DisplayMode[] modes = null;

	public void gatherDisplayModes() throws LWJGLException {
		modes = Display.getAvailableDisplayModes();
		for (int i = 0; i < modes.length; i++) {
			if (modes[i].getWidth() == Mowve.getWidth()
					&& modes[i].getHeight() == Mowve.getHeight()
					&& modes[i].isFullscreenCapable()) {
				displayMode = modes[i];
			}
		}
	}

	public void setFullscreen(boolean fullscreen) {
		try {
			gatherDisplayModes();
			Display.setFullscreen(true);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
	}
}