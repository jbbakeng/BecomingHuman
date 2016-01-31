package thestinkerbell.becominghuman.human.properties;

import com.google.common.collect.Range;

import thestinkerbell.becominghuman.human.risks.RiskFactor;

public class HearthRateHumanProperty extends HumanProperty {
	
	public HearthRateHumanProperty() {
		super("Heart Rate", 70, "bpm", Range.closed(0, 220));
	}
	
	public enum HeartRateRiskFactor implements RiskFactor  {
		HR_LOW,
		HR_NORMAL,
		HR_HIGH
	}

}
