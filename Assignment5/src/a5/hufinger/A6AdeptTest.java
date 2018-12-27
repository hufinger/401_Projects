package a5.hufinger;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import a6adept.*;
import junit.framework.Assert;

public class A6AdeptTest {
		
	static public String[] getTestNames() {
		String[] test_names = new String[2];
		
		test_names[0] = "adeptTest1";
		test_names[1] = "adeptTest2";
		return test_names;
	}
		
	@Test
	public void adeptTest1() {
		Picture p = new PictureImpl(10,5);
		int i = 10;
		Iterator<Pixel> jump = p.sample(1, 2, 3, 1); 
		int o = 1;
		while (jump.hasNext()) {
			jump.next();
			o++;
		}
		assertEquals("Sample is not counting correctly", o, i);
	}
	
	@Test
	
	public void adeptTest2() {
		Picture p = new PictureImpl(10,5);
		int i = 10;
		try {
		Iterator<Pixel> a = p.sample(-1, 2, 1, 1);
			fail("Sample cannot have negative arguments");
		} catch (RuntimeException e) {
			} catch(Exception e) {
				fail("Sample cannot have negative arguments");
		}
	}
}
