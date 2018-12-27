package a6jedi;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ZigzagPixelIterator implements Iterator<Pixel> {
	private Picture source;
	private int x,y = 0;
	public ZigzagPixelIterator(Picture source) {
		this.source = source;
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
			if ((x+y) % 2 == 0 ) {
				if (x == source.getWidth() - 1) {
					y++;
				} else {
					x++;
					y--;
					if (y < 0) {
						y++;
					}
				}
				
			} else if ((x+y) % 2 == 1) {
				if (y == source.getHeight() - 1) {
					x++;
				} else {
					x--;
					y++;
					if (x < 0) {
						x++;
					}
				}
			}
			return test;
		}
	}


