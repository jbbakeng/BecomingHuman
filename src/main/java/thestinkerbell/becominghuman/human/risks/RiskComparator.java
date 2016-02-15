package thestinkerbell.becominghuman.human.risks;

import java.util.Comparator;

public class RiskComparator implements Comparator<Risk> {

	@Override
	public int compare(Risk o1, Risk o2) {
		return o1.ordinal() - o2.ordinal();
	}

}
