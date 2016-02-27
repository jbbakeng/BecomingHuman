package thestinkerbell.becominghuman.human.properties.basic;

import thestinkerbell.becominghuman.human.HumanBiology;
import thestinkerbell.becominghuman.human.properties.BasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.AgeBasicHumanProperty.AgeRisk;
import thestinkerbell.becominghuman.human.risks.DoubleRiskRange;
import thestinkerbell.becominghuman.human.risks.Risk;

public class WhiteBloodCellsBasicHumanProperty extends BasicHumanProperty {

	
	public WhiteBloodCellsBasicHumanProperty() {
		//https://www.sharecare.com/health/blood-basics/how-many-white-blood-cells
		//cells per microliter of blood = mcL
		//https://www.nlm.nih.gov/medlineplus/ency/article/003643.htm
		super(HumanBiology.wbc, 7000.0, "mcL", 0.0, 20000.0);
		this.risk_ranges.add(new DoubleRiskRange(WhiteBloodCellsRisk.WBC_LOW, 0.0, 4499.0));
		this.risk_ranges.add(new DoubleRiskRange(WhiteBloodCellsRisk.WBC_NORMAL, 4500.0, 10000.0));
		this.risk_ranges.add(new DoubleRiskRange(WhiteBloodCellsRisk.WBC_HIGH, 10001.0, 20000.0));
	}
	
	public enum WhiteBloodCellsRisk implements Risk  {
		WBC_LOW,
		WBC_NORMAL,
		WBC_HIGH
	}
}
