package thestinkerbell.becominghuman.human.properties.basic;

import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskRange;

public class DiastolicBloodPressureBasicHumanProperty extends BasicHumanProperty {
	
	public DiastolicBloodPressureBasicHumanProperty() {
		super("Systolic Blood Pressure", 70, "mmHg", 0, 190);
		this.risk_ranges.add(new RiskRange(DiastoligBloodPressureRateRisk.DBP_LOW, 0, 59));
		this.risk_ranges.add(new RiskRange(DiastoligBloodPressureRateRisk.DBP_NORMAL, 60, 79));
		this.risk_ranges.add(new RiskRange(DiastoligBloodPressureRateRisk.DBP_HIGH, 80, 190));
	}
	
	public enum DiastoligBloodPressureRateRisk implements Risk  {
		DBP_LOW,
		DBP_NORMAL,
		DBP_HIGH
	}
}
