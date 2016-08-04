package jate.render;

import java.awt.Graphics2D;
import java.awt.image.VolatileImage;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jate.map.TokenMap;
import jate.token.Token;
import jate.util.XY;

public class TokenRenderer {

	private static Logger log = LoggerFactory.getLogger(TokenRenderer.class);

	public static void render(Graphics2D gg, TokenMap tokenMap, int pixelLeft, int pixelTop, int pixelRight,
			int pixelBottom, int unitWidth, int unitHeight) {

		if (gg == null) {
			throw new IllegalArgumentException("gg must not be null");
		}
		if (tokenMap == null) {
			throw new IllegalArgumentException("tileMap must not be null");
		}

		long startTime = System.nanoTime();

		// TODO take size into account?

		float left = pixelLeft / unitWidth;
		float top = pixelTop / unitHeight;
		float right = pixelRight / unitWidth;
		float bottom = pixelBottom / unitHeight;

		log.trace("Rendering tokens within " + left + "," + top + " -> " + right + "," + bottom);

		Map<XY, Token> subTokenMap = tokenMap.getTokensBetween(left, top, right, bottom);

		for (XY xy : subTokenMap.keySet()) {
			Token t = subTokenMap.get(xy);
			float x = xy.x;
			float y = xy.y;
			int pixelX = (int) (x * unitWidth);
			int pixelY = (int) (y * unitHeight);

			log.trace("Rending token at " + pixelX + "," + pixelY);
			VolatileImage tokenImage = t.getImage();
			gg.drawImage(tokenImage, pixelX, pixelY, null);

		}

		long endTime = System.nanoTime();
		long intervalMs = (endTime - startTime) / 1000000;
		log.trace("Rendered in " + intervalMs + " ms");

	}
}
