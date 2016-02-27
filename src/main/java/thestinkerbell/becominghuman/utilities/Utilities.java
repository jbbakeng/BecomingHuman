package thestinkerbell.becominghuman.utilities;

import io.netty.buffer.ByteBuf;
import thestinkerbell.becominghuman.human.properties.HumanProperty;

public class Utilities {
	
	public static void print(ByteBuf buf) {
		for (int i = 0; i < buf.capacity(); i ++) {
			 byte b = buf.getByte(i);
			 System.out.println((char) b);
		}
	}
	
	public static int seconds_to_ticks(int seconds) {
		int ticks_per_second = 20;
		int ticks = seconds * ticks_per_second;
		return ticks;
	}
	
	public static boolean chanceOccured(double chance_of_occuring) {
		double random_chance = Math.random();
		boolean occur = random_chance <= chance_of_occuring;
		return occur;
	}
}
