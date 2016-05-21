package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.HumanBiology;
import thestinkerbell.becominghuman.human.diseases.Disease;
import thestinkerbell.becominghuman.human.diseases.Diseases;
import thestinkerbell.becominghuman.human.diseases.HumanDisease;
import thestinkerbell.becominghuman.human.diseases.HypertensionDisease;
import thestinkerbell.becominghuman.human.properties.HumanProperty.GeneralRisk;
import thestinkerbell.becominghuman.human.properties.BasicHumanProperty;
import thestinkerbell.becominghuman.human.properties.DoubleHumanProperty;
import thestinkerbell.becominghuman.human.properties.Properties;
import thestinkerbell.becominghuman.human.properties.Property;
import thestinkerbell.becominghuman.human.properties.compound.BloodPressureCompoundHumanProperty.BloodPressureRisk;
import thestinkerbell.becominghuman.human.properties.germ.InfluenzaAVirusHumanProperty;
import thestinkerbell.becominghuman.human.properties.germ.InfluenzaAVirusHumanProperty.InfluenzaARisk;
import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.Risks;
import thestinkerbell.becominghuman.human.symptoms.Symptoms;
import thestinkerbell.becominghuman.tests.utilities.TestHuman;
import thestinkerbell.becominghuman.tests.utilities.TestUtilities;
import thestinkerbell.becominghuman.human.symptoms.HumanSymptom.Severity;
import thestinkerbell.becominghuman.utilities.Utilities;

public class HumanTest {
	
	//--- TESTS ---
	
	@Test
	public void canCreateAHuman() {
		Human human = new Human();
	}
	
	@Test
	public void canGetListOfBasicHumanProperties() {
		Human human = new Human();
		Integer numberOfProperties = human.getListOfBasicHumanProperties().size();
		assertNotNull(numberOfProperties);
		assertTrue(numberOfProperties >=5);
	}
	
	@Test
	public void canGetListOfAllHumanProperties() {
		Human human = new Human();
		Integer numberOfProperties = human.getListOfAllHumanProperties().size();
		assertNotNull(numberOfProperties);
		assertTrue(numberOfProperties >=5);
	}
	
	@Test
	public void canGetRiskFromAllHumanProperties() {
		Human human = new Human();
		Properties properties = human.getListOfAllHumanProperties();
		for(Property property : properties) {
			Risk risk = property.getRisk();
		}
	}
	
	@Test
	public void canGetAListOfCurrentRisks() {
		Human human = new Human();
		Risks risks = human.getCurrentRisks();
		assertNotNull(risks);
	}
	
	@Test
	public void canGetAHumanProperty() {
		Human human = new Human();
		Property property = human.getHumanPropertyWithName(HumanBiology.age);
		assertNotNull(property);
	}
	
	@Test
	public void canSetValueOnABasicHumanProperty() {		
		Human human = new Human();
		Double newValue = 10.0;
		Double oldValue = TestUtilities.tryToSetValue(human, HumanBiology.age, newValue);
		Property newProperty = human.getHumanPropertyWithName(HumanBiology.age);
		assertTrue(newValue.doubleValue() == ((Double)newProperty.getValue()).doubleValue());
	}
	
	@Test
	public void canNotSetValueOnACompoundHumanProperty() {		
		Human human = new Human();
		Double oldValue = TestUtilities.tryToSetValue(human, HumanBiology.bmi, 10.0);
		Property newProperty = human.getHumanPropertyWithName(HumanBiology.bmi);
		assertTrue(oldValue.doubleValue() == ((Double)newProperty.getValue()).doubleValue());
	}
	
	@Test
	public void settingAPropertyThatDoesNotExistThrowsAnException() {
		Human human = new Human();
		try {
			human.setValue("NOT_DEFINED", 0.0);
		} catch (Exception e) {
			return;
		}
		fail("SetValue in Human did not fail, property does exist.");
	}
	
	@Test
	public void canSerializeAndDeserializeAllHumanProperties() {
		Human human = new Human();
		Properties list = human.getListOfBasicHumanProperties();
		for(Property property: list) {
			if(property instanceof DoubleHumanProperty)
				HumanPropertyTest.canSerializeAnDeserialize((BasicHumanProperty)property);
		}
	}

