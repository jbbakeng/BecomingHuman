package thestinkerbell.becominghuman.human.properties.basic;

import thestinkerbell.becominghuman.human.HumanBiology;
import thestinkerbell.becominghuman.human.properties.BasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.AgeBasicHumanProperty.AgeRisk;
import thestinkerbell.becominghuman.human.risks.DoubleRiskRange;
import thestinkerbell.becominghuman.human.risks.Risk;

public class FitnessBasicHumanProperty extends BasicHumanProperty {

	public FitnessBasicHumanProperty() {
		super(HumanBiology.fitness, 45, "mL/(kg*min)", 0, 100);
		this.risk_ranges.add(new DoubleRiskRange(FitnessRisk.FITNESS_POOR, 0, 41));
		this.risk_ranges.add(new DoubleRiskRange(FitnessRisk.FITNESS_FAIR, 42, 45));
		this.risk_ranges.add(new DoubleRiskRange(FitnessRisk.FITNESS_GOOD, 46, 50));
		this.risk_ranges.add(new DoubleRiskRange(FitnessRisk.FITNESS_EXCELLENT, 51, 55));
		this.risk_ranges.add(new DoubleRiskRange(FitnessRisk.FITNESS_SUPERIOR, 56, 100));
	}
	
	public enum FitnessRisk implements Risk {
		FITNESS_POOR,
		FITNESS_FAIR,
		FITNESS_GOOD,
		FITNESS_EXCELLENT,
		FITNESS_SUPERIOR
	};
	
}
