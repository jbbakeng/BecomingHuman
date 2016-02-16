package thestinkerbell.becominghuman.human.properties.virus;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import thestinkerbell.becominghuman.human.properties.HumanProperty;
import thestinkerbell.becominghuman.human.properties.HumanProperty.GeneralRisk;
import thestinkerbell.becominghuman.human.properties.basic.BasicHumanProperty;
import thestinkerbell.becominghuman.human.risks.DoubleRiskRange;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskRange;

public class VirusHumanProperty extends HumanProperty<Double> {
	
	protected Double antibodies;
	
	public static void deserialize(ByteBuf buf_in, VirusHumanProperty property) {
		ByteBufUtils bufUtils = new ByteBufUtils();
		String name = bufUtils.readUTF8String(buf_in);
		Double value = buf_in.readDouble();
		String unit = bufUtils.readUTF8String(buf_in);;
		Double range_min = buf_in.readDouble();
		Double range_max = buf_in.readDouble();
		Double antibodies = buf_in.readDouble();
		property.name = name;
		property.value = value;
		property.unit = unit;
		property.range_min = range_min;
		property.range_max = range_max;
		property.antibodies = antibodies;
	}

	public static void serialize(VirusHumanProperty property, ByteBuf buf_out) {
		ByteBufUtils bufUtils = new ByteBufUtils();
		bufUtils.writeUTF8String(buf_out, property.name);
		buf_out.writeDouble(property.value);
		bufUtils.writeUTF8String(buf_out, property.unit);
		buf_out.writeDouble(property.range_min);
		buf_out.writeDouble(property.range_max);
		buf_out.writeDouble(property.antibodies);
	}
	
	public VirusHumanProperty() {
		this("DEFAULT_NAME_VIRUS", 0.0, "DEFAULT_UNIT_VIRUS", 0.0, 1.0);
		this.risk_ranges.add(new DoubleRiskRange(GeneralRisk.HEALTHY, range_min, range_max));
	}

	public VirusHumanProperty(String virusName, Double defaultValue, String unit, Double range_min, Double range_max) {
		super(virusName, defaultValue, unit, range_min, range_max);
		this.antibodies = 0.0;
	}
	
	@Override
	final public Double getValue() {
		return this.value;
	}

	@Override
	final public void setValue(Double value) {
		this.value = value;
	}

	@Override
	final public Risk getRisk() {
		Double risk = this.value - this.antibodies;
		if(risk < 0.0)
			risk = 0.0;
		return RiskRange.getRisk(risk_ranges, risk);
	}
	
	@Override
	public String toString() {
		return  super.toString()+
				"\n 	antibodies="+this.antibodies
				;
	}
}
