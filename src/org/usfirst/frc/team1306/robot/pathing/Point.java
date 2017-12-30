package org.usfirst.frc.team1306.robot.pathing;

/**
 * @Point
 * 
 * Specific point in a Profile, containing what position and velocity the wheels should have, the acceleration, and jerk at this point in time.
 * 
 * @author Jackson Goth
 */
public class Point {

	public double position, velocity, acceleration, jerk;
	
	public Point(double p, double v, double a, double j) {
		position = p;
		velocity = v;
		acceleration = a;
		jerk = j;
	}
}