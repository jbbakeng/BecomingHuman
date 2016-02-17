package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import thestinkerbell.becominghuman.human.symptoms.HumanSymptom;
import thestinkerbell.becominghuman.human.symptoms.HumanSymptom.Severity;
import thestinkerbell.becominghuman.human.symptoms.Symptom;
import thestinkerbell.becominghuman.human.symptoms.effects.Effect;
import thestinkerbell.becominghuman.utilities.Utilities;

public class SymptomTest {
	
	public static Effect effect = new Effect();

	private Symptom getSymptom() {
		Symptom symptom = new HumanSymptom(Effect.pain.id, Utilities.seconds_to_ticks(2), Severity.SYMPTOM_MILD);
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

	@Test
	public void twoSymptomsOfTheSameClassAreTheSame() {
		Symptom symptom1 = getSymptom();
		Symptom symptom2 = getSymptom();
		assertTrue(symptom1.equals(symptom2));
	}
}
