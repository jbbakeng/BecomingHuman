package thestinkerbell.becominghuman.human.properties.compound;

import thestinkerbell.becominghuman.human.properties.HumanProperty;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskRange;

public abstract class CompoundHumanProperty<A, B, TYPE> extends HumanProperty<TYPE> {

	protected A propertyA;
	protected B propertyB;

	public CompoundHumanProperty(String propertyName, TYPE defaultValue, String unit, TYPE range_min, TYPE range_max) {
		super(propertyName, defaultValue, unit, range_min, range_max);
	}

	@Override
	public TYPE getValue() {
		return this.value;
	}

	@Override
	public final void setValue(TYPE value) {
		//CompoundHumanProperty calculates its value based on its BasicHumanProperty values
		return;
	}

	@Override
	public final Risk getRisk() {
		return RiskRange.getRisk(risk_ranges, this.value);
	}

}