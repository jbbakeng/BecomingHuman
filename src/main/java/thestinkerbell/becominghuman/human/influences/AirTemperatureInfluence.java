package thestinkerbell.becominghuman.human.influences;

import net.minecraft.world.biome.BiomeGenBase.TempCategory;
import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.properties.Property;

public class AirTemperatureInfluence extends HumanInfluence implements Influence {
	
	private final double cold_factor = -0.0001;
	private final double medium_factor = 0.0;
	private final double warm_factor = 0.0001;
	private final double ocean_factor = cold_factor*0.1;
	private final TempCategory temp;
	private String bt = "Body Temperature";

	public AirTemperatureInfluence(Human human, TempCategory temp) {
		super(human);
		this.temp = temp;
	}

	@Override
	public void apply() {
		Property body_temperatur = this.human.getHumanPropertyWithName(bt);
		Double old_body_temperatur = (Double) body_temperatur.getValue();
		Double temp_change = getTemperatureChange();
		Double new_body_temperatur = old_body_temperatur + temp_change;
		
		//printDebug(old_body_temperatur, temp_change, new_body_temperatur);
		
		try {
			this.human.setValue(bt, new_body_temperatur);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		Double set_point_temp = 37.0;
		Double current_temp = (Double) this.human.getHumanPropertyWithName(bt).getValue();
		TempCategory relative_temp = this.temp;
		if(this.temp == TempCategory.MEDIUM) {
			relative_temp = (set_point_temp - current_temp) > 0 ? TempCategory.WARM : TempCategory.COLD;
		}
		return relative_temp;
	}

	private void printDebug(Double old_body_temperatur, Double temp_change, Double new_body_temperatur) {
		System.out.println("Applying AirTemperatureInfluence");
		System.out.println("old_body_temperatur: "+old_body_temperatur);
		System.out.println("temp_change: "+temp_change);
		System.out.println("new_body_temperatur: "+new_body_temperatur);
		Double after_one_min = (temp_change*20*60)+old_body_temperatur;
		System.out.println("after 1 min: "+after_one_min);
		Double after_five_min = (temp_change*20*60*5)+old_body_temperatur;
		System.out.println("after 5 min: "+after_five_min);
	}
}
