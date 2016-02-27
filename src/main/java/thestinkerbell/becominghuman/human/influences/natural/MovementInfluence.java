package thestinkerbell.becominghuman.human.influences.natural;

import net.minecraft.world.biome.BiomeGenBase.TempCategory;
import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.HumanBiology;
import thestinkerbell.becominghuman.human.influences.HumanInfluence;
import thestinkerbell.becominghuman.human.influences.Influence;
import thestinkerbell.becominghuman.human.influences.natural.MovementInfluence.Zone;
import thestinkerbell.becominghuman.human.properties.Property;
import thestinkerbell.becominghuman.utilities.Utilities;

public class MovementInfluence extends HumanInfluence implements Influence {

	public static final double max_walking_heart_rate_addition = 30.0;
	public static final double max_training_body_temperature = 40.0;

	private final Zone zone;
	
	public enum Zone {
		ZONE_RESTING,
		ZONE_RESTITUTING,
		ZONE_TRAINING;
		
		private static final double stationary_speed_km_h = 0.01;
		private static final double walking_speed_km_h = 15.5;
		
		public static Zone getZone(double speed_km_h) {
			Zone zone = Zone.ZONE_RESTING;
			if(isResting(speed_km_h)) {
				zone = Zone.ZONE_RESTING;
			} else if(isRestituting(speed_km_h)) {
				zone = Zone.ZONE_RESTITUTING;
			} else if(isTraining(speed_km_h)) {
				zone = Zone.ZONE_TRAINING;
			}
			return zone;
		}
		
		private static boolean isTraining(double speed_km_h) {
			return speed_km_h > walking_speed_km_h;
		}

		private static boolean isRestituting(double speed_km_h) {
			return speed_km_h <= walking_speed_km_h;
		}

		private static boolean isResting(double speed_km_h) {
			return speed_km_h <= stationary_speed_km_h;
		}
	}
	
	public MovementInfluence(Human human, Zone zone) {
		super(human);
		this.zone = zone;
	}
	
	@Override
	public void apply() {

		HumanBiology.applyChange(this.human, HumanBiology.bt, getBodyTempChange(this.human, zone));
		HumanBiology.applyChange(this.human, HumanBiology.fitness, getFitnessChange(this.human,zone));
		HumanBiology.applyChange(this.human, HumanBiology.hr, getHeartRateChange(human, zone));
		HumanBiology.applyChange(this.human, HumanBiology.water, getWaterChange(human, zone));
	}


	private double getWaterChange(Human human, Zone zone) {
		Double current_temp = (Double) this.human.getHumanPropertyWithName(HumanBiology.bt).getValue();
		
		double water_change = 0.0;
		if(zone == Zone.ZONE_RESTING) {
			water_change = 0.0;
		} else if(zone == Zone.ZONE_RESTITUTING) {
			water_change = -1.0/(double)Utilities.hours_to_ticks(2);
		} else if(zone == Zone.ZONE_TRAINING) {
			water_change = -1.0/(double)Utilities.hours_to_ticks(1);
		}
		return water_change;
	}

	private double getBodyTempChange(Human human, Zone zone) {
		Double set_point_temp = (Double) this.human.getHumanPropertyWithName(HumanBiology.bt_sp).getValue();
		Double current_temp = (Double) this.human.getHumanPropertyWithName(HumanBiology.bt).getValue();
		
		//running a marathon can rais your bt to max 40C
		double factor = 1.0/(double)Utilities.hours_to_ticks(4);
		
		double bt_change = 0.0;
		if(zone == Zone.ZONE_RESTING) {
			//stabilize at bt_sp, it will take maximum 1 hour to get from max to set-point
			factor *= 4;
			bt_change = (set_point_temp - current_temp)*factor;
		} else if(zone == Zone.ZONE_RESTITUTING) {
			//stabilize at bt_sp
			bt_change = (set_point_temp - current_temp)*factor;
		} else if(zone == Zone.ZONE_TRAINING) {
			//increase up to max_training_body_temperate
			bt_change = (max_training_body_temperature - current_temp)*factor;
		}
		return bt_change;
	}
	
	private Double getFitnessChange(Human human, Zone zone) {
		double fitness_change = 0.0; //vo2max
		//training 12 hours should increase fitness with 1 vo2max
		double factor = 1.0/(double)Utilities.hours_to_ticks(12);
		if(zone == Zone.ZONE_RESTING) {
			//decrease
			fitness_change = -factor;
		} else if(zone == Zone.ZONE_RESTITUTING) {
			//stable
			fitness_change = 0.0;
		} else if(zone == Zone.ZONE_TRAINING) {
			//increase
			fitness_change = factor;
		}
		return fitness_change;
	}

	private double getHeartRateChange(Human human, Zone zone) {
		
		double current_heart_rate = (Double) human.getHumanPropertyWithName(HumanBiology.hr).getValue();
		double heart_rate_resting = (Double) human.getHumanPropertyWithName(HumanBiology.hr_resting).getValue();
		double heart_rate_max = (Double) human.getHumanPropertyWithName(HumanBiology.hr_max).getValue();

		double factor = 0.001;
		
		double hr_change = 0.0;
		if(zone == Zone.ZONE_RESTING) {
			//stabilize at hr_rest
			hr_change = (heart_rate_resting - current_heart_rate) * factor;
		} else if(zone == Zone.ZONE_RESTITUTING) {
			//stabilize at hr_rest + max_walking_heart_rate_addition
			hr_change = ((heart_rate_resting + max_walking_heart_rate_addition) - current_heart_rate) * factor;
		} else if(zone == Zone.ZONE_TRAINING) {
			//stabilize at hr_max
			hr_change = (heart_rate_max - current_heart_rate) * factor;
		}
		
		return hr_change;
	}

}
