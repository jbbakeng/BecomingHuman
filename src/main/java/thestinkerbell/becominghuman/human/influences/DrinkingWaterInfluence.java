package thestinkerbell.becominghuman.human.influences;

import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.properties.Property;

public class DrinkingWaterInfluence extends HumanInfluence implements Influence {

	private final double drinking_water_liter;
	private final String w = "Water";
	
	public DrinkingWaterInfluence(Human human, double water_liter) {
		super(human);
		this.drinking_water_liter = water_liter;
	}
	
	@Override
	public void apply() {
		
		Property water = this.human.getHumanPropertyWithName(w);
		Double old_water = (Double) water.getValue();
		Double change = this.drinking_water_liter;
		Double new_water = old_water + change;
		
		try {
			this.human.setValue(w, new_water);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
