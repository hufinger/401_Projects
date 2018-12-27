package a6adept;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SamplePixelIterator implements Iterator<Pixel> {
	//Create fields for the parameters in the constructor
	private int initX, initY, dx, dy, originalX;
	private Picture source;
	//Create the constructor
	public SamplePixelIterator(Picture source, int initX, int initY,  int dx,  int dy) {
		//Declare the value of the fields
		this.initX = initX;
		this.initY = initY;
		this.dx = dx;
		this.dy = dy;
		this.source = source;
		this.originalX = initX;
	}
	//Check to make sure there is a Pixel after the current one
	public boolean hasNext() {
		if (initX < source.getWidth() && initY < source.getHeight()) {
			return true;
		} else {
			return false;
		}
	}
	//Return the next pixel
	public Pixel next() {
			if (hasNext() == false) {
				throw new NoSuchElementException("No such Pixel Exists");
			}
			//retrieve the initial Pixel
			Pixel test = source.getPixel(initX, initY);
			//Increment by dx to get the sample x values
				initX += dx;
				//When x > width of source, reset x to original value and increment y by dy
				if (initX >= source.getWidth() && initY < source.getHeight()) {
						initX = originalX;
						initY += dy;
						return test;
					}
		return test;
	}

}
