package thestinkerbell.becominghuman.tests.utilities;

import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.diseases.Disease;

public class TestHuman extends Human {
	
	public void addActiveDisease(Disease disease) {
		active_diseases.add(disease);
	}

}
