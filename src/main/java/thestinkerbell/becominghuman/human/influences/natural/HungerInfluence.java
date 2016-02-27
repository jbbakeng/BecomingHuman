package thestinkerbell.becominghuman.human.influences.natural;

import net.minecraft.util.FoodStats;
import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.influences.HumanInfluence;
import thestinkerbell.becominghuman.human.influences.Influence;
import thestinkerbell.becominghuman.human.properties.Property;

public class HungerInfluence extends HumanInfluence implements Influence {

	private FoodStats food_stats;
	
	private int max_food_level = 20;
	private String w = "Weight";
	
	public HungerInfluence(Human human, FoodStats food_stats) {
		super(human);
		this.food_stats = food_stats;
	}
	
	@Override
	public void apply() {
		
		Property weight = this.human.getHumanPropertyWithName(w);
		Double old_weight = (Double) weight.getValue();
		Double change = getWeightChange(old_weight);
		Double new_weight = old_weight + change;
		
		try {
			this.human.setValue(w, new_weight);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Double getWeightChange(Double old_weight) {
		// there are 20*60*60*24=1728000 ticks in a day
		// you can loose up to 1 kg per day
		// 1/1728000 = 5.8e-7
		double change_rate = 5.8e-7;
		double hunger_level = food_stats.getFoodLevel();
		double saturation_level = food_stats.getSaturationLevel();
		
		double change = 0.0;
		if(hunger_level == max_food_level && saturation_level > 0) {
			//gain weight
			change = change_rate;
		}else if(saturation_level == 0 && hunger_level < max_food_level) {
			//loose weight
			change = -change_rate;
		}
		return change;
	}

}
