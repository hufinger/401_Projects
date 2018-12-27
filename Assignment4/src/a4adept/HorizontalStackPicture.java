package a4adept;

public class HorizontalStackPicture implements Picture {
	Picture left, right;
	Pixel[][] creation;
	int height, width;
	public HorizontalStackPicture(Picture left, Picture right) {
		if(left==null || right==null) {
			throw new IllegalArgumentException("Picture left or right is null");
		} else if (left.getHeight() != right.getHeight()) {
			throw new IllegalArgumentException("Picture heights aren't equal");
		}
		this.left = left;
		this.right = right;
		height = left.getHeight();
		width = left.getWidth()*2;
		creation = new Pixel[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				creation[i][j] = new GrayPixel(.5);
			}
		} 
	}

	public int getWidth() {
		return right.getWidth() + left.getWidth();
	}

	public int getHeight() {
		return right.getHeight();
	}

	public void setPixel(int x, int y, Pixel p) {
		if (x >= left.getWidth()) {
			right.setPixel(x - left.getWidth(), y, p);
		} else {
			left.setPixel(x, y, p);
		}
	}

	public Pixel getPixel(int x, int y) {
		if (x >= left.getWidth()) {
			return right.getPixel(x - left.getWidth(), y);
		}
		return left.getPixel(x, y);
	}

	public int countRange(double low, double high) {
		if (low < 0 || high < 0) {
			throw new IllegalArgumentException("Input out of bounds");
		}
		int range = 0;
		for (int c = 0; c < height; c++) {
			for (int v = 0; v < width; v++) {
				if (left.getPixel(v, c).getIntensity() >= low && left.getPixel(v, c).getIntensity() <= high) {
					range += 1;
				}
			}
		}
		return range;
	}

	public void print() {
		left.print();
		right.print();
	}

	public SubPicture extract(int xOffset, int yOffset, int width, int height) {
		SubPicture sub = new SubPictureImpl(this, xOffset, yOffset, width, height);
		return sub;
	}
	
	}
