package thestinkerbell.becominghuman.human.risks;

import com.google.common.collect.Range;

import thestinkerbell.becominghuman.utilities.Pair;

public class PairDoubleRiskRange extends RiskRange<Pair<Double>> {

	public PairDoubleRiskRange(Risk risk, Pair<Double> min, Pair<Double> max) {
		super(risk, min, max);
	}

	public Boolean contains(Pair<Double> value) {
		Boolean containsFirst = Range.closed(this.min.first(), this.max.first()).contains(value.first());
		Boolean containsSecond = Range.closed(this.min.second(), this.max.second()).contains(value.second());
		return (containsFirst && containsSecond);
	}
}