	@Test
	public void canGetAllSymptomsFromAHuman() {
		Human human = new Human();
		Symptoms all_symptoms = human.getListOfAllSymptoms();
		assertNotNull(all_symptoms);
	}
	
	
	@Test
	public void defaultHumanDoesNotHaveAnyDiseases() {
		Human human = new Human();
		Diseases diseases = human.getActiveDiseases();
		assertTrue(diseases.isEmpty());
	}
	
	@Test
	public void riskStage1HypertensionCausesDiseaseHypertension() {
		Human human = new Human();
		TestUtilities.createBPStage1Hypertension(human);
		
		Risks risks = human.getCurrentRisks();
		assertTrue(risks.contains(BloodPressureRisk.BP_STAGE1HYPERTENSION));
		
		Diseases diseases = human.getActiveDiseases();
		assertTrue(diseases.contains(new HypertensionDisease()));
	}
	
	@Test
	public void riskPreHypertensionDoesNotCausesDiseaseHypertension() {
		Human human = new Human();
		TestUtilities.createBPPreHypertension(human);
		
		Risks risks = human.getCurrentRisks();
		assertTrue(risks.contains(BloodPressureRisk.BP_PREHYPERTENSION));
		
		Diseases diseases = human.getActiveDiseases();
		assertFalse(diseases.contains(new HypertensionDisease()));
	}
	
	@Test
	public void noBPRisksDoesNotCausesDiseaseHypertension() {
		Human human = new Human();
		TestUtilities.createUndefinedBPRisk(human);
		
		Risks risks = human.getCurrentRisks();
		assertTrue(risks.contains(GeneralRisk.UNDEFINED));
		
		Diseases diseases = human.getActiveDiseases();
		assertFalse(diseases.contains(new HypertensionDisease()));
	}
	
	@Test
	public void having65log10mLOfInfluenzaAWillGiveInfluenzaDisease() {
		Human human = new Human();
		try {
			human.setValue(HumanBiology.influenza_a, 6.5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Risks risks = human.getCurrentRisks();
		assertTrue(risks.contains(InfluenzaARisk.IA_HIGH));
		
		Diseases diseases = human.getActiveDiseases();
		assertFalse(diseases.contains(new InfluenzaAVirusHumanProperty()));
	}
	
	@Test
	public void humanCanUpdateHealth() {
		Human human = new Human();
		int numberOfDiseasesBeforeUpdate = human.getActiveDiseases().size();
		human.updateHealth();
		int numberOfDiseasesAfterUpdate = human.getActiveDiseases().size();
		assertTrue(numberOfDiseasesBeforeUpdate == numberOfDiseasesAfterUpdate);
	}
	
	@Test
	public void humanCanUpdateHealthAndGetDiseases() {
		Human human = new Human();
		int numberOfDiseasesBeforeUpdate = human.getActiveDiseases().size();
		assertTrue(numberOfDiseasesBeforeUpdate == 0);
		
		TestUtilities.createBPStage1Hypertension(human);
		human.updateHealth();
		int numberOfDiseasesAfterFirstUpdate = human.getActiveDiseases().size();
		assertTrue(numberOfDiseasesBeforeUpdate != numberOfDiseasesAfterFirstUpdate);
		
		human.updateHealth();
		int numberOfDiseasesAfterSecondUpdate = human.getActiveDiseases().size();
		assertTrue(numberOfDiseasesAfterSecondUpdate == numberOfDiseasesAfterFirstUpdate);
	}
	
	@Test
	public void humanCanUpdateHealthAndGetCuredForDiseases() {
		TestHuman human = new TestHuman();
		
		String disease_name = "DISEASE_TEST";
		int disease_tick_duration = 10;
		Disease disease = TestUtilities.createDisease(disease_name, Severity.SYMPTOM_MILD ,disease_tick_duration);
		human.addActiveDisease(disease);
		
		for(int i=0; i<disease_tick_duration; i++) {			
			assertTrue(human.getActiveDiseases().size() == 1);
			human.updateHealth();
		}
		assertTrue(human.getActiveDiseases().size() == 0);
	}

}
