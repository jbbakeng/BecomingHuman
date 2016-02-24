package thestinkerbell.becominghuman.human.properties;

import java.util.ArrayList;

import io.netty.buffer.ByteBuf;
import thestinkerbell.becominghuman.human.risks.DoubleRiskRange;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.RiskRange;

public class GermHumanProperty extends DoubleHumanProperty {
	
	protected Double antibodies = 0.0;
	protected final ArrayList<Transmission> transmission_ways = new ArrayList<Transmission>();
	
	public static void deserialize(ByteBuf buf_in, GermHumanProperty property) {
		DoubleHumanProperty.deserialize(buf_in, property);
		Double antibodies = buf_in.readDouble();
		property.antibodies = antibodies;
	}

	public static void serialize(GermHumanProperty property, ByteBuf buf_out) {
		DoubleHumanProperty.serialize(property, buf_out);
		buf_out.writeDouble(property.antibodies);
	}
	
	public GermHumanProperty() {
		this("DEFAULT_NAME_GERM", 0.0, "DEFAULT_UNIT_GERM", 0.0, 1.0);
		this.risk_ranges.add(new DoubleRiskRange(GeneralRisk.HEALTHY, range_min, range_max));
	}

	public GermHumanProperty(String virusName, Double defaultValue, String unit, Double range_min, Double range_max) {
		super(virusName, defaultValue, unit, range_min, range_max);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((antibodies == null) ? 0 : antibodies.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof GermHumanProperty))
			return false;
		GermHumanProperty other = (GermHumanProperty) obj;
		if (antibodies == null) {
			if (other.antibodies != null)
				return false;
		} else if (!antibodies.equals(other.antibodies))
			return false;
		return true;
	}

	public Double getAntibodies() {
		return this.antibodies;
	}

	public void setAntibodies(Double antibodies) {
		this.antibodies = antibodies;
	}
	
	public boolean canBeTransmittedBy(Transmission transmission) {
		return this.transmission_ways.contains(transmission);
	}
	
	public enum Transmission {
		//https://en.wikipedia.org/wiki/Transmission_(medicine)
		TRANSMISSION_AIRBORN,
		TRANSMISSION_DROPLET,
		TRANSMISSION_FECAL_ORAL,
		TRANSMISSION_SEXUAL,
		TRANSMISSION_ORAL,
		TRANSMISSION_DIRECT_CONTACT,
		TRANSMISSION_VERTICAL,
		TRANSMISSION_LATROGENIC,
		TRANSMISSION_VECTOR_BORNE
	}
}
