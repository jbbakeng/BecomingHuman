package thestinkerbell.becominghuman.human.influences.natural;

import net.minecraft.world.biome.BiomeGenBase.TempCategory;
import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.HumanBiology;
import thestinkerbell.becominghuman.human.influences.HumanInfluence;
import thestinkerbell.becominghuman.human.influences.Influence;
import thestinkerbell.becominghuman.human.properties.Property;

public class AirTemperatureInfluence extends HumanInfluence implements Influence {
	
	private final double cold_factor = -0.0001;
	private final double medium_factor = 0.0;
	private final double warm_factor = 0.0001;
	private final double ocean_factor = cold_factor*0.1;
	private final TempCategory temp;

	public AirTemperatureInfluence(Human human, TempCategory temp) {
		super(human);
		this.temp = temp;
	}

	@Override
	public void apply() {
		Double temp_change = getTemperatureChange();
		HumanBiology.applyChange(this.human, HumanBiology.bt, temp_change);
	}


	private double getTemperatureChange() {
		double temp_change = 0.0;
		TempCategory relative_temp = this.getRelativeTemp();
		switch (relative_temp) {
		case COLD:
			temp_change = this.apply_per_number_of_ticks * cold_factor;
			break;
		case MEDIUM:
			temp_change = this.apply_per_number_of_ticks * medium_factor;
			break;
		case WARM:
			temp_change = this.apply_per_number_of_ticks * warm_factor;
			break;
		case OCEAN:
			temp_change = this.apply_per_number_of_ticks * ocean_factor;
			break;
		default:
			break;
		}
		
		return temp_change;
	}

	private TempCategory getRelativeTemp() {
		Double set_point_temp = (Double) this.human.getHumanPropertyWithName(HumanBiology.bt_sp).getValue();
		Double current_temp = (Double) this.human.getHumanPropertyWithName(HumanBiology.bt).getValue();
		TempCategory relative_temp = this.temp;
		if(this.temp == TempCategory.MEDIUM) {
			relative_temp = (set_point_temp - current_temp) > 0 ? TempCategory.WARM : TempCategory.COLD;
		}
		return relative_temp;
	}
}
