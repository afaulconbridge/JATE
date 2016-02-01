package jate.token;

import java.awt.image.VolatileImage;
import java.util.Objects;

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
	
	@Override 
	public boolean equals(Object other) { 
		if (other == this) { 
			return true; 
		} 
		if (other == null || !(other instanceof FileToken)) { 
			return false; 
		}
		FileToken otherToken = (FileToken) other;
		return Objects.equals(this.name, otherToken.name);
	}
	
	@Override
    public int hashCode() {
		return Objects.hash(name);
	}

}
