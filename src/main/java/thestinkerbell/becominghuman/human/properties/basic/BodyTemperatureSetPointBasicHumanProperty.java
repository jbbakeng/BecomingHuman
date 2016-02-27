package thestinkerbell.becominghuman.human.properties.basic;

import thestinkerbell.becominghuman.human.HumanBiology;

final public class BodyTemperatureSetPointBasicHumanProperty extends BodyTemperatureBasicHumanProperty{

	public BodyTemperatureSetPointBasicHumanProperty() {
		super(HumanBiology.bt_sp, 37, "celcius", -273, 980);
	}
}
