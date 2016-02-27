package thestinkerbell.becominghuman.human.properties.compound;

import thestinkerbell.becominghuman.human.HumanBiology;
import thestinkerbell.becominghuman.human.properties.DoubleOneTypeCompoundHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.AgeBasicHumanProperty;

public class HeartRateMaxBasicHumanProperty extends DoubleOneTypeCompoundHumanProperty<AgeBasicHumanProperty> {
	
	private final static double max_hr_max = 220.0;
	
	public HeartRateMaxBasicHumanProperty(AgeBasicHumanProperty age) {
		super(HumanBiology.hr_max, max_hr_max, "bpm", 0.0, max_hr_max, age);
	}
	
	@Override
	final public Double getValue() {
		Double age = propertyA.getValue();
		return max_hr_max - age;
	}
}
