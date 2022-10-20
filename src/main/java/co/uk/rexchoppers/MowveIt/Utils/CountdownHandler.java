package co.uk.rexchoppers.MowveIt.Utils;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class CountdownHandler {

	private long startTime;
	private int minutes;

	private long finalTime;

	public CountdownHandler(long startingTime, int minutes) {
		this.startTime = startingTime;
		this.minutes = minutes;

		this.finalTime = startTime + (minutes * 1000 * 60 * 60);
	}

	public void start() {
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (Calendar.getInstance().getTimeInMillis() < startTime) {

				}
			}
		}, 0, 1000);
	}

}