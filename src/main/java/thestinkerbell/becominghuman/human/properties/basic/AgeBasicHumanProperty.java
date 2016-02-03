package thestinkerbell.becominghuman.human.properties.basic;

import java.util.ArrayList;
import java.util.List;

import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskRange;

public class AgeBasicHumanProperty extends BasicHumanProperty {
	
	public AgeBasicHumanProperty() {
		super("Age", 18, "years", 0, 122);
		this.risk_ranges.add(new RiskRange(AgeRisk.AGE_INFANCY, 0, 2));
		this.risk_ranges.add(new RiskRange(AgeRisk.AGE_EARLYCHILDHOOD, 3, 5));
		this.risk_ranges.add(new RiskRange(AgeRisk.AGE_MIDDLECHILDHOOD, 6, 8));
		this.risk_ranges.add(new RiskRange(AgeRisk.AGE_LATECHILDHOOD, 9, 11));
		this.risk_ranges.add(new RiskRange(AgeRisk.AGE_ADOLESCENCE, 12, 20));
		this.risk_ranges.add(new RiskRange(AgeRisk.AGE_EARLYADULTHOOD, 21, 35));
		this.risk_ranges.add(new RiskRange(AgeRisk.AGE_MIDLIFE, 36, 50));
		this.risk_ranges.add(new RiskRange(AgeRisk.AGE_MATUREADULTHOOD, 51, 79));
		this.risk_ranges.add(new RiskRange(AgeRisk.AGE_LATEADULTHOOD, 80, 122));
	}

	public enum AgeRisk implements Risk  {
		//http://www.institute4learning.com/stages_of_life.php
		AGE_INFANCY, //0-2
		AGE_EARLYCHILDHOOD, //3-5
		AGE_MIDDLECHILDHOOD, //6-8
		AGE_LATECHILDHOOD, //9-11
		AGE_ADOLESCENCE, //12-20
		AGE_EARLYADULTHOOD, //21-35
		AGE_MIDLIFE, //36-50
		AGE_MATUREADULTHOOD, //50-80
		AGE_LATEADULTHOOD, //80+
	};
}
