package thestinkerbell.becominghuman.human.influences;

import thestinkerbell.becominghuman.human.Human;

public class HumanInfluence implements Influence {

	protected final int apply_per_number_of_ticks = 1;
	protected final Human human;
	
	public HumanInfluence(Human human) {
		this.human = human;
	}

	@Override
	public void apply() {
	}

}
