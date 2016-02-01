package jate.map;

import java.util.HashMap;
import java.util.Map;

import jate.tile.Tile;

public class TileNestedHashMap implements TileMap {

	protected Map<Integer, Map<Integer, Tile>> map = new HashMap<>();
	
	protected final int width;
	protected final int height;
	protected final int tileWidth;
	protected final int tileHeight;
	
	public TileNestedHashMap(int width, int height, int tileWidth, int tileHeight) {
		this.width = width;
		this.height = height;
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}

	@Override
	public Tile get(int x, int y) {
		if (x < 0 || x > width) throw new IllegalArgumentException("Cannot get tile outside of x bounds ("+x+")");
		if (y < 0 || y > width) throw new IllegalArgumentException("Cannot get tile outside of y bounds ("+y+")");
		
		if (map.containsKey(x)) {
			if (map.get(x).containsKey(y)) {
				return map.get(x).get(y);
			}
		}
		return null;
	}

	@Override
	public void set(int x, int y, Tile tile) {
		if (!map.containsKey(x)) {
			map.put(x, new HashMap<>());
		}
		map.get(x).put(y, tile);
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getTileWidth() {
		return tileWidth;
	}

	@Override
	public int getTileHeight() {
		return tileHeight;
	}

}
