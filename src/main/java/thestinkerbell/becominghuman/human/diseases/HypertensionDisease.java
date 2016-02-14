package thestinkerbell.becominghuman.human.diseases;

import net.minecraft.potion.Potion;
import thestinkerbell.becominghuman.human.properties.compound.BloodPressureCompoundHumanProperty.BloodPressureRisk;
import thestinkerbell.becominghuman.human.risks.Risks;
import thestinkerbell.becominghuman.human.symptoms.HumanSymptom;
import thestinkerbell.becominghuman.utilities.Utilities;

final public class HypertensionDisease extends HumanDisease implements Disease {

	public HypertensionDisease() {
		super("Hypertension");
		this.assosiated_minimum_risks.add(BloodPressureRisk.BP_STAGE1HYPERTENSION);
		this.symptoms.add(new HumanSymptom(Potion.blindness.id, Utilities.seconds_to_ticks(60)));
	}

}
