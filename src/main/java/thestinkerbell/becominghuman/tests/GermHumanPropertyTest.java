package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import thestinkerbell.becominghuman.human.properties.GermHumanProperty;
import thestinkerbell.becominghuman.human.properties.germ.InfluenzaAVirusHumanProperty;
import thestinkerbell.becominghuman.human.risks.Risk;

public class GermHumanPropertyTest {
	
	private GermHumanProperty getVirusHumanProperty_1() {
		return new InfluenzaAVirusHumanProperty();
	}
	
	private void canSerializeAnDeserialize(GermHumanProperty property) {
		ByteBuf buf = Unpooled.buffer(128);
		GermHumanProperty.serialize(property, buf);
		assertNotNull(buf);
		
		GermHumanProperty property2 = new GermHumanProperty();
		GermHumanProperty.deserialize(buf, property2);
		assertEquals(property, property2);
	}
	
	// --- TESTS

	@Test
	public void canCreateInfluenzaA() {
		InfluenzaAVirusHumanProperty influenza_a = new InfluenzaAVirusHumanProperty();
		assertNotNull(influenza_a);
		
		String name = influenza_a.getName();
		assertNotNull(name);
		
		Risk risk = influenza_a.getRisk();
		assertNotNull(risk);
		
		Double value = influenza_a.getValue();
		assertNotNull(value);
	}

	@Test
	public void canSetValue() {
		GermHumanProperty influenza_a = getVirusHumanProperty_1();
		Double oldValue = influenza_a.getValue();
		Double newValue = 1.0;
		influenza_a.setValue(newValue);
		assertTrue(influenza_a.getValue().doubleValue() == newValue.doubleValue());
	}
	
	@Test
	public void canSerializeAndDeserializeVirusHumanProperty() {
		GermHumanProperty property = getVirusHumanProperty_1();
		this.canSerializeAnDeserialize(property);
	}

}
