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
import jate.map.TokenMap;
import jate.render.TileRenderer;
import jate.render.TokenRenderer;

public class TokenPanelScrollable extends TokenPanel implements Scrollable {

	private static final long serialVersionUID = 8495111616006504813L;

	private Logger log = LoggerFactory.getLogger(getClass());

	public TokenPanelScrollable(TokenMap tokenMap, int unitWidth, int unitHeight) {
		super(tokenMap, unitWidth, unitHeight);
		setPreferredSize(new Dimension((int) tokenMap.getWidth() * unitWidth,
				(int) tokenMap.getHeight() * unitHeight));
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
				// going up
				return (int) (visibleRect.getHeight() / 2);
			} else {
				// assume down
				return (int) (visibleRect.getHeight() / 2);
			}
		} else {
			// assume horizontal
			if (direction < 0) {
				// going left
				return (int) (visibleRect.getWidth() / 2);
			} else {
				// assume right
				return (int) (visibleRect.getWidth() / 2);
			}
		}
	}

	@Override
	public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
		return 1;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D gg = (Graphics2D) g;

		if (tokenMap == null) {
			return;
		}
		Rectangle r = getVisibleRect();
		TokenRenderer.render(gg, tokenMap, r.x, r.y, r.x + r.width, r.y + r.height, unitWidth, unitHeight);
	}

}
