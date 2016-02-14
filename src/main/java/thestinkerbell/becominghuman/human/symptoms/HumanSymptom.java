package thestinkerbell.becominghuman.human.symptoms;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class HumanSymptom implements Symptom {

	final int potion_id;
	final int duration;
	
	public HumanSymptom(int potion_id, int duration) {
		this.potion_id = potion_id;
		this.duration = duration;
	}
	
	@Override
	public boolean hasEffects() {
		return true;
	}

	@Override
	public PotionEffect getEffect() {
		return new PotionEffect(potion_id, duration);
	}
	
	@Override
	public String toString() {
		return "SYMPTOM: potion_id="+this.potion_id+", duration="+this.duration;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + duration;
		result = prime * result + potion_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof HumanSymptom))
			return false;
		HumanSymptom other = (HumanSymptom) obj;
		if (duration != other.duration)
			return false;
		if (potion_id != other.potion_id)
			return false;
		return true;
	}

}
