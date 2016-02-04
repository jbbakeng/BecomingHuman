package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.conditions.AnalyseRisk;
import thestinkerbell.becominghuman.human.conditions.Condition;
import thestinkerbell.becominghuman.human.properties.compound.BloodPressureCompoundHumanProperty.BloodPressureRisk;
import thestinkerbell.becominghuman.human.risks.Risk;

public class ConditionTest {
	
	@Test
	public void havingHightBloodPressureLeadsToHypertension() {
		Risk risk = BloodPressureRisk.BP_STAGE1HYPERTENSION;
		
		Condition condition = AnalyseRisk.analyse(risk);
		assertTrue(condition == Condition.HYPERTENSION);
	}
	
	@Test
	public void defaultHumanPropertiesShouldLeadToAHealtyCondition() {
		Human human = new Human();
		
		List<Risk> risks = human.getListOfCurrentRisks();
		assertNotNull(risks);
		
		List<Condition> conditions = AnalyseRisk.analyse(risks);
		assertNotNull(conditions);
		assertTrue(conditions.size() == 1);
		assertTrue(conditions.get(0) == Condition.HEALTHY);
	}

}
