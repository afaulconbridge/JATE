package jate.demo;

import javax.swing.JFrame;

import jate.TilePanel;
import jate.map.TileMap;
import jate.map.TileNestedHashMap;
import jate.tile.FileTile;
import jate.tile.Tile;

public class TilePanelDemo {

	public TilePanelDemo() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		frame.setSize(640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Tile tile = new FileTile("tile_001568", true);

		TileMap tileMap = new TileNestedHashMap(100, 100, 32, 32);
		for (int x = 0; x < 100; x++) {
			for (int y = 0; y < 100; y++) {
				tileMap.set(x, y, tile);
			}
		}

		TilePanel panel = new TilePanel(tileMap);

		frame.add(panel);

		frame.setVisible(true);

	}

}
