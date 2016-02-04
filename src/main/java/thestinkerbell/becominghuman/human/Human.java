package thestinkerbell.becominghuman.human;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import thestinkerbell.becominghuman.human.properties.HumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.AgeBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.BasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.BodyTemperatureBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.HearthRateBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.HeightBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.WeightBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.compound.BMICompoundHumanProperty;
import thestinkerbell.becominghuman.human.properties.compound.CompoundHumanProperty;

public class Human {

	private HashMap<String, BasicHumanProperty> basic_properties; //sync using packages
	private HashMap<String, CompoundHumanProperty> compound_properties; //updates when its basic components updates
	
	public Human() {
		basic_properties = new HashMap<String, BasicHumanProperty>();
		compound_properties = new HashMap<String, CompoundHumanProperty>();
		this.addHumanProperties();
	}
	
	public List<BasicHumanProperty> getListOfBasicHumanProperties() {
		return new ArrayList<BasicHumanProperty>(basic_properties.values());
	}
	
	public List<HumanProperty> getListOfAllHumanProperties() {
		List<HumanProperty> list = new ArrayList<HumanProperty>();
		list.addAll(basic_properties.values());
		list.addAll(compound_properties.values());
		return list;
	}
	
	public BasicHumanProperty getHumanPropertyWithName(String name) {
		return basic_properties.get(name);
	}
	
	private void addHumanProperties() {
		WeightBasicHumanProperty weight = new WeightBasicHumanProperty();
		HeightBasicHumanProperty height = new HeightBasicHumanProperty();

		this.addHumanProperty(basic_properties, new AgeBasicHumanProperty());
		this.addHumanProperty(basic_properties, weight);
		this.addHumanProperty(basic_properties, height);
		this.addHumanProperty(basic_properties, new BodyTemperatureBasicHumanProperty());
		this.addHumanProperty(basic_properties, new HearthRateBasicHumanProperty());
		
		this.addHumanProperty(compound_properties, new BMICompoundHumanProperty(weight, height));
	}

	static private void addHumanProperty(HashMap<String, BasicHumanProperty> properties, BasicHumanProperty humanProperty) {
		properties.put(humanProperty.name, humanProperty);
	}
	
	static private void addHumanProperty(HashMap<String, CompoundHumanProperty> properties, CompoundHumanProperty humanProperty) {
		properties.put(humanProperty.name, humanProperty);
	}

	public void setValue(String name, Double value) throws Exception {
		BasicHumanProperty property = getHumanPropertyWithName(name);
		if(property != null)
			property.setValue(value);
		else
			throw new Exception("No human property named "+name);
	}
}
