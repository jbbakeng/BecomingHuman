package thestinkerbell.becominghuman.tests;

import org.junit.Test;

import thestinkerbell.becominghuman.human.HumanProperty;

public class HumanPropertyTest {

	private HumanProperty property;
	
	private HumanProperty getHumanProperty() {
		String name = "Name";
		Integer defaultValue = 100;
		String unit = "cm";
		return new HumanProperty(name, defaultValue, unit);
	}
	
	// ==== TESTS ====
	
	@Test
	public void canCreate() {
		HumanProperty property = getHumanProperty();
	}
	
	@Test
	public void canSetValue() {
		HumanProperty property = getHumanProperty();
		Integer value = 100;
		property.set(value);
	}

	
	@Test
	public void canGetValue() {
		HumanProperty property = getHumanProperty();
		Integer value = 100;
		property.set(value);
		Integer value2 = property.getValue();
	}

}
