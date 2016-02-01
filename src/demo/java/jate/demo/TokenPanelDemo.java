package jate.demo;

import javax.swing.JFrame;

import jate.TokenPanel;
import jate.map.TokenHashMap;
import jate.map.TokenMap;
import jate.token.FileToken;
import jate.token.Token;

public class TokenPanelDemo {

	public TokenPanelDemo() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		frame.setSize(640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Token token = new FileToken("tile_001568");
		
		TokenMap tokenMap = new TokenHashMap(100,100, 32,32);
		for (int x = 0; x < 100; x++) {
			for (int y = 0; y < 100; y++) {
				tokenMap.add(x, y, token);
			}
		}
		
		TokenPanel panel = new TokenPanel(tokenMap);
		
		frame.add(panel);
		
		frame.setVisible(true);
		
	}

}
