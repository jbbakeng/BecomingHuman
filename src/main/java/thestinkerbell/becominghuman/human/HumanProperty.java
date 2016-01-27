package thestinkerbell.becominghuman.human;

import thestinkerbell.becominghuman.human.HumanProperties.Sex;

public class HumanProperty {

	final private String name;
	final private String unit;
	private Integer value;

	public HumanProperty(String propertyName, Integer defaultValue, String unit) {
		this.name = propertyName;
		this.value = defaultValue;
		this.unit = unit;
	}

	public HumanProperty(String propertyName, Enum defaulValue, String unit) {
		this(propertyName, defaulValue.ordinal(), unit);
	}

	public void set(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}

}
