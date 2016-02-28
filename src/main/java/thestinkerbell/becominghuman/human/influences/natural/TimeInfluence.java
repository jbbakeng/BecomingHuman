package thestinkerbell.becominghuman.human.influences.natural;

import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.HumanBiology;
import thestinkerbell.becominghuman.human.influences.HumanInfluence;
import thestinkerbell.becominghuman.human.influences.Influence;
import thestinkerbell.becominghuman.human.properties.Property;
import thestinkerbell.becominghuman.utilities.Utilities;

public class TimeInfluence extends HumanInfluence implements Influence {

	private final int ticks;
	
	public TimeInfluence(Human human, int ticks) {
		super(human);
		this.ticks = ticks;
	}
	
	@Override 
	public void apply() {
		
		HumanBiology.applyChange(this.human, HumanBiology.age, getAgeChange());
		HumanBiology.applyChange(this.human, HumanBiology.sleepy, getSleepyChange(this.human, ticks));
	}

	public double getAgeChange() {
		double age_change = Utilities.ticks_to_years(this.ticks);
		return age_change;
	}

	private double getSleepyChange(Human human, int ticks) {
		double sleepy_change = ticks/(double)Utilities.hours_to_ticks(24);
		return sleepy_change*100.0;
	}

}
