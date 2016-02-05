package thestinkerbell.becominghuman.human.properties.basic;

import thestinkerbell.becominghuman.human.risks.DoubleRiskRange;
import thestinkerbell.becominghuman.human.risks.Risk;

final public class SystolicBloodPressureBasicHumanProperty extends BasicHumanProperty{

	public SystolicBloodPressureBasicHumanProperty() {
		super("Systolic Blood Pressure", 105, "mmHg", 0, 340);
		this.risk_ranges.add(new DoubleRiskRange(SystolicBloodPressureRateRisk.SBP_LOW, 0, 89));
		this.risk_ranges.add(new DoubleRiskRange(SystolicBloodPressureRateRisk.SBP_NORMAL, 90, 119));
		this.risk_ranges.add(new DoubleRiskRange(SystolicBloodPressureRateRisk.SBP_HIGH, 120, 340));
	}
	
	public enum SystolicBloodPressureRateRisk implements Risk  {
		SBP_LOW,
		SBP_NORMAL,
		SBP_HIGH
	}
}
