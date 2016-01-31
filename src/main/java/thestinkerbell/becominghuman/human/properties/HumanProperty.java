package thestinkerbell.becominghuman.human.properties;

import com.google.common.collect.Range;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import thestinkerbell.becominghuman.human.risks.GeneralRiskFactor;
import thestinkerbell.becominghuman.human.risks.RiskFactor;

public class HumanProperty {

	public String name;
	public String unit;	

	public Integer value;
	public Range range;
	
	public static void deserialize(ByteBuf buf_in, HumanProperty property_out) {
		ByteBufUtils bufUtils = new ByteBufUtils();
		String name = bufUtils.readUTF8String(buf_in);
		Integer value = buf_in.readInt();
		String unit = bufUtils.readUTF8String(buf_in);;
		property_out.name = name;
		property_out.value = value;
		property_out.unit = unit;
	}
	
	static public void serialize(HumanProperty property_in, ByteBuf buf_out) {
		ByteBufUtils bufUtils = new ByteBufUtils();
		bufUtils.writeUTF8String(buf_out, property_in.name);
		buf_out.writeInt(property_in.value);
		bufUtils.writeUTF8String(buf_out, property_in.unit);
	}

	public HumanProperty() {
		this("DEFAULT_NAME", 0, "DEFAULT_UNIT", Range.closed(0, 1));
	}
	
	public HumanProperty(String propertyName, Integer defaultValue, String unit, Range range) {
		this.name = propertyName;
		this.value = defaultValue;
		this.unit = unit;
		this.range = range;
	}

	public HumanProperty(String propertyName, Enum defaulValue, String unit, Range range) {
		this(propertyName, defaulValue.ordinal(), unit, range);
	}
	
	public void set(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return this.value;
	}
	
	public Object getRange() {
		return Range.closed(0, 0);
	}

	public RiskFactor getRiskFactor() {
		return GeneralRiskFactor.HEALTHY;
	}

	//To be on the safe side, let the Eclipse IDE generate the equals and hashCode functions as a pair: Source > Generate hashCode() and equals()

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((range == null) ? 0 : range.hashCode());
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
		if (getClass() != obj.getClass())
			return false;
		HumanProperty other = (HumanProperty) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (range == null) {
			if (other.range != null)
				return false;
		} else if (!range.equals(other.range))
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
		System.out.println(prefix+"		HumanProperty: name="+name+", value="+value+", unit="+unit);
	}
	

	static public void print(ByteBuf buf) {
		for (int i = 0; i < buf.capacity(); i ++) {
			 byte b = buf.getByte(i);
			 System.out.println((char) b);
		}
	}

}
