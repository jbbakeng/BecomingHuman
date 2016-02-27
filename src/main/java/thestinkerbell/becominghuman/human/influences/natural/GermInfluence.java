package thestinkerbell.becominghuman.human.influences.natural;

import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.HumanBiology;
import thestinkerbell.becominghuman.human.influences.HumanInfluence;
import thestinkerbell.becominghuman.human.influences.Influence;
import thestinkerbell.becominghuman.human.properties.GermHumanProperty;

public class GermInfluence extends HumanInfluence {
	
	public static final double change = 1.0e-10; //how many germs (balanced for viruses)
	private final GermHumanProperty germ;

	public GermInfluence(Human human, GermHumanProperty germ) {
		super(human);
		this.germ = germ;
	}
	
	@Override
	public void apply() {
		HumanBiology.applyChange(this.human, this.germ.getName(), change);
	}

}