package a7;

import java.util.ArrayList;
import java.util.List;

public class ObservablePictureImpl implements ObservablePicture {
	private Picture source;
	private ArrayList<MultipleObserver> _moi;
	private Region suspended_info;
	private boolean suspended;
	public ObservablePictureImpl(Picture p) {
		source = p;
		_moi = new ArrayList<MultipleObserver>();
		suspended_info = null;
		suspended = false;
	}
	
	public int getWidth() {
		return source.getWidth();
	}
	
	public int getHeight() {
		return source.getHeight();
	}
	
	public Pixel getPixel(int x, int y) {
		return source.getPixel(x, y);
	}
	
	public Pixel getPixel(Coordinate c) {
		return source.getPixel(c);
	}
	
	public void setPixel(int x, int y, Pixel a) {
		source.setPixel(x,y,a);	
		if(suspended != true) {
			for (MultipleObserver i : _moi) {
				i.notify(this, new RegionImpl(new Coordinate(x,y), new Coordinate(x,y)));
			} 
		} else {
			Region suspendedR = new RegionImpl(new Coordinate (x,y), new Coordinate(x,y));
			suspended_info = suspendedR.union(suspended_info);
			
		}
	}
	
	public void setPixel(Coordinate c, Pixel a) {
		this.setPixel(c.getX(), c.getY(), a);	
	}

	public SubPicture extract(int xoff, int yoff, int width, int height) {
		return source.extract(xoff, yoff, width, height);
	}
	
	public SubPicture extract(Coordinate a, Coordinate b) {
		return source.extract(a, b);
	}
	//have the observer observe the region r
	public void registerROIObserver(ROIObserver observer, Region r) {
		_moi.add(new MultipleObserverImpl(observer,r));
	}
	//unregister a region from the observer
	public void unregisterROIObservers(Region r) {
		ArrayList<MultipleObserver> removedmoi = new ArrayList<MultipleObserver>();
			for (int b = 0; b < _moi.size(); b++) {
				try {
					_moi.get(b).getRegion().intersect(r);
				} catch (NoIntersectionException e) {
					removedmoi.add(_moi.get(b));
				}
			}
		System.out.print(removedmoi.size());
		_moi = removedmoi;
	}
	//Unregister a observers from the list
	public void unregisterROIObserver(ROIObserver observer) {
		ArrayList<MultipleObserver> removedob = new ArrayList<MultipleObserver>();
		for(MultipleObserver i : _moi) {
			if (i.getWrappedObserver() != observer) {
				removedob.add(i);
			}
		}
		_moi = removedob;
	}
	//return an array of ROIObservers
	public ROIObserver[] findROIObservers(Region r) {
		int roisize = 0;
		for (MultipleObserver i : _moi) {
			try {
				i.getRegion().intersect(r);
				roisize++;
			} catch (NoIntersectionException e) {
				continue;
			}
		}
		ROIObserver[] roiobserv = new ROIObserver[roisize];
		int counter = 0;
		for (MultipleObserver k : _moi) {
			try {
				k.getRegion().intersect(r);
				roiobserv[counter] = k.getWrappedObserver();
				counter++;
			} catch (NoIntersectionException e) {
				continue;
			}
		}
		return roiobserv;
	}
	//stop notification of observers
	public void suspendObservable() {
		suspended = true;
	}
	//resume notification and notify observers of changed info
	public void resumeObservable() {
		if (suspended_info != null) {
			for (MultipleObserver m : _moi) {
				m.notify(this,suspended_info);
			}
		}
		suspended = false;
		suspended_info = null;
	}	
}
