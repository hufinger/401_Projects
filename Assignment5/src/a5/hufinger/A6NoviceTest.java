package a5.hufinger;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

import a6novice.*;

public class A6NoviceTest {
		
	static public String[] getTestNames() {
		String[] test_names = new String[3];
		
		test_names[0] = "noviceTest1";
		test_names[1] = "noviceTest2";
		test_names[2] = "noviceTest3";
		return test_names;
	}
		
	@Test
	public void noviceTest1() {
		Picture p = new PictureImpl(2,2);
		Pixel a = new ColorPixel(.1,.7,.8);
		try {
		Coordinate bad = new Coordinate (-1,-1);
		p.setPixel(bad,a);
		fail("Should have thrown a RuntimeException");
		} catch (RuntimeException e) {
		} catch(Exception e) {
				fail("Should have thrown a RuntimeException");
			}
	}
	
	@Test
	public void noviceTest2() {
		Coordinate n = new Coordinate(1,1);
		Pixel p = new GrayPixel(.5);
		Picture a = new PictureImpl(2,2);
		a.setPixel(n, p);
		Assert.assertEquals(p, a.getPixel(n));
	}
	
	@Test
	public void noviceTest3() {
		Pixel x = new ColorPixel(.1,.7,.8);
		Pixel r = new GrayPixel(.5);
		Coordinate n = new Coordinate(0,0);
		Coordinate q = new Coordinate(0,1);
		Coordinate c = new Coordinate(1,0);
		Coordinate m = new Coordinate(1,1);
		Picture w = new PictureImpl(3,3);
		w.setPixel(n,r);
		w.setPixel(q, x);
		w.setPixel(c, r);
		w.setPixel(m, x);
		SubPicture h = new SubPictureImpl(w, 0, 0, 1, 1);
		w.extract(n, m);
		Assert.assertEquals(h.getPixel(0,0), w.getPixel(n)); 
		Assert.assertEquals(h.getPixel(0,1), w.getPixel(q));
		Assert.assertEquals(h.getPixel(1,0), w.getPixel(c));
		Assert.assertEquals(h.getPixel(1,1), w.getPixel(m));
	}
}
