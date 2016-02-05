package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import thestinkerbell.becominghuman.human.Symptoms.Symptom;
import thestinkerbell.becominghuman.human.Symptoms.Symptoms;
import thestinkerbell.becominghuman.human.diseases.Disease;
import thestinkerbell.becominghuman.human.diseases.HumanDisease;
import thestinkerbell.becominghuman.human.diseases.HypertensionDisease;

public class DiseaseTest {
	
	private final String disease_name = "DISEASE_TEST"; 

	@Test
	public void canCreateADisease() {
		Disease disease = new HumanDisease(disease_name);
		assertNotNull(disease);
	}
	
	@Test
	public void diseaseHasAName() {
		Disease disease = new HumanDisease(disease_name);
		assertNotNull(disease.getName());
		assertTrue(disease.getName() == disease_name);
	}
	
	@Test
	public void diseaseCausesSymptoms() {
		Disease disease = new HumanDisease(disease_name);
		assertFalse(disease.hasSymptoms());
		Symptoms symptomps = disease.getSymptoms();
		assertNotNull(symptomps);
		assertTrue(symptomps.isEmpty());
	}
	
	@Test
	public void canCreateTheDiseaseHypertension() {
		Disease hypertension = new HypertensionDisease();
		assertNotNull(hypertension);
		assertTrue(hypertension.hasSymptoms());
		Symptoms symptoms = hypertension.getSymptoms();
		assertFalse(symptoms.isEmpty());
		Symptom symptom = symptoms.get(0);
		assertNotNull(symptom);
	}

}
