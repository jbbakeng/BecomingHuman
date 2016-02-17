package thestinkerbell.becominghuman.human.properties;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public abstract class DoubleHumanProperty extends HumanProperty<Double> {

	public static void deserialize(ByteBuf buf_in, DoubleHumanProperty property) {
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
		//Not serializing risk_ranges because they are of inherited types
		//And when sending packages the client already knows the risk_ranges because the object
		//is created equally on the other side.
	}

	public static void serialize(DoubleHumanProperty property, ByteBuf buf_out) {
		ByteBufUtils bufUtils = new ByteBufUtils();
		bufUtils.writeUTF8String(buf_out, property.name);
		buf_out.writeDouble(property.value);
		bufUtils.writeUTF8String(buf_out, property.unit);
		buf_out.writeDouble(property.range_min);
		buf_out.writeDouble(property.range_max);
	}

	public DoubleHumanProperty(String propertyName, Double defaultValue, String unit, Double range_min, Double range_max) {
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

}