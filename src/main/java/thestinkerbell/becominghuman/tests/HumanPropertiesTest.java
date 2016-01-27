package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import thestinkerbell.becominghuman.human.HumanProperties;
import thestinkerbell.becominghuman.human.HumanProperty;

public class HumanPropertiesTest {
	
	private final String testPropertyName = "Age";

	@Test
	public void canCreate() {
		HumanProperties properties = new HumanProperties();
	}
	
	@Test
	public void canGetNumberOfHumanProperties() {
		HumanProperties properties = new HumanProperties();
		Integer numberOfProperties = properties.getNumberOfProperties();
		assertNotNull(numberOfProperties);
		assertTrue(numberOfProperties >=5);
	}
	
	@Test
	public void canGetAHumanProperty() {
		HumanProperties properties = new HumanProperties();
		HumanProperty age = properties.getHumanPropertyWithName(testPropertyName);
		assertNotNull(age);
	}
	
	@Test
	public void canUpdateAHumanProperty() {
		HumanProperties properties = new HumanProperties();
		properties.updateHumanProperty(testPropertyName, 33);	
	}

}
