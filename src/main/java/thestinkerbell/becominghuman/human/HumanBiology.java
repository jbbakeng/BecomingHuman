package thestinkerbell.becominghuman.human;

import java.util.HashMap;

import thestinkerbell.becominghuman.human.properties.BasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.CompoundHumanProperty;
import thestinkerbell.becominghuman.human.properties.GermHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.AgeBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.BodyTemperatureBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.DiastolicBloodPressureBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.HeartRateBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.HeartRateMaxBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.HeartRateRestingBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.HeightBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.SetPointBodyTemperatureBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.SystolicBloodPressureBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.WaterBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.WeightBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.WhiteBloodCellsBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.compound.BMICompoundHumanProperty;
import thestinkerbell.becominghuman.human.properties.compound.BloodPressureCompoundHumanProperty;
import thestinkerbell.becominghuman.human.properties.compound.HydrationLevelCompoundHumanProperty;
import thestinkerbell.becominghuman.human.properties.germ.InfluenzaAVirusHumanProperty;

public class HumanBiology {
	public HashMap<String, BasicHumanProperty> basic_properties;
	public HashMap<String, CompoundHumanProperty> compound_properties;
	public HashMap<String, GermHumanProperty> germ_properties;

	public HumanBiology() {
		basic_properties = new HashMap<String, BasicHumanProperty>();
		compound_properties = new HashMap<String, CompoundHumanProperty>();
		germ_properties = new HashMap<String, GermHumanProperty>();
		this.addHumanProperties();
	}
	
	private void addHumanProperties() {
		WeightBasicHumanProperty weight = new WeightBasicHumanProperty();
		HeightBasicHumanProperty height = new HeightBasicHumanProperty();
		SystolicBloodPressureBasicHumanProperty systolic = new SystolicBloodPressureBasicHumanProperty();
		DiastolicBloodPressureBasicHumanProperty diastolic = new DiastolicBloodPressureBasicHumanProperty();
		WaterBasicHumanProperty water = new WaterBasicHumanProperty();

		this.addHumanProperty(basic_properties, new AgeBasicHumanProperty());
		this.addHumanProperty(basic_properties, weight);
		this.addHumanProperty(basic_properties, height);
		this.addHumanProperty(basic_properties, water);
		this.addHumanProperty(basic_properties, new BodyTemperatureBasicHumanProperty());
		this.addHumanProperty(basic_properties, new SetPointBodyTemperatureBasicHumanProperty());
		this.addHumanProperty(basic_properties, new HeartRateBasicHumanProperty());
		this.addHumanProperty(basic_properties, new HeartRateRestingBasicHumanProperty());
		this.addHumanProperty(basic_properties, new HeartRateMaxBasicHumanProperty());
		this.addHumanProperty(basic_properties, systolic);
		this.addHumanProperty(basic_properties, diastolic);
		this.addHumanProperty(basic_properties, new WhiteBloodCellsBasicHumanProperty());
		
		this.addHumanProperty(compound_properties, new BMICompoundHumanProperty(weight, height));
		this.addHumanProperty(compound_properties, new BloodPressureCompoundHumanProperty(systolic, diastolic));
		this.addHumanProperty(compound_properties, new HydrationLevelCompoundHumanProperty(water, weight));
		
		this.addHumanProperty(germ_properties, new InfluenzaAVirusHumanProperty());
	}
	
	private void addHumanProperty(HashMap<String, GermHumanProperty> properties, GermHumanProperty humanProperty) {
		properties.put(humanProperty.getName(), humanProperty);
	}

	static private void addHumanProperty(HashMap<String, BasicHumanProperty> properties, BasicHumanProperty humanProperty) {
		properties.put(humanProperty.getName(), humanProperty);
	}
	
	static private void addHumanProperty(HashMap<String, CompoundHumanProperty> properties, CompoundHumanProperty humanProperty) {
		properties.put(humanProperty.getName(), humanProperty);
	}
}