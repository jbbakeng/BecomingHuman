package thestinkerbell.becominghuman.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import net.minecraft.util.MathHelper;
import thestinkerbell.becominghuman.utilities.SpeedConverter;

public class SpeedConversionTest {
	
	//http://minecraft.gamepedia.com/Transportation
	double expected_walking_speed_ms = 4.3;
	double expected_sprinting_speed_ms = 5.6;

	@Test
	public void canConvertFromInGameMotionToSpeedMetersPerSeconds() {
		//values are taken from ingame prinout of the diff between servers
		//vector this tick and the last when walking
		double x_walking = -0.21583235495552344;
		double z_walking = 0.0015408199978992343;
		double ground_walking_speed_ms = SpeedConverter.getSpeed_ms(x_walking, z_walking);
		assertTrue(expected_walking_speed_ms - ground_walking_speed_ms < 1e-2);
	}
	
	@Test
	public void canConvertFromInGameMotionToSpeedKilometersPerHour() {
		//values are taken from ingame prinout of the diff between servers
		//vector this tick and the last when sprinting
		double x_sprinting = 0.2806007716148926;
		double z_sprinting = -0.0013435502769425511;
		double ground_sprinting_speed_kph = SpeedConverter.getSpeed_kph(x_sprinting, z_sprinting);
		assertTrue(expected_sprinting_speed_ms - ground_sprinting_speed_kph < 1e-2);
	}

}
