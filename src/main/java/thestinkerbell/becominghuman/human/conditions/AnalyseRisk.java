package thestinkerbell.becominghuman.human.conditions;

import java.util.ArrayList;
import java.util.List;

import thestinkerbell.becominghuman.human.properties.compound.BloodPressureCompoundHumanProperty.BloodPressureRisk;
import thestinkerbell.becominghuman.human.risks.Risk;

public class AnalyseRisk {
	
	public static <TYPE> Boolean greaterThan(TYPE risk1, TYPE risk2) {
		return true;
	}

	public static Condition analyse(Risk risk) {
		Condition condition = null;
		if(risk == BloodPressureRisk.BP_STAGE1HYPERTENSION)
			condition = Condition.HYPERTENSION;
		return condition;
	}

	public static List<Condition> analyse(List<Risk> risks) {
		List<Condition> conditions = new ArrayList();
		for (Risk risk : risks) {
			Condition condition = AnalyseRisk.analyse(risk);
			if(condition != null)
				conditions.add(condition);
		}
		checkIfHealthy(conditions);
		return conditions;
	}

	private static void checkIfHealthy(List<Condition> conditions) {
		if (conditions.isEmpty())
			conditions.add(Condition.HEALTHY);
	}

}
