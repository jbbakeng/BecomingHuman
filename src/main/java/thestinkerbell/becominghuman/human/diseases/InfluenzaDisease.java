package thestinkerbell.becominghuman.human.diseases;

import thestinkerbell.becominghuman.BecomingHuman;
import thestinkerbell.becominghuman.human.properties.germ.InfluenzaAVirusHumanProperty.InfluenzaARisk;
import thestinkerbell.becominghuman.human.symptoms.HumanSymptom;
import thestinkerbell.becominghuman.human.symptoms.HumanSymptom.Severity;
import thestinkerbell.becominghuman.human.symptoms.effects.Effect;
import thestinkerbell.becominghuman.utilities.Utilities;

public class InfluenzaDisease extends HumanDisease {

	public InfluenzaDisease() {
		super("Influenza", Severity.SYMPTOM_SEVERE);
		
		this.assosiated_risks.add(InfluenzaARisk.IA_HIGH);
		this.assosiated_risks.add(InfluenzaARisk.IA_DEADLY);
		
		this.symptoms.add(new HumanSymptom(Effect.cough.id, Utilities.seconds_to_ticks(5), severity));
		this.symptoms.add(new HumanSymptom(Effect.sneez.id, Utilities.seconds_to_ticks(5), severity));
		this.symptoms.add(new HumanSymptom(Effect.tired.id, Utilities.seconds_to_ticks(5), severity));
		this.symptoms.add(new HumanSymptom(Effect.fever.id, Utilities.seconds_to_ticks(5), severity));
	}

}
