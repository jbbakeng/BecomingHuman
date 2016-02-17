package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import javafx.beans.property.DoubleProperty;
import thestinkerbell.becominghuman.human.properties.DoubleHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.BasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.HeightBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.WeightBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.compound.BMICompoundHumanProperty;
import thestinkerbell.becominghuman.human.properties.compound.DoubleCompoundHumanProperty;
import thestinkerbell.becominghuman.human.properties.germ.GermHumanProperty;
import thestinkerbell.becominghuman.human.properties.germ.InfluenzaAVirusHumanProperty;

public class HumanPropertyTest {
	
	
	private BasicHumanProperty getBasicHumanProperty_1() {
		return new WeightBasicHumanProperty();
	}
	
	private BasicHumanProperty getBasicHumanProperty_2() {
		return new HeightBasicHumanProperty();
	}
	
	private GermHumanProperty getGermHumanProperty_1() {
		return new InfluenzaAVirusHumanProperty();
	}
	
	private DoubleCompoundHumanProperty<WeightBasicHumanProperty, HeightBasicHumanProperty> getCompoundHumanProperty() {
		return new BMICompoundHumanProperty(new WeightBasicHumanProperty(), new HeightBasicHumanProperty());
	}
	
	public static void canSerializeAnDeserialize(BasicHumanProperty property) {
		ByteBuf buf = Unpooled.buffer(128);
		BasicHumanProperty.serialize(property, buf);
		assertNotNull(buf);
		
		DoubleHumanProperty property2 = new BasicHumanProperty();
		BasicHumanProperty.deserialize(buf, property2);
		assertEquals(property, property2);
	}
	
	public static void canSerializeAnDeserialize(GermHumanProperty property) {
		ByteBuf buf = Unpooled.buffer(128);
		GermHumanProperty.serialize(property, buf);
		assertNotNull(buf);

		GermHumanProperty property2 = new GermHumanProperty();
		GermHumanProperty.deserialize(buf, property2);
		assertEquals(property, property2);
	}
	
	// ==== TESTS ====
	
	// --- Basic
	
	@Test
	public void canCreateBasicHumanProperty() {
		DoubleHumanProperty basic1 = getBasicHumanProperty_1();
		assertNotNull(basic1);
		DoubleHumanProperty basic2 = getBasicHumanProperty_2();
		assertNotNull(basic2);
		
		assertNotNull(basic1.getName());
		assertNotNull(basic1.getUnit());
		assertNotNull(basic1.toString());
	}
	
	@Test
	public void twoBasicPropertiesAreNotTheSame() {
		DoubleHumanProperty basic1 = getBasicHumanProperty_1();
		DoubleHumanProperty basic2 = getBasicHumanProperty_2();
		assertNotEquals(basic1, basic2);
	}
	
	
	@Test
	public void basicAndCompoundPropertiesAreNotTheSame() {
		DoubleHumanProperty basic1 = getBasicHumanProperty_1();
		DoubleCompoundHumanProperty compound = getCompoundHumanProperty();
		assertNotEquals(basic1, compound);
	}

	@Test
	public void canSetValue() {
		DoubleHumanProperty basic = getBasicHumanProperty_1();
		Double oldValue = basic.getValue();
		Double newValue = oldValue*2;
		basic.setValue(newValue);
		assertTrue(basic.getValue() == newValue);
	}
	
	@Test
	public void canGetValue() {
		DoubleHumanProperty property = getBasicHumanProperty_1();
		Double value = property.getValue();
		assertNotNull(value);
	}
	
	@Test
	public void canSerializeAndDeserializeBasicHumanProperty() {
		BasicHumanProperty basic_1 = getBasicHumanProperty_1();
		this.canSerializeAnDeserialize(basic_1);
	}
	
	// --- Germs
	
	@Test
	public void canSerializeAndDeserializeGermHumanProperty() {
		GermHumanProperty germ_1 = getGermHumanProperty_1();
		this.canSerializeAnDeserialize(germ_1);
	}
	
	@Test
	public void settingValueToLessThanRangeMinWillClampValue() {
		GermHumanProperty germ_1 = getGermHumanProperty_1();
		germ_1.setValue(-10.0);
		assertTrue(germ_1.getValue() == 0);
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
