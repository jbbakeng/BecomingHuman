package thestinkerbell.becominghuman.human.influences;

import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.properties.Property;

public class MovementInfluence extends HumanInfluence implements Influence {

	public static final double stationary_speed_km_h = 0.0;
	public static final double walking_speed_km_h = 15.5;
	public static final double max_walking_heart_rate_addition = 30.0;

	private final double speed_km_h;
	private String hr = "Heart Rate";
	private String hr_resting = "Heart Rate Resting";
	private String hr_max = "Heart Rate Max";
	
	public MovementInfluence(Human human, double speed_km_h) {
		super(human);
		this.speed_km_h = speed_km_h;
	}
	
	@Override
	public void apply() {
		
		Property heart_rate = this.human.getHumanPropertyWithName(hr);
		Double old_heart_rate = (Double) heart_rate.getValue();
		Double change = getHeartRateChange(old_heart_rate);
		Double new_heart_rate = old_heart_rate + change;
		
		try {
			this.human.setValue(hr, new_heart_rate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private double getHeartRateChange(double current_heart_rate) {
		double heart_rate_resting = (Double) this.human.getHumanPropertyWithName(hr_resting).getValue();
		double heart_rate_max = (Double) this.human.getHumanPropertyWithName(hr_max).getValue();

		double factor = 0.001;
		
		double hr_change = 0.0;
		if(speed_km_h == 0) {
			//stabilize at hr_rest
			hr_change = (heart_rate_resting - current_heart_rate) * factor;
		} else if(speed_km_h <= walking_speed_km_h) {
			//stabilize at hr_rest + max_walking_heart_rate_addition
			hr_change = ((heart_rate_resting + max_walking_heart_rate_addition) - current_heart_rate) * factor;
		} else if(speed_km_h > walking_speed_km_h) {
			//stabilize at hr_max
			hr_change = (heart_rate_max - current_heart_rate) * factor;
		}
		
		return hr_change;
	}


}
