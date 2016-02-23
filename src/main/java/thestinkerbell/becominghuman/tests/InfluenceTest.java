package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import net.minecraft.world.biome.BiomeGenBase.TempCategory;
import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.influences.AirTemperatureInfluence;
import thestinkerbell.becominghuman.human.influences.HumanInfluence;
import thestinkerbell.becominghuman.human.influences.Influence;
import thestinkerbell.becominghuman.human.influences.InfluenceQueue;
import thestinkerbell.becominghuman.human.influences.MovementInfluence;
import thestinkerbell.becominghuman.human.properties.Property;

public class InfluenceTest {
	
	private void popQueue(Influence influence, int add_times) {
		InfluenceQueue queue = new InfluenceQueue();
		for(int i=0 ; i < add_times; i++) {
			queue.add(influence);			
		}
		assertTrue(queue.size() == add_times);
		queue.process();
		assertTrue(queue.size() == 0);
	}
	
	private double applyAirTemperatureInfluence(TempCategory temp) {
		Human human = new Human();
		Property body_temperature = human.getHumanPropertyWithName("Body Temperature");
		Double old_body_temp = (Double) body_temperature.getValue();
		assertTrue((Double)body_temperature.getValue() == 37.0);
		
		Influence air_temp_influence = new AirTemperatureInfluence(human, temp);
		air_temp_influence.apply();
		
		Double new_body_temp = (Double)body_temperature.getValue();
		return new_body_temp;
	}
	
	// --- TESTS
	

	@Test
	public void canCreateAInfluenceQueue() {
		InfluenceQueue influences = new InfluenceQueue();
		assertNotNull(influences);
	}
	
	@Test
	public void canCreateAHumanInfluence() {
		Influence influence = new HumanInfluence(new Human());
		assertNotNull(influence);
	}
	
	@Test
	public void canAddInflunceToInfluenceQueue() {
		InfluenceQueue queue = new InfluenceQueue();
		assertTrue(queue.size() == 0);
		
		Influence influence = new HumanInfluence(new Human());
		
		queue.add(influence);
		assertTrue(queue.size() == 1);
	}
	
	@Test
	public void canProcessQueueEmptyQueue() {
		InfluenceQueue queue = new InfluenceQueue();
		assertTrue(queue.size() == 0);
		queue.process();
		assertTrue(queue.size() == 0);
	}
	
	@Test
	public void canProcessQueue() {
		Influence influence = new HumanInfluence(new Human());
		this.popQueue(influence, 3);		
	}
	
	@Test
	public void airTemperatureCanInfluenceHumanProperties() {
		assertTrue(this.applyAirTemperatureInfluence(TempCategory.COLD) < 37.0);
		assertTrue(Math.round(this.applyAirTemperatureInfluence(TempCategory.MEDIUM)) == 37.0);
		assertTrue(this.applyAirTemperatureInfluence(TempCategory.WARM) > 37.0);
		assertTrue(this.applyAirTemperatureInfluence(TempCategory.OCEAN) < 37.0);
		
	}
	
	@Test
	public void canProcessQueueWithOneAirTemperatureInfluence() {
		Influence influence = new AirTemperatureInfluence(new Human(), TempCategory.COLD);
		this.popQueue(influence, 3);
	}
	
	@Test
	public void canProcessQueueWithOneMovementInfluence() {
		Influence influence = new MovementInfluence(new Human());
		this.popQueue(influence, 3);
	}

}
