package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import thestinkerbell.becominghuman.human.properties.basic.DiastolicBloodPressureBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.SystolicBloodPressureBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.compound.BloodPressureCompoundHumanProperty;
import thestinkerbell.becominghuman.human.properties.compound.BloodPressureCompoundHumanProperty.BloodPressureRisk;
import thestinkerbell.becominghuman.utilities.Pair;

public class BloodPressureTest {

	private BloodPressureCompoundHumanProperty getBloodPressure() {
		SystolicBloodPressureBasicHumanProperty systolic = new SystolicBloodPressureBasicHumanProperty();
		assertNotNull(systolic);
		DiastolicBloodPressureBasicHumanProperty diastolic = new DiastolicBloodPressureBasicHumanProperty();
		assertNotNull(diastolic);
		BloodPressureCompoundHumanProperty bp = new BloodPressureCompoundHumanProperty(systolic, diastolic);
		assertNotNull(bp);
		return bp;
	}
	
	@Test
	public void bloodPressureIsNotNull() {
		BloodPressureCompoundHumanProperty bp = getBloodPressure();
		assertNotNull(bp.getValue());
	}
	
	@Test
	public void bloodPressureCanReturnTwoValues() {
		BloodPressureCompoundHumanProperty bp = getBloodPressure();
		Pair<Double> values = bp.getValue();
	}
	
	@Test
	public void canGetRiskFromBloodPressure() {
		BloodPressureCompoundHumanProperty bp = getBloodPressure();
		assertNotNull(bp.getRisk());
	}
	
	@Test
	public void bloodPressureIsDefaultToDesired() {
		BloodPressureCompoundHumanProperty bp = getBloodPressure();
		assertTrue(bp.getRisk() == BloodPressureRisk.BP_DESIRED);
	}
}
