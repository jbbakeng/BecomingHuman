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

	public boolean isSmallerOrEqualRiskPresentThan(Risk risk) {
		boolean retval = false;
		Risks same_type_risks = this.getSubsetOfRisksOfTheSameType(risk);
		if(!same_type_risks.isEmpty()) {
			RiskComparator comparator = new RiskComparator();
			same_type_risks.sort(comparator);
			Risk lowest_risk = same_type_risks.get(0);
			retval = comparator.compare(lowest_risk, risk) <= 0 ? true : false;
		}
		return retval;
	}

}
