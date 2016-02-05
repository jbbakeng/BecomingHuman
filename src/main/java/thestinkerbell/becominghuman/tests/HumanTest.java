package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.properties.Properties;
import thestinkerbell.becominghuman.human.properties.Property;
import thestinkerbell.becominghuman.human.properties.basic.BasicHumanProperty;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.Risks;

public class HumanTest {
	
	private final String BasicHumanPropertyName = "Age";
	private final String CompoundHumanPropertyName = "BMI";

	private Double tryToSetValue(Human human, String propertyname, Double newValue) {
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
	
	//--- TESTS ---
	
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
	public void canGetRiskFromAllHumanProperties() {
		Human human = new Human();
		Properties properties = human.getListOfAllHumanProperties();
		for(Property property : properties) {
			Risk risk = property.getRisk();
		}
	}
	
	@Test
	public void canGetAListOfCurrentRisks() {
		Human human = new Human();
		Risks risks = human.getListOfCurrentRisks();
		assertNotNull(risks);
	}
	
	@Test
	public void canGetAHumanProperty() {
		Human human = new Human();
		Property property = human.getHumanPropertyWithName(BasicHumanPropertyName);
		assertNotNull(property);
	}
	
	@Test
	public void canSetValueOnABasicHumanProperty() {		
		Human human = new Human();
		Double newValue = 10.0;
		Double oldValue = tryToSetValue(human, BasicHumanPropertyName, newValue);
		Property newProperty = human.getHumanPropertyWithName(BasicHumanPropertyName);
		assertTrue(newValue.doubleValue() == ((Double)newProperty.getValue()).doubleValue());
	}
	
	@Test
	public void canNotSetValueOnACompoundHumanProperty() {		
		Human human = new Human();
		Double oldValue = tryToSetValue(human, CompoundHumanPropertyName, 10.0);
		Property newProperty = human.getHumanPropertyWithName(CompoundHumanPropertyName);
		assertTrue(oldValue.doubleValue() == ((Double)newProperty.getValue()).doubleValue());
	}
	
	@Test
	public void settingAPropertyThatDoesNotExistThrowsAnException() {
		Human human = new Human();
		try {
			human.setValue("NOT_DEFINED", 0.0);
		} catch (Exception e) {
			return;
		}
		fail("SetValue in Human did not fail, property does exist.");
	}
	
	@Test
	public void canSerializeAndDeserializeAllHumanProperties() {
		Human human = new Human();
		Properties list = human.getListOfBasicHumanProperties();
		for(Property property: list) {
			if(property instanceof BasicHumanProperty)
				HumanPropertyTest.canSerializeAnDeserialize((BasicHumanProperty)property);
		}
	}

}
