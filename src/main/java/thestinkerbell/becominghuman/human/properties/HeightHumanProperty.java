package thestinkerbell.becominghuman.human.properties;

import com.google.common.collect.Range;

import thestinkerbell.becominghuman.human.risks.RiskFactor;

public class HeightHumanProperty extends HumanProperty {
	
	public HeightHumanProperty() {
		super("Height", 170, "cm", Range.closed(55, 272));
	}
	
	public enum HeightRiskFactor implements RiskFactor  {
		HEIGHT_SHORT,
		HEIGHT_NORMAL,
		HEIGHT_TALL
	}

}
