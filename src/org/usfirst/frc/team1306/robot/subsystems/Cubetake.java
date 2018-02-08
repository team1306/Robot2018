package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1306.robot.Constants;

/**
 * @Cubetake - Subsystem controlling the power cube intake, which currently includes a set of wheels on each side.
 * @author Ethan Dong
 */
public class Cubetake extends Subsystem{

	private Spark leftIntakeMotor;
	private Spark rightIntakeMotor;
	private TalonSRX talon;
	private DoubleSolenoid solenoid;
	
	public Cubetake() {
		leftIntakeMotor = new Spark(RobotMap.LEFT_CUBETAKE_PORT);
		rightIntakeMotor = new Spark(RobotMap.RIGHT_CUBETAKE_PORT);
		solenoid = new DoubleSolenoid(RobotMap.CUBETAKE_CHANNEL_ONE_PORT, RobotMap.CUBETAKE_CHANNEL_TWO_PORT);
	}
	
	public void spinIn() {
		if(Constants.INTAKE_ENABLED) {
			leftIntakeMotor.set(0.5);
			rightIntakeMotor.set(-0.65);
		}
	}
	
	public void spinOut() {
		if(Constants.INTAKE_ENABLED) {
			leftIntakeMotor.set(-0.5);
			rightIntakeMotor.set(0.65);
		}
	}
	
	public void flip() {
		if(Constants.INTAKE_ENABLED) {
			leftIntakeMotor.set(-0.6);
			rightIntakeMotor.set(-0.6);
		}
	}
	
	public void stop() {
		leftIntakeMotor.set(0.0);
		rightIntakeMotor.set(0.0);
	}
	
	public void CubetakeArmUp() {
		solenoid.set(DoubleSolenoid.Value.kReverse);
	}

	public void CubetakeArmDown() {
		solenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	@Override
	protected void initDefaultCommand() { }
}
