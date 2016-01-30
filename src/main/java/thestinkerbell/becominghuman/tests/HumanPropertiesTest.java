package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
		Integer numberOfProperties = properties.getListOfHumanProperties().size();
		assertNotNull(numberOfProperties);
		assertTrue(numberOfProperties >=5);
	}
	
	@Test
	public void canGetAHumanProperty() {
		HumanProperties properties = new HumanProperties();
		HumanProperty property = properties.getHumanPropertyWithName(testPropertyName);
		assertNotNull(property);
	}
	
	@Test
	public void canUpdateAHumanProperty() {
		Integer newValue = 33;
		HumanProperties properties = new HumanProperties();
		HumanProperty oldProperty = properties.getHumanPropertyWithName(testPropertyName);
		assertNotNull(oldProperty);
		assertTrue(newValue != oldProperty.getValue());
		properties.setValue(testPropertyName, newValue);
		HumanProperty newProperty = properties.getHumanPropertyWithName(testPropertyName);
		assertTrue(newValue == newProperty.getValue());
	}
	
	@Test
	public void canSerializeAndDeserializeAllHumanProperties() {
		HumanProperties properties = new HumanProperties();
		List<HumanProperty> list = properties.getListOfHumanProperties();
		for(HumanProperty property: list) {
			HumanPropertyTest.canSerializeAnDeserialize(property);
		}
	}

}
