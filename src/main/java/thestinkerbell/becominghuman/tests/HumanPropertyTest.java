package thestinkerbell.becominghuman.tests;

import org.junit.Test;

import thestinkerbell.becominghuman.human.HumanProperty;

public class HumanPropertyTest {

	private HumanProperty property;
	
	private HumanProperty getHumanProperty() {
		String properyName = "Name";
		return new HumanProperty(properyName);
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
