package thestinkerbell.becominghuman.human.risks;

import com.google.common.collect.Range;

public class RiskRange {
	
	public Risk risk;
	public Double min;
	public Double max;
	
	public RiskRange(Risk risk, Integer min, Integer max) {
		this(risk, (double) min, (double) max);
	}
	
	public RiskRange(Risk risk, Double min, Double max) {
		this.risk = risk;
		this.min = min;
		this.max = max;
	}
	
	public Boolean contains(Double value) {
		return Range.closed(this.min, this.max).contains(value);
	}

}
