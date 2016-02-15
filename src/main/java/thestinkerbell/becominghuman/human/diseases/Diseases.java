package thestinkerbell.becominghuman.human.diseases;

import java.util.ArrayList;

public class Diseases extends ArrayList<Disease> {

	public String toString() {
		String retval = "DISEASES: ";
		for(Disease disease : this) {
			retval += " "+disease.toString();
		}
		return retval;
	}
	
}
