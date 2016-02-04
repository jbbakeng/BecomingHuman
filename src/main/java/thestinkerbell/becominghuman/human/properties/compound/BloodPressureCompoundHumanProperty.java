package thestinkerbell.becominghuman.human.properties.compound;

import thestinkerbell.becominghuman.human.properties.basic.DiastolicBloodPressureBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.SystolicBloodPressureBasicHumanProperty;
import thestinkerbell.becominghuman.human.risks.PairDoubleRiskRange;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskRange;
import thestinkerbell.becominghuman.utilities.Pair;

public class BloodPressureCompoundHumanProperty extends PairedCompoundHumanProperty<SystolicBloodPressureBasicHumanProperty, DiastolicBloodPressureBasicHumanProperty> {
	
	public BloodPressureCompoundHumanProperty(SystolicBloodPressureBasicHumanProperty systolic, DiastolicBloodPressureBasicHumanProperty diastolic) {
		super("Blood Pressure", new Pair(105.0, 70.0), "mmHg", new Pair(0.0, 0.0), new Pair(340.0, 190.0));
		this.propertyA = systolic;
		this.propertyB = diastolic;
		this.risk_ranges.add(new PairDoubleRiskRange(BloodPressureRisk.BP_HYPOTENSION, new Pair(0.0, 0.0), new Pair(90.0, 60.0)));
		this.risk_ranges.add(new PairDoubleRiskRange(BloodPressureRisk.BP_DESIRED, new Pair(91.0, 61.0), new Pair(119.0, 79.0)));
		this.risk_ranges.add(new PairDoubleRiskRange(BloodPressureRisk.BP_PREHYPERTENSION, new Pair(120.0, 80.0), new Pair(139.0, 89.0)));
		this.risk_ranges.add(new PairDoubleRiskRange(BloodPressureRisk.BP_STAGE1HYPERTENSION, new Pair(140.0, 90.0), new Pair(159.0, 99.0)));
		this.risk_ranges.add(new PairDoubleRiskRange(BloodPressureRisk.BP_STAGE2HYPERTENSION, new Pair(160.0, 100.0), new Pair(179.0, 109.0)));
		this.risk_ranges.add(new PairDoubleRiskRange(BloodPressureRisk.BP_HYPERTENSIVEURGENCY, new Pair(180.0, 110.0), new Pair(340.0, 190.0)));
	}
	
	@Override
	final public Pair<Double> getValue() {
		return new Pair<Double>(this.propertyA.getValue(), this.propertyB.getValue());
	}
	
	public enum BloodPressureRisk implements Risk  {
		//https://en.wikipedia.org/wiki/Blood_pressure
		BP_HYPOTENSION,
		BP_DESIRED,
		BP_PREHYPERTENSION,
		BP_STAGE1HYPERTENSION,
		BP_STAGE2HYPERTENSION,
		BP_HYPERTENSIVEURGENCY
	}
}
