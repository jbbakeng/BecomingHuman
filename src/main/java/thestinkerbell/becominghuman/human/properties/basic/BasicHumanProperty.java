package thestinkerbell.becominghuman.human.properties.basic;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import thestinkerbell.becominghuman.human.properties.HumanProperty;
import thestinkerbell.becominghuman.human.risks.DoubleRiskRange;
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
		property.range_min = range_min;
		property.range_max = range_max;
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
		this.risk_ranges.add(new DoubleRiskRange(GeneralRisk.HEALTHY, range_min, range_max));
	}
	
	public BasicHumanProperty(String propertyName, Integer defaultValue, String unit, Integer range_min, Integer range_max) {
		this(propertyName, new Double(defaultValue.doubleValue()), unit, new Double(range_min.doubleValue()), new Double(range_max.doubleValue()));
	}
	
	public BasicHumanProperty(String propertyName, Double defaultValue, String unit, Double range_min, Double range_max) {
		super(propertyName, defaultValue, unit, range_min, range_max);
	}
	
	@Override
	final public void setValue(Double value) {
		this.value = value;
	}

	@Override
	final public Double getValue() {
		return this.value;
	}

	@Override
	final public Risk getRisk() {
		return RiskRange.getRisk(risk_ranges, this.value);
	}

}
