package thestinkerbell.becominghuman.human.diseases;

import thestinkerbell.becominghuman.human.symptoms.Symptoms;

public interface Disease {
	
	public String getName();
	public Symptoms getSymptoms();
	public Boolean hasSymptoms();

}
