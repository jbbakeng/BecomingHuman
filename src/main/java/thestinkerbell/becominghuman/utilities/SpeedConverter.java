package thestinkerbell.becominghuman.utilities;

import net.minecraft.util.MathHelper;

public class SpeedConverter {
	
	//assuming 
	//	- 1 block is 1 meter
	//	- tickrate: 20 ticks per second
	
	//http://minecraft.gamepedia.com/Transportation
	// 4.3 m/s = 0.2158590406179428 * conversion_factor_ms
	//private static double conversion_factor_ms = 36.48; //motionX on client side
	private static double conversion_factor_ms = 19.9204072606379; //vector diff on server side
	private static double conversion_factor_ms_to_kph = 3.6;

	public static double getSpeed_ms(double motionX, double motionZ) {
		double ground_motion = getGroundMotion(motionX, motionZ);
		return ground_motion * conversion_factor_ms;
	}

	public static double getSpeed_kph(double motionX, double motionZ) {
		double speed_ms = getSpeed_ms(motionX, motionZ);
		return speed_ms * conversion_factor_ms_to_kph;
	}

	private static double getGroundMotion(double motionX, double motionZ) {
		double ground = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
		return ground;
	}
}
