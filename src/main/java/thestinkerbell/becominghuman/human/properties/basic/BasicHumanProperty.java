package thestinkerbell.becominghuman.human.properties.basic;

import com.google.common.collect.Range;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import thestinkerbell.becominghuman.human.properties.HumanProperty;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskRange;

public class BasicHumanProperty extends HumanProperty<Double> {

	public static void deserialize(ByteBuf buf_in, BasicHumanProperty property) {
		ByteBufUtils bufUtils = new ByteBufUtils();
		String name = bufUtils.readUTF8String(buf_in);
		Double value = buf_in.readDouble();
		String unit = bufUtils.readUTF8String(buf_in);;
		Double range_min = buf_in.readDouble();
		Double range_max = buf_in.readDouble();
		property.name = name;
		property.value = value;
		property.unit = unit;
		property.setValueRange(range_min, range_max);
	}

	public static void serialize(BasicHumanProperty property, ByteBuf buf_out) {
		ByteBufUtils bufUtils = new ByteBufUtils();
		bufUtils.writeUTF8String(buf_out, property.name);
		buf_out.writeDouble(property.value);
		bufUtils.writeUTF8String(buf_out, property.unit);
		buf_out.writeDouble(property.range_min);
		buf_out.writeDouble(property.range_max);
	}
	
	public BasicHumanProperty() {
		this("DEFAULT_NAME_BASIC", 0.0, "DEFAULT_UNIT_BASIC", 0.0, 1.0);
		this.risk_ranges.add(new RiskRange(GeneralRisk.HEALTHY, range_min, range_max));
	}
	
	public BasicHumanProperty(String propertyName, Integer defaultValue, String unit, Integer range_min, Integer range_max) {
		this(propertyName, (double) defaultValue, unit, (double) range_min, (double) range_max);
	}
	
	public BasicHumanProperty(String propertyName, Double defaultValue, String unit, Double range_min, Double range_max) {
		super();
		this.name = propertyName;
		this.value = defaultValue;
		this.unit = unit;
		this.range_min = range_min;
		this.range_max = range_max;
	}
	
	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public Double getValue() {
		return this.value;
	}

	@Override
	public Range getValueRange() {
		return Range.closed(range_min, range_max);
	}

	@Override
	public Risk getRisk() {
		for(RiskRange range : risk_ranges)
		{
			if(range.contains(this.value));
				return range.risk;
		}
		return null;
	}

}
