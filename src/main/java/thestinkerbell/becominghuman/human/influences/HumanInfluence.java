package thestinkerbell.becominghuman.human.influences;

import thestinkerbell.becominghuman.human.Human;

public class HumanInfluence implements Influence {

	protected final int apply_per_number_of_ticks = 1; //apply is called every tick
	protected final Human human;
	
	public HumanInfluence(Human human) {
		this.human = human;
	}

	@Override
	public void apply() {
	}
	
	protected void printDebug(Double old_value, Double change, Double new_value) {
		System.out.println("\nApplying "+this.getClass().getSimpleName());
		System.out.println("old_value: "+old_value);
		System.out.println("change: "+change);
		System.out.println("new_value: "+new_value);
		Double after_half_a_min = (change*20*30)+old_value;
		System.out.println("after 0.5 min: "+after_half_a_min);
		Double after_one_min = (change*20*60)+old_value;
		System.out.println("after 1 min: "+after_one_min);
		Double after_five_min = (change*20*60*5)+old_value;
		System.out.println("after 5 min: "+after_five_min);
	}
	
}
