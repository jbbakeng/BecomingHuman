package thestinkerbell.becominghuman.human.influences.external;

import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.influences.HumanInfluence;
import thestinkerbell.becominghuman.human.influences.Influence;
import thestinkerbell.becominghuman.human.properties.Property;

public class DrinkingWaterInfluence extends HumanInfluence implements Influence {

	private final double drinking_water_liter;
	private static final String w = "Water";
	
	public DrinkingWaterInfluence(Human human, double water_liter) {
		super(human);
		this.drinking_water_liter = water_liter;
	}
	
	@Override
	public void apply() {
		
		applyDrinkingWater(this.human ,this.drinking_water_liter);
	}

	public static void applyDrinkingWater(Human human, double drinking_water_liter) {
		Property water = human.getHumanPropertyWithName(w);
		Double old_water = (Double) water.getValue();
		Double change = drinking_water_liter;
		Double new_water = old_water + change;
		
		try {
			human.setValue(w, new_water);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
