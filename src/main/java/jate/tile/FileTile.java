package jate.tile;

import java.awt.image.VolatileImage;

import jate.image.ImageLoader;

public class FileTile implements Tile {

	protected static ImageLoader imageLoader = new ImageLoader();
	
	protected String name;
	
	public FileTile(String name) {
		this.name = name;
	}

	@Override
	public VolatileImage getImage() {
		//reload each image each time it is used
		//the image loader will handle caching and 
		//managements of volatile memory failures
		return imageLoader.getImageVolatile(name);
	}

}
