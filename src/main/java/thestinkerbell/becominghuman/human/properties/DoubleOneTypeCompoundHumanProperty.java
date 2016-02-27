package thestinkerbell.becominghuman.human.properties;

public abstract class DoubleOneTypeCompoundHumanProperty<A> extends OneTypeCompoundHumanProperty<A, Double> {

	public DoubleOneTypeCompoundHumanProperty(String propertyName, Double defaultValue, String unit, Double range_min, Double range_max, A propertyA) {
		super(propertyName, defaultValue, unit, range_min, range_max, propertyA);
	}
	
}
