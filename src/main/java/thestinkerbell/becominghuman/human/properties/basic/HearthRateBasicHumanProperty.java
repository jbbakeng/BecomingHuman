package thestinkerbell.becominghuman.human.properties.basic;

import thestinkerbell.becominghuman.human.risks.DoubleRiskRange;
import thestinkerbell.becominghuman.human.risks.Risk;

public class HearthRateBasicHumanProperty extends BasicHumanProperty {
	
	public HearthRateBasicHumanProperty() {
		super("Heart Rate", 70, "bpm", 0, 220);
		this.risk_ranges.add(new DoubleRiskRange(HeartRateRisk.HR_LOW, 0, 49));
		this.risk_ranges.add(new DoubleRiskRange(HeartRateRisk.HR_NORMAL, 50, 90));
		this.risk_ranges.add(new DoubleRiskRange(HeartRateRisk.HR_HIGH, 91, 220));
	}
	
	public enum HeartRateRisk implements Risk  {
		HR_LOW,
		HR_NORMAL,
		HR_HIGH
	}

}
