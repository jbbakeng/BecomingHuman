package thestinkerbell.becominghuman.human.properties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HumanProperties {

	private HashMap<String, HumanProperty> properties;
	
	public HumanProperties() {
		properties = new HashMap<String, HumanProperty>();
		this.addDefaultHumanProperties(properties);
	}
	
	public List<HumanProperty> getListOfHumanProperties() {
		return new ArrayList<HumanProperty>(properties.values());
	}
	
	public HumanProperty getHumanPropertyWithName(String name) {
		return properties.get(name);
	}
	
	private void addDefaultHumanProperties(HashMap<String, HumanProperty> properties) {
		//wikipedia supplied the ranges
		this.addHumanProperty(properties, new AgeHumanProperty());
		this.addHumanProperty(properties, new HeightHumanProperty());
		this.addHumanProperty(properties, new WeightHumanProperty());
		this.addHumanProperty(properties, new BodyTemperatureHumanProperty());
		this.addHumanProperty(properties, new HearthRateHumanProperty());
	}

	static private void addHumanProperty(HashMap<String, HumanProperty> properties, HumanProperty humanProperty) {
		properties.put(humanProperty.name, humanProperty);
	}

	public void setValue(String name, int value) {
		HumanProperty property = getHumanPropertyWithName(name);
		if(property != null)
			property.setValue(value);
		else
			System.err.println("No human property named "+name);
	}
}
