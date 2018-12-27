package a6jedi;

import java.util.Iterator;

public class SubPictureImpl implements SubPicture {
	//Create variables to hold the values in SubPictureImpl
	int xOffset, yOffset, width, height;
	Picture source;
	//Constructor of SubPictureImpl
	public SubPictureImpl(Picture source, int xOffset, int yOffset, int width, int height) {
		//Throw IllegalArgument Exceptions if the parameters of the Arguments aren't met
		if(source == null) {
			throw new IllegalArgumentException("Source is null");
		} else if (width > source.getWidth() || height > source.getHeight() || xOffset > source.getWidth() || yOffset > source.getHeight()) {
			throw new IllegalArgumentException("Height, Width, or offset out of bounds");
		} else if (width + xOffset > source.getWidth() || height + yOffset > source.getHeight()) {
			throw new IllegalArgumentException("Offset + Size out of bounds");
		}
		//Save the values of the arguments
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.width = width;
		this.height = height;
		this.source = source;
	}
	//Create the methods from the interfaces
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	//Get and Set the pixels from the PictureImpl Class
	public void setPixel(int x, int y, Pixel p) {
		source.setPixel(x + xOffset, y + yOffset, p);
	}

	public Pixel getPixel(int x, int y) {
		return source.getPixel(x + xOffset, y + yOffset);
	}
	//Count the range from this class
	public int countRange(double low, double high) {
		if (low < 0 || high < 0) {
			throw new IllegalArgumentException("Input out of bounds");
		}
		int range = 0;
		for (int c = 0; c < height; c++) {
			for (int v = 0; v < width; v++) {
				if (source.getPixel(v, c).getIntensity() >= low && source.getPixel(v, c).getIntensity() <= high) {
					range += 1;
				}
			}
		}
		return range;
	}
	
	//print from the source
	public void print() {
		source.print();
	}
	// Return the original picture
	public Picture getSource() {
		return source;
	}
	//How far offset from the original x is the subpicture
	public int getXOffset() {
		return xOffset;
	}
	//How far offset from the original y is the subpicture
	public int getYOffset() {
		return yOffset;
	}
	//Extract a subpicture
	public SubPicture extract(int xOffset, int yOffset, int width, int height) {
		SubPicture sub = new SubPictureImpl(this, xOffset, yOffset, width, height);
		return sub;
	}
	//Set with a coordinate as x and y
	public void setPixel(Coordinate c, Pixel p) {
		source.setPixel(c.getX() + xOffset, c.getY() + yOffset, p);
	}
	//Get with a coordinate as x and y
	public Pixel getPixel(Coordinate c) {
		return source.getPixel(c.getX()+xOffset, c.getY()+yOffset);
	}
	
	public SubPicture extract(Coordinate corner_a, Coordinate corner_b) {
		int xOffset, yOffset;
		//check for the smaller x value to use as xoffset
		if (corner_a.getX() < corner_b.getX()) {
			xOffset = corner_a.getX();
		} else {
			xOffset = corner_b.getX();
		} 
		//check for the smaller y value to use as yoffset
		if (corner_a.getY() < corner_b.getY()) {
			yOffset = corner_a.getY();
		} else {
			yOffset = corner_b.getY();
		}
		
		SubPicture wCoordinates = new SubPictureImpl(this, xOffset, yOffset,
				width - xOffset, height - yOffset);
		
		return wCoordinates;
	}
	
	public Iterator<Pixel> iterator() {
		Iterator<Pixel> n = new RowMajorPixelIterator(this); 
		return n;
	}
	//
	public Iterator<Pixel> sample(int init_x, int init_y, int dx, int dy) {
		Iterator<Pixel> _sample = new SamplePixelIterator(this, init_x, init_y, dx, dy);
		return _sample;
	}
	//
	public Iterator<SubPicture> window(int window_width, int window_height) {
		Iterator<SubPicture> _window = new WindowPixelIterator(this, window_width, window_height);
		return _window;
	}
	public Iterator<SubPicture> tile(int tile_width, int tile_height) {
		Iterator<SubPicture> _tile = new TilePixelIterator(this, tile_width, tile_height);
		return _tile;
	}
	public Iterator<Pixel> zigzag() {
		Iterator<Pixel> _zigzag = new ZigzagPixelIterator(this);
		return _zigzag;
	}

}
