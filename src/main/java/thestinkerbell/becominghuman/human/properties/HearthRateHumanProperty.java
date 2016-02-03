package thestinkerbell.becominghuman.human.properties;

import thestinkerbell.becominghuman.human.properties.BodyTemperatureHumanProperty.BodyTemperatureRisk;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskRange;

public class HearthRateHumanProperty extends HumanProperty {
	
	public HearthRateHumanProperty() {
		super("Heart Rate", 70, "bpm", 0, 220);
		this.risk_ranges.add(new RiskRange(HeartRateRisk.HR_LOW, 0, 49));
		this.risk_ranges.add(new RiskRange(HeartRateRisk.HR_NORMAL, 50, 90));
		this.risk_ranges.add(new RiskRange(HeartRateRisk.HR_HIGH, 91, 220));
	}
	
	public enum HeartRateRisk implements Risk  {
		HR_LOW,
		HR_NORMAL,
		HR_HIGH
	}

}
