package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.lib.util.PIDParameters;
import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.drivetrain.AdjustSpeed.Speed;
import org.usfirst.frc.team1306.robot.drivetrain.DriveCommand;
import org.usfirst.frc.team1306.robot.drivetrain.DriveSide;
import org.usfirst.frc.team1306.robot.drivetrain.Settings;
import org.usfirst.frc.team1306.robot.drivetrain.Settings.DriveMode;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @Drivetrain
 * 
 * This subsystem is initialzed with a set of Settings provided from the CommandBase.
 * It uses that configuration to set-up sensors and the control mode for the driver, as well as putting each
 * side of the drivetrain into a seperate DriveSide object with the appropriate amount of Talons SRXs to control.
 * 
 * @author Jackson Goth
 */
public class Drivetrain extends Subsystem {

	private DriveSide leftMotors, rightMotors; //Sides of the drivetrain (each behaves like a TalonSRX)
	private Speed speed = Speed.FAST; //Default adjustment is 100% of input
	private DriveMode mode; //Initial manual drive-mode to use (Tank-drive, arcade, etc.)
	public AHRS navx; //NavX mxp, the gyroscope we use
	
	public Drivetrain(Settings settings) {
		leftMotors = new DriveSide(settings.leftSide);
		rightMotors = new DriveSide(settings.rightSide);
		mode = settings.driveMode; //Which manual drive-mode to use (Tank-drive, arcade, etc.)
		
		/* If encoders are present, initialized them in appropriate driveside */
		if(settings.encodersPresent) {
			leftMotors.initEncoders();
			leftMotors.setPIDParams(new PIDParameters(1.074,0.0,0.0,0.0));
			rightMotors.initEncoders();
			rightMotors.reverseSensor();
			rightMotors.setPIDParams(new PIDParameters(1.074,0.0,0.0,0.0));
		}
		
		try {
			navx = new AHRS(SPI.Port.kMXP);
			navx.reset(); //Resets yaw
		} catch(RuntimeException ex) { SmartDashboard.putString("ERROR:","Cannot initialize the NavX"); }
	}

	/** Drives the robot in 'PercentVBus' mode (-1.0-1.0) by giving the left and right motors potentially different speeds */
	public void drivePercentOutput(double leftVal, double rightVal) {
		if(Constants.DRIVETRAIN_ENABLED) {
			if(speed.equals(Speed.SLOW)) { leftVal *= 0.6; rightVal *= 0.6; }
			SmartDashboard.putNumber("leftOutput",leftVal);
			SmartDashboard.putNumber("rightOutput",rightVal);
			leftMotors.set(ControlMode.PercentOutput,leftVal);
			rightMotors.set(ControlMode.PercentOutput,-rightVal); 
		}
	}
	
	/** Drives the robot in 'Velocity' mode by giving left and right side motors potentially different speeds */
	public void driveVelocity(double leftVal, double rightVal) {
		if(Constants.DRIVETRAIN_ENABLED) {
			SmartDashboard.putNumber("leftOutput",leftVal);
			SmartDashboard.putNumber("rightOutput",rightVal);
			leftMotors.set(ControlMode.Velocity,leftVal);
			rightMotors.set(ControlMode.Velocity,-rightVal); 
		}
	}
	
	/** Adjusts speed up or down for various tasks (up for long dist, down for precision) */
	public void adjust(Speed s) {
		speed = s;
	}
	
	/** Stops turning all drive-motors */
	public void stop() {
		leftMotors.set(ControlMode.PercentOutput,0.0);
		rightMotors.set(ControlMode.PercentOutput,0.0);
	}
	
	/** Set's position of both encoders back to zero */
	public void resetEncoders() {
		leftMotors.resetEncoderPos();
		rightMotors.resetEncoderPos();
	}
	
	/** Return encoder position of either Left or Right sides of drivetrain) */
	public double getEncoderPos(Side side) {
		if(side.equals(Side.LEFT)) { return leftMotors.getEncoderPos(); }
		else { return rightMotors.getEncoderPos(); }
	}
	
	/** Return encoder velocity of either Left or Right sides of drivetrain) */
	public double getEncoderVel(Side side) {
		if(side.equals(Side.LEFT)) { return leftMotors.getEncoderVel(); }
		else { return rightMotors.getEncoderVel(); }
	}
	
	/** Returns total accumulated yaw value in degrees */
	public double getGyroAngle() {
		return navx.getAngle();
	}
	
	/**  Returns current yaw value (-180 to 180 degrees only) */
	public double getGyroYaw() {
		return navx.getYaw();
	}
	
	/** Returns displacement from navx along a given axis */
	public double getGyroDisplacement(Axis axis) {
		if(axis.equals(Axis.X)) {
			return navx.getDisplacementX();
		} else if(axis.equals(Axis.Y)) {
			return navx.getDisplacementY();
		} else {
			return navx.getDisplacementZ();
		}
	}
	
	public enum Side {LEFT,RIGHT}; //Enum used to differentiate between left and right wheels for accessing encoder data
	public enum Axis {X,Y,Z}; //Enum used to store possible axis to access displacement values
	
	/** Starts the manual driving command in a specified drive-mode */
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveCommand(mode));
	}
}
