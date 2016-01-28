package jate;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.VolatileImage;

import javax.swing.Scrollable;
import javax.swing.SwingConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jate.map.TileMap;
import jate.tile.Tile;

public class TilePanelScrollable extends TilePanel implements Scrollable {

	private static final long serialVersionUID = 8495111616006504813L;

    private Logger log = LoggerFactory.getLogger(getClass());
	
	public TilePanelScrollable(TileMap tileMap) {
		super(tileMap);
		setPreferredSize(new Dimension((int)tileMap.getWidth()*tileMap.getTileWidth(),
				(int)tileMap.getHeight()*tileMap.getTileHeight()));
	}

	@Override
	public Dimension getPreferredScrollableViewportSize() {
		return getPreferredSize();
	}


	@Override
	public boolean getScrollableTracksViewportHeight() {
		return false;
	}

	@Override
	public boolean getScrollableTracksViewportWidth() {
		return false;
	}
	
	@Override
	public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
		if (orientation == SwingConstants.VERTICAL) {
			if (direction < 0) {
				//going up
				return (int) (visibleRect.getHeight()/2);
			} else {
				//assume down
				return (int) (visibleRect.getHeight()/2);
			}
		} else {
			//assume horizontal
			if (direction < 0) {
				//going left
				return (int) (visibleRect.getWidth()/2);
			} else {
				//assume right
				return (int) (visibleRect.getWidth()/2);
			}
		}
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
		return 1;
	}

	public int getVisiblTileLeft() {
		return getVisibleRect().x/tileMap.getTileWidth();
	}

	public int getVisiblTileRight() {
        int visibletileright = getVisiblTileLeft()+(getVisibleRect().width/tileMap.getTileWidth())+2;
        visibletileright = Math.min(tileMap.getWidth(), visibletileright);
        return visibletileright;		
	}

	public int getVisiblTileTop() {
		return getVisibleRect().y/tileMap.getTileHeight();
	}

	public int getVisiblTileBottom() {
		int visibletilebottom = getVisiblTileTop()+(getVisibleRect().height/tileMap.getTileHeight())+2;
        visibletilebottom = Math.min(tileMap.getHeight(), visibletilebottom);
        return visibletilebottom;
	}
	@Override
    protected void paintComponent(Graphics g) {
    	Graphics2D gg = (Graphics2D) g;
    	
    	if (tileMap == null) {
    		return;
    	}
    	
    	long startTime = System.nanoTime();
    	
    	//work out which tiles can be seen
        int visibletileleft = getVisiblTileLeft();
        int visibletileright = getVisiblTileRight();
        int visibletiletop = getVisiblTileTop();
        int visibletilebottom = getVisiblTileBottom();
    	
        log.info("Can see tiles ("+visibletileleft+","+visibletiletop+") -> ("+visibletileright+","+visibletilebottom+")");

        for (int y = visibletiletop; y < visibletilebottom; y++) {
        	int pixelY = y*tileMap.getTileHeight();
	        for (int x = visibletileleft; x < visibletileright; x++) {
	        	int pixelX = x*tileMap.getTileWidth();
	        	
    			Tile tile = tileMap.get(x, y);
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
