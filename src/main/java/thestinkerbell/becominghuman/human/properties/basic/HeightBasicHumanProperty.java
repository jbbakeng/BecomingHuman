package thestinkerbell.becominghuman.human.properties.basic;

import thestinkerbell.becominghuman.human.risks.DoubleRiskRange;
import thestinkerbell.becominghuman.human.risks.Risk;

final public class HeightBasicHumanProperty extends BasicHumanProperty {
	
	public HeightBasicHumanProperty() {
		super("Height", 170, "cm", 55, 272);
		this.risk_ranges.add(new DoubleRiskRange(HeightRisk.HEIGHT_DWARF, 55, 140));
		this.risk_ranges.add(new DoubleRiskRange(HeightRisk.HEIGHT_SHORT, 141, 159));
		this.risk_ranges.add(new DoubleRiskRange(HeightRisk.HEIGHT_NORMAL, 160, 190));
		this.risk_ranges.add(new DoubleRiskRange(HeightRisk.HEIGHT_TALL, 191, 200));
		this.risk_ranges.add(new DoubleRiskRange(HeightRisk.HEIGHT_GIANT, 201, 272));
	}
	
	public enum HeightRisk implements Risk  {
		HEIGHT_DWARF,
		HEIGHT_SHORT,
		HEIGHT_NORMAL,
		HEIGHT_TALL,
		HEIGHT_GIANT
	}

}
