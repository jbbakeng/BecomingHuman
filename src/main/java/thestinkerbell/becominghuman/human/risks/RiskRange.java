package thestinkerbell.becominghuman.human.risks;

import java.util.List;

import com.google.common.collect.Range;

import thestinkerbell.becominghuman.utilities.Pair;

public class RiskRange<TYPE> {
	
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
	
	public Boolean contains(TYPE value) {
		if(value instanceof Double)
			return this.contains((Double)value);
		if(value instanceof Integer)
			return this.contains((Integer)value);
		if(value instanceof Pair<?>)
			return this.contains((Pair<Double>)value);
		
		System.err.println("Not implemented");
		return null;
	}

	public Boolean contains(Integer value) {
		return Range.closed((Integer)this.min, (Integer)this.max).contains(value);
	}
	
	public Boolean contains(Double value) {
		return Range.closed((Double)this.min, (Double)this.max).contains(value);
	}
	
	public Boolean contains(Pair<Double> value) {
		Pair<Double> min = (Pair<Double>)this.min;
		Pair<Double> max = (Pair<Double>)this.max;
		Boolean containsFirst = Range.closed(min.first(), max.first()).contains(value.first());
		Boolean containsSecond = Range.closed(min.second(), max.second()).contains(value.second());
		return (containsFirst && containsSecond);
	}
	
    public String toString() {
        return  this.risk + ": " + this.min + ", "+this.max;
    }

}
