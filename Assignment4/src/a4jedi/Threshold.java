package a4jedi;

public class Threshold implements PixelTransformation {
	double _threshold;
	public Threshold(double threshold) {
		this._threshold = threshold;
	}

	public Pixel transform(Pixel p) {
		Pixel _transformed;
		if (p.getIntensity() > _threshold) {
			_transformed = new ColorPixel(1,1,1);
			return _transformed;
		} else {
			_transformed = new ColorPixel(0,0,0);
			return _transformed;
		}
	}

}
