package thestinkerbell.becominghuman.human.properties.germ;

import thestinkerbell.becominghuman.human.properties.GermHumanProperty;

public class VirusHumanProperty extends GermHumanProperty {

	public static final double defaultValue = 0.0;
	public static final double min_value = 0.0;
	public static final double max_value = 7.5;
	public static final String unit = "log10 copies/mL";

	public VirusHumanProperty() {
		super();
	}

	public VirusHumanProperty(String virusName, Double defaultValue, String unit, Double range_min, Double range_max) {
		super(virusName, defaultValue, unit, range_min, range_max);
	}

}