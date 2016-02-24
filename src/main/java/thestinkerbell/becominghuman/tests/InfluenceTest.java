package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import net.minecraft.util.FoodStats;
import net.minecraft.world.biome.BiomeGenBase.TempCategory;
import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.influences.HumanInfluence;
import thestinkerbell.becominghuman.human.influences.Influence;
import thestinkerbell.becominghuman.human.influences.external.AirTemperatureInfluence;
import thestinkerbell.becominghuman.human.influences.external.BreathingInfluence;
import thestinkerbell.becominghuman.human.influences.external.DrinkingWaterInfluence;
import thestinkerbell.becominghuman.human.influences.external.HungerInfluence;
import thestinkerbell.becominghuman.human.influences.external.InfluenceQueue;
import thestinkerbell.becominghuman.human.influences.external.MovementInfluence;
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
		
		Influence influence = new AirTemperatureInfluence(human, temp);
		influence.apply();
		
		Double new_body_temp = (Double)body_temperature.getValue();
		return new_body_temp;
	}
	
	private double applyMovementInfluence(double speed_km_h) {
		Human human = new Human();
		Property heart_rate = human.getHumanPropertyWithName("Heart Rate");
		Double old_heart_rate = (Double) heart_rate.getValue();
		assertTrue((Double)heart_rate.getValue() == 70.0);
		
		Influence influence = new MovementInfluence(human, speed_km_h);
		influence.apply();
		
		Double new_heart_rate = (Double)heart_rate.getValue();
		return new_heart_rate;
	}
	
	private double applyHungerInfluence(FoodStats food_stat) {
		Human human = new Human();
		Property weight = human.getHumanPropertyWithName("Weight");
		Double old_weight = (Double) weight.getValue();
		assertTrue((Double)weight.getValue() == 75.0);
		
		Influence influence = new HungerInfluence(human, food_stat);
		influence.apply();
		
		Double new_weight = (Double)weight.getValue();
		return new_weight;
	}
	
	private double applyDrinkingWaterInfluence(double water_liter) {
		Human human = new Human();
		Property water = human.getHumanPropertyWithName("Water");
		Double old_water = (Double) water.getValue();
		assertTrue((Double)water.getValue() == 50.0);
		
		Influence influence = new DrinkingWaterInfluence(human, water_liter);
		influence.apply();
		
		Double new_water = (Double)water.getValue();
		return new_water;
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
		double walking_speed_km_h = 5.0; //avarage walking speed
		Influence influence = new MovementInfluence(new Human(), walking_speed_km_h);
		this.popQueue(influence, 3);
	}
	
	@Test
	public void movementCanInfluenceHumanProperties() {
		double heart_rate_resting = 70.0;
		double heart_rate_walking = heart_rate_resting + MovementInfluence.max_walking_heart_rate_addition;
		double heart_rate_max = 220.0;
		
		double standing_still_speed = MovementInfluence.stationary_speed_km_h;
		double walking_speed = MovementInfluence.walking_speed_km_h;
		double running_speed = MovementInfluence.walking_speed_km_h+2.0;
		
		assertTrue(this.applyMovementInfluence(standing_still_speed) == heart_rate_resting);
		assertTrue(this.applyMovementInfluence(walking_speed) > heart_rate_resting);
		assertTrue(this.applyMovementInfluence(walking_speed) <= heart_rate_walking);
		assertTrue(this.applyMovementInfluence(running_speed) > heart_rate_resting);
		assertTrue(this.applyMovementInfluence(running_speed) <= heart_rate_max);
	}
	
	@Test
	public void canProcessQueueWithOneHungerInfluence() {
		FoodStats food_stats = new FoodStats();
		food_stats.addExhaustion(0.0F);
		
		Influence hunger = new HungerInfluence(new Human(), food_stats);
		this.popQueue(hunger, 1);
	}
	
	@Test
	public void hungerCanInfluenceHumanProperties() {
		double weight = 75.0;
		
		FoodStats gain_weight = new FoodStats();
		gain_weight.setFoodLevel(20);
		gain_weight.setFoodSaturationLevel(20);
		assertTrue(this.applyHungerInfluence(gain_weight) > weight);
		gain_weight.setFoodSaturationLevel(1);
		assertTrue(this.applyHungerInfluence(gain_weight) > weight);
		gain_weight.setFoodSaturationLevel(0);
		assertTrue(this.applyHungerInfluence(gain_weight) == weight);
		
		FoodStats loose_weight = new FoodStats();
		loose_weight.setFoodSaturationLevel(0);
		loose_weight.setFoodLevel(20);
		assertTrue(this.applyHungerInfluence(loose_weight) == weight);
		loose_weight.setFoodLevel(10);
		assertTrue(this.applyHungerInfluence(loose_weight) < weight);
		loose_weight.setFoodLevel(0);
		assertTrue(this.applyHungerInfluence(loose_weight) < weight);
	}
	
	@Test
	public void drinkingWaterBottleInfluencesHumanProperties() {
		double drinking_water = 1.0;
		double not_drinking_water = 0.0;
		
		assertTrue(this.applyDrinkingWaterInfluence(not_drinking_water) == 50.0);
		assertTrue(this.applyDrinkingWaterInfluence(drinking_water) > 50.0);
	}
	
	@Test
	public void canProcessQueueWithOneBreathingInfluence() {

		Influence breathing = new BreathingInfluence(new Human());
		this.popQueue(breathing, 1);
	}

}
