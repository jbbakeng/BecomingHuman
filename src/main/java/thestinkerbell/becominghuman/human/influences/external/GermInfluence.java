package thestinkerbell.becominghuman.human.influences.external;

import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.HumanBiology;
import thestinkerbell.becominghuman.human.influences.HumanInfluence;
import thestinkerbell.becominghuman.human.influences.Influence;
import thestinkerbell.becominghuman.human.properties.GermHumanProperty;

public class GermInfluence extends HumanInfluence {

	public static void applyInfectWithGerm(Human human, GermHumanProperty germ) {
		double old_value = human.getGermHumanPropertyWithName(germ.getName()).getValue();
		double change = 1.0e-10; //how many germs (balanced for viruses)
		double new_value = old_value + change;
		try {
			human.setValue(germ.getName(), new_value);
			System.out.println("Infected with "+germ.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private final GermHumanProperty germ;

	public GermInfluence(Human human, GermHumanProperty germ) {
		super(human);
		this.germ = germ;
	}
	
	@Override
	public void apply() {
		GermInfluence.applyInfectWithGerm(this.human, this.germ);
	}

}