package thestinkerbell.becominghuman.human.diseases;

import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.Risks;
import thestinkerbell.becominghuman.human.symptoms.HumanSymptom.Severity;
import thestinkerbell.becominghuman.human.symptoms.Symptoms;

public class HumanDisease implements Disease {
	
	private final String name;
	protected final Severity severity;
	protected Symptoms symptoms;
	protected Risks assosiated_risks;
	
	public HumanDisease(String name, Severity severity) {
		this.name = name;
		this.severity = severity;
		this.symptoms = new Symptoms();
		this.assosiated_risks = new Risks();
	}
	
	@Override
	final public String getName() {
		return this.name;
	}

	@Override
	final public Symptoms getSymptoms() {
		return symptoms;
	}

	@Override
	final public Boolean hasSymptoms() {
		return !symptoms.isEmpty();
	}
	
	@Override
	public String toString() {
		return "\n	DISEASE: "
			 + "\n	name="+this.getName()+", "
	 		 + "\n	symptoms="+this.getSymptoms();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((symptoms == null) ? 0 : symptoms.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof HumanDisease))
			return false;
		HumanDisease other = (HumanDisease) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (symptoms == null) {
			if (other.symptoms != null)
				return false;
		} else if (!symptoms.equals(other.symptoms))
			return false;
		return true;
	}

	@Override
	public Risks assosiatedRisks() {
		return this.assosiated_risks;
	}
	
	static public boolean risksMeetsRequirementsForDisease(Risks risks_present_in_human, Disease disease) {
		//return risksHaveToBeGreaterThanOrEqual(risks_present_in_human, disease);
		return risksHaveToBeEqual(risks_present_in_human, disease);
	}

	private static boolean risksHaveToBeEqual(Risks risks_present_in_human, Disease disease) {
		Risks risks_assosiated_with_disease = disease.assosiatedRisks();
		boolean all_risks_for_disease_are_present = true;
		for(Risk associated_risk : risks_assosiated_with_disease) {
			Risks same_type_risks_in_human = risks_present_in_human.getSubsetOfRisksOfTheSameType(associated_risk);
			if(same_type_risks_in_human.isEmpty()){				
				all_risks_for_disease_are_present &= false;
			} else {
				boolean found_risk = false;
				for(Risk risk_in_human : same_type_risks_in_human) {
					if(risks_assosiated_with_disease.contains(risk_in_human))
						found_risk = true;
				}
				all_risks_for_disease_are_present &= found_risk;
			}
			
		}
		return all_risks_for_disease_are_present;
	}

	@Deprecated
	private static boolean risksHaveToBeGreaterThanOrEqual(Risks risks_present_in_human, Disease disease) {
		Risks risks_assosiated_with_disease = disease.assosiatedRisks();
		boolean all_risks_for_disease_are_present = true;
		for(Risk associated_risk : risks_assosiated_with_disease) {
			boolean risk_meets_disease_requirements = riskMeetsRisksRequirements(risks_present_in_human, associated_risk);
			all_risks_for_disease_are_present &= risk_meets_disease_requirements;
		}
		return all_risks_for_disease_are_present;
	}

	@Deprecated
	private static boolean riskMeetsRisksRequirements(Risks risks_present_in_human, Risk associated_risk) {
		boolean risk_meets_disease_requirements = risks_present_in_human.containsGreaterThanOrEqual(associated_risk);
		return risk_meets_disease_requirements;
	}

}
