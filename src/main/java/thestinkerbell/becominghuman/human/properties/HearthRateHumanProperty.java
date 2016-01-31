package thestinkerbell.becominghuman.human.properties;

import thestinkerbell.becominghuman.human.risks.RiskFactor;

public class HearthRateHumanProperty extends HumanProperty {
	
	public HearthRateHumanProperty() {
		super("Heart Rate", 70, "bpm", 0, 220);
	}
	
	public enum HeartRateRiskFactor implements RiskFactor  {
		HR_LOW,
		HR_NORMAL,
		HR_HIGH
	}

}
