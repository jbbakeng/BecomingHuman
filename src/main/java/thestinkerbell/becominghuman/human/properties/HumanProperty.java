package thestinkerbell.becominghuman.human.properties;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Range;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import thestinkerbell.becominghuman.human.properties.basic.BasicHumanProperty;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskRange;

public abstract class HumanProperty<TYPE> {

	public String name;
	public String unit;
	final public List<RiskRange> risk_ranges = new ArrayList<RiskRange>();

	protected TYPE value;
	protected TYPE range_min;
	protected TYPE range_max;

	public enum GeneralRisk implements Risk {
		HEALTHY
	}
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}

	public abstract TYPE getValue();
	public abstract void setValue(Double value);
	public abstract Risk getRisk();
	public abstract Range getValueRange();
	
	public void setValueRange(TYPE range_min2, TYPE range_max2) {
		this.range_min = range_min2;
		this.range_max = range_max2;
	}
	
	
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

	// === DEBUG ===
	
	public static void print(ByteBuf buf) {
		for (int i = 0; i < buf.capacity(); i ++) {
			 byte b = buf.getByte(i);
			 System.out.println((char) b);
		}
	}
	
	public void print(String prefix) {
		System.out.println(prefix+
				"		HumanProperty: "
				+ "name="+name+
				", value="+this.getValue()+
				", unit="+unit+
				", range_min: "+range_min+
				", range_max: "+range_max+
				", class name: "+this.getClass().getName()
				);
	}

}