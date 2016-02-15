package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import thestinkerbell.becominghuman.human.properties.HumanProperty.GeneralRisk;
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
	
	@Test
	public void canSetBloodPressureToCreateRisks() {
		SystolicBloodPressureBasicHumanProperty systolic = new SystolicBloodPressureBasicHumanProperty();
		DiastolicBloodPressureBasicHumanProperty diastolic = new DiastolicBloodPressureBasicHumanProperty();
		BloodPressureCompoundHumanProperty bp = new BloodPressureCompoundHumanProperty(systolic, diastolic);
		
		assertTrue(bp.getRisk() == BloodPressureRisk.BP_DESIRED);
		systolic.setValue(80.0);
		diastolic.setValue(50.0);
		assertTrue(bp.getRisk() == BloodPressureRisk.BP_HYPOTENSION);
		systolic.setValue(120.0);
		diastolic.setValue(80.0);
		assertTrue(bp.getRisk() == BloodPressureRisk.BP_PREHYPERTENSION);
		systolic.setValue(0.0);
		diastolic.setValue(120.0);		
		assertTrue(bp.getRisk() == GeneralRisk.UNDEFINED);
	}
}
