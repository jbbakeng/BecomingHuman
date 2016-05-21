package thestinkerbell.becominghuman.tests.utilities;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.HumanBiology;
import thestinkerbell.becominghuman.human.diseases.Disease;
import thestinkerbell.becominghuman.human.diseases.HumanDisease;
import thestinkerbell.becominghuman.human.properties.Property;
import thestinkerbell.becominghuman.human.symptoms.HumanSymptom.Severity;

public class TestUtilities {
	
	static public Disease createDisease(String name, Severity severity, int tick_duration) {
		return new HumanDisease(name, severity, tick_duration);
	}
	
	static public Double tryToSetValue(Human human, String propertyname, Double newValue) {
		Property oldProperty = human.getHumanPropertyWithName(propertyname);
		Double oldValue = (Double)oldProperty.getValue();
		assertNotNull(oldProperty);
		assertTrue(newValue != oldProperty.getValue());
		try {
			human.setValue(propertyname, newValue);
		} catch (Exception e) {
			fail("SetValue in Human failed, property does not exist.");
		}
		return oldValue;
	}
	
	static public void createBPStage1Hypertension(Human human) {
		try {
			human.setValue("Systolic Blood Pressure", 145.0);
			human.setValue("Diastolic Blood Pressure", 98.0);
			human.updateHealth();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static public void createBPPreHypertension(Human human) {
		try {
			human.setValue(HumanBiology.bp_s, 120.0);
			human.setValue(HumanBiology.bp_d, 80.0);
			human.updateHealth();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static public void createUndefinedBPRisk(Human human) {
		try {
			human.setValue(HumanBiology.bp_s, 120.0);
			human.setValue(HumanBiology.bp_d, 79.0);
			human.updateHealth();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static public void createInfluenzaRisk(Human human) {
		try {
			human.setValue(HumanBiology.influenza_a, 6.5);
			human.updateHealth();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
