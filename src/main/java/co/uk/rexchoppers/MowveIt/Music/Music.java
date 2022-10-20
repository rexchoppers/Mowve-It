package co.uk.rexchoppers.MowveIt.Music;

import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Music {

	private static float currentVolume = 100;

	private static String[] songs = null;
	private static Audio song = null;

	public void initMusic() {
		songs = MusicFiles.getMusicFromDir("music/");
	}

	public void playSong() {
		randomSong();
		song.playAsMusic(1, 1, true);
	}

	private void randomSong() {
		String songName = null;
		int index = (int) (Math.random() * songs.length);
		songName = songs[index];
		try {
			song = AudioLoader.getAudio(
					"WAV",
					ResourceLoader.getResourceAsStream("music/" + songName
							+ ".wav"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String[] getSongs() {
		return songs;
	}

	public static void setSongs(String[] songs) {
		Music.songs = songs;
	}

	public static float getSongVolume() {
		return currentVolume;
	}
}
