package thestinkerbell.becominghuman.human.diseases;

import thestinkerbell.becominghuman.human.risks.Risk;
import thestinkerbell.becominghuman.human.risks.Risks;
import thestinkerbell.becominghuman.human.symptoms.Symptoms;

public class HumanDisease implements Disease {
	
	private String name;
	protected Symptoms symptoms;
	protected Risks assosiated_minimum_risks;
	
	//duration/time-left: or should a disease only dissapear when risks are removed?
	//symptoms/signs: during the disease course
	
	public HumanDisease(String name) {
		this.name = name;
		this.symptoms = new Symptoms();
		this.assosiated_minimum_risks = new Risks();
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
	public Risks assosiatedMinimumRisks() {
		return this.assosiated_minimum_risks;
	}
	
	static public boolean risksMeetsRequirementsForDisease(Risks risks_present_in_human, Disease disease) {
		Risks risks_assosiated_with_disease = disease.assosiatedMinimumRisks();
		boolean all_risks_for_disease_are_present = true;
		for(Risk present_risk : risks_present_in_human) {
			boolean risk_is_present =  risks_assosiated_with_disease.isSmallerOrEqualRiskPresentThan(present_risk);
			all_risks_for_disease_are_present &= risk_is_present;
		}
		return all_risks_for_disease_are_present;
	}

}
