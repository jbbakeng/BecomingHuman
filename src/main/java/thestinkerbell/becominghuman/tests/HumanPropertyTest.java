package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import thestinkerbell.becominghuman.human.HumanProperty;

public class HumanPropertyTest {
	
	private HumanProperty getHumanProperty() {
		String name = "Name";
		Integer defaultValue = 100;
		String unit = "cm";
		return new HumanProperty(name, defaultValue, unit);
	}
	
	public static void canSerializeAnDeserialize(HumanProperty property) {
		ByteBuf buf = Unpooled.buffer(128);
		HumanProperty.serialize(property, buf);
		assertNotNull(buf);
		
		HumanProperty property2 = new HumanProperty();
		HumanProperty.deserialize(buf, property2);
		assertEquals(property, property2);
	}
	
	// ==== TESTS ====
	
	@Test
	public void canCreateSpecificHumanProperty() {
		HumanProperty property = getHumanProperty();
	}
	
	@Test
	public void canSetValue() {
		HumanProperty property = getHumanProperty();
		Integer oldValue = property.getValue();
		Integer newValue = oldValue*2;
		property.set(newValue);
		assertTrue(property.getValue() == newValue);
	}
	
	@Test
	public void canGetValue() {
		HumanProperty property = getHumanProperty();
		Integer value = 100;
		property.set(value);
		Integer value2 = property.getValue();
	}
	
	@Test
	public void canSerializeAndDeserialize() {
		HumanProperty property = getHumanProperty();
		this.canSerializeAnDeserialize(property);
	}

}
