package jate;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jate.map.TileMap;
import jate.render.TileRenderer;

public class TilePanel extends JPanel {

	private static final long serialVersionUID = -419684051431254556L;

	protected TileMap tileMap = null;

	private Logger log = LoggerFactory.getLogger(getClass());

	public TilePanel(TileMap tileMap) {
		super();

		// mark as opaque
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

		TileRenderer.render(gg, tileMap, 0, 0, getWidth(), getHeight());
	}

}
