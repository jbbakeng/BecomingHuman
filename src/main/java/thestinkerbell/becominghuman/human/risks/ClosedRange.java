package thestinkerbell.becominghuman.human.risks;

import com.google.common.collect.Range;

public class ClosedRange {
	final public Range range;
	
	public ClosedRange(Integer lower, Integer upper) {
		this.range = Range.closed(lower, upper);
	}

}
