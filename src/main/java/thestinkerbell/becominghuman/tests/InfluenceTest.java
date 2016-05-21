package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import net.minecraft.item.ItemFood;
import net.minecraft.util.FoodStats;
import net.minecraft.world.biome.BiomeGenBase.TempCategory;
import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.HumanBiology;
import thestinkerbell.becominghuman.human.influences.HumanInfluence;
import thestinkerbell.becominghuman.human.influences.Influence;
import thestinkerbell.becominghuman.human.influences.InfluenceQueue;
import thestinkerbell.becominghuman.human.influences.natural.AirTemperatureInfluence;
import thestinkerbell.becominghuman.human.influences.natural.BreathingGermsInfluence;
import thestinkerbell.becominghuman.human.influences.natural.DrinkingWaterInfluence;
import thestinkerbell.becominghuman.human.influences.natural.EatingInfluence;
import thestinkerbell.becominghuman.human.influences.natural.HungerInfluence;
import thestinkerbell.becominghuman.human.influences.natural.MovementInfluence;
import thestinkerbell.becominghuman.human.influences.natural.SleepingInfluence;
import thestinkerbell.becominghuman.human.influences.natural.MovementInfluence.Zone;
import thestinkerbell.becominghuman.human.influences.natural.TimeInfluence;
import thestinkerbell.becominghuman.human.influences.natural.TouchingGermsInfluence;
import thestinkerbell.becominghuman.human.properties.Property;
import thestinkerbell.becominghuman.utilities.Utilities;

public class InfluenceTest {
	
	private void canProcessQueueWithInfluence(Influence influence, int add_times) {
		InfluenceQueue queue = new InfluenceQueue();
		for(int i=0 ; i < add_times; i++) {
			queue.add(influence);			
		}
		assertTrue(queue.size() == add_times);
		queue.process();
		assertTrue(queue.size() == 0);
	}
	
	
	public double applyInfluence(String property_name, Human human, Influence influence) {
		Property property = human.getHumanPropertyWithName(property_name);

		int ticks = 1; //Utilities.days_to_ticks(1);
		for(int i = 0; i < ticks; i++) {
			influence.apply();
		}
		Double new_value = (Double)property.getValue();
		
		return new_value;
	}
	
	private double applyAirTemperatureInfluence(TempCategory temp) {
		Human human = new Human();
		Influence influence = new AirTemperatureInfluence(human, temp);
		
		return applyInfluence(HumanBiology.bt, human, influence);
	}
	
	private double applyMovementInfluenceToHR(Zone zone) {
		Human human = new Human();
		Influence influence = new MovementInfluence(human, zone);

		return applyInfluence(HumanBiology.hr, human, influence);
	}
	
	private double applyMovementInfluenceToBT(Zone zone) {
		Human human = new Human();
		Influence influence = new MovementInfluence(human, zone);

		return applyInfluence(HumanBiology.bt, human, influence);
	}
	
	private double applyMovementInfluenceToFitness(Zone zone) {
		Human human = new Human();
		Influence influence = new MovementInfluence(human, zone);

		return applyInfluence(HumanBiology.fitness, human, influence);
	}
	
	private double applyMovementInfluenceToWater(Zone zone) {
		Human human = new Human();
		Influence influence = new MovementInfluence(human, zone);

		return applyInfluence(HumanBiology.water, human, influence);
	}
	
	private double applyHungerInfluence(FoodStats food_stat) {
		Human human = new Human();
		Influence influence = new HungerInfluence(human, food_stat);
		
		return applyInfluence(HumanBiology.weight, human, influence);
	}
	
	private double applyDrinkingWaterInfluence(double water_liter) {
		Human human = new Human();
		Influence influence = new DrinkingWaterInfluence(human, water_liter);
		
		return applyInfluence(HumanBiology.water, human, influence);
	}
	
	private double applyEatingInfluence(ItemFood food) {
		Human human = new Human();
		Influence influence = new EatingInfluence(human, food);
		
		return applyInfluence(HumanBiology.water, human, influence);
	}
	
	private double applyTimeInfluenceToAge(int ticks) {
		Human human = new Human();
		Influence influence = new TimeInfluence(human, ticks);
		
		return applyInfluence(HumanBiology.age, human, influence);
	}
	
	private double applyTimeInfluenceToSleepy(int ticks) {
		Human human = new Human();
		Influence influence = new TimeInfluence(human, ticks);
		
		return applyInfluence(HumanBiology.sleepy, human, influence);
	}

	private double applySleepingInfluenceToSleepy() {
		Human human = new Human();
		Influence influence = new SleepingInfluence(human);
		
		return applyInfluence(HumanBiology.sleepy, human, influence);
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
		this.canProcessQueueWithInfluence(influence, 3);		
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
		this.canProcessQueueWithInfluence(influence, 3);
	}
	
	@Test
	public void canProcessQueueWithOneMovementInfluence() {
		double walking_speed_km_h = 5.0; //avarage walking speed
		Influence influence = new MovementInfluence(new Human(), Zone.getZone(walking_speed_km_h));
		this.canProcessQueueWithInfluence(influence, 3);
	}
	
