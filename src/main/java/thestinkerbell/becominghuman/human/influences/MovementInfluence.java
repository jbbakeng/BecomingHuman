package thestinkerbell.becominghuman.human.influences;

import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.properties.Property;

public class MovementInfluence extends HumanInfluence implements Influence {

	private String hr = "Heart Rate";
	
	public MovementInfluence(Human human) {
		super(human);
	}
	
	@Override
	public void apply() {
		
		Property heart_rate = this.human.getHumanPropertyWithName(hr);
		Double old_heart_rate = (Double) heart_rate.getValue();
		Double change = getHeartRateChange();
		Double new_heart_rate = old_heart_rate + change;
		
		try {
			this.human.setValue(hr, new_heart_rate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private double getHeartRateChange() {
		double hr_change = 0.0;
		return hr_change;
	}


}
