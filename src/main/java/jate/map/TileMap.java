package jate.map;

import jate.tile.Tile;

public interface TileMap {

	public int getWidth();

	public int getHeight();

	public Tile get(int x, int y);

	public void set(int x, int y, Tile tile);
}
