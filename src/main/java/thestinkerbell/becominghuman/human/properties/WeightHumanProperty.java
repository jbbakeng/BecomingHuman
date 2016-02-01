package thestinkerbell.becominghuman.human.properties;

import thestinkerbell.becominghuman.human.properties.AgeHumanProperty.AgeRisk;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskFactor;

public class WeightHumanProperty extends HumanProperty {
	
	public WeightHumanProperty() {
		super("Weight", 75, "kg", 0, 635);
		this.risk_factors.add(new RiskFactor(WeightRisk.WEIGHT_LOWWEIGHT, 0, 49));
		this.risk_factors.add(new RiskFactor(WeightRisk.WEIGHT_NORMALWEIGHT, 50, 90));
		this.risk_factors.add(new RiskFactor(WeightRisk.WEIGHT_HIGHWEIGHT, 91, 635));
	}
	
	public enum WeightRisk implements Risk  {
		WEIGHT_LOWWEIGHT,
		WEIGHT_NORMALWEIGHT,
		WEIGHT_HIGHWEIGHT	
	}

}
