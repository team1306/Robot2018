package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1306.robot.Constants;

public class Intake2 extends Subsystem{

	private Spark leftIntakeMotor;
	private Spark rightIntakeMotor;
	
	public Intake2() {
		leftIntakeMotor = new Spark(RobotMap.LEFT_INTAKE_PORT);
		rightIntakeMotor = new Spark(RobotMap.RIGHT_INTAKE_PORT);
	}
	
	public void spinIn() {
		leftIntakeMotor.set(Constants.RIGHT_INTAKE_VELOCITY);
		rightIntakeMotor.set(Constants.LEFT_INTAKE_VELOCITY);
	}
	
	public void spinOut() {
		leftIntakeMotor.set(-Constants.RIGHT_INTAKE_VELOCITY);
		rightIntakeMotor.set(-Constants.LEFT_INTAKE_VELOCITY);
	}
	
	public void stop() {
		leftIntakeMotor.set(0);
		rightIntakeMotor.set(0);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
