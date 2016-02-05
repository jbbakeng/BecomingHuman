package thestinkerbell.becominghuman.human.properties;

import java.util.ArrayList;
import java.util.List;

import io.netty.buffer.ByteBuf;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskRange;

public abstract class HumanProperty<TYPE> implements Property<TYPE> {

	final public List<RiskRange> risk_ranges = new ArrayList<RiskRange>();

	protected String name;
	protected String unit;

	protected TYPE value;
	protected TYPE range_min;
	protected TYPE range_max;

	public enum GeneralRisk implements Risk {
		HEALTHY
	}
	
	public HumanProperty(String propertyName, TYPE defaultValue, String unit, TYPE range_min, TYPE range_max) {
		this.name = propertyName;
		this.value = defaultValue;
		this.unit = unit;
		this.range_min = range_min;
		this.range_max = range_max;
	}
	
	@Override
	final public String getName() {
		return this.name;
	}
	
	@Override
	final public String getUnit() {
		return this.name;
	}

	@Override
	public abstract TYPE getValue();
	
	@Override
	public abstract void setValue(TYPE value);
	
	@Override
	public abstract Risk getRisk();
	
	//To be on the safe side, let the Eclipse IDE generate the equals and hashCode functions as a pair: Source > Generate hashCode() and equals()
	//Use instance_of instead of equals because we are using inheritance

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((range_max == null) ? 0 : range_max.hashCode());
		result = prime * result + ((range_min == null) ? 0 : range_min.hashCode());
		result = prime * result + ((unit == null) ? 0 : unit.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof HumanProperty))
			return false;
		HumanProperty other = (HumanProperty) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (range_max == null) {
			if (other.range_max != null)
				return false;
		} else if (!range_max.equals(other.range_max))
			return false;
		if (range_min == null) {
			if (other.range_min != null)
				return false;
		} else if (!range_min.equals(other.range_min))
			return false;
		if (unit == null) {
			if (other.unit != null)
				return false;
		} else if (!unit.equals(other.unit))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return  "\n	HumanProperty: "+
				"\n 	name="+name+
				",\n 	value="+getValue()+
				",\n 	unit="+unit+
				",\n 	range_min: "+range_min+
				",\n 	range_max: "+range_max+
				",\n 	risk class: "+getRisk().getClass().getName()+
				",\n 	class name: "+getClass().getName()
				;
	}

}