package org.usfirst.frc.team1306.robot.pathing;

/**
 * @Point - Point in a profile ontaining what position, velocity, acceleration, and jerk the robot should have at a point in time.
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
