package thestinkerbell.becominghuman.human.properties.compound;

import thestinkerbell.becominghuman.human.properties.basic.WaterBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.WeightBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.compound.BMICompoundHumanProperty.BMIRisk;
import thestinkerbell.becominghuman.human.risks.DoubleRiskRange;
import thestinkerbell.becominghuman.human.risks.Risk;

public class HydrationLevelCompoundHumanProperty extends DoubleCompoundHumanProperty<WaterBasicHumanProperty, WeightBasicHumanProperty> {

	public HydrationLevelCompoundHumanProperty(WaterBasicHumanProperty water, WeightBasicHumanProperty weight) {
		super("Hydration Level", 60.0, "%", 0.0, 100.0);
		this.propertyA = water;
		this.propertyB = weight;
		this.risk_ranges.add(new DoubleRiskRange(HydrationLevelRisk.HYDRATION_VERYLOW, 0.0, 48.0));
		this.risk_ranges.add(new DoubleRiskRange(HydrationLevelRisk.HYDRATION_LOW, 48.1, 55.0));
		this.risk_ranges.add(new DoubleRiskRange(HydrationLevelRisk.HYDRATION_DESIRED, 55.1, 75.0));
		this.risk_ranges.add(new DoubleRiskRange(HydrationLevelRisk.HYDRATION_HIGH, 75.1, 90.0));
		this.risk_ranges.add(new DoubleRiskRange(HydrationLevelRisk.HYDRATION_VERYHIGH, 90.1, 100.0));
	}
	@Override
	final public Double getValue() {
		Double water_liter = propertyA.getValue();
		Double weight_kg = propertyB.getValue();
		Double hydration_level = water_liter/weight_kg;
		return hydration_level;
	}
	
	public enum HydrationLevelRisk implements Risk  {
		HYDRATION_VERYLOW,
		HYDRATION_LOW,
		HYDRATION_DESIRED,
		HYDRATION_HIGH,
		HYDRATION_VERYHIGH
	}
}
