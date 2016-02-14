package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import thestinkerbell.becominghuman.human.diseases.AllKnownDiseases;
import thestinkerbell.becominghuman.human.diseases.Disease;
import thestinkerbell.becominghuman.human.diseases.Diseases;
import thestinkerbell.becominghuman.human.diseases.HumanDisease;
import thestinkerbell.becominghuman.human.diseases.HypertensionDisease;
import thestinkerbell.becominghuman.human.properties.compound.BloodPressureCompoundHumanProperty.BloodPressureRisk;
import thestinkerbell.becominghuman.human.risks.Risks;
import thestinkerbell.becominghuman.human.symptoms.Symptom;
import thestinkerbell.becominghuman.human.symptoms.Symptoms;

public class DiseaseTest {
	
	private final String disease_name = "DISEASE_TEST"; 

	private Disease getDisease() {
		return new HumanDisease(disease_name);
	}

	@Test
	public void canCreateADisease() {
		Disease disease = getDisease();
		assertNotNull(disease);
	}

	@Test
	public void twoDiseasesOfTheSameClassAreTheSame() {
		Disease disease1 = getDisease();
		Disease disease2 = getDisease();
		assertTrue(disease1.equals(disease2));
	}
	
	@Test
	public void diseaseHasAName() {
		Disease disease = getDisease();
		assertNotNull(disease.getName());
		assertTrue(disease.getName() == disease_name);
	}
	
	@Test
	public void defaultDiseaseDoesNotHaveSymptoms() {
		Disease disease = getDisease();
		assertFalse(disease.hasSymptoms());
		
		Symptoms symptomps = disease.getSymptoms();
		assertNotNull(symptomps);
		assertTrue(symptomps.isEmpty());
	}
	
	@Test
	public void canCreateTheDiseaseHypertensionAndItHasSymptoms() {
		Disease hypertension = new HypertensionDisease();
		assertNotNull(hypertension);
		assertTrue(hypertension.hasSymptoms());
		
		Symptoms symptoms = hypertension.getSymptoms();
		assertFalse(symptoms.isEmpty());
		Symptom symptom = symptoms.get(0);
		assertNotNull(symptom);
	}
	
	@Test
	public void canGetListOfAllKnownDiseases() {
		AllKnownDiseases akd = new AllKnownDiseases();
		assertNotNull(akd);
		assertFalse(akd.isEmpty());
	}
	
	@Test
	public void canGetTheDiseaseHypertensionFromKnownRisks() {
		Risks risks = new Risks();
		risks.add(BloodPressureRisk.BP_STAGE1HYPERTENSION);
		
		AllKnownDiseases akd = new AllKnownDiseases();
		assertNotNull(akd);
		
		Diseases diseases = akd.identifyDiseasesBasedOnRisks(risks);
		assertNotNull(diseases);
		assertTrue(diseases.contains(new HypertensionDisease()));
	}
	
	@Test
	public void risksMeetRequirementForHypertension() {
		AllKnownDiseases akd = new AllKnownDiseases();
		assertNotNull(akd);

		Risks risks1 = new Risks();
		risks1.add(BloodPressureRisk.BP_DESIRED);
		Risks risks2 = new Risks();
		risks2.add(BloodPressureRisk.BP_PREHYPERTENSION);
		Risks risks3 = new Risks();
		risks3.add(BloodPressureRisk.BP_STAGE1HYPERTENSION);
		Risks risks4 = new Risks();
		risks4.add(BloodPressureRisk.BP_STAGE2HYPERTENSION);
		
		Disease hypertension = new HypertensionDisease();
		assertFalse(HumanDisease.risksMeetsRequirementsForDisease(risks1, hypertension));
		assertFalse(HumanDisease.risksMeetsRequirementsForDisease(risks2, hypertension));
		assertTrue(HumanDisease.risksMeetsRequirementsForDisease(risks3, hypertension));
		assertTrue(HumanDisease.risksMeetsRequirementsForDisease(risks4, hypertension));
	}

}
