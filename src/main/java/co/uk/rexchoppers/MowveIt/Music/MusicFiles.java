package co.uk.rexchoppers.MowveIt.Music;

import java.io.File;
import java.util.ArrayList;

public class MusicFiles {

	public static String[] getMusicFromDir(String directory) {
		String[] songsList = null;
		ArrayList<String> songs = new ArrayList<String>();
		File dir = new File(directory);
		File[] dirListing = dir.listFiles();
		if (dirListing != null) {
			for (File child : dirListing) {
				songs.add(child.toString().replaceAll(".wav", "")
						.replaceAll("\\\\", "").replaceAll("music", "")
						.replaceAll("/", ""));
			}
		}
		songsList = songs.toArray(new String[songs.size()]);
		return songsList;
	}
}