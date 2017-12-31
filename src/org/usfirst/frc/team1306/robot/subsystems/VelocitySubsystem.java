package org.usfirst.frc.team1306.robot.subsystems;

import java.util.ArrayList;
import org.usfirst.frc.team1306.lib.util.PIDParameters;
import org.usfirst.frc.team1306.robot.Constants;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;
import com.ctre.CANTalon.VelocityMeasurementPeriod;
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
	private ArrayList<CANTalon> talons;
	private boolean enabled = true;
	private FeedbackDevice device;
	private double p; //,f,i,d;
	private String mechanism;
	
	public VelocitySubsystem(PIDParameters params, String name) {
		mechanism = name;
		defaultCommand = null;
		talons = new ArrayList<CANTalon>();
		
		//f = params.f;
		p = params.p;
		//i = params.i;
		//d = params.d;
	}
	
	public VelocitySubsystem(PIDParameters params, Command dCommand, String name) {
		mechanism = name;
		defaultCommand = dCommand;
		talons = new ArrayList<CANTalon>();
		
		//f = params.f;
		p = params.p;
		//i = params.i;
		//d = params.d;
	}
	
	/** Spins all motors within subsytem at given speed */
	public void spinAll(double speed) {
		if(talons.size() > 0 && enabled) {
			speed += (speed - (getEncoderVelocity() * getEncoderAdj())) * p;
			talons.get(0).set(speed);
		}
	}
	
	/* Stops all the motors within subsytem */
	public void stopAll() {
		if(talons.size() > 0) {
			talons.get(0).changeControlMode(TalonControlMode.PercentVbus);
			talons.get(0).set(0.0);
		}
	}
	
	/** Disable the subsytem */
	public void disable() {
		enabled = false;
	}
	
	/** Adds a new TalonSRX to the motor pool and if there already is a master talons, the rest should follow it */
	public void addTalonSRX(int port) {
		try { //Use Try / Catch so any port errors don't crash code
			CANTalon t = new CANTalon(port);
			if(talons.size() > 0) { //Makes new Talons follow the master one (Talon Zero)
				t.changeControlMode(TalonControlMode.Follower);
				t.set(talons.get(0).getDeviceID());
			}
			t.enable(); //Need to enable all talons
			talons.add(t);
		} catch(Exception e) {
			SmartDashboard.putString("ERROR:",mechanism + " is trying to re-use an existing or non-existent port");
		}
	}
	
	/** Set's up the encoder parameter for this subsytem, this assumes QuadEncoders are RS7 encoders and all other encoders used will be versaplanetary encoders. */
	public void setTalonParams(FeedbackDevice device) {
		if(talons.size() > 0) {
			talons.get(0).setFeedbackDevice(device);
			this.device = device;
			if(device.equals(FeedbackDevice.QuadEncoder)) {
				talons.get(0).configEncoderCodesPerRev(12); //Only QuadEncoders (RS7 encoders) need this line
			}
			talons.get(0).configNominalOutputVoltage(+0.0f, -0.0f);
			talons.get(0).configPeakOutputVoltage(+12.0f, -12.0f);
			talons.get(0).SetVelocityMeasurementPeriod(VelocityMeasurementPeriod.Period_10Ms); //Lowest possible measurement period
			talons.get(0).SetVelocityMeasurementWindow(20); //Lowest possible measurement window
		}
	}
	
	/** Changes the control mode the talons are in (PercentVBus and Speed are main ones) */
	public void changeControlMode(TalonControlMode mode) {
		if(talons.size() > 0) {
			talons.get(0).changeControlMode(mode);
		}
	}
	
	/** Reverses output the sensor receives */
	public void reverseSensor() {
		if(talons.size() > 0) {
			talons.get(0).reverseSensor(true);
		}
	}
	
	/** Get's velocity adjustment for encoder to put an encoder velocity into useable units */
	public double getEncoderAdj() {
		if(device.equals(FeedbackDevice.QuadEncoder)) {
			return getEncoderVelocity() * Constants.RS7_VEL_ADJ;
		} else if(device.equals(FeedbackDevice.CtreMagEncoder_Relative)) {
			return getEncoderVelocity() * Constants.CTRE_MAG_VEL_ADJ;
		} else {
			SmartDashboard.putString("ERROR:",mechanism + " can't convert encoder velocity to actual velocity!");
			return 0.0;
		}
	}
	
	/** Gets the current encoder position */
	public double getEncoderPosition() {
		try {
			SmartDashboard.putNumber(mechanism + " Encoder Position",talons.get(0).getEncPosition());
			return talons.get(0).getEncPosition();
		} catch(Exception e) {
			SmartDashboard.putString("ERROR:","Can't get encoder position from " + mechanism);
		} return 0.0;
	}
	
	/** Gets the current encoder velocity */
	public double getEncoderVelocity() {
		try {
			SmartDashboard.putNumber(mechanism + " Encoder Velocity",talons.get(0).getEncVelocity());
			return talons.get(0).getEncVelocity();
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
