package org.usfirst.frc.team1306.robot.pathing;

/**
 * @ProfileParams
 * 
 * Parameters for creating a Profile, max velocity, max acceleration, and max jerk.
 * 
 * @author Jackson Goth
 */
public class ProfileParams {

	public double velocity, acceleration, jerk;
	
	public ProfileParams(double v, double a, double j) {
		velocity = v;
		acceleration = a;
		jerk = j;
	}
}