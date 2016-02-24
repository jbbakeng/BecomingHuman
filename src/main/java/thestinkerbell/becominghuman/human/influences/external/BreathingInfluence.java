package thestinkerbell.becominghuman.human.influences.external;

import thestinkerbell.becominghuman.human.Human;
import thestinkerbell.becominghuman.human.HumanBiology;
import thestinkerbell.becominghuman.human.influences.HumanInfluence;
import thestinkerbell.becominghuman.human.influences.Influence;
import thestinkerbell.becominghuman.human.properties.GermHumanProperty;
import thestinkerbell.becominghuman.human.properties.GermHumanProperty.Transmission;

public class BreathingInfluence extends HumanInfluence implements Influence {

	//20ticks*60seconds*60minutes*24hours*7days = 12096000
	// 1/12096000 = 0,00000008267196
	// -> chance of getting infected once every week
	private final double chance_of_getting_infected_airborn = 8.2e-8;

	private final HumanBiology biology = new HumanBiology();
	
	public BreathingInfluence(Human human) {
		super(human);
	}
	
	@Override
	public void apply() {
		double random_getting_sick = Math.random();
		if(random_getting_sick <= chance_of_getting_infected_airborn) {
			GermHumanProperty germ = biology.getRandomGerm();
			if(germ.canBeTransmittedBy(Transmission.TRANSMISSION_AIRBORN));
			{
				double old_value = this.human.getGermHumanPropertyWithName(germ.getName()).getValue();
				double change = 1.0e-10; //how many germs
				double new_value = old_value + change;
				printDebug(old_value, change, new_value);
				try {
					this.human.setValue(germ.getName(), new_value);
					System.out.println("Infected with "+germ.getName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
