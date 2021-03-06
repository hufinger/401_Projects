package a3adept;

public interface Pixel {

	public double getGreen();
	public double getRed();
	public double getBlue();
	public double getIntensity();
	public char getChar();
	public Pixel blend(Pixel p, double weight);
	public Pixel lighten(double factor);
	public Pixel darken(double factor);
	public boolean equals(Pixel p);
	
	}

