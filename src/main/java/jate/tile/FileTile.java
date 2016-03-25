package jate.tile;

import java.awt.image.VolatileImage;

import jate.image.ImageLoader;

public class FileTile implements Tile {

	protected final String name;
	protected final boolean solid;

	public FileTile(String name, boolean solid) {
		this.name = name;
		this.solid = solid;
	}

	@Override
	public VolatileImage getImage() {
		// reload each image each time it is used
		// the image loader will handle caching and
		// managements of volatile memory failures
		return ImageLoader.singleton.getImageVolatile(name);
	}

	public boolean getSolid() {
		return solid;
	}

}
