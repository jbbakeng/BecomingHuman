package thestinkerbell.becominghuman.human.properties.basic;

import thestinkerbell.becominghuman.human.properties.basic.HearthRateBasicHumanProperty.HeartRateRisk;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskRange;

public class SystolicBloodPressureBasicHumanProperty extends BasicHumanProperty{

	public SystolicBloodPressureBasicHumanProperty() {
		super("Systolic Blood Pressure", 105, "mmHg", 0, 340);
		this.risk_ranges.add(new RiskRange(SystolicBloodPressureRateRisk.SBP_LOW, 0, 89));
		this.risk_ranges.add(new RiskRange(SystolicBloodPressureRateRisk.SBP_NORMAL, 90, 119));
		this.risk_ranges.add(new RiskRange(SystolicBloodPressureRateRisk.SBP_HIGH, 120, 340));
	}
	
	public enum SystolicBloodPressureRateRisk implements Risk  {
		SBP_LOW,
		SBP_NORMAL,
		SBP_HIGH
	}
}
