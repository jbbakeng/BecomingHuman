package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import thestinkerbell.becominghuman.human.properties.HumanProperty;
import thestinkerbell.becominghuman.human.properties.HumanProperty.GeneralRisk;
import thestinkerbell.becominghuman.human.properties.basic.BasicHumanProperty;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskRange;

public class RiskTest {
	
	private void assertRiskFactorBoundries(RiskRange range) {
		assertFalse(range.contains(range.min-1));
		assertTrue(range.contains(range.min));
		assertTrue(range.contains(range.max/2));
		assertTrue(range.contains(range.max));
		assertFalse(range.contains(range.max+1));
	}
	
	@Test
	public void canCreateARiskFactor() {
		Risk risk = GeneralRisk.HEALTHY;
		assertNotNull(risk);
	}
	
	@Test
	public void canCheckIfValueIsContainedByRiskFactor() {
		Integer min = 0;
		Integer max = 100;
		Risk risk = GeneralRisk.HEALTHY;
		this.assertRiskFactorBoundries(new RiskRange(risk, min, max));
	}
	
	@Test
	public void canGetARiskFactorFromDefaultHumanProperty() {
		HumanProperty property = new BasicHumanProperty();
		Risk risk = property.getRisk();
		assertTrue(risk == GeneralRisk.HEALTHY);
	}
	

}
