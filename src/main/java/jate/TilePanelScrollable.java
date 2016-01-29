package jate;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.Scrollable;
import javax.swing.SwingConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jate.map.TileMap;
import jate.render.TileRenderer;

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
    	Rectangle r = getVisibleRect();
    	TileRenderer.render(gg, tileMap, r.x, r.y , r.x+r.width, r.y+r.height);
	}

}
