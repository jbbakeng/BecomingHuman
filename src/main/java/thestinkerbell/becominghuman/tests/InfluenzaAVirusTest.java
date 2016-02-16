package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import thestinkerbell.becominghuman.human.properties.basic.BasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.virus.InfluenzaAVirusHumanProperty;
import thestinkerbell.becominghuman.human.properties.virus.VirusHumanProperty;
import thestinkerbell.becominghuman.human.risks.Risk;

public class InfluenzaAVirusTest {
	
	private VirusHumanProperty getVirusHumanProperty_1() {
		return new InfluenzaAVirusHumanProperty();
	}
	
	private void canSerializeAnDeserialize(VirusHumanProperty property) {
		ByteBuf buf = Unpooled.buffer(128);
		VirusHumanProperty.serialize(property, buf);
		assertNotNull(buf);
		
		VirusHumanProperty property2 = new VirusHumanProperty();
		VirusHumanProperty.deserialize(buf, property2);
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
		InfluenzaAVirusHumanProperty influenza_a = new InfluenzaAVirusHumanProperty();
		Double oldValue = influenza_a.getValue();
		Double newValue = 1.0;
		influenza_a.setValue(newValue);
		assertTrue(influenza_a.getValue().doubleValue() == newValue.doubleValue());
	}
	
	@Test
	public void canSerializeAndDeserializeVirusHumanProperty() {
		VirusHumanProperty property = getVirusHumanProperty_1();
		this.canSerializeAnDeserialize(property);
	}

}
