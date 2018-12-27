package a3novice;

public class ColorPixel implements Pixel {
	//Constructor
	private double _red, _blue, _green;
	public ColorPixel(double red, double green, double blue) {
		if (red < 0 || red > 1) {
			throw new RuntimeException("Red Out of Bounds");
		} else if (blue < 0 || blue > 1) {
			throw new RuntimeException("Blue Out of Bounds");
		} else if (green < 0 || green > 1) {
			throw new RuntimeException("Green Out of Bounds");
		}
		this._green = green;
		this._blue = blue;
		this._red = red;
		
	}
	
	
	public double getGreen() {
		return _green;
	}
	
	public double getRed() {
		return _red;
	}

	public double getBlue() {
		return _blue;
	}

	public double getIntensity() {
		return .299*_red + .587*_green + .114*_blue;
	}
	
	public char getChar() {
		double intensity = this.getIntensity();
		if (intensity < .10) {
			return '#';
		} else if (intensity >= .10 && intensity < .20) {
			return 'M';
		} else if (intensity >=.20 && intensity < .30) {
			return 'X';
		} else if (intensity >= .30 && intensity <.4) {
			return 'D';
		} else if (intensity >= .4 && intensity < .5) {
			return '<';
		} else if (intensity >= .5 && intensity < .6) {
			return '>';
		} else if (intensity >= .6 && intensity < .7) {
			return 's';
		} else if (intensity >= .7 && intensity < .8) {
			return ':';
		} else if (intensity >= .8 && intensity < .9) {
			return '-';
		} 
		return ' ';
	}

}
