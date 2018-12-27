package a4adept;

public class VerticalStackPicture implements Picture {
	Picture top, bottom;
	int width, height;
	Pixel[][] creation;
	public VerticalStackPicture(Picture top, Picture bottom) {
		if (top == null || bottom == null) {
			throw new IllegalArgumentException("Picture top or bottom is null");
		} else if (top.getWidth() != bottom.getWidth()) {
			throw new IllegalArgumentException("Picture widths aren't equal");
		}
		this.top = top;
		this.bottom = bottom;
		height = bottom.getHeight()*2;
		width = bottom.getWidth();
		creation = new Pixel[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				creation[i][j] = new GrayPixel(.5);
			}
		} 
	}

	public int getWidth() {
		return bottom.getWidth();
	}

	public int getHeight() {
		return top.getHeight() + bottom.getHeight();
	}

	public void setPixel(int x, int y, Pixel p) {
		if (y >= top.getHeight()) {
			bottom.setPixel(x, y - top.getHeight(), p);
		} else {
			top.setPixel(x, y, p);
		}
	}

	public Pixel getPixel(int x, int y) {
		if (y >= top.getHeight()) {
			return bottom.getPixel(x, y - top.getHeight());
		}
		return top.getPixel(x, y);
	}

	public int countRange(double low, double high) {
		if (low < 0 || high < 0) {
			throw new IllegalArgumentException("Input out of bounds");
		}
		int range = 0;
		for (int c = 0; c < height; c++) {
			for (int v = 0; v < width; v++) {
				if (top.getPixel(v, c).getIntensity() >= low && top.getPixel(v, c).getIntensity() <= high) {
					range += 1;
				}
			}
		}
		return range;
	}

	public void print() {
		top.print();
		bottom.print();
	}

	public SubPicture extract(int xOffset, int yOffset, int width, int height) {
		SubPicture sub = new SubPictureImpl(this, xOffset, yOffset, width, height);
		return sub;
	}

}
