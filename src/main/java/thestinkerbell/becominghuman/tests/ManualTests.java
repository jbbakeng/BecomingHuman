package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.*;

import org.junit.Test;

public class ManualTests {

	@Test
	public void testRandomNumber() {
		//testing that the logic in BreathingInfluence is correct
		for(int i =0; i<12096000*10; i++) {
			double random = Math.random();
			if(random <= 8.2e-8)
				System.out.println(random);
		}
	}

}
