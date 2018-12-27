package a4jedi;

public class SubPictureImpl implements SubPicture {
	//Create variables to hold the values in SubPictureImpl
	int xOffset, yOffset, width, height;
	Pixel[][]creation;
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
		//Store a GrayPixel in the array as a baseline
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

	public void print() {
		source.print();
	}

	public Picture getSource() {
		return source;
	}

	public int getXOffset() {
		return xOffset;
	}

	public int getYOffset() {
		return yOffset;
	}

	public SubPicture extract(int xOffset, int yOffset, int width, int height) {
		SubPicture sub = new SubPictureImpl(this, xOffset, yOffset, width, height);
		return sub;
	}

	public TransformedPicture transform(PixelTransformation xform) {
		TransformedPicture created = new TransformedPicture(this, xform);
		return created;
	}

}
