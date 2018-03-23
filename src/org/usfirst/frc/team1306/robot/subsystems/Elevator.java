package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

	private TalonSRX elevatorMotor;
	private DoubleSolenoid brake;
	
	public Elevator() {
		elevatorMotor = new TalonSRX(RobotMap.ELEVATOR_TALON);
		elevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,0);
		elevatorMotor.configNominalOutputForward(0,0);
		elevatorMotor.configNominalOutputReverse(0,0);
		elevatorMotor.configPeakOutputForward(1,0);
		elevatorMotor.configPeakOutputReverse(-1,0);
		elevatorMotor.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_10Ms,0);
		elevatorMotor.configVelocityMeasurementWindow(20,0);
		
		brake = new DoubleSolenoid(4,5);
		brake();
	}
	
	public void brake() {
		brake.set(DoubleSolenoid.Value.kForward);
	}
	
	public void unbrake() {
		brake.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void vbus(double speed) {
		elevatorMotor.set(ControlMode.PercentOutput,speed);
	}
	
	public void move(Position pos) {
		//double dist = pos.height - getEncoderPos();
	}
	
//	private void move(int vel) {
//		elevatorMotor.set(ControlMode.Velocity,vel);
//	}
	
	public double getEncoderPos() {
		return elevatorMotor.getSensorCollection().getQuadraturePosition();
	}
	
	public Position getPosition() {
		if(checkInRange(Position.FLOOR)) { return Position.FLOOR; }
		else if(checkInRange(Position.SWITCH)) { return Position.SWITCH; }
		else if(checkInRange(Position.CARRYING)) { return Position.CARRYING; }
		else if(checkInRange(Position.SCALE)) { return Position.SCALE; }
		else { return Position.UNKNOWN; }
	}
	
	private boolean checkInRange(Position pos) {
		return Math.abs(getEncoderPos() - pos.height) < Constants.ELEVATOR_IN_RANGE_POS;
	}
	
	public void stop() {
		elevatorMotor.set(ControlMode.PercentOutput,0);
	}
	
	@Override
	protected void initDefaultCommand() { }

	public enum Position {
		FLOOR(Constants.ELEVATOR_FLOOR_HEIGHT),
		SWITCH(Constants.ELEVATOR_SWITCH_HEIGHT),
		CARRYING(Constants.ELEVATOR_CARRYING_HEIGHT),
		SCALE(Constants.ELEVATOR_SCALE_HEIGHT),
		UNKNOWN;
		
		public double height;
		private Position(double height) { this.height = height; }
		private Position() { };
	};
}
