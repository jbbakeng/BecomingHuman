package thestinkerbell.becominghuman.human.influences.natural;

import java.util.ArrayList;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.HumanBiology;
import thestinkerbell.becominghuman.human.influences.HumanInfluence;
import thestinkerbell.becominghuman.human.influences.Influence;
import thestinkerbell.becominghuman.human.properties.GermHumanProperty;
import thestinkerbell.becominghuman.human.properties.GermHumanProperty.Transmission;
import thestinkerbell.becominghuman.utilities.Utilities;

public class EatingInfluence extends HumanInfluence implements Influence {

	private final HumanBiology biology = new HumanBiology();
	private final ItemFood food;
	private double drinking_water_liter = 0.0;

	public EatingInfluence(Human human, ItemFood food) {
		super(human);
		this.food = food;
		//saturation range from 0.1F to 1.2F,
		//pretends this represents dl of water in the food
		this.drinking_water_liter = food.getSaturationModifier(new ItemStack(food))/10;
	}

	@Override
	public void apply() {
		
		DrinkingWaterInfluence.applyDrinkingWater(human, this.drinking_water_liter);
		
		GermHumanProperty germ = biology.getRandomGerm();
		if(this.foodIsInfected(germ));
		{
			HumanBiology.applyChange(this.human, germ.getName(), GermInfluence.change);
		}
		
	}

	private boolean foodIsInfected(GermHumanProperty germ) {
		boolean infected = false;
		if(germ.canBeTransmittedBy(Transmission.TRANSMISSION_ORAL))
		{
			ArrayList<String> contaminated_food_keywords = new ArrayList();
			contaminated_food_keywords.add("rotten");
			contaminated_food_keywords.add("Raw");
			
			String unlocalizedName = new ItemStack(this.food).getUnlocalizedName();
			for(String s: contaminated_food_keywords) {
				//contains is case sensitive
				if(unlocalizedName.contains(s)) {
					double chance_of_occuring = 0.5; // 50% chance of getting infected
					infected = Utilities.chanceOccured(chance_of_occuring);
				}
			}
		}
		return infected;
	}
}
