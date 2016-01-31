package thestinkerbell.becominghuman.human.properties;

import com.google.common.collect.Range;

public class WeightHumanProperty extends HumanProperty {
	
	public WeightHumanProperty() {
		super("Weight", 75, "kg", Range.closed(0, 635));
	}

}
