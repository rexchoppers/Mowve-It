package co.uk.RandomPanda30.MowveIt.Map;

import co.uk.RandomPanda30.MowveIt.Tiles.Tile;
import co.uk.RandomPanda30.MowveIt.Tiles.TileType;

public class Map {

	public Tile[][] map;

	public Map () {
		map = new Tile[16][15];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.GRASS);
			}
		}
	}

	public void setTile(int x, int y, TileType type) {
		map[x][y] = new Tile(x * 64, y * 64, 64, 64, type);
	}

	public Tile getTile(int x, int y) {
		return map[x][y];
	}

	public Map (int[][] newMap) {
		map = new Tile[16][15];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				switch (newMap[j][i]) {
				case 0:
					map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.GRASS);
					break;
				case 1:
					map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.DIRT);
					break;
				case 2:
					map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.WATER);
					break;
				case 3:
					map[i][j] = new Tile(i * 64, j * 64, 64, 64,
							TileType.ORNAMENT);
					break;
				case 4:
					map[i][j] = new Tile(i * 64, j * 64, 64, 64,
							TileType.FLOWER);
					break;
				case 5:
					map[i][j] = new Tile(i * 64, j * 64, 64, 64,
							TileType.PLAYER);
					break;
				case 6:
					map[i][j] = new Tile(i * 64, j * 64, 64, 64, TileType.GNOME);
				}
			}
		}
	}

	public void draw() {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				Tile t = map[i][j];
				t.draw();
			}
		}
	}
}
