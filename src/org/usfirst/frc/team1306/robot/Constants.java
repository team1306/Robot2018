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
	
	/** VelocitySubsystem Constants */
	public final static double RS7_VEL_ADJ = 1.0;
	public final static double CTRE_MAG_VEL_ADJ = 1.0;
	
	/** PositionalSubsystem Constants */
	public final static double CTRE_MAG_POS_ADJ = 1.0;
	public final static double ROT_WITHIN_RANGE = 0.02;
	public final static double ROT_VEL = 0.2; //Max rotation velocity (in rotations/sec)
	public final static double ROT_ACCEL = 0.2; //Max rotation acceleration (in rotations/sec)
	public final static double ROT_JERK = 0.8; //Max rotation jerk (in rotations/sec)
	public final static double ROT_TIME = 2; //Max amount of time a turn can take
}