package org.usfirst.frc.team1306.robot;

/**
 * @RobotMap
 * 
 * The RobotMap is a mapping from the ports sensors and actuators are wired into to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers floating around.
 * 
 * @authors Jackson Goth, Sam Roquitte, and Ethan Dong
 */
public class RobotMap {
	
	/** OI Ports */
	public static final int PRIMARY_PORT = 0;
	public static final int SECONDARY_PORT = 1;
	
	/** Climber Ports */
	public static final int CLIMBER_SPARK = 2;
	
	/** Cubetake Ports */
	public static final int RIGHT_CUBETAKE_SPARK = 0;
	public static final int LEFT_CUBETAKE_SPARK = 1;
	public static final int CUBETAKE_SOLENOID_CHANNEL_ONE = 6;
	public static final int CUBETAKE_SOLENOID_CHANNEL_TWO = 7;
	
	/** Drivetrain Ports */
	public static final int LEFT_DRIVETRAIN_TALON = 1;
	public static final int RIGHT_DRIVETRAIN_TALON = 2;
	public static final int LEFT_DRIVETRAIN_VICTOR = 3;
	public static final int RIGHT_DRIVETRAIN_VICTOR = 4;
	
	/** Elevator Ports */
	public static final int ELEVATOR_TALON = 5;
	public static final int ELEVATOR_SOLENOID_CHANNEL = 2;

}
