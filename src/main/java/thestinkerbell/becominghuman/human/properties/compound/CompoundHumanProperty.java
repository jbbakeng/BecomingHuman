package thestinkerbell.becominghuman.human.properties.compound;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Range;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import thestinkerbell.becominghuman.human.properties.HumanProperty;
import thestinkerbell.becominghuman.human.properties.HumanProperty.GeneralRisk;
import thestinkerbell.becominghuman.human.properties.basic.BasicHumanProperty;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskRange;

public abstract class CompoundHumanProperty<A, B> extends HumanProperty<Double> {

	protected A propertyA;
	protected B propertyB;
	
	public CompoundHumanProperty(String propertyName, Double defaultValue, String unit, Double range_min, Double range_max) {
		super(propertyName, defaultValue, unit, range_min, range_max);
	}

	@Override
	public Double getValue() {
		return this.value;
	}

	@Override
	final public void setValue(Double value) {
		//CompoundHumanProperty calculates its value based on its BasicHumanProperty values
		return;
	}

	@Override
	final public Risk getRisk() {
		return RiskRange.getRisk(risk_ranges, this.value);
	}

}
