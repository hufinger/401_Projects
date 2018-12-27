package a4jedi;

public class GammaCorrect implements PixelTransformation {
	double _gamma;
	public GammaCorrect(double gamma) {
		if (gamma == 0) {
			throw new IllegalArgumentException("gamma is 0");
		}
		this._gamma = gamma;
	}

	public Pixel transform(Pixel p) {
		if (p == null) {
			throw new IllegalArgumentException("p is null");
		} 
		double _oldGreen, _newGreen, _oldBlue, _newBlue, _oldRed, _newRed;
		_oldGreen = p.getGreen();
		_oldBlue = p.getBlue();
		_oldRed = p.getRed();
		_newGreen = Math.pow(_oldGreen, (1 / _gamma));
		_newBlue = Math.pow(_oldBlue, (1 / _gamma));
		_newRed = Math.pow(_oldRed, (1 / _gamma));
		Pixel corrected = new ColorPixel(_newRed, _newGreen, _newBlue);
		return corrected;
	}
	
}
