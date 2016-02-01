package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import thestinkerbell.becominghuman.human.properties.HumanProperty;
import thestinkerbell.becominghuman.human.properties.HumanProperty.GeneralRisk;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskFactor;

public class RiskTest {
	
	private void assertRiskFactorBoundries(Risk risk, Integer max, Integer min) {
		RiskFactor risk_factor = new RiskFactor(risk, min, max);
		assertFalse(risk_factor.contains(min-1));
		assertTrue(risk_factor.contains(min));
		assertTrue(risk_factor.contains(max/2));
		assertTrue(risk_factor.contains(max));
		assertFalse(risk_factor.contains(max+1));
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
		this.assertRiskFactorBoundries(risk, min, max);
	}
	
	@Test
	public void canGetARiskFactorFromDefaultHumanProperty() {
		HumanProperty property = new HumanProperty();
		Risk risk = property.getRisk();
		assertTrue(risk == GeneralRisk.HEALTHY);
	}

}
