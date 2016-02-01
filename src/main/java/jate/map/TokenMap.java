package jate.map;

import java.util.Set;

import jate.token.Token;

public interface TokenMap {

	public double getX(Token t);
	public double getY(Token t);
	
	public Set<Token> getTokens();
	
	public void add(double x, double y, Token t);
	public void remove(Token t);
	

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
