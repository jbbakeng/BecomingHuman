package thestinkerbell.becominghuman.human;

import java.util.HashMap;
import java.util.HashSet;

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

	public Integer getNumberOfProperties() {
		return properties.size();
	}
	
	public HumanProperty getHumanPropertyWithName(String name) {
		return properties.get(name);
	}
	
	private void addDefaultHumanProperties(HashMap<String, HumanProperty> properties) {
		properties.put("Age", new HumanProperty("Age", 18, "years"));
		properties.put("Height", new HumanProperty("Height", 170, "cm"));
		properties.put("Weight", new HumanProperty("Weight", 75, "kg"));
		properties.put("Sex", new HumanProperty("Sex", Sex.UNDEFINED, "gender"));
		properties.put("Body Temperatur", new HumanProperty("Body Temperatur", 37, "celcius"));
		properties.put("Heart Rate", new HumanProperty("Heart Rate", 70, "bpm"));
	}

	public void updateHumanProperty(String name, int value) {
		getHumanPropertyWithName(name).set(value);
	}
}
