package thestinkerbell.becominghuman.human.properties;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Range;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import thestinkerbell.becominghuman.human.properties.basic.HeightBasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.basic.WeightBasicHumanProperty;

public abstract class DoubleTwoTypeCompoundHumanProperty<A, B> extends TwoTypesCompoundHumanProperty<A, B, Double> {

	public DoubleTwoTypeCompoundHumanProperty(String propertyName, Double defaultValue, String unit, Double range_min, Double range_max, A propertyA, B propertyB) {
		super(propertyName, defaultValue, unit, range_min, range_max, propertyA, propertyB);
	}

}
