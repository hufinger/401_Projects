package a7;

public class MultipleObserverImpl implements MultipleObserver{

	private ROIObserver observer;
	private Region region;
	public MultipleObserverImpl(ROIObserver q, Region w) {
		observer = q;
		region = w;
	}
	
	public void notify(ObservablePicture picture, Region changed_region) {
		try{
			Region hold = region.intersect(changed_region);
			observer.notify(picture, hold);
		} catch (NoIntersectionException e) {
		}
	}
	public ROIObserver getWrappedObserver() {
		return observer;
	}
	public Region getRegion() {
		return region;
	}
}
