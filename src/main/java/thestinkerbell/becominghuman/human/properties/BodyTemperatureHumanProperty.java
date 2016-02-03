package thestinkerbell.becominghuman.human.properties;

import thestinkerbell.becominghuman.human.properties.HumanProperty.GeneralRisk;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskRange;

public class BodyTemperatureHumanProperty extends HumanProperty {

	public BodyTemperatureHumanProperty() {
		super("Body Temperatur", 37, "celcius", -273, 980);
		this.risk_ranges.add(new RiskRange(BodyTemperatureRisk.BODYTEMPERATURE_VERYCOLD, -273, 31));
		this.risk_ranges.add(new RiskRange(BodyTemperatureRisk.BODYTEMPERATURE_COLD, 32, 35));
		this.risk_ranges.add(new RiskRange(BodyTemperatureRisk.BODYTEMPERATURE_NORMAL, 36, 38));
		this.risk_ranges.add(new RiskRange(BodyTemperatureRisk.BODYTEMPERATURE_WARM, 39, 42));
		this.risk_ranges.add(new RiskRange(BodyTemperatureRisk.BODYTEMPERATURE_VERYWARM, 43, 980));
	}
	
	public enum BodyTemperatureRisk implements Risk  {
		BODYTEMPERATURE_VERYCOLD,
		BODYTEMPERATURE_COLD,
		BODYTEMPERATURE_NORMAL,
		BODYTEMPERATURE_WARM,
		BODYTEMPERATURE_VERYWARM	
	}
}
