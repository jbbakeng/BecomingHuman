package thestinkerbell.becominghuman.human.risks;

import java.util.ArrayList;

public class Risks extends ArrayList<Risk>{
	
	public String toString() {
		String retval = "RISKS: ";
		for(Risk risk : this) {
			retval += " "+risk.toString();
		}
		return retval;
	}

}
