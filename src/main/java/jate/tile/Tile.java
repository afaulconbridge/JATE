package jate.tile;

import java.awt.image.VolatileImage;

public interface Tile {

	public VolatileImage getImage();

	public boolean getSolid();
}
