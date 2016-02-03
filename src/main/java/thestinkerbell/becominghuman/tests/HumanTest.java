package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.properties.HumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.BasicHumanProperty;

public class HumanTest {
	
	private final String testPropertyName = "Age";

	@Test
	public void canCreateAHuman() {
		Human human = new Human();
	}
	
	@Test
	public void canGetListOfBasicHumanProperties() {
		Human human = new Human();
		Integer numberOfProperties = human.getListOfBasicHumanProperties().size();
		assertNotNull(numberOfProperties);
		assertTrue(numberOfProperties >=5);
	}
	
	@Test
	public void canGetListOfAllHumanProperties() {
		Human human = new Human();
		Integer numberOfProperties = human.getListOfAllHumanProperties().size();
		assertNotNull(numberOfProperties);
		assertTrue(numberOfProperties >=5);
	}
	
	@Test
	public void canGetAHumanProperty() {
		Human human = new Human();
		HumanProperty property = human.getHumanPropertyWithName(testPropertyName);
		assertNotNull(property);
	}
	
	@Test
	public void canUpdateAHumanProperty() {
		Double newValue = 33.0;
		Human human = new Human();
		HumanProperty oldProperty = human.getHumanPropertyWithName(testPropertyName);
		assertNotNull(oldProperty);
		assertTrue(newValue != oldProperty.getValue());
		human.setValue(testPropertyName, newValue);
		HumanProperty newProperty = human.getHumanPropertyWithName(testPropertyName);
		assertTrue(newValue == newProperty.getValue());
	}
	
	@Test
	public void canTryToSetAPropertyThatDoesNotExist() {
		Human human = new Human();
		human.setValue("NOT_DEFINED", 0.0);
	}
	
	@Test
	public void canSerializeAndDeserializeAllHumanProperties() {
		Human human = new Human();
		List<BasicHumanProperty> list = human.getListOfBasicHumanProperties();
		for(HumanProperty property: list) {
			if(property instanceof BasicHumanProperty)
				HumanPropertyTest.canSerializeAnDeserialize((BasicHumanProperty)property);
		}
	}

}
