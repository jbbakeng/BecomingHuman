package thestinkerbell.becominghuman.human.diseases;

import net.minecraft.potion.Potion;
import thestinkerbell.becominghuman.human.symptoms.HumanSymptom;
import thestinkerbell.becominghuman.utilities.Utilities;

final public class HypertensionDisease extends HumanDisease implements Disease {

	public HypertensionDisease() {
		super("Hypertension");
		this.symptoms.add(new HumanSymptom(Potion.blindness.id, Utilities.seconds_to_ticks(60)));
	}

}
