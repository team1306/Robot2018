package org.usfirst.frc.team1306.robot.drivetrain;

import java.util.ArrayList;
import org.usfirst.frc.team1306.lib.util.PIDParameters;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

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

	private TalonSRX master; //The 'Master' talon that all others will imitate
	
	/** Initializes the new DriveSide using a given array filled with this side's corresponding talons */
	public DriveSide(ArrayList<TalonSRX> t) {
		ArrayList<TalonSRX> talons = new ArrayList<TalonSRX>();
		talons = t;  //All talons for this side of the drivetrain
		
		if(talons.size() > 0) {
			master = talons.get(0); //First talon in array is the master talon
			master.set(ControlMode.PercentOutput,0.0);
			
			for(int i = 1; i < talons.size(); i++) { //Sets every other talon as a follower of the master talon
				talons.get(i).follow(master);
			}
		}
	}
	
	/** Has this side of the drivetrain spin at the given speed */
	public void set(ControlMode mode, double speed) {
		master.set(mode,speed);
	}

	/** Initializes the drivetrain encoders */
	public void initEncoders() {
		master.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,0,0);
		master.configNominalOutputForward(0,0);
		master.configNominalOutputReverse(0,0);
		master.configPeakOutputForward(12,0);
		master.configPeakOutputReverse(-12,0);
		master.setSelectedSensorPosition(0,0,0);
	}
	
	/** Sets up the PIDF control values */
	public void setPIDParams(PIDParameters params) {
		master.config_kF(0,params.f,0);
		master.config_kP(0,params.p,0);
		master.config_kI(0,params.i,0);
		master.config_kD(0,params.d,0);
	}
	
	/** Resets the encoder reading back to zero */
	public void resetEncoderPos() {
		master.setSelectedSensorPosition(0,0,0);
	}
	
	/** Returns the current position from the encoder */
	public double getEncoderPos() {
		return master.getSelectedSensorPosition(0);
	}
	
	/** Returns the current velocity from the encoder */
	public double getEncoderVel() {
		return master.getSelectedSensorVelocity(0);
	}
}
