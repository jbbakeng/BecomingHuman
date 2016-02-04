package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import thestinkerbell.becominghuman.human.conditions.AnalyseRisk;
import thestinkerbell.becominghuman.human.properties.HumanProperty;
import thestinkerbell.becominghuman.human.properties.HumanProperty.GeneralRisk;
import thestinkerbell.becominghuman.human.properties.basic.AgeBasicHumanProperty.AgeRisk;
import thestinkerbell.becominghuman.human.properties.basic.BasicHumanProperty;
import thestinkerbell.becominghuman.human.risks.DoubleRiskRange;
import thestinkerbell.becominghuman.human.risks.PairDoubleRiskRange;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskRange;
import thestinkerbell.becominghuman.utilities.Pair;

public class RiskTest {
	
	private void assertRiskFactorBoundries(RiskRange range, Double min, Double max) {
		assertFalse(range.contains(min-1));
		assertTrue(range.contains(min));
		assertTrue(range.contains(max/2));
		assertTrue(range.contains(max));
		assertFalse(range.contains(max+1));
	}
	
	@Test
	public void canCreateARiskFactor() {
		Risk risk = GeneralRisk.HEALTHY;
		assertNotNull(risk);
	}
	
	@Test
	public void canCheckIfValueIsContainedByRiskFactor() {
		Double min = 0.0;
		Double max = 100.0;
		Risk risk = GeneralRisk.HEALTHY;
		this.assertRiskFactorBoundries(new DoubleRiskRange(risk, min, max), min, max);
	}
	
	@Test
	public void canGetARiskFromDefaultHumanProperty() {
		HumanProperty property = new BasicHumanProperty();
		Risk risk = property.getRisk();
		assertTrue(risk == GeneralRisk.HEALTHY);
	}
	
	@Test
	public void canCreateRiskRangesUsingDouble() {
		Double min = 0.0;
		Double max = 1.0;
		RiskRange risk_range = new DoubleRiskRange(GeneralRisk.HEALTHY, min, max);
		assertTrue(risk_range.contains(0.0));
	}
	
	@Test
	public void canCreateRiskRangesUsingPairDouble() {
		Pair<Double> min = new Pair<Double>(0.0, 0.0);
		Pair<Double> max = new Pair<Double>(1.0, 1.0);
		Pair<Double> shouldContain = new Pair<Double>(0.5, 0.5);
		Pair<Double> shoulNotContain = new Pair<Double>(2.0, 2.0);
		RiskRange risk_range = new PairDoubleRiskRange(GeneralRisk.HEALTHY, min, max);
		assertTrue(risk_range.contains(shouldContain));
		assertFalse(risk_range.contains(shoulNotContain));
	}
	
	@Test
	public void risksCanBeCompared() {
		AgeRisk age_risk1 = AgeRisk.AGE_ADOLESCENCE;
		AgeRisk age_risk2 = AgeRisk.AGE_EARLYADULTHOOD;
		
		assertTrue(AnalyseRisk.greaterThan(age_risk1, age_risk2));
	}
	

}
