package thestinkerbell.becominghuman.human.properties;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Range;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;

public abstract class DoubleCompoundHumanProperty<A, B> extends CompoundHumanProperty<A, B, Double> {

	public DoubleCompoundHumanProperty(String propertyName, Double defaultValue, String unit, Double range_min, Double range_max) {
		super(propertyName, defaultValue, unit, range_min, range_max);
	}

}
