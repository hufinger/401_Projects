package a3jedi;

import a3jedi.Pixel;

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

	public Pixel blend(Pixel p, double weight) {
		if (weight < 0 || weight > 1) {
			throw new RuntimeException("Weight Out of Bounds");
		} else if (p == null) {
			throw new RuntimeException("Pixel is null");
		}
		double blue2, red2, green2, finalBlue, finalGreen, finalRed;
		blue2 = p.getBlue();
		green2 = p.getGreen();
		red2 = p.getRed();
		finalBlue = (this.getBlue() * weight) + (blue2*(1-weight));
		finalRed = (this.getRed() * weight) + (red2*(1-weight));
		finalGreen = (this.getGreen() * weight) + (green2*(1-weight));
		Pixel blended = new ColorPixel(finalRed,finalGreen,finalBlue);
		return blended;
	}

	public Pixel lighten(double factor) {
		if (factor > 1 || factor < 0) {
			throw new RuntimeException("Factor Out of Bounds");
		}
		Pixel white = new ColorPixel (1,1,1);
		Pixel lightened = this.blend(white,(1 - factor));
		return lightened;
	}

	public Pixel darken(double factor) {
		if (factor > 1 || factor < 0) {
			throw new RuntimeException("Factor Out of Bounds");
		}
		Pixel black = new ColorPixel(0,0,0);
		Pixel darkened = this.blend(black,(1-factor));
		return darkened;
	}

	public boolean equals(Pixel p) {
		if (p==null) {
		throw new RuntimeException("Pixel is Null");	
		}
		if (p.getBlue() - this.getBlue() < -.1 || p.getBlue() - this.getBlue() > .1) {
			return false;
		} else if (p.getRed() - this.getRed() < -.1 || p.getRed() - this.getRed() > .1) {
			return false;
		} else if (p.getGreen() - this.getGreen() < -.1 || p.getGreen() - this.getGreen() > .1) {
			return false;
		}
		return true;
	}	
}