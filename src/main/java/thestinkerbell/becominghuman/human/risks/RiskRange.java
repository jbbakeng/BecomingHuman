package thestinkerbell.becominghuman.human.risks;

import java.util.List;

public abstract class RiskRange<TYPE> {
	
	public Risk risk;
	public TYPE min;
	public TYPE max;
	
	static public <TYPE> Risk getRisk(List<RiskRange> risk_ranges, TYPE value) {
		Risk risk = null;
		for(RiskRange range : risk_ranges)
		{
			if(range.contains(value)){
				risk = range.risk;
				break;
			}
		}
		return risk;
	}

	public RiskRange(Risk risk, TYPE min, TYPE max) {
		this.risk = risk;
		this.min = min;
		this.max = max;
	}
	
	public abstract Boolean contains(TYPE value);

	
    public String toString() {
        return  this.risk + ": " + this.min + ", "+this.max;
    }

}
