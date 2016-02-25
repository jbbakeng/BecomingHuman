package thestinkerbell.becominghuman.human.influences.external;

import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.HumanBiology;
import thestinkerbell.becominghuman.human.influences.HumanInfluence;
import thestinkerbell.becominghuman.human.influences.Influence;
import thestinkerbell.becominghuman.human.properties.GermHumanProperty;
import thestinkerbell.becominghuman.human.properties.GermHumanProperty.Transmission;

public class BreathingInfluence extends HumanInfluence implements Influence {

	private final HumanBiology biology = new HumanBiology();
	
	public BreathingInfluence(Human human) {
		super(human);
	}
	
	@Override
	public void apply() {
		GermHumanProperty germ = biology.getRandomGerm();
		if(germ.canBeTransmittedBy(Transmission.TRANSMISSION_AIRBORN));
		{
			GermInfluence.applyInfectWithGerm(this.human, germ);
		}
	}

}
