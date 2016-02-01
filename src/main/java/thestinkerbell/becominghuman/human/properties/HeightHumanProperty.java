package thestinkerbell.becominghuman.human.properties;

import thestinkerbell.becominghuman.human.properties.HearthRateHumanProperty.HeartRateRisk;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskFactor;

public class HeightHumanProperty extends HumanProperty {
	
	public HeightHumanProperty() {
		super("Height", 170, "cm", 55, 272);
		this.risk_factors.add(new RiskFactor(HeightRisk.HEIGHT_DWARF, 55, 140));
		this.risk_factors.add(new RiskFactor(HeightRisk.HEIGHT_SHORT, 141, 159));
		this.risk_factors.add(new RiskFactor(HeightRisk.HEIGHT_NORMAL, 160, 190));
		this.risk_factors.add(new RiskFactor(HeightRisk.HEIGHT_TALL, 191, 200));
		this.risk_factors.add(new RiskFactor(HeightRisk.HEIGHT_GIANT, 201, 272));
	}
	
	public enum HeightRisk implements Risk  {
		HEIGHT_DWARF,
		HEIGHT_SHORT,
		HEIGHT_NORMAL,
		HEIGHT_TALL,
		HEIGHT_GIANT
	}

}
