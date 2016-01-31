package thestinkerbell.becominghuman.human.properties;

import thestinkerbell.becominghuman.human.risks.RiskFactor;

public class AgeHumanProperty extends HumanProperty {
	
	public AgeHumanProperty() {
		super("Age", 18, "years", 0, 122);
	}

	public enum AgeRiskFactor implements RiskFactor  {
		//http://www.institute4learning.com/stages_of_life.php
		AGE_INFANCY, //0-3
		AGE_EARLYCHILDHOOD, //3-6
		AGE_MIDDLECHILDHOOD, //6-8
		AGE_LATECHILDHOOD, //9-11
		AGE_ADOLESCENCE, //12-20
		AGE_EARLYADULTHOOD, //20-35
		AGE_MIDLIFE, //35-50
		AGE_MATUREADULTHOOD, //50-80
		AGE_LATEADULTHOOD, //80+
	};
}
