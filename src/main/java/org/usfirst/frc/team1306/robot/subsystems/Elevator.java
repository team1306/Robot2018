package org.usfirst.frc.team1306.robot.subsystems;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.elevator.Elevate;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @Elevator
 * 
 * Subsystem powering our two-stage cascading elevator, featuring a pneumatic brake for when we aren't lifting and motion profiling for lifting
 * cubes during auto / tele-op to the correct height with setpoints.
 * 
 * @author Jackson Goth
 */
public class Elevator extends Subsystem {

	private BaseMotorController master; //The 'master' motor-controller which others will imitate.
	private DoubleSolenoid brake; //DoubleSolenoid controlling our pneumatic disc brake.
	
	public Elevator(ArrayList<BaseMotorController> c) {
		ArrayList<BaseMotorController> controllers = new ArrayList<BaseMotorController>();
		controllers = c; //All motor-controllers for the elevator.
		
		if(controllers.size() > 0) {
			master = controllers.get(0); //First controller in array is the master controller.
			master.set(ControlMode.PercentOutput, 0.0);
			
			/** Setting up the encoder... */
			master.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
//			master.setStatusFramePeriod(StatusFrame.Status_13_Base_PIDF0, 10, 0);
//			master.setStatusFramePeriod(StatusFrame.Status_10_MotionMagic, 10, 0);
			master.configNominalOutputForward(0, 0);
			master.configNominalOutputReverse(0, 0);
			master.configPeakOutputForward(1, 0);
			master.configPeakOutputReverse(-1, 0);
			master.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_10Ms, 0);
			master.configVelocityMeasurementWindow(20, 0);
			
//			master.setSelectedSensorPosition(0, 0, 0); //This resets the elevator encoder to zero, and assumes the robot will be turned on with elevator fully down.
//			master.config_kF(0, Constants.ELEVATOR_F ,0);
//			master.config_kP(0, Constants.ELEVATOR_P, 0);
//			master.config_kI(0, Constants.ELEVATOR_I, 0);
//			master.config_kD(0, Constants.ELEVATOR_D, 0);
//			master.configMotionCruiseVelocity(15000, 0);
//			master.configMotionAcceleration(6000, 0);
			
			for(int i = 1; i < controllers.size(); i++) { //Sets every other controller as a follower of the master controller.
				controllers.get(i).follow(master);
			}
		}
		
		brake = new DoubleSolenoid(RobotMap.ELEVATOR_SOLENOID_CHANNEL_ONE, RobotMap.ELEVATOR_SOLENOID_CHANNEL_TWO);
		brake(); //Should auto-brake when robot enables.
	}
	
	/** Clamp down with the pneumatic brake to stop the elevator */
	public void brake() {
		brake.set(DoubleSolenoid.Value.kForward); //Doesn't matter if elevator is enabled, needs to be able to brake even when disabled.
	}
	
	/** Un-clamp the pneumatic brake so the elevator drive up or down */
	public void unbrake() {
		if(Constants.ELEVATOR_ENABLED) { brake.set(DoubleSolenoid.Value.kReverse); }
	}
	
	/** Drives the elevator up or down using a desired 'PercentOutput' speed (-1.0 -> 1.0) */
	public void movePercentOutput(double speed) {
		try { if(Constants.ELEVATOR_ENABLED) { master.set(ControlMode.PercentOutput, speed); } }
		catch(Exception e) { SmartDashboard.putString("ERROR:","Elevator unable to drive the motor(s) in percentoutput mode"); }
	}
	
	/** Returns the encoder position given by the elevator gearbox */
	public double getEncoderPos() {
		try { return ((IMotorControllerEnhanced)(master)).getSensorCollection().getQuadraturePosition(); }
		catch(Exception e) { SmartDashboard.putString("ERROR:","Unable to get the encoder position of the elevator"); return 0; }
	}
	
	/** Returns the encoder velocity given by the elevator gearbox */
	public double getEncoderVel() {
		try { return ((IMotorControllerEnhanced)(master)).getSensorCollection().getQuadratureVelocity(); }
		catch(Exception e) { SmartDashboard.putString("ERROR:","Unable to get the encoder velocity of the elevator"); return 0; }
	}
	
	/** Returns which setpoint the elevator is in or if it's in-between setpoints */
	public Position getPosition() {
		if(checkInRange(Position.FLOOR)) { return Position.FLOOR; }
		else if(checkInRange(Position.SWITCH)) { return Position.SWITCH; }
		else if(checkInRange(Position.CARRYING)) { return Position.CARRYING; }
		else if(checkInRange(Position.SCALE)) { return Position.SCALE; }
		else { return Position.IN_BETWEEN; }
	}
	
	/** Checks to see if the elevator is in range of a given setpoint (if it's at that setpoint */
	public boolean checkInRange(Position pos) {
		return Math.abs(getEncoderPos() - pos.height) < Constants.ELEVATOR_IN_RANGE_POS;
	}
	
	/** Stops driving the elevator motor */
	public void stop() {
		master.set(ControlMode.PercentOutput, 0);
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new Elevate());
	}

	/** Enum containing all elevator setpoints */
	public enum Position {
		FLOOR(Constants.ELEVATOR_FLOOR_HEIGHT),
		SWITCH(Constants.ELEVATOR_SWITCH_HEIGHT),
		CARRYING(Constants.ELEVATOR_CARRYING_HEIGHT),
		SCALE(Constants.ELEVATOR_SCALE_HEIGHT),
		IN_BETWEEN;
		
		public double height;
		private Position(double height) { this.height = height; }
		private Position() { };
	};
}
