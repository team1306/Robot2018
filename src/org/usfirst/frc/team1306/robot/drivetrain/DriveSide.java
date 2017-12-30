package org.usfirst.frc.team1306.robot.drivetrain;

import java.util.ArrayList;
import org.usfirst.frc.team1306.lib.util.PIDParameters;
import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

/**
 * @DriveSide
 * 
 * This object contains one side of the drivetrain (either left or right), and it's purpose is to make adding/removing motor controllers
 * from the drivetrain subsystem much faster/easier in code. To adjust the number of motor controllers all you have to do is change a few lines of code
 * in CommandBase.java where the drivetrain settings are initialized. To make code in the drivetrain subsystem simpler, the methods of this class
 * are very similar to that of a motor controller with methods like 'set(motorSpeed)' and 'changeControlMode(controlMode)'.
 * 
 * @author Jackson Goth
 */
public class DriveSide {

	private CANTalon master; //The 'Master' talon that all others will imitate
	
	/** Initializes the new DriveSide using a given array filled with this side's corresponding talons */
	public DriveSide(ArrayList<CANTalon> t) {
		ArrayList<CANTalon> talons = new ArrayList<CANTalon>();
		talons = t;  //All talons for this side of the drivetrain
		
		if(talons.size() > 0) {
			master = talons.get(0); //First talon in array is the master talon
			master.changeControlMode(TalonControlMode.PercentVbus);
			master.set(0.0);
			master.enable();
			
			for(int i = 1; i < talons.size(); i++) { //Sets every other talon as a follower of the master talon
				talons.get(i).changeControlMode(TalonControlMode.Follower);
				talons.get(i).set(master.getDeviceID());
				talons.get(i).enable();
			}
		}
	}
	
	/** Changes the control mode of the master talon */
	public void changeControlMode(TalonControlMode mode) {
		master.changeControlMode(mode);
	}
	
	/** Has this side of the drivetrain spin at the given speed */
	public void set(double speed) {
		master.set(speed);
	}

	/** Initializes the drivetrain encoders */
	public void initEncoders() {
		master.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		master.configEncoderCodesPerRev(256); //256 codes per rev for grayhill encoders
		master.configNominalOutputVoltage(+0.0f, -0.0f);
		master.configPeakOutputVoltage(+12.0f, -12.0f);
		master.setEncPosition(0);
	}
	
	/** Sets up the PIDF control values */
	public void setPIDParams(PIDParameters params) {
		master.setF(params.f);
		master.setP(params.p);
		master.setI(params.i);
		master.setD(params.d);
	}
	
	/** Reverses motor output from the loop output, (ex. if true a loop output of 1 would turn the motor at -1) */
	public void flipLoopOutput(boolean flipped) {
		master.reverseOutput(flipped);
	}
	
	/** Reverses encoder output (ex. if true an encoder output of 200 would read -200) */
	public void flipEncoderOutput(boolean flipped) {
		master.reverseSensor(flipped);
	}
	
	/** Returns the current position from the encoder */
	public double getEncoderPos() {
		return master.getEncPosition();
	}
	
	/** Resets the encoder reading back to zero */
	public void resetEncoderPos() {
		master.setEncPosition(0);
	}
	
	/** Returns the current velocity from the encoder */
	public double getEncoderVel() {
		return master.getEncVelocity();
	}
}
