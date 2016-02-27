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
	
	// --- TIME
	
	public static int seconds_to_ticks(int seconds) {
		int ticks_per_second = 20;
		int ticks = seconds * ticks_per_second;
		return ticks;
	}
	
	public static int minutes_to_ticks(int minutes) {
		int seconds_per_minute = 60;
		int ticks = seconds_to_ticks(seconds_per_minute) * minutes;
		return ticks;
	}
	
	public static int hours_to_ticks(int hours) {
		int minutes_per_hour = 60;
		int ticks = minutes_to_ticks(minutes_per_hour) * hours;
		return ticks;
	}
	
	public static int days_to_ticks(int days) {
		int hours_per_day = 24;
		int ticks = hours_to_ticks(hours_per_day) * days;
		return ticks;
	}
	
	public static int weeks_to_ticks(int weeks) {
		int days_per_week = 7;
		int ticks = days_to_ticks(days_per_week) * weeks;
		return ticks;
	}
	
	// --- CHANCE
	
	public static double getChanceOfHappening(double ticks) {
		return 1/ticks;
	}
	
	public static boolean chanceOccured(double chance_of_occuring) {
		double random_chance = Math.random();
		boolean occur = random_chance <= chance_of_occuring;
		return occur;
	}
}
