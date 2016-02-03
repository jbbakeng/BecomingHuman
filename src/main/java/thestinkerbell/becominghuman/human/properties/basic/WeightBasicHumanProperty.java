package thestinkerbell.becominghuman.human.properties.basic;

import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskRange;

public class WeightBasicHumanProperty extends BasicHumanProperty {
	
	public WeightBasicHumanProperty() {
		super("Weight", 75, "kg", 0, 635);
		this.risk_ranges.add(new RiskRange(WeightRisk.WEIGHT_LOWWEIGHT, 0, 49));
		this.risk_ranges.add(new RiskRange(WeightRisk.WEIGHT_NORMALWEIGHT, 50, 90));
		this.risk_ranges.add(new RiskRange(WeightRisk.WEIGHT_HIGHWEIGHT, 91, 635));
	}
	
	public enum WeightRisk implements Risk  {
		WEIGHT_LOWWEIGHT,
		WEIGHT_NORMALWEIGHT,
		WEIGHT_HIGHWEIGHT	
	}

}
