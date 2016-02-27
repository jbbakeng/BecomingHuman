package thestinkerbell.becominghuman.human.properties;

import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskRange;

public abstract class OneTypeCompoundHumanProperty<A, TYPE> extends HumanProperty<TYPE> {

	protected A propertyA;

	public OneTypeCompoundHumanProperty(String propertyName, TYPE defaultValue, String unit, TYPE range_min, TYPE range_max, A propertyA) {
		super(propertyName, defaultValue, unit, range_min, range_max);
		this.propertyA = propertyA;
	}

	@Override
	public TYPE getValue() {
		return this.value;
	}

	@Override
	final public void setValue(TYPE value) {
		//CompoundHumanProperty calculates its value based on its BasicHumanProperty values
		return;
	}

	@Override
	final public Risk getRisk() {
		return RiskRange.getRisk(risk_ranges, this.getValue());
	}
}