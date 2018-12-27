package a7;

public interface MultipleObserver extends ROIObserver {

	public Region getRegion();
	public ROIObserver getWrappedObserver();
	

}
