package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import thestinkerbell.becominghuman.human.diseases.DiseaseProgression;

public class DiseaseProgressionTest {
	private int tick_duration = 1;

	@Test
	public void diseaseProgesssionIsConstructedInactive() {
		DiseaseProgression disease_progression = new DiseaseProgression(tick_duration);
		assertFalse(disease_progression.isActive());
	}
	
	@Test
	public void progressingInactiveDiseaseProgressionDoesNotActivateIt() {
		DiseaseProgression disease_progression = new DiseaseProgression(tick_duration);
		assertFalse(disease_progression.isActive());
		disease_progression.progress();
		assertFalse(disease_progression.isActive());
	}
	
	@Test
	public void diseaseProgressionCanBeActivated() {
		DiseaseProgression disease_progression = new DiseaseProgression(tick_duration);
		assertFalse(disease_progression.isActive());
		disease_progression.activate();
		assertTrue(disease_progression.isActive());
	}
	
	@Test
	public void activeDiseaseProgressionCanProgressUntilInactive() {
		DiseaseProgression disease_progression = new DiseaseProgression(tick_duration);
		assertFalse(disease_progression.isActive());
		disease_progression.activate();
		assertTrue(disease_progression.isActive());
		for(int i=0; i<tick_duration; i++) {
			assertTrue(disease_progression.isActive());
			disease_progression.progress();
		}
		assertFalse(disease_progression.isActive());
	}

}
