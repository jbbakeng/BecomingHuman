package thestinkerbell.becominghuman.human.properties.basic;

import thestinkerbell.becominghuman.human.HumanBiology;
import thestinkerbell.becominghuman.human.properties.BasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.AgeBasicHumanProperty.AgeRisk;
import thestinkerbell.becominghuman.human.risks.DoubleRiskRange;
import thestinkerbell.becominghuman.human.risks.Risk;

public class WaterBasicHumanProperty extends BasicHumanProperty {

	public WaterBasicHumanProperty() {
		super(HumanBiology.water, 50, "liters", 0, 635);
	}
}
