package thestinkerbell.becominghuman.human.properties;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Range;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskFactor;

public class HumanProperty {

	//needs to be in equals and hashCode
	public String name;
	public String unit;	
	public Integer value;
	private Integer range_min;
	private Integer range_max;
	
	//does not need to be asserted in equals and hashCode because the are generated from other data
	protected List<RiskFactor> risk_factors;
	
	public static void deserialize(ByteBuf buf_in, HumanProperty property_out) {
		ByteBufUtils bufUtils = new ByteBufUtils();
		String name = bufUtils.readUTF8String(buf_in);
		Integer value = buf_in.readInt();
		String unit = bufUtils.readUTF8String(buf_in);;
		Integer range_min = buf_in.readInt();
		Integer range_max = buf_in.readInt();
		property_out.name = name;
		property_out.value = value;
		property_out.unit = unit;
		property_out.setRange(range_min, range_max);
	}
	
	static public void serialize(HumanProperty property_in, ByteBuf buf_out) {
		ByteBufUtils bufUtils = new ByteBufUtils();
		bufUtils.writeUTF8String(buf_out, property_in.name);
		buf_out.writeInt(property_in.value);
		bufUtils.writeUTF8String(buf_out, property_in.unit);
		buf_out.writeInt(property_in.range_min);
		buf_out.writeInt(property_in.range_max);
	}

	public HumanProperty() {
		this("DEFAULT_NAME", 0, "DEFAULT_UNIT", 0, 1);
		this.risk_factors.add(new RiskFactor(GeneralRisk.HEALTHY, range_min, range_max));
	}
	
	public HumanProperty(String propertyName, Integer defaultValue, String unit, Integer range_min, Integer range_max) {
		this.name = propertyName;
		this.value = defaultValue;
		this.unit = unit;
		this.range_min = range_min;
		this.range_max = range_max;
		this.risk_factors = new ArrayList();
	}

	public HumanProperty(String propertyName, Enum defaulValue, String unit, Integer range_min, Integer range_max) {
		this(propertyName, defaulValue.ordinal(), unit, range_min, range_max);
	}
	
	public enum GeneralRisk implements Risk {
		HEALTHY
	}
	
	public void set(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return this.value;
	}
	
	public void setRange(Integer min, Integer max) {
		this.range_min = min;
		this.range_max = max;
	}
	
	public Range getRange() {
		return Range.closed(range_min, range_max);
	}

	public Risk getRisk() {
		for(RiskFactor factor : risk_factors)
		{
			if(factor.contains(this.value));
				return factor.risk;
		}
		return null;
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
	
	//==== DEBUG UTILITIES ====
	
	public void print(String prefix) {
		System.out.println(prefix+
				"		HumanProperty: "
				+ "name="+name+
				", value="+value+
				", unit="+unit+
				", range_min: "+range_min+
				", range_max: "+range_max
				);
	}
	

	static public void print(ByteBuf buf) {
		for (int i = 0; i < buf.capacity(); i ++) {
			 byte b = buf.getByte(i);
			 System.out.println((char) b);
		}
	}

}
