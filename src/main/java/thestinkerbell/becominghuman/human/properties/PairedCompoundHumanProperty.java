package thestinkerbell.becominghuman.human.properties;

import com.google.common.collect.Range;

import thestinkerbell.becominghuman.human.properties.basic.DiastolicBloodPressureBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.SystolicBloodPressureBasicHumanProperty;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskRange;
import thestinkerbell.becominghuman.utilities.Pair;

public abstract class PairedCompoundHumanProperty<A, B> extends TwoTypesCompoundHumanProperty<A, B, Pair<Double>> {

	public PairedCompoundHumanProperty(String propertyName, Pair<Double> defaultValue, String unit, Pair<Double> range_min, Pair<Double> range_max, A propertyA, B propertyB) {
		super(propertyName, defaultValue, unit, range_min, range_max, propertyA, propertyB);
	}
}
