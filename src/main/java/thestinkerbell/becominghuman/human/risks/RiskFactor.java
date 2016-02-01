package thestinkerbell.becominghuman.human.risks;

import com.google.common.collect.Range;

public class RiskFactor {
	
	public final Risk risk;
	final private Integer min;
	final private Integer max;
	
	public RiskFactor(Risk risk, Integer min, Integer max) {
		this.risk = risk;
		this.min = min;
		this.max = max;
	}

	public boolean contains(Integer value) {
		return Range.closed(min, max).contains(value);
	}
}
