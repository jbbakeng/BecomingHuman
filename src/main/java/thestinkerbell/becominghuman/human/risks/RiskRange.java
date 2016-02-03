package thestinkerbell.becominghuman.human.risks;

public class RiskRange {
	
	public final Risk risk;
	public final Integer min;
	public final Integer max;
	
	public RiskRange(Risk risk, Integer min, Integer max) {
		this.risk = risk;
		this.min = min;
		this.max = max;
	}

	public boolean contains(Integer value) {
		return new ClosedRange(min, max).range.contains(value);
	}
}
