package jate;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.VolatileImage;

import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jate.map.TileMap;
import jate.tile.Tile;

public class TilePanel extends JPanel {

	private static final long serialVersionUID = -419684051431254556L;

	protected TileMap tileMap = null;
	
    private Logger log = LoggerFactory.getLogger(getClass());
	
	public TilePanel(TileMap tileMap) {
		super();

		//mark as opaque
        this.setOpaque(true);
        
		this.tileMap = tileMap;
	}

	public TileMap getTileMap() {
		return tileMap;
	}

	@Override
    protected void paintComponent(Graphics g) {
    	Graphics2D gg = (Graphics2D) g;
    	
    	if (tileMap == null) {
    		return;
    	}
    	
    	long startTime = System.nanoTime();
    	
    	//work out how many tiles can be seen
    	int tileHorizontalCount = (getWidth()/tileMap.getTileWidth()) +1;
    	int tileVerticalCount = (getHeight()/tileMap.getTileHeight()) +1;
    	
    	for (int tileX = 0; tileX < tileHorizontalCount ; tileX++) {
    		for (int tileY = 0; tileY < tileVerticalCount ; tileY++) {
    			int pixelX = tileX*tileMap.getTileWidth();
    			int pixelY = tileY*tileMap.getTileHeight();
    			
    			Tile tile = tileMap.get(tileX, tileY);
    			if (tile != null) {
	    			VolatileImage tileImage = tile.getImage();
	    			gg.drawImage(tileImage, pixelX, pixelY, null);
    			}
    		}
    	}
    	
    	long endTime = System.nanoTime();
    	long intervalMs = (endTime-startTime)/1000;
    	log.info("Rendered in "+intervalMs+" ms");
	}
	
}
