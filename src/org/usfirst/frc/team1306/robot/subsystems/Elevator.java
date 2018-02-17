package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

	private TalonSRX elevatorMotor;
	private Solenoid brake;
	
	public Elevator() {
		elevatorMotor = new TalonSRX(RobotMap.ELEVATOR_TALON);
		elevatorMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,0);
		elevatorMotor.configNominalOutputForward(0,0);
		elevatorMotor.configNominalOutputReverse(0,0);
		elevatorMotor.configPeakOutputForward(1,0);
		elevatorMotor.configPeakOutputReverse(-1,0);
		elevatorMotor.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_10Ms,0);
		elevatorMotor.configVelocityMeasurementWindow(20,0);
		
		brake = new Solenoid(RobotMap.ELEVATOR_SOLENOID_CHANNEL);
		brake.set(true);
	}

	public void release() {
		brake.set(false);
	}
	
	public void stop() {
		elevatorMotor.set(ControlMode.PercentOutput,0);
		brake.set(true);
	}
	
	@Override
	protected void initDefaultCommand() { }

	public enum Position {FLOOR,SWITCH,CARRYING,SCALE};
}
