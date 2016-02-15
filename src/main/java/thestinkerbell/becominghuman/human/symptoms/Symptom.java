package thestinkerbell.becominghuman.human.symptoms;

import net.minecraft.potion.PotionEffect;

public interface Symptom {

	boolean hasEffects();

	PotionEffect getEffect();

}
