package org.usfirst.frc.team1306.robot;

/**
 * Constants
 * 
 * To store finalized variables all in one place.
 * 
 * @author Jackson Goth and Sam Roquitte
 */
public class Constants {

	/** Subsystem Control (Switching to false will disable all output for that subsystem) */
	public final static boolean DRIVETRAIN_ENABLED = true;		
	
	/** SmartDashboard Debug Modes */
	public static final boolean DRIVETRAIN_DEBUG = true;
	public static final boolean GYRO_DEBUG = true;
	
	/** OI Constants */
	public final static double DEADBAND = 0.15; //Joystick and trigger deadband
	public final static double JOYSTICK_MULTIPLIER = 1.0; //Joystick inputs raised to this power
	
	/** Drivetrain Constants */
	public final static double BASE_WIDTH = 25.5; //In inches
	public final static double WHEEL_DIAMETER = 4; //In inches
	public final static double PROFILE_STEP_TIME = 0.01;
	public final static double ENCODER_CODES_REV = 256; //256 for grayhill encoders
	public final static double GYRO_ERROR_MULTIPLIER = 9;
	public final static double ENCODER_ERROR_MULTIPLIER = 2.25;
	public final static double WHEEL_CIRCUMFERENCE = WHEEL_DIAMETER * Math.PI;
	public final static double FPS_TO_RPM_CONVERSION = (12/WHEEL_CIRCUMFERENCE) * 60; //Converts feet/sec into rot/min
	public final static double IPS_TO_RPM_CONVERSION = (1/WHEEL_CIRCUMFERENCE) * 60; //Converts inch/sec into rot/min
	public final static double ENCODER_INCHES_CONVERSION = (1 / (ENCODER_CODES_REV*4)) * WHEEL_CIRCUMFERENCE; //Converts encoder position to distance drivetrain has traveled
}