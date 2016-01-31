package thestinkerbell.becominghuman.human.properties;

import thestinkerbell.becominghuman.human.risks.RiskFactor;

public class BodyTemperatureHumanProperty extends HumanProperty {

	public BodyTemperatureHumanProperty() {
		super("Body Temperatur", 37, "celcius", -273, 980);
	}
	
	public enum BodyTemperatureRiskFactor implements RiskFactor  {
		BODYTEMPERATURE_VERYCOLD,
		BODYTEMPERATURE_COLD,
		BODYTEMPERATURE_NORMAL,
		BODYTEMPERATURE_WARM,
		BODYTEMPERATURE_VERYWARM	
	}
}
