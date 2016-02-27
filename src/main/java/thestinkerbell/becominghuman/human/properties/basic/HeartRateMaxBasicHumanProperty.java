package thestinkerbell.becominghuman.human.properties.basic;

import thestinkerbell.becominghuman.human.HumanBiology;

public class HeartRateMaxBasicHumanProperty extends HeartRateBasicHumanProperty {
	
	public HeartRateMaxBasicHumanProperty() {
		super(HumanBiology.hr_max, 220, "bpm", 0, 220);
	}
}
