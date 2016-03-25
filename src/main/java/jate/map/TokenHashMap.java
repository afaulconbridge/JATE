package jate.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import jate.token.FileToken;
import jate.token.Token;
import jate.util.XY;

public class TokenHashMap implements TokenMap {

	protected final Map<Token, Set<XY>> forwardMap = new HashMap<>();
	protected final NavigableMap<XY, Token> reverseMap = new TreeMap<>();

	protected final int width;
	protected final int height;

	protected final int unitWidth;
	protected final int unitHeight;

	public TokenHashMap(int width, int height, int unitWidth, int unitHeight) {
		this.width = width;
		this.height = height;
		this.unitWidth = unitWidth;
		this.unitHeight = unitHeight;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getUnitWidth() {
		return unitWidth;
	}

	@Override
	public int getUnitHeight() {
		return unitHeight;
	}

	@Override
	public Set<Token> getTokens() {
		return Collections.unmodifiableSet(forwardMap.keySet());
	}

	@Override
	public void add(float x, float y, Token t) {
		XY xy = new XY(x, y);
		add(xy, t);
	}

	@Override
	public void add(XY xy, Token t) {
		if (!forwardMap.containsKey(t)) {
			forwardMap.put(t, new HashSet<>());
		}
		forwardMap.get(t).add(xy);
		reverseMap.put(xy, t);
	}

	@Override
	public void remove(float x, float y, Token t) {
		XY xy = new XY(x, y);
		remove(xy, t);
	}

	@Override
	public void remove(XY xy, Token t) {
		forwardMap.get(t).remove(xy);
		if (forwardMap.get(t).size() == 0) {
			forwardMap.remove(t);
		}
		reverseMap.remove(xy);
	}

	@Override
	public Set<XY> getXYs(Token t) {
		return Collections.unmodifiableSet(forwardMap.get(t));
	}

	@Override
	public Map<XY, Token> getTokensBetween(float left, float top, float right, float bottom) {
		XY leftTop = new XY(left, top);
		XY rightBottom = new XY(right, bottom);
		return getTokensBetween(leftTop, rightBottom);
	}

	@Override
	public Map<XY, Token> getTokensBetween(XY leftTop, XY rightBottom) {
		return reverseMap.subMap(leftTop, true, rightBottom, true);
	}

}
