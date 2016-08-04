package jate;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jate.map.TokenMap;
import jate.render.TokenRenderer;

public class TokenPanel extends JPanel {

	private static final long serialVersionUID = -419684051431254556L;

	protected TokenMap tokenMap = null;
	public final int unitWidth;
	public final int unitHeight;

	private Logger log = LoggerFactory.getLogger(getClass());

	public TokenPanel(TokenMap tokenMap, int unitWidth, int unitHeight) {
		super();
		
		this.unitHeight = unitHeight;
		this.unitWidth = unitWidth;

		// mark as transparent
		this.setOpaque(false);

		this.tokenMap = tokenMap;
	}

	public TokenMap getTokenMap() {
		return tokenMap;
	}

	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D gg = (Graphics2D) g;

		if (tokenMap == null) {
			return;
		}

		TokenRenderer.render(gg, tokenMap, 0, 0, getWidth(), getHeight(), unitWidth, unitHeight);
	}

}
