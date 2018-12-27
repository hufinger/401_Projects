package a6jedi;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TilePixelIterator implements Iterator<SubPicture>{
	private Picture source;
	private int maxX, maxY, x, y;
		public TilePixelIterator(Picture source, int maxX, int maxY) {
				this.source = source;
				this.maxY = maxY;
				this.maxX = maxX;
				x = 0;
				y = 0;
		}
		
	//check to see if there is another Pixel in the source
	public boolean hasNext() {
		if (x + maxX <= source.getWidth() && y + maxY <= source.getHeight()) {
			return true;
		} else {
			return false;
		}
	}
	//return all the pixels in the source, throw exception if there isnt a next
	public SubPicture next() {
		if (hasNext() == false) {
			throw new NoSuchElementException("No such Pixel Exists");
		}
		SubPicture test = source.extract(x, y, maxX, maxY);
			x += maxX;
			if (x >= source.getWidth() && y <= source.getHeight()) {
				x = 0;
				y += maxY;
				return test;
			}
			return test;
		}

}
