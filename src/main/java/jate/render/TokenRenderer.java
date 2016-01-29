package jate.render;

import java.awt.Graphics2D;
import java.awt.image.VolatileImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jate.map.TileMap;
import jate.map.TokenMap;
import jate.token.Token;

public class TokenRenderer {

    private static Logger log = LoggerFactory.getLogger(TokenRenderer.class);
	
	public static void render(Graphics2D gg,TokenMap tokenMap, int left, int top, int right, int bottom) {

    	if (gg == null) {
    		throw new IllegalArgumentException("gg must not be null");
    	}
    	if (tokenMap == null) {
    		throw new IllegalArgumentException("tileMap must not be null");
    	}
    	    	
    	long startTime = System.nanoTime();
    	    	
    	//TODO make this more efficient by storing the tokens in a better data structure - TreeMap?
    	
    	for (Token t : tokenMap.getTokens()){
    		double x = tokenMap.getX(t);
			double y = tokenMap.getY(t);
			int pixelX = (int) (x*tokenMap.getUnitWidth());
			int pixelY = (int) (y*tokenMap.getUnitHeight());
    		if (pixelX >= left && pixelX <= right) { //TODO take radius/size into account
    			if (pixelY >= top && pixelY <= bottom) {
	    			VolatileImage tokenImage = t.getImage();
	    			gg.drawImage(tokenImage, pixelX, pixelY, null);
    			}
    		}
    	}
    	
    	long endTime = System.nanoTime();
    	long intervalMs = (endTime-startTime)/1000000;
    	log.trace("Rendered in "+intervalMs+" ms");
    	
	}
}
