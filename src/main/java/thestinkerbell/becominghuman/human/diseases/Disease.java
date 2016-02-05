package thestinkerbell.becominghuman.human.diseases;

import thestinkerbell.becominghuman.human.Symptoms.Symptoms;

public interface Disease {
	
	public String getName();
	public Symptoms getSymptoms();
	public Boolean hasSymptoms();

}
