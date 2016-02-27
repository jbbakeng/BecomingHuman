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
		
		Double change = Utilities.ticks_to_years(this.ticks);
		HumanBiology.applyChange(this.human, HumanBiology.age, change);
		
	}

}
