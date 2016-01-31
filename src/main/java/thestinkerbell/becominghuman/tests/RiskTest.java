package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import thestinkerbell.becominghuman.human.properties.HumanProperty;
import thestinkerbell.becominghuman.human.risks.GeneralRiskFactor;
import thestinkerbell.becominghuman.human.risks.RiskFactor;

public class RiskTest {
	
	@Test
	public void canCreateARiskFactor() {
		RiskFactor risk = GeneralRiskFactor.HEALTHY;
		assertNotNull(risk);
	}
	
	@Test
	public void canGetARiskFactorFromOneHumanProperty() {
		HumanProperty property = new HumanProperty();
		RiskFactor risk = property.getRiskFactor();
		assertNotNull(risk);
	}
	
	

}
