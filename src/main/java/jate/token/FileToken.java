package jate.token;

import java.awt.image.VolatileImage;

import jate.image.ImageLoader;

public class FileToken implements Token {
	
	protected String name;
	
	public FileToken(String name) {
		this.name = name;
	}

	@Override
	public VolatileImage getImage() {
		//reload each image each time it is used
		//the image loader will handle caching and 
		//managements of volatile memory failures
		return ImageLoader.singleton.getImageVolatile(name);
	}


}
