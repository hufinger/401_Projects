package a6jedi;

import java.util.Iterator;

public class PictureImpl implements Picture {
	//Creating fields for the variables passed to the Constructor
	private int width, height;
	private Pixel[][] creation;
	//Constructing a PictureImpl
	public PictureImpl(int width, int height) {
	//Checking the parameters to make sure they are valid
		if (width < 0 || height < 0) {
			throw new RuntimeException("Width or Height < 0");
		}
		//save the values from the arguments into the fields
		this.width = width;
		this.height = height;
		//create an array to hold each pixel value in the picture
		creation = new Pixel[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				creation[i][j] = new GrayPixel(.5);
			}
		}
	}
	//Create the methods from the interfaces
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setPixel(int x, int y, Pixel p) {
		//Check to make sure the x,y are in bounds
		if (x < 0 || y < 0) {
			throw new RuntimeException("Coordinates out of bounds");
		} else if (x > width || y > height) {
			throw new RuntimeException("Coordinates out of bounds");
		} if (p == null) {
			throw new RuntimeException("Coordinates out of bounds");
		}
		//Change the pixel at the specified location to p
		creation[x][y] = p;
	}

	public Pixel getPixel(int x, int y) {
		//Check to make sure the x,y are in bounds
		if (x < 0 || y < 0) {
			throw new RuntimeException("Coordinates out of bounds");
		} 
		return creation[x][y];
	}

	public int countRange(double low, double high) {
		//Check to make sure high and low are in bounds
		if (low < 0 || high < 0) {
			throw new RuntimeException("Input out of bounds");
		}
		//Create a counter for how many pixels inbetween the given intensities
		int range = 0;
		for (int c = 0; c < height; c++) {
			for (int v = 0; v < width; v++) {
				if (creation[c][v].getIntensity() >= low && creation[c][v].getIntensity() <= high) {
					range += 1;
				}
			}
		}
		return range;
	}
	//For loops to print the array
	public void print() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(creation[i][j]);
			}
			System.out.println("");
		}
	}
	//Extract a smaller picture out of the larger one
	public SubPicture extract(int xOffset, int yOffset, int width, int height) {
		SubPicture sub = new SubPictureImpl(this, xOffset, yOffset, width, height);
		return sub;
	}
	//Set Pixels with the coordinate location as your x and y
	public void setPixel(Coordinate c, Pixel p) {
		creation[c.getX()][c.getY()] = p;
	}
	//Get Pixels with the coordinate as x and y
	public Pixel getPixel(Coordinate c) {
		return creation[c.getX()][c.getY()];
	}

	//Extract with coordinates instead of ints
	public SubPicture extract(Coordinate corner_a, Coordinate corner_b) {
		int xOffset, yOffset;
		//check for the smaller x value as the offset
		if (corner_a.getX() < corner_b.getX()) {
			xOffset = corner_a.getX();
		} else {
			xOffset = corner_b.getX();
		} 
		//check for smaller y value as offset
		if (corner_a.getY() < corner_b.getY()) {
			yOffset = corner_a.getY();
		} else {
			yOffset = corner_b.getY();
		}
		SubPicture wCoordinates = new SubPictureImpl(this, xOffset, yOffset,
				width - xOffset, height - yOffset);
		
		return wCoordinates;
	}
	//Make this available to the iterator
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
	//
	public Iterator<SubPicture> tile(int tile_width, int tile_height) {
		Iterator<SubPicture> _tile = new TilePixelIterator(this, tile_width, tile_height);
		return _tile;
	}
	public Iterator<Pixel> zigzag() {
		Iterator<Pixel> _zigzag = new ZigzagPixelIterator(this);
		return _zigzag;
	}

}
