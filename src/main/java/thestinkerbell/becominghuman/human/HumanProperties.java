package thestinkerbell.becominghuman.human;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class HumanProperties {
	
	public enum Sex {
		UNDEFINED,
		FEMALE,
		MALE
		
	}

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
		properties.put("Age", new HumanProperty("Age", 18, "years"));
		properties.put("Height", new HumanProperty("Height", 170, "cm"));
		properties.put("Weight", new HumanProperty("Weight", 75, "kg"));
		//properties.put("Sex", new HumanProperty("Sex", Sex.UNDEFINED, "gender"));
		properties.put("Body Temperatur", new HumanProperty("Body Temperatur", 37, "celcius"));
		properties.put("Heart Rate", new HumanProperty("Heart Rate", 70, "bpm"));
	}

	public void setValue(String name, int value) {
		HumanProperty property = getHumanPropertyWithName(name);
		if(property != null)
			property.set(value);
		else
			System.err.println("No human property named "+name);
	}
}
