package thestinkerbell.becominghuman.human.diseases;

import net.minecraft.potion.Potion;
import thestinkerbell.becominghuman.human.properties.compound.BloodPressureCompoundHumanProperty.BloodPressureRisk;
import thestinkerbell.becominghuman.human.properties.germ.InfluenzaAVirusHumanProperty.InfluenzaARisk;
import thestinkerbell.becominghuman.human.symptoms.HumanSymptom;
import thestinkerbell.becominghuman.human.symptoms.HumanSymptom.Severity;
import thestinkerbell.becominghuman.human.symptoms.effects.Effect;
import thestinkerbell.becominghuman.utilities.Utilities;

final public class CommonColdDisease extends HumanDisease {

	public CommonColdDisease() {
		super("Common Cold", Severity.SYMPTOM_MODERATE);
		
		this.assosiated_risks.add(InfluenzaARisk.IA_MILD);
		this.assosiated_risks.add(InfluenzaARisk.IA_MODERATE);
		
		this.symptoms.add(new HumanSymptom(Effect.cough.id, Utilities.seconds_to_ticks(3), severity));
		this.symptoms.add(new HumanSymptom(Effect.sneez.id, Utilities.seconds_to_ticks(3), severity));
		this.symptoms.add(new HumanSymptom(Effect.tired.id, Utilities.seconds_to_ticks(3), severity));
		this.symptoms.add(new HumanSymptom(Effect.fever.id, Utilities.seconds_to_ticks(3), severity));
	}

}
