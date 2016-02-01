package jate.map;

import java.util.Map;
import java.util.Set;

import jate.token.Token;
import jate.util.XY;

public interface TokenMap {

	public int getWidth();
	public int getHeight();
	
	public Set<Token> getTokens();
	public Set<XY> getXYs(Token t);
	public Map<XY, Token> getTokensBetween(float left, float top, float right, float bottom);
	public Map<XY, Token> getTokensBetween(XY leftTop, XY rightBottom);
	
	public void add(float x, float y, Token t);
	public void add(XY xy, Token t);
	public void remove(float x, float y, Token t);
	public void remove(XY xy, Token t);
	

	/**
	 * Returns the scaling to pixels of each horizontal unit
	 * @return
	 */
	public int getUnitWidth();
	/**
	 * Returns the scaling to pixels of each vertical unit
	 * @return
	 */
	public int getUnitHeight();
}
