package a3novice;

public class GrayPixel implements Pixel {
	private double red, blue, green, intensity;
	public GrayPixel(double intensity) {
		if (intensity < 0 || intensity > 1) {
			throw new RuntimeException("Intensity Out of Bounds");
		}
		this.red = intensity;
		this.blue = intensity;
		this.green= intensity;
		this.intensity = intensity;
		
	}
	
	public double getRed() {
		return red;
	}
	
	public double getGreen() {
		return green	;
	}
	
	public double getBlue() {
		return blue;
	}
	
	public double getIntensity() {
		return intensity;
	}

	public char getChar() {
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
