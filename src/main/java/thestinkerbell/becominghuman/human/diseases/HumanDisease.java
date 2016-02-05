package thestinkerbell.becominghuman.human.diseases;

import thestinkerbell.becominghuman.human.symptoms.Symptoms;

public class HumanDisease implements Disease {
	
	private String name;
	protected Symptoms symptoms;
	
	//duration/time-left: or should a disease only dissapear when risks are removed?
	//symptoms/signs: during the disease course
	
	public HumanDisease(String name) {
		this.name = name;
		this.symptoms = new Symptoms();
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

}
