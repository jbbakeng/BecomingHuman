package thestinkerbell.becominghuman.human.diseases;

import thestinkerbell.becominghuman.human.properties.compound.BloodPressureCompoundHumanProperty.BloodPressureRisk;
import thestinkerbell.becominghuman.human.symptoms.HumanSymptom;
import thestinkerbell.becominghuman.human.symptoms.HumanSymptom.Severity;
import thestinkerbell.becominghuman.human.symptoms.effects.Effect;
import thestinkerbell.becominghuman.utilities.Utilities;

final public class HypertensionDisease extends HumanDisease implements Disease {

	public HypertensionDisease() {
		super("Hypertension", Severity.SYMPTOM_MODERATE, Utilities.weeks_to_ticks(3));
		
		this.assosiated_risks.add(BloodPressureRisk.BP_STAGE1HYPERTENSION);
		this.assosiated_risks.add(BloodPressureRisk.BP_STAGE2HYPERTENSION);
		this.assosiated_risks.add(BloodPressureRisk.BP_HYPERTENSIVEURGENCY);
		
		this.symptoms.add(new HumanSymptom(Effect.headache.id, Utilities.seconds_to_ticks(3), severity));
	}

}
