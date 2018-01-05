package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.lib.util.PIDParameters;
import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.PositionalCommand;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @PositionalSubsystem
 * 
 * This subsystem's purpose is meant to speed up the process of creating and modifying subsytems that use TalonSRX position control with encoders.
 * This will hopefully condense a few subsytems down into one common class with the same functionality and reduce the time it takes to program those mechanisms.
 * This is meant to replace mechanisms like a turret, intake motor arm, etc.
 * 
 * @author Jackson Goth
 */
public class PositionalSubsystem extends Subsystem {

	private boolean enabled = true;
	private Command defaultCommand;
	private TalonSRX talon;
	private String mechanism;
	private double p; //,f,i,d;
	private double degLeft = 0, degRight = 0, degDefault = 0;	
	
	public PositionalSubsystem(PIDParameters params, TalonSRX motor, String name) {
		mechanism = name;
		defaultCommand = null;
		talon = motor;
		
		talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,0);
		talon.configNominalOutputForward(0,0);
		talon.configNominalOutputReverse(0,0);
		talon.configPeakOutputForward(12,0);
		talon.configPeakOutputReverse(-12,0);
		talon.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_10Ms,0);
		talon.configVelocityMeasurementWindow(20,0);
		
		//f = params.f;
		p = params.p;
		//i = params.i;
		//d = params.d;
	}
	
	public PositionalSubsystem(PIDParameters params, TalonSRX motor, Command dCommand, String name) {
		mechanism = name;
		defaultCommand = dCommand;
		talon = motor;
		
		talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,0);
		talon.configNominalOutputForward(0,0);
		talon.configNominalOutputReverse(0,0);
		talon.configPeakOutputForward(12,0);
		talon.configPeakOutputReverse(-12,0);
		talon.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_10Ms,0);
		talon.configVelocityMeasurementWindow(20,0);
		
		//f = params.f;
		p = params.p;
		//i = params.i;
		//d = params.d;
	}
	
	/** Turns the mechanism at a given speed, while it follows a profile, if it's out of range return it to the default position */
	public void turn(double speed) {
		if(enabled) {
			if(getPosition() <= degLeft || getPosition() >= degRight) {
				reset();
			} else {
				speed += (speed - (getEncoderVelocity() * getEncoderAdj())) * p;
				talon.set(ControlMode.Velocity,speed);
			}
		}
	}
	
	/** Resets the mechanism, moving it back to the default position */
	public void reset() {
		new PositionalCommand(this,degDefault);
	}
	
	/** Set's the range the mechanism can turn to the left and right (in rotations) */
	public void setRange(double dLeft, double dRight) {
		degLeft = dLeft;
		degRight = dRight;
	}
	
	/** Returns the position of the mechanism in rotations from starting position */
	public double getPosition() {
		return getEncoderPosition() * Constants.CTRE_MAG_POS_ADJ;
	}
	
	/** Get's velocity adjustment for encoder to put an encoder velocity into useable units */
	public double getEncoderAdj() {
		return getEncoderVelocity() * Constants.CTRE_MAG_VEL_ADJ;
	}
	
	/** Gets the current encoder position */
	public double getEncoderPosition() {
		try {
			SmartDashboard.putNumber(mechanism + " Encoder Position",talon.getSelectedSensorPosition(0));
			return talon.getSelectedSensorPosition(0);
		} catch(Exception e) {
			SmartDashboard.putString("ERROR:","Can't get encoder position from " + mechanism);
		} return 0.0;
	}
	
	/** Gets the current encoder velocity */
	public double getEncoderVelocity() {
		try {
			SmartDashboard.putNumber(mechanism + " Encoder Velocity",talon.getSelectedSensorVelocity(0));
			return talon.getSelectedSensorVelocity(0);
		} catch(Exception e) {
			SmartDashboard.putString("ERROR:","Can't get encoder velocity from " + mechanism);
		} return 0.0;
	}
	
	/** Stops turning the motor to hold mechanism in this position */
	public void stop() {
		talon.set(ControlMode.PercentOutput,0.0);
	}
	
	/** Disable the subsytem */
	public void disable() {
		enabled = false;
	}
	
	/** Sets the default command if one was provided */
	@Override
	protected void initDefaultCommand() {
		if(defaultCommand != null) {
			setDefaultCommand(defaultCommand);
		}
	}
}
