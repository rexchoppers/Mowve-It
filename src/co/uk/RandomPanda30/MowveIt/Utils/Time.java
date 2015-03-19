package co.uk.RandomPanda30.MowveIt.Utils;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import co.uk.RandomPanda30.MowveIt.Mowve.Mowve;

public class Time {

	public static void checkTime() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (Calendar.getInstance().getTimeInMillis() >= Mowve.time) {
					Mowve.timeup = true;
				}
			}
		}, 20);
	}

}
