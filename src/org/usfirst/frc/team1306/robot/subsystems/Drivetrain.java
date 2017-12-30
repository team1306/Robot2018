package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.drivetrain.Drive;
import org.usfirst.frc.team1306.robot.drivetrain.DriveSide;
import org.usfirst.frc.team1306.robot.drivetrain.Settings;
import org.usfirst.frc.team1306.robot.drivetrain.Settings.DriveMode;
import com.ctre.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

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
	private DriveMode mode; //Initial manual drive-mode to use (Tank-drive, arcade, etc.)
	public Gyro gyro; //Main gyro object other classes with reference
	
	public Drivetrain(Settings settings) {
		leftMotors = new DriveSide(settings.leftSide);
		rightMotors = new DriveSide(settings.rightSide);
		mode = settings.driveMode; //Which manual drive-mode to use (Tank-drive, arcade, etc.)
		
		/* If gyro is present, makes it accessible */
		if(settings.gyro != null) {
			gyro = settings.gyro;
		}
		/* If encoders are present, initialized them in appropriate driveside */
		if(settings.encodersPresent) {
			leftMotors.initEncoders();
			rightMotors.initEncoders();
		}
	}

	/** Drives the robot in 'PercentVBus' mode (-1.0-1.0) by giving the left and right motors potentially different speeds */
	public void driveVBus(double leftVal, double rightVal) {
		leftMotors.changeControlMode(TalonControlMode.PercentVbus);
		rightMotors.changeControlMode(TalonControlMode.PercentVbus);
		
		if(Constants.DRIVETRAIN_ENABLED) {
			leftMotors.set(leftVal);
			rightMotors.set(-rightVal); 
		}
	}
	
	/** Drives the robot in 'Speed' mode by giving left and right side motors potentially different speeds */
	public void driveSpeed(double leftVal, double rightVal) {
		leftMotors.changeControlMode(TalonControlMode.Speed);
		rightMotors.changeControlMode(TalonControlMode.Speed);
		
		if(Constants.DRIVETRAIN_ENABLED) {
			leftMotors.set(leftVal);
			rightMotors.set(-rightVal); 
		}
	}
	
	/** Stops turning all drive-motors */
	public void stop() {
		leftMotors.changeControlMode(TalonControlMode.PercentVbus);
		rightMotors.changeControlMode(TalonControlMode.PercentVbus);
		leftMotors.set(0.0);
		rightMotors.set(0.0);
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
	
	public enum Side {LEFT,RIGHT};
	
	/** Starts the manual driving command in a specified drive-mode */
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Drive(mode));
	}
}
