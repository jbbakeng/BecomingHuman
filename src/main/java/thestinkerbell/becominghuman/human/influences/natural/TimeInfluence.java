package thestinkerbell.becominghuman.human.influences.natural;

import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.influences.HumanInfluence;
import thestinkerbell.becominghuman.human.influences.Influence;
import thestinkerbell.becominghuman.human.properties.Property;
import thestinkerbell.becominghuman.utilities.Utilities;

public class TimeInfluence extends HumanInfluence implements Influence {

	private final String age = "Age";
	private final int ticks;
	
	public TimeInfluence(Human human, int ticks) {
		super(human);
		this.ticks = ticks;
	}
	
	@Override 
	public void apply() {
		
		Property age_property = this.human.getHumanPropertyWithName(age);
		Double old_age = (Double) age_property.getValue();
		Double change = Utilities.ticks_to_years(this.ticks);
		Double new_age = old_age + change;
		
		try {
			this.human.setValue(age, new_age);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
