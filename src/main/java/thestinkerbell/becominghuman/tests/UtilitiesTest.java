package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import thestinkerbell.becominghuman.utilities.Utilities;

public class UtilitiesTest {

	@Test
	public void verifyTickAndChanceConversions() {
		double ticks_in_a_week = 12096000;
		assertTrue(Utilities.weeks_to_ticks(1) == ticks_in_a_week);
		
		double chance_of_happening_once_a_week = 8.267196e-8;
		assertTrue((chance_of_happening_once_a_week - Utilities.getChanceOfHappening(Utilities.weeks_to_ticks(1))) < 1e-14);
	}
	
	@Test
	public void ticksPerYear() {
		double years_to_ticks = 1.7223324514991182E-9;
		assertTrue(Utilities.ticks_to_years(1) == years_to_ticks);
	}

}
