package a4jedi;

import java.util.ArrayList;
import java.util.List;

public class TransformedPicture implements Picture {
	Picture _source;
	PixelTransformation _xform;
	List<points> _points = new ArrayList<points>();
	public TransformedPicture(Picture source, PixelTransformation xform) {
		if (source == null || xform == null) {
			throw new IllegalArgumentException("source or xform is null");
		}
		this._source = source;
		this._xform = xform; 
		
	}
	
	public int getWidth() {
		return _source.getWidth();
	}
	
	public int getHeight() {
		return _source.getHeight();
	}

	public void setPixel(int x, int y, Pixel p) {
		throw new UnsupportedOperationException("YOU CANT DO THAT");
	}

	public Pixel getPixel(int x, int y) {
	
		return _xform.transform(_source.getPixel(x, y));
	}

	public int countRange(double low, double high) {
		return _source.countRange(low, high);
	}

	public void print() {
		_source.print();
	}

	public SubPicture extract(int xOffset, int yOffset, int width, int height) {
		SubPicture sub = new SubPictureImpl(_source, xOffset, yOffset, width, height);
		return sub;
	}

	@Override
	public TransformedPicture transform(PixelTransformation xform) {
		TransformedPicture created = new TransformedPicture(this, xform);
		return created;
	}
}
