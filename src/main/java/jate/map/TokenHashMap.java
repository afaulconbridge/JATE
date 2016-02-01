package jate.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jate.token.Token;

public class TokenHashMap implements TokenMap {

	
	protected final Map<Token, double[]> map = new HashMap<>();
	protected final int unitWidth;
	protected final int unitHeight;
	
	public TokenHashMap(int unitWidth, int unitHeight) {
		this.unitWidth = unitWidth;
		this.unitHeight = unitHeight;
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
	public double getX(Token t) {
		return map.get(t)[0];
	}

	@Override
	public double getY(Token t) {
		return map.get(t)[1];
	}

	@Override
	public Set<Token> getTokens() {
		return Collections.unmodifiableSet(map.keySet());
	}

	@Override
	public void add(double x, double y, Token t) {
		double[] xy = new double[2];
		xy[0] = x;
		xy[1] = y;
		map.put(t, xy);
	}

	@Override
	public void remove(Token t) {
		map.remove(t);
	}

}
