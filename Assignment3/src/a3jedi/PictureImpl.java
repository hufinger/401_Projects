package a3jedi;

public class PictureImpl implements Picture {
	private int width, height;
	private Pixel[][] creation;
	public PictureImpl(int width, int height) {
		if (width < 0 || height < 0) {
			throw new RuntimeException("Width or Height < 0");
		}
		this.width = width;
		this.height = height;
		creation = new Pixel[width][height];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				creation[i][j] = new GrayPixel(.5);
			}
		}
	}
	
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setPixel(int x, int y, Pixel p) {
		if (x < 0 || y < 0) {
			throw new RuntimeException("Coordinates out of bounds");
		} else if (x > height || y > width) {
			throw new RuntimeException("Coordinates out of bounds");
		} if (p == null) {
			throw new RuntimeException("Coordinates out of bounds");
		}
		creation[x][y] = p;
	}

	public Pixel getPixel(int x, int y) {
		if (x < 0 || y < 0) {
			throw new RuntimeException("Coordinates out of bounds");
		} 
		return creation[x][y];
	}

	public int countRange(double low, double high) {
		if (low < 0 || high < 0) {
			throw new RuntimeException("Input out of bounds");
		}
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

	public void print() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				System.out.print(creation[i][j]);
			}
			System.out.println("");
		}
	}


	public double unequalPixelRatio(Picture p) {
		if (p == null) {
			throw new RuntimeException("Picture is Null");
		} else if (p.getWidth() != this.getWidth()) {
			throw new RuntimeException("Picture aren't the same size");
		} else if (p.getHeight() != this.getHeight()) {
			throw new RuntimeException("Picture aren't the same size");
		}
		double different = 0;
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				if(!this.creation[i][j].equals(p.getPixel(i, j))) {
					different += 1;
				}
			}
		}
		return different/(this.getWidth()*this.getHeight());
	}


	public double calculatePSNR(Picture p) {
		if (p == null) {
			throw new RuntimeException("Picture is Null");
		} else if (p.getWidth() != this.getWidth()) {
			throw new RuntimeException("Picture aren't the same size");
		} else if (p.getHeight() != this.getHeight()) {
			throw new RuntimeException("Picture aren't the same size");
		}
		double msr = 0;
		for (int i = 0; i < this.getWidth(); i++) {
			for (int j = 0; j < this.getHeight(); j++) {
				msr += Math.pow(this.getPixel(i, j).getIntensity() - p.getPixel(i, j).getIntensity(), 2);
			}
		}
		if (msr == 0) {
			throw new RuntimeException("MSE = 0");
		}
		double psnr = 20*Math.log10(1) - 10*Math.log10(msr);
		return psnr;
	}

}
