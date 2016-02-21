package thestinkerbell.becominghuman.human.influences;

import java.util.LinkedList;

public class InfluenceQueue extends LinkedList<Influence> {

	public void process() {
		int size = this.size();
		while(this.size() > 0) {
			Influence influence = this.removeFirst();
			influence.apply();
			if(this.size() > size) {
				System.err.println("Queue growing... BAD!");
			}
		}
		
	}


}
