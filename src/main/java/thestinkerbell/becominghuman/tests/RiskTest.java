package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import thestinkerbell.becominghuman.human.properties.HumanProperty;
import thestinkerbell.becominghuman.human.properties.HumanProperty.GeneralRisk;
import thestinkerbell.becominghuman.human.properties.basic.AgeBasicHumanProperty.AgeRisk;
import thestinkerbell.becominghuman.human.properties.basic.BasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.compound.BMICompoundHumanProperty.BMIRisk;
import thestinkerbell.becominghuman.human.risks.DoubleRiskRange;
import thestinkerbell.becominghuman.human.risks.PairDoubleRiskRange;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskComparator;
import thestinkerbell.becominghuman.human.risks.RiskRange;
import thestinkerbell.becominghuman.human.risks.Risks;
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
	public void canCheckIfRisksContainsOtherRisks() {
		Risks risks1 = new Risks();
		Risks risks2 = new Risks();
		for(Risk risk : risks1) {
			assertTrue(risks2.contains(risk));
		}	
	}
	
	@Test
	public void canCompareRisks() {
		Risk risk1 = AgeRisk.AGE_INFANCY;
		Risk risk2 = AgeRisk.AGE_EARLYCHILDHOOD;
		
		assertTrue(new RiskComparator().compare(risk1, risk2) == -1);
		assertFalse(new RiskComparator().compare(risk1, risk2) >= 0);
	}
	
	@Test
	public void riskHasOrdinal() {
		Risk risk = GeneralRisk.HEALTHY;
		assertTrue(risk.ordinal() == 0);
	}
	
	@Test
	public void canGetSubsetOfRisksOfTheSameType() {
		Risk risk1 = AgeRisk.AGE_INFANCY;
		Risk risk2 = AgeRisk.AGE_EARLYCHILDHOOD;
		Risk risk3 = GeneralRisk.HEALTHY;
		Risk risk4 = BMIRisk.BMI_MODERATELYOVERWEIGHT;
		Risks risks = new Risks();
		risks.add(risk1);
		risks.add(risk2);
		risks.add(risk3);
		risks.add(risk4);
		
		Risks same_type_risks = risks.getSubsetOfRisksOfTheSameType(risk1);
		assertTrue(same_type_risks.size() == 2);
	}
	
	@Test
	public void canCheckIfRiskThatIsSmallerOrEqualIsInRisks() {
		Risk risk1 = AgeRisk.AGE_INFANCY;
		Risk risk2 = AgeRisk.AGE_EARLYCHILDHOOD;
		Risk risk3 = GeneralRisk.HEALTHY;
		Risk risk4 = BMIRisk.BMI_MODERATELYOVERWEIGHT;
		Risks risks = new Risks();
		risks.add(risk1);
		risks.add(risk2);
		risks.add(risk3);
		risks.add(risk4);
		
		assertTrue(risks.isSmallerOrEqualRiskPresentThan(AgeRisk.AGE_MIDLIFE));
	}

}
