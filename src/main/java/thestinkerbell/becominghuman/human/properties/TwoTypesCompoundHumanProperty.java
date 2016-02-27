package thestinkerbell.becominghuman.human.properties;

import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskRange;

public abstract class TwoTypesCompoundHumanProperty<A, B, TYPE> extends OneTypeCompoundHumanProperty<A, TYPE>{

	protected B propertyB;

	public TwoTypesCompoundHumanProperty(String propertyName, TYPE defaultValue, String unit, TYPE range_min, TYPE range_max, A propertyA, B propertyB) {
		super(propertyName, defaultValue, unit, range_min, range_max, propertyA);
		this.propertyB = propertyB;
	}

}