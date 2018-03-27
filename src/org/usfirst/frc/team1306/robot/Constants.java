package org.usfirst.frc.team1306.robot;

/**
 * @Constants - To store finalized variables all in one place
 * @authors Jackson Goth, Sam Roquitte, and Ethan Dong
 */
public class Constants {

	/** Subsystem Control (Switching to false will disable all output for that subsystem) */
	public final static boolean DRIVETRAIN_ENABLED = true;
	public final static boolean CUBETAKE_ENABLED = true;
	public final static boolean ELEVATOR_ENABLED = false;
	
	/** SmartDashboard Debug Modes */
	public static final boolean DRIVETRAIN_DEBUG = true;
	public static final boolean GYRO_DEBUG = true;
	public static final boolean CUBETAKE_DEBUG = false;
	
	/** OI Constants */
	public final static double DEADBAND = 0.15; //Joystick and trigger deadband
	public final static double JOYSTICK_MULTIPLIER = 1.0; //Joystick inputs raised to this power
	
	/** Autonomous Constants*/
	public final static double AUTO_PROFILE_TIME = 5; //Seconds
	
	/** Drivetrain Constants */
	public final static double TRACK_WIDTH = 24.5; //Inches
	public final static double TRACK_WIDTH_FT = TRACK_WIDTH / 2;
	public final static double WHEEL_DIAMETER = 6; //Inches
	public final static double PROFILE_STEP_TIME = 0.1;
	public final static double PROFILE_TIME_ADJ = 0.25; //To ensure the Follow2DPath command gets through the entire velocity profile.
	public final static double ENCODER_CODES_REV = 256; //256 for grayhill encoders
	public final static double GYRO_ERROR_MULTIPLIER = 9;
	public final static double ENCODER_ERROR_MULTIPLIER = 2.25;
	public final static double WHEEL_CIRCUMFERENCE = (WHEEL_DIAMETER * Math.PI) / 12; //Feet
	public final static double FPS_TO_RPM_CONVERSION = 60 / (WHEEL_CIRCUMFERENCE); //Converts feet/sec into rot/min
	public final static double IPS_TO_RPM_CONVERSION = (1/WHEEL_CIRCUMFERENCE) * 60; //Converts inch/sec into rot/min
	public final static double ENCODER_INCHES_CONVERSION = (1 / (ENCODER_CODES_REV*4)) * WHEEL_CIRCUMFERENCE; //Converts encoder position to distance drivetrain has traveled
	
	/** Elevator Constants */
	public final static double ELEVATOR_MAX_POS = 1000; //TODO Calibrate
	public final static double ELEVATOR_IN_RANGE_POS = 25;
	public final static double ELEVATOR_SCALE_HEIGHT = 800;
	public final static double ELEVATOR_SWITCH_HEIGHT = 400;
	public final static double ELEVATOR_CARRYING_HEIGHT = 400;
	public final static double ELEVATOR_FLOOR_HEIGHT = 50;
	public final static double ELEVATOR_POUTPUT_UP = -0.95;
	public final static double ELEVATOR_POUTPUT_DOWN = 0.6;
	
	/** Cubetake Constants */
	public final static double CUBETAKE_SPEED_SLOWER = -0.55;
	public final static double CUBETAKE_SPEED_FASTER = -0.7;
	public final static double CUBETAKE_THROUGH = -0.95;
	public final static double CUBETAKE_SPIT_TIME = 2; //Time it takes to autonomously spit cube, with extra just to be sure...
	
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
