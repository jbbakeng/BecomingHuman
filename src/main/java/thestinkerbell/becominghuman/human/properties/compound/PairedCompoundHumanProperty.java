package thestinkerbell.becominghuman.human.properties.compound;

import com.google.common.collect.Range;

import thestinkerbell.becominghuman.human.properties.HumanProperty;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskRange;
import thestinkerbell.becominghuman.utilities.Pair;

public abstract class PairedCompoundHumanProperty<A, B> extends HumanProperty<Pair<Double>> {

	protected A propertyA;
	protected B propertyB;
	
	public PairedCompoundHumanProperty(String name, Pair defaultValue, String unit, Pair range_min, Pair range_max) {
		super(name, defaultValue, unit, range_min, range_max);
	}

	@Override
	public Pair<Double> getValue() {
		return this.value;
	}

	@Override
	final public void setValue(Pair<Double> value) {
		//PairedCompoundHumanProperty calculates its value based on its BasicHumanProperty values
		return;
	}
	
	@Override
	final public Risk getRisk() {
		return RiskRange.getRisk(risk_ranges, this.value);
	}

}