	@Test
	public void movementCanInfluenceHRHumanProperties() {
		double heart_rate_resting = 70.0;
		double heart_rate_walking = heart_rate_resting + MovementInfluence.max_walking_heart_rate_addition;
		double heart_rate_max = 220.0;
		
		assertTrue(this.applyMovementInfluenceToHR(Zone.ZONE_RESTING) == heart_rate_resting);
		assertTrue(this.applyMovementInfluenceToHR(Zone.ZONE_RESTITUTING) > heart_rate_resting);
		assertTrue(this.applyMovementInfluenceToHR(Zone.ZONE_RESTITUTING) <= heart_rate_walking);
		assertTrue(this.applyMovementInfluenceToHR(Zone.ZONE_TRAINING) > heart_rate_resting);
		assertTrue(this.applyMovementInfluenceToHR(Zone.ZONE_TRAINING) <= heart_rate_max);
	}
	
	@Test
	public void movementCanInfluenceBTHumanProperties() {
		double bt_resting = 37.0;
		
		assertTrue(this.applyMovementInfluenceToBT(Zone.ZONE_RESTING) == bt_resting);
		assertTrue(this.applyMovementInfluenceToBT(Zone.ZONE_RESTITUTING) == bt_resting);
		assertTrue(this.applyMovementInfluenceToBT(Zone.ZONE_TRAINING) > bt_resting);
		assertTrue(this.applyMovementInfluenceToBT(Zone.ZONE_TRAINING) <= MovementInfluence.max_training_body_temperature);
	}
	
	@Test
	public void movementCanInfluenceFitnessHumanProperties() {
		double fitness_default = 45.0;
		
		assertTrue(this.applyMovementInfluenceToFitness(Zone.ZONE_RESTING) < fitness_default);
		assertTrue(this.applyMovementInfluenceToFitness(Zone.ZONE_RESTITUTING) == fitness_default);
		assertTrue(this.applyMovementInfluenceToFitness(Zone.ZONE_TRAINING) > fitness_default);
		assertTrue(this.applyMovementInfluenceToFitness(Zone.ZONE_TRAINING) <= 100.0);
	}
	
	@Test
	public void movementCanInfluenceWaterHumanProperties() {
		double water_default = 50.0;
		
		assertTrue(this.applyMovementInfluenceToWater(Zone.ZONE_RESTING) == water_default);
		assertTrue(this.applyMovementInfluenceToWater(Zone.ZONE_RESTITUTING) < water_default);
		assertTrue(this.applyMovementInfluenceToWater(Zone.ZONE_TRAINING) < water_default);
	}

	@Test
	public void canProcessQueueWithOneHungerInfluence() {
		FoodStats food_stats = new FoodStats();
		food_stats.addExhaustion(0.0F);
		
		Influence hunger = new HungerInfluence(new Human(), food_stats);
		this.canProcessQueueWithInfluence(hunger, 1);
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
		Influence breathing = new BreathingGermsInfluence(new Human());
		this.canProcessQueueWithInfluence(breathing, 1);
	}
	
	@Test
	public void canProcessQueueWithOneEatingInfluence() {
		ItemFood food = new ItemFood(5, 0.6F, false);
		canProcessQueueWithInfluence(new EatingInfluence(new Human(), food), 1);
	}
	
	@Test
	public void eatingInfluencesHumanProperties() {
		ItemFood bread = new ItemFood(5, 0.6F, false);
		bread.setUnlocalizedName("bread");
		
		assertTrue(this.applyEatingInfluence(bread) > 50.0);
	}
	
	@Test
	public void canProcessQueueWithOneTouchInfluence() {
		canProcessQueueWithInfluence(new TouchingGermsInfluence(new Human()), 1);
	}
	
	@Test
	public void canProcessQueueWithOneTimeInfluence() {
		int ticks = 20;
		canProcessQueueWithInfluence(new TimeInfluence(new Human(), ticks), 1);
	}
	
	@Test
	public void timeCanInfluenceAgeHumanProperties() {
		double age_default = 18.0;
		int one_month = Utilities.months_to_ticks(1);
		int one_year = Utilities.years_to_ticks(1);
		
		assertTrue(this.applyTimeInfluenceToAge(0) == age_default);
		assertTrue(this.applyTimeInfluenceToAge(one_month) > age_default);
		assertTrue(this.applyTimeInfluenceToAge(one_year) >= 19.0);
	}
	
	@Test
	public void timeCanInfluenceSleepyHumanProperties() {
		double sleepy_default = 0.0;
		double sleepy_max = 100.0;
		int eight_hours_awake = Utilities.hours_to_ticks(8);
		int twentyfour_hours_awake = Utilities.hours_to_ticks(24);
		
		assertTrue(this.applyTimeInfluenceToSleepy(0) == sleepy_default);
		assertTrue(this.applyTimeInfluenceToSleepy(eight_hours_awake) > sleepy_default);
		assertTrue(this.applyTimeInfluenceToSleepy(twentyfour_hours_awake) == sleepy_max);

	}
	
	@Test
	public void canProcessQueueWithOneSleepingInfluence() {
		int ticks = 20;
		canProcessQueueWithInfluence(new SleepingInfluence(new Human()), 1);
	}	
	
	@Test
	public void sleepingCanInfluenceSleepyHumanProperties() {
		double sleepy_default = 0.0;
		
		assertTrue(this.applySleepingInfluenceToSleepy() == sleepy_default);

	}

}
