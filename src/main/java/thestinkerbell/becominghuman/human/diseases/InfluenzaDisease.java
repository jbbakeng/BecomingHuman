package thestinkerbell.becominghuman.human.diseases;

import thestinkerbell.becominghuman.BecomingHuman;
import thestinkerbell.becominghuman.human.properties.germ.InfluenzaAVirusHumanProperty.InfluenzaARisk;
import thestinkerbell.becominghuman.human.symptoms.HumanSymptom;
import thestinkerbell.becominghuman.utilities.Utilities;

public class InfluenzaDisease extends HumanDisease {

	public InfluenzaDisease() {
		super("Influenza");
		this.assosiated_risks.add(InfluenzaARisk.IA_HIGH);
		this.assosiated_risks.add(InfluenzaARisk.IA_DEADLY);
		this.symptoms.add(new HumanSymptom(BecomingHuman.pain.id, Utilities.seconds_to_ticks(3)));
	}

}
