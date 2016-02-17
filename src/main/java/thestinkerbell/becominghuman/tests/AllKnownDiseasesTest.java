package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import thestinkerbell.becominghuman.human.diseases.AllKnownDiseases;
import thestinkerbell.becominghuman.human.diseases.CommonColdDisease;
import thestinkerbell.becominghuman.human.diseases.Disease;
import thestinkerbell.becominghuman.human.diseases.Diseases;
import thestinkerbell.becominghuman.human.diseases.HypertensionDisease;
import thestinkerbell.becominghuman.human.diseases.InfluenzaDisease;
import thestinkerbell.becominghuman.human.properties.germ.InfluenzaAVirusHumanProperty.InfluenzaARisk;
import thestinkerbell.becominghuman.human.risks.Risks;

public class AllKnownDiseasesTest {

	final private AllKnownDiseases akd = new AllKnownDiseases();
	
	private void assertDiseaseCanBeIdentified(Disease disease) {
		Risks risks = disease.assosiatedRisks();
		assertRisksCanIdentifyDisease(disease, risks);
	}

	private void assertRisksCanIdentifyDisease(Disease disease, Risks risks) {
		Diseases identified = akd.identifyDiseasesBasedOnRisks(risks);
		assertTrue(identified.size() == 1);
		assertTrue(identified.contains(disease));
	}
	
	// --- TESTS
	
	@Test
	public void canCreateAllKnownDiseases() {
		assertNotNull(akd);
	}
	
	@Test
	public void thereAreKnownDiseases() {
		assertFalse(akd.isEmpty());
	}
	
	@Test
	public void canIdentifyAllKnownDiseses() {
		Diseases all_known_diseses = akd.getAllKnownDiseses();
		for(Disease disease : all_known_diseses) {
			assertDiseaseCanBeIdentified(disease);
		}
	}
	
	@Test
	public void canIdentifyInluenzaBasedOnSingleRisks() {
		InfluenzaDisease influenza = new InfluenzaDisease();

		Risks risks1 = new Risks();
		risks1.add(InfluenzaARisk.IA_HIGH);
		assertRisksCanIdentifyDisease(influenza, risks1);

		
		Risks risks2 = new Risks();
		risks2.add(InfluenzaARisk.IA_DEADLY);
		assertRisksCanIdentifyDisease(influenza, risks2);	
	}

}
