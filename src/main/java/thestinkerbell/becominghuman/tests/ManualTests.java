package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

public class ManualTests {

	@Ignore
	@Test
	public void testRandomNumber() {
		//testing that the logic in BreathingInfluence is correct
		for(int i =0; i<12096000*10; i++) {
			double random = Math.random();
			if(random <= 8.2e-8)
				System.out.println(random);
		}
	}
	
	@Test
	public void testContains() {
		String test1 = "s";
		String test2 = "Test";
		String test3 = "TesT";
		String test4 = "TESTt";
		
		ArrayList<String> list = new ArrayList<String>();
		list.add(test1);
		
		assertTrue(list.contains(test1));
		assertFalse(list.contains(test2));
		assertFalse(list.contains(test3));
		assertFalse(list.contains(test4));
	}
	
	@Test
	public void testStringContains() {
		String test1 = "Raw";
		String test2 = "raw";
		String test3 = "testRaw";
		String test4 = "testRawtest";
		
		assertTrue(test1.contains(test1));
		assertFalse(test2.contains(test1));
		assertTrue(test3.contains(test1));
		assertTrue(test4.contains(test1));
	}

}
