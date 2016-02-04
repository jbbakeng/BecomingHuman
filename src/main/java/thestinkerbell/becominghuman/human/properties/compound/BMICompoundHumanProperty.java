package thestinkerbell.becominghuman.human.properties.compound;

import java.util.ArrayList;
import java.util.List;

import thestinkerbell.becominghuman.human.properties.basic.HeightBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.WeightBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.AgeBasicHumanProperty.AgeRisk;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskRange;

public class BMICompoundHumanProperty extends CompoundHumanProperty<WeightBasicHumanProperty, HeightBasicHumanProperty> {
	
	public BMICompoundHumanProperty(WeightBasicHumanProperty weight, HeightBasicHumanProperty height) {
		super("BMI", 20.0, "kg/m^2", 0.0, 204.0);
		this.propertyA = weight;
		this.propertyB = height;
		this.risk_ranges.add(new RiskRange(BMIRisk.BMI_VERYSEVERLYUNDERWEIGHT, 0.0, 14.9));
		this.risk_ranges.add(new RiskRange(BMIRisk.BMI_SEVERLYUNDERWEIGHT, 15.0, 15.9));
		this.risk_ranges.add(new RiskRange(BMIRisk.BMI_UNDERWEIGHT, 16.0, 18.4));
		this.risk_ranges.add(new RiskRange(BMIRisk.BMI_NORMALWEIGHT, 18.5, 24.9));
		this.risk_ranges.add(new RiskRange(BMIRisk.BMI_OVERWEIGHT, 25.0, 29.9));
		this.risk_ranges.add(new RiskRange(BMIRisk.BMI_MODERATELYOVERWEIGHT, 30.0, 34.9));
		this.risk_ranges.add(new RiskRange(BMIRisk.BMI_SEVERLYOVERWEIGHT, 35.0, 39.9));
		this.risk_ranges.add(new RiskRange(BMIRisk.BMI_VERYSEVERLYOVERWEIGHT, 40.0, 204.0));
	}
	
	@Override
	final public Double getValue() {
		Double weight_kg = propertyA.getValue();
		Double height_m = propertyB.getValue()/100;
		Double bmi = weight_kg/(height_m*height_m);
		return bmi;
	}
	
	public enum BMIRisk implements Risk  {
		BMI_VERYSEVERLYUNDERWEIGHT,
		BMI_SEVERLYUNDERWEIGHT,
		BMI_UNDERWEIGHT,
		BMI_NORMALWEIGHT,
		BMI_OVERWEIGHT,
		BMI_MODERATELYOVERWEIGHT,
		BMI_SEVERLYOVERWEIGHT,
		BMI_VERYSEVERLYOVERWEIGHT
	}

}
