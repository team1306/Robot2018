package org.usfirst.frc.team1306.robot.subsystems;

import java.util.ArrayList;
import org.usfirst.frc.team1306.lib.util.PIDParameters;
import org.usfirst.frc.team1306.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @VelocitySubsystem
 * 
 * This subsystem's purpose is meant to speed up the process of creating and modifying subsytems that use TalonSRX velocity control with encoders.
 * This will hopefully condense a few subsytems down into one common class with the same functionality and reduce the time it takes to program those mechanisms.
 * This is meant to replace mechanisms like a flywheel or other things that may require speed control.
 * 
 * @author Jackson Goth
 */
public class VelocitySubsystem extends Subsystem {

	private Command defaultCommand;
	private ArrayList<TalonSRX> talons;
	private boolean enabled = true;
	private FeedbackDevice device;
	private double p; //,f,i,d;
	private String mechanism;
	
	public VelocitySubsystem(PIDParameters params, String name) {
		mechanism = name;
		defaultCommand = null;
		talons = new ArrayList<TalonSRX>();
		
		//f = params.f;
		p = params.p;
		//i = params.i;
		//d = params.d;
	}
	
	public VelocitySubsystem(PIDParameters params, Command dCommand, String name) {
		mechanism = name;
		defaultCommand = dCommand;
		talons = new ArrayList<TalonSRX>();
		
		//f = params.f;
		p = params.p;
		//i = params.i;
		//d = params.d;
	}
	
	/** Spins all motors within subsytem at given speed */
	public void spinAll(double speed) {
		if(talons.size() > 0 && enabled) {
			speed += (speed - (getEncoderVelocity() * getEncoderAdj())) * p;
			talons.get(0).set(ControlMode.Velocity,speed);
		}
	}
	
	/* Stops all the motors within subsytem */
	public void stopAll() {
		if(talons.size() > 0) {
			talons.get(0).set(ControlMode.PercentOutput,0.0);
		}
	}
	
	/** Disable the subsytem */
	public void disable() {
		enabled = false;
	}
	
	/** Adds a new TalonSRX to the motor pool and if there already is a master talons, the rest should follow it */
	public void addTalonSRX(int port) {
		try { //Use Try / Catch so any port errors don't crash code
			TalonSRX t = new TalonSRX(port);
			if(talons.size() > 0) { //Makes new Talons follow the master one (Talon Zero)
				t.follow(talons.get(0));
			}
			talons.add(t);
		} catch(Exception e) {
			SmartDashboard.putString("ERROR:",mechanism + " is trying to re-use an existing or non-existent port");
		}
	}
	
	/** Set's up the encoder parameter for this subsytem, this assumes QuadEncoders are RS7 encoders and all other encoders used will be versaplanetary encoders. */
	public void setTalonParams(FeedbackDevice device) {
		if(talons.size() > 0) {
			talons.get(0).configSelectedFeedbackSensor(device,0,0);
			this.device = device;
			talons.get(0).configNominalOutputForward(0,0);
			talons.get(0).configNominalOutputReverse(0,0);
			talons.get(0).configPeakOutputForward(12,0);
			talons.get(0).configPeakOutputReverse(-12,0);
			talons.get(0).configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_10Ms,0);
			talons.get(0).configVelocityMeasurementWindow(20,0);
		}
	}
	
	/** Get's velocity adjustment for encoder to put an encoder velocity into useable units */
	public double getEncoderAdj() {
		if(device.equals(FeedbackDevice.QuadEncoder)) {
			return getEncoderVelocity() * Constants.RS7_VEL_ADJ;
		} else if(device.equals(FeedbackDevice.CTRE_MagEncoder_Relative)) {
			return getEncoderVelocity() * Constants.CTRE_MAG_VEL_ADJ;
		} else {
			SmartDashboard.putString("ERROR:",mechanism + " can't convert encoder velocity to actual velocity!");
			return 0.0;
		}
	}
	
	/** Gets the current encoder position */
	public double getEncoderPosition() {
		try {
			SmartDashboard.putNumber(mechanism + " Encoder Position",talons.get(0).getSelectedSensorPosition(0));
			return talons.get(0).getSelectedSensorPosition(0);
		} catch(Exception e) {
			SmartDashboard.putString("ERROR:","Can't get encoder position from " + mechanism);
		} return 0.0;
	}
	
	/** Gets the current encoder velocity */
	public double getEncoderVelocity() {
		try {
			SmartDashboard.putNumber(mechanism + " Encoder Velocity",talons.get(0).getSelectedSensorVelocity(0));
			return talons.get(0).getSelectedSensorVelocity(0);
		} catch(Exception e) {
			SmartDashboard.putString("ERROR:","Can't get encoder velocity from " + mechanism);
		} return 0.0;
	}
	
	/** Sets the default command if one was provided */
	@Override
	protected void initDefaultCommand() {
		if(defaultCommand != null) {
			setDefaultCommand(defaultCommand);
		}
	}
}
