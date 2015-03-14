package co.uk.RandomPanda30.Utils;

import org.lwjgl.Sys;

public class Clock {

	private static boolean paused = false;
	public static long lastFrame, totalTime;

	public static float d = 0;
	public static float multiplier = 1;

	public static long getTime() {
		return Sys.getTime() * 1000 / Sys.getTimerResolution();
	}

	public static float getDelta() {
		long currentTime = Sys.getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = Sys.getTime();
		return delta * 0.01f;
	}

	public static float delta() {
		if (paused) {
			return 0;
		} else {
			return d * multiplier;
		}
	}

	public static float totalTime() {
		return totalTime;
	}

	public static float multiplier() {
		return multiplier;
	}

	public static void update() {
		d = getDelta();
		totalTime += d;
	}

	public static void changeMultiplier(int change) {
		if (multiplier + change < -1 && multiplier + change > 7) {

		} else {
			multiplier += change;
		}
	}

	public static void pause() {
		if (paused) {
			paused = false;
		} else {
			paused = true;
		}
	}
}