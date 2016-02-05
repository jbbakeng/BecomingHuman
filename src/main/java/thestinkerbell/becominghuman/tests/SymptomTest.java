package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import thestinkerbell.becominghuman.human.Symptoms.HumanSymptom;
import thestinkerbell.becominghuman.human.Symptoms.Symptom;

public class SymptomTest {

	private Symptom getSymptom() {
		Symptom symptom = new HumanSymptom(Potion.moveSlowdown.id, 1200);
		return symptom;
	}

	@Test
	public void canCreateASymptom() {
		Symptom symptom = getSymptom();
		assertNotNull(symptom);
	}
	
	@Test
	public void aSymptomHasAnEffect() {
		Symptom symptom = getSymptom();
		assertTrue(symptom.hasEffects());
	}

}
