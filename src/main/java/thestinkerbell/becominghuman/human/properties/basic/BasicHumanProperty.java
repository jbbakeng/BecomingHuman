package thestinkerbell.becominghuman.human.properties.basic;

import thestinkerbell.becominghuman.human.properties.DoubleHumanProperty;
import thestinkerbell.becominghuman.human.risks.DoubleRiskRange;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskRange;

public class BasicHumanProperty extends DoubleHumanProperty {

	public BasicHumanProperty() {
		this("DEFAULT_NAME_BASIC", 0.0, "DEFAULT_UNIT_BASIC", 0.0, 1.0);
		this.risk_ranges.add(new DoubleRiskRange(GeneralRisk.HEALTHY, range_min, range_max));
	}
	
	public BasicHumanProperty(String propertyName, Integer defaultValue, String unit, Integer range_min, Integer range_max) {
		this(propertyName, new Double(defaultValue.doubleValue()), unit, new Double(range_min.doubleValue()), new Double(range_max.doubleValue()));
	}
	
	public BasicHumanProperty(String propertyName, Double defaultValue, String unit, Double range_min, Double range_max) {
		super(propertyName, defaultValue, unit, range_min, range_max);
	}

	@Override
	final public Risk getRisk() {
		return RiskRange.getRisk(risk_ranges, this.value);
	}

}
