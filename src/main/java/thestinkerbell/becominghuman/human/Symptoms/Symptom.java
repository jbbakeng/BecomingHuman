package thestinkerbell.becominghuman.human.Symptoms;

import net.minecraft.potion.PotionEffect;

public interface Symptom {

	boolean hasEffects();

	PotionEffect getEffect();

}
