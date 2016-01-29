package jate.render;

import java.awt.Graphics2D;
import java.awt.image.VolatileImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jate.map.TileMap;
import jate.tile.Tile;

public class TileRenderer {

    private static Logger log = LoggerFactory.getLogger(TileRenderer.class);
	
	public static void render(Graphics2D gg, TileMap tileMap, int left, int top, int right, int bottom) {

    	if (gg == null) {
    		throw new IllegalArgumentException("gg must not be null");
    	}
    	if (tileMap == null) {
    		throw new IllegalArgumentException("tileMap must not be null");
    	}
    	    	
    	long startTime = System.nanoTime();
    	
    	int visibletileleft = left/tileMap.getTileWidth();
    	int visibletileright = right/tileMap.getTileWidth();
    	int visibletiletop = top/tileMap.getTileHeight();
    	int visibletilebottom = bottom/tileMap.getTileHeight();

        log.trace("Can see tiles ("+visibletileleft+","+visibletiletop+") -> ("+visibletileright+","+visibletilebottom+")");

        for (int y = visibletiletop; y <= visibletilebottom; y++) {
        	int pixelY = y*tileMap.getTileHeight();
	        for (int x = visibletileleft; x <= visibletileright; x++) {
	        	int pixelX = x*tileMap.getTileWidth();
	        	
    			Tile tile = tileMap.get(x, y);
    			if (tile != null) {
	    			VolatileImage tileImage = tile.getImage();
	    			gg.drawImage(tileImage, pixelX, pixelY, null);
    			}
    		}
    	}
    	
    	long endTime = System.nanoTime();
    	long intervalMs = (endTime-startTime)/1000000;
    	log.trace("Rendered in "+intervalMs+" ms");
	}

}
