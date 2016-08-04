package jate.render;

import java.awt.Graphics2D;
import java.awt.image.VolatileImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jate.map.TileMap;
import jate.tile.Tile;

public class TileRenderer {

	private static Logger log = LoggerFactory.getLogger(TileRenderer.class);

	/**
	 * Draws part of tile map using a Graphics2D object.
	 * 
	 * 
	 * @param gg Graphics2D to use as destination
	 * @param tileMap TileMap to use as source
	 * @param left left edge of TileMap (in pixels)
	 * @param top top edge of TileMap (in pixels)
	 * @param right right edge of TileMap (in pixels)
	 * @param bottom bottom edge of TileMap (in pixels)
	 * @param tileWidth width of each tile (in pixels)
	 * @param tileHeight height of each tile (in pixels)
	 */
	public static void render(Graphics2D gg, TileMap tileMap, int left, int top, int right, int bottom, int tileWidth, int tileHeight) {

		if (gg == null) {
			throw new IllegalArgumentException("gg must not be null");
		}
		if (tileMap == null) {
			throw new IllegalArgumentException("tileMap must not be null");
		}

		long startTime = System.nanoTime();

		int visibletileleft = left / tileWidth;
		int visibletileright = right / tileWidth;
		int visibletiletop = top / tileHeight;
		int visibletilebottom = bottom / tileHeight;

		log.trace("Can see tiles (" + visibletileleft + "," + visibletiletop + ") -> (" + visibletileright + ","
				+ visibletilebottom + ")");

		for (int y = visibletiletop; y <= visibletilebottom; y++) {
			int pixelY = y * tileHeight;
			for (int x = visibletileleft; x <= visibletileright; x++) {
				int pixelX = x * tileWidth;

				Tile tile = tileMap.get(x, y);
				if (tile != null) {
					VolatileImage tileImage = tile.getImage();
					gg.drawImage(tileImage, pixelX, pixelY, null);
				}
			}
		}

		long endTime = System.nanoTime();
		long intervalMs = (endTime - startTime) / 1000000;
		log.trace("Rendered in " + intervalMs + " ms");
	}

}
