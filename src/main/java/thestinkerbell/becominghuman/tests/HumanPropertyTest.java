package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import thestinkerbell.becominghuman.human.properties.basic.BasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.HeightBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.WeightBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.compound.BMICompoundHumanProperty;
import thestinkerbell.becominghuman.human.properties.compound.CompoundHumanProperty;
import thestinkerbell.becominghuman.human.properties.compound.DoubleCompoundHumanProperty;

public class HumanPropertyTest {
	
	
	private BasicHumanProperty getBasicHumanProperty_1() {
		return new WeightBasicHumanProperty();
	}
	
	private BasicHumanProperty getBasicHumanProperty_2() {
		return new HeightBasicHumanProperty();
	}
	
	private DoubleCompoundHumanProperty<WeightBasicHumanProperty, HeightBasicHumanProperty> getCompoundHumanProperty() {
		return new BMICompoundHumanProperty(new WeightBasicHumanProperty(), new HeightBasicHumanProperty());
	}
	
	public static void canSerializeAnDeserialize(BasicHumanProperty property) {
		ByteBuf buf = Unpooled.buffer(128);
		BasicHumanProperty.serialize(property, buf);
		assertNotNull(buf);
		
		BasicHumanProperty property2 = new BasicHumanProperty();
		BasicHumanProperty.deserialize(buf, property2);
		assertEquals(property, property2);
	}
	
	// ==== TESTS ====
	
	// --- Basic
	
	@Test
	public void canCreateBasicHumanProperty() {
		BasicHumanProperty basic1 = getBasicHumanProperty_1();
		assertNotNull(basic1);
		BasicHumanProperty basic2 = getBasicHumanProperty_2();
		assertNotNull(basic2);
		
		assertNotNull(basic1.getName());
		assertNotNull(basic1.getUnit());
		assertNotNull(basic1.toString());
	}
	
	@Test
	public void twoBasicPropertiesAreNotTheSame() {
		BasicHumanProperty basic1 = getBasicHumanProperty_1();
		BasicHumanProperty basic2 = getBasicHumanProperty_2();
		assertNotEquals(basic1, basic2);
	}
	
	
	@Test
	public void basicAndCompoundPropertiesAreNotTheSame() {
		BasicHumanProperty basic1 = getBasicHumanProperty_1();
		DoubleCompoundHumanProperty compound = getCompoundHumanProperty();
		assertNotEquals(basic1, compound);
	}

	@Test
	public void canSetValue() {
		BasicHumanProperty basic = getBasicHumanProperty_1();
		Double oldValue = basic.getValue();
		Double newValue = oldValue*2;
		basic.setValue(newValue);
		assertTrue(basic.getValue() == newValue);
	}
	
	@Test
	public void canGetValue() {
		BasicHumanProperty property = getBasicHumanProperty_1();
		Double value = property.getValue();
		assertNotNull(value);
	}

	@Test
	public void canSerializeAndDeserializeHumanProperty() {
		BasicHumanProperty property = getBasicHumanProperty_1();
		this.canSerializeAnDeserialize(property);
	}
	
	@Test
	public void canSerializeAndDeserializeBasicHumanProperty() {
		BasicHumanProperty basic_1 = getBasicHumanProperty_1();
		this.canSerializeAnDeserialize(basic_1);
	}
	
	
	// --- Compound
	
	
	@Test
	public void canCreateCompoundHumanProperty() {
		DoubleCompoundHumanProperty compound = getCompoundHumanProperty();
		assertNotNull(compound);
	}
	
	@Test
	public void canGetValueOfCompoundProperty() {
		DoubleCompoundHumanProperty compound = getCompoundHumanProperty();
		assertNotNull(compound.getValue());
	}

}
