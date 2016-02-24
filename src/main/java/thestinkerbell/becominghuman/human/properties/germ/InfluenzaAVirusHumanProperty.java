package thestinkerbell.becominghuman.human.properties.germ;

import thestinkerbell.becominghuman.human.properties.GermHumanProperty;
import thestinkerbell.becominghuman.human.risks.DoubleRiskRange;
import thestinkerbell.becominghuman.human.risks.Risk;

public class InfluenzaAVirusHumanProperty extends GermHumanProperty {

	public InfluenzaAVirusHumanProperty() {
		//https://jid.oxfordjournals.org/content/200/4/492.long
		//Viral load, also known as viral burden, viral titre or viral titer, is a numerical expression of the quantity of virus in a given volume. 
		//It is often expressed as viral particles, or infectious particles per mL depending on the type of assay
		//mL = milliliter
		super("Influenza A", 0.0, "log10 copies/mL", 0.0 , 7.5);
		this.risk_ranges.add(new DoubleRiskRange(InfluenzaARisk.IA_NONE, 0.0,	0.0));
		this.risk_ranges.add(new DoubleRiskRange(InfluenzaARisk.IA_MILD, 0.01, 2.84));
		this.risk_ranges.add(new DoubleRiskRange(InfluenzaARisk.IA_MODERATE, 2.85, 6.18));
		this.risk_ranges.add(new DoubleRiskRange(InfluenzaARisk.IA_HIGH, 6.19, 7.0));
		this.risk_ranges.add(new DoubleRiskRange(InfluenzaARisk.IA_DEADLY, 7.01, 7.5));
		
		this.antibodies = 0.0;
		
		this.transmission_ways.add(Transmission.TRANSMISSION_AIRBORN);
		this.transmission_ways.add(Transmission.TRANSMISSION_DROPLET);
		this.transmission_ways.add(Transmission.TRANSMISSION_DIRECT_CONTACT);
	}
	
	public enum InfluenzaARisk implements Risk  {
		IA_NONE,
		IA_MILD,
		IA_MODERATE,
		IA_HIGH,
		IA_DEADLY
	}
}
