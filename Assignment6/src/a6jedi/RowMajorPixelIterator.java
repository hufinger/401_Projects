package a6jedi;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RowMajorPixelIterator implements Iterator<Pixel> {
	private Picture source;
	private int x, y;
		public RowMajorPixelIterator(Picture source) {
			this.source = source;
			x = 0;
			y = 0;
		}
	//iterate through the source
	public Iterator<Pixel> iterator() {
		Iterator<Pixel> iter = source.iterator();
		return iter;
	}
	//check to see if there is another Pixel in the source
	public boolean hasNext() {
		if (x < source.getWidth() && y < source.getHeight()) {
			return true;
		} else {
			return false;
		}
		
	}
	//return all the pixels in the source, throw exception if there isnt a next
	public Pixel next() {
		if (hasNext() == false) {
			throw new NoSuchElementException("No such Pixel Exists");
		}
		Pixel test = source.getPixel(x, y);
			x++;
			if (x >= source.getWidth() && y < source.getHeight()) {
					x = 0;
					y++;
					return test;
				}
	return test;
	}
}
