package thestinkerbell.becominghuman.human.diseases;

public class DiseaseProgression {

	private boolean active = false;
	private int tick_duration = 0;
	private int tick_progressed = 0;
	
	public DiseaseProgression(int tick_duration) {
		this.tick_duration = tick_duration;
	}
	
	public boolean isActive() {
		return this.active;
	}

	public void progress() {
		tick_progressed += 1;
		this.active = (tick_duration - tick_progressed) > 0;
	}

	public void activate() {
		this.active = true;
	}
	
	@Override
	public String toString() {
		return "\n	DISEASE_PROGRESSION: "
			 + "\n	active="+this.active+", "
	 		 + "\n	tick_duration="+this.tick_duration+", "
			 + "\n	tick_progressed="+this.tick_progressed;
	}

}
