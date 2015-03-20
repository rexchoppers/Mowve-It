package co.uk.RandomPanda30.MowveIt.Tiles;

public enum TileType {

	GRASS("grass", true), DIRT("red", false), WATER("water", false), ORNAMENT(
			"orn1", false), FLOWER("flower", false), PLAYER("ene", false), GNOME(
			"gnome", false);

	String textureName;
	boolean build;

	TileType(String textureName, boolean build) {
		this.textureName = textureName;
		this.build = build;
	}

}
