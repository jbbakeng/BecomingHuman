package thestinkerbell.becominghuman.human.properties.basic;

import thestinkerbell.becominghuman.human.HumanBiology;
import thestinkerbell.becominghuman.human.properties.BasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.FitnessBasicHumanProperty.FitnessRisk;
import thestinkerbell.becominghuman.human.risks.DoubleRiskRange;
import thestinkerbell.becominghuman.human.risks.Risk;

public class SleepyBasicHumanProperty extends BasicHumanProperty {

	public SleepyBasicHumanProperty() {
		super(HumanBiology.sleepy, 0.0, "%", 0.0, 100.0);
		this.risk_ranges.add(new DoubleRiskRange(SleepyRisk.SLEEPY_ENERGIZED, 0, 19));
		this.risk_ranges.add(new DoubleRiskRange(SleepyRisk.SLEEPY_REFRESHED, 20, 39));
		this.risk_ranges.add(new DoubleRiskRange(SleepyRisk.SLEEPY_BALANCED, 40, 66));
		this.risk_ranges.add(new DoubleRiskRange(SleepyRisk.SLEEPY_TIRED, 67, 89));
		this.risk_ranges.add(new DoubleRiskRange(SleepyRisk.SLEEPY_EXHAUSTED, 90, 100));
	}
	
	public enum SleepyRisk implements Risk {
		SLEEPY_ENERGIZED,
		SLEEPY_REFRESHED,
		SLEEPY_BALANCED,
		SLEEPY_TIRED,
		SLEEPY_EXHAUSTED
	};
	
}
