package thestinkerbell.becominghuman.human.risks;

import com.google.common.collect.Range;

public class DoubleRiskRange extends RiskRange<Double> {

	public DoubleRiskRange(Risk risk, Double min, Double max) {
		super(risk, min, max);
	}
	
	public Boolean contains(Double value) {
		return Range.closed(this.min, this.max).contains(value);
	}
	
	// For convenience
	public DoubleRiskRange(Risk risk, Integer min, Integer max) {
		this(risk, min.doubleValue(), max.doubleValue());
	}
	
	public Boolean contains(Integer value) {
		return this.contains(value.doubleValue());
	}

}
