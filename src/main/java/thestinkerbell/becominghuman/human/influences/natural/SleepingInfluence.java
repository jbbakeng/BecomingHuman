package thestinkerbell.becominghuman.human.influences.natural;

import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.HumanBiology;
import thestinkerbell.becominghuman.human.influences.HumanInfluence;
import thestinkerbell.becominghuman.human.influences.Influence;

public class SleepingInfluence extends HumanInfluence implements Influence {

	public SleepingInfluence(Human human) {
		super(human);
	}
	
	@Override
	public void apply() {
		HumanBiology.applyChange(this.human, HumanBiology.sleepy, getSleepyChange(human));
	}

	private double getSleepyChange(Human human) {
		double sleepy_change = -100.0;
		return sleepy_change;
	}

}
