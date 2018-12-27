package a7;

public class RegionImpl implements Region {
	private Coordinate a, b;
	public RegionImpl(Coordinate a, Coordinate b) {
		this.a = a;
		this.b = b;
	}
	//get smallest x and y values
	public Coordinate getUpperLeft() {
		Coordinate upLeft = new Coordinate(this.getLeft(), this.getTop());
		return upLeft;
		}
	//get largest x and y value
	public Coordinate getLowerRight() {
		Coordinate loRight = new Coordinate(this.getRight(), this.getBottom());
		return loRight;
	}
	//get smallest y
	public int getTop() {
		if (b.getY() < a.getY()) {
			return b.getY();
		} else {
			return a.getY();
		}
	}
	//get largest y
	public int getBottom() {
		if (a.getY() > b.getY()) {
			return a.getY();
		} else {
			return b.getY();
		}
	}
	//get smallest x
	public int getLeft() {
		if (a.getX() < b.getX()) {
			return a.getX();
		} else {
		return b.getX();
		}	
	}
	//get largest x
	public int getRight() {
		if (a.getX() > b.getX()) {
			return a.getX();
		} else {
			return b.getX();
		}
	}
	//create a region that is the intersection of 2 regions
	public Region intersect(Region other) throws NoIntersectionException {
		if (other == null) {
			throw new NoIntersectionException();
		} else if (other.getBottom() < this.getTop()) {
			throw new NoIntersectionException();
		} else if (other.getTop() > this.getBottom()){
			throw new NoIntersectionException();
		} else if(other.getRight() < this.getLeft()){
			throw new NoIntersectionException();
		} else if(other.getLeft() > this.getRight()) {
			throw new NoIntersectionException();
		} else {
			int minX, minY, maxX, maxY;
			minX = this.getLeft();
			minY = this.getTop();
			maxX = this.getRight();
			maxY = this.getBottom();
			if (other.getLeft() > this.getLeft()) {
				minX += (other.getLeft() - this.getLeft());
			} 
			if (other.getTop() > this.getTop()) {
				minY += (other.getTop() - this.getTop());
			}
			if (other.getRight() < this.getRight()) {
				maxX -= (this.getRight()-other.getRight());
			} 
			if (other.getBottom() < this.getBottom()) {
				maxY -= (this.getBottom() - other.getBottom());
			}
			Coordinate newUpLeft = new Coordinate(minX, minY);
			Coordinate newBotRight = new Coordinate(maxX, maxY);
			return new RegionImpl(newUpLeft,newBotRight);
		}
	}
	//join two regions together
	public Region union(Region other) {
		if(other == null) {
			return this;
		}
		int minX, minY, maxX, maxY;
		minX = this.getLeft();
		minY = this.getTop();
		maxX = this.getRight();
		maxY = this.getBottom();
		if (other.getLeft() < minX) {
			minX = other.getLeft();
		} 
		if (other.getTop() < minY) {
			minY = other.getTop();
		}
		if (other.getRight() > maxX) {
			maxX = other.getRight();
		} 
		if (other.getBottom() > maxY) {
			maxY = other.getBottom();
		}
		Coordinate newUpLeft = new Coordinate(minX, minY);
		Coordinate newBotRight = new Coordinate(maxX, maxY);
		return new RegionImpl(newUpLeft,newBotRight);
	}
}
