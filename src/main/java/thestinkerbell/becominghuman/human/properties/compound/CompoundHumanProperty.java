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

public class CompoundHumanProperty<A, B> extends HumanProperty<Double> {

	protected A propertyA;
	protected B propertyB;
	
/*
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((propertyA == null) ? 0 : propertyA.hashCode());
		result = prime * result + ((propertyB == null) ? 0 : propertyB.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof CompoundHumanProperty))
			return false;
		CompoundHumanProperty other = (CompoundHumanProperty) obj;
		if (propertyA == null) {
			if (other.propertyA != null)
				return false;
		} else if (!propertyA.equals(other.propertyA))
			return false;
		if (propertyB == null) {
			if (other.propertyB != null)
				return false;
		} else if (!propertyB.equals(other.propertyB))
			return false;
		return true;
	}
	*/

	public CompoundHumanProperty() {
		this("DEFAULT_NAME_COMPOUND", 0.0, "DEFAULT_UNIT_COMPOUND", 0.0, 1.0);
		this.propertyA = null;
		this.propertyB = null;
		this.risk_ranges.add(new RiskRange(GeneralRisk.HEALTHY, range_min, range_max));
	}
	
	public CompoundHumanProperty(
			String propertyName, 
			Double defaultValue, 
			String unit, 
			Double range_min, 
			Double range_max
			) {
		super();
		this.name = propertyName;
		this.value = defaultValue;
		this.unit = unit;
		this.range_min = range_min;
		this.range_max = range_max;
	}

	@Override
	public Double getValue() {
		return this.value;
	}

	@Override
	public void setValue(Double value) {
		//CompoundHumanProperty calculates its value based on its BasicHumanProperty values
		return;
	}

	@Override
	public Range getValueRange() {
		return Range.closed(range_min, range_max);
	}

	@Override
	public Risk getRisk() {
		Risk risk = null;
		for(RiskRange range : risk_ranges)
		{
			if(range.contains(this.value));
				risk = range.risk;
		}
		return risk;
	}

}
