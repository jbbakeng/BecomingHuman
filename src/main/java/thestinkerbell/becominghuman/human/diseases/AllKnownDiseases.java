package thestinkerbell.becominghuman.human.diseases;

import thestinkerbell.becominghuman.human.risks.Risks;

final public class AllKnownDiseases {

	private Diseases all_known_diseases = new Diseases();
	
	public AllKnownDiseases() {
		all_known_diseases.add(new HypertensionDisease());
	}

	public Diseases identifyDiseasesBasedOnRisks(Risks risks_present_in_human) {
		Diseases matching_diseases = new Diseases();
		for(Disease known_disease : all_known_diseases) {
			boolean all_risks_for_disease_are_present = HumanDisease.risksMeetsRequirementsForDisease(risks_present_in_human, known_disease);
			if(all_risks_for_disease_are_present) {
				matching_diseases.add(known_disease);
			}
		}
		return matching_diseases;
	}

	public boolean isEmpty() {
		return all_known_diseases.isEmpty();
	}

}
