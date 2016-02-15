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
	
	public Risks getSubsetOfRisksOfTheSameType(Risk match_risk) {
		Risks retval = new Risks();
		for(Risk risk : this) {
			if(risk.getClass().equals(match_risk.getClass()))
				retval.add(risk);
		}
		return retval;
	}

	public boolean containsGreaterThanOrEqual(Risk risk) {
		Risks same_type_risks = this.getSubsetOfRisksOfTheSameType(risk);
		boolean contains_greater_than_or_equal = false;
		if(!same_type_risks.isEmpty()) {
			RiskComparator comparator = new RiskComparator();
			same_type_risks.sort(comparator);
			Risk highest_risk = same_type_risks.get(same_type_risks.size()-1);
			contains_greater_than_or_equal = comparator.compare(highest_risk, risk) >= 0 ? true : false;
		}
		return contains_greater_than_or_equal;
	}

}
