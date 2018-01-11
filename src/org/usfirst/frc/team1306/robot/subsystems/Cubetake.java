package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1306.robot.Constants;

/**
 * @Cubetake
 * 
 * Subsystem controlling power cube intake, which currently includes a set of wheels on each side.
 * 
 * @author Ethan Dong
 */
public class Cubetake extends Subsystem{

	private Spark leftIntakeMotor;
	private Spark rightIntakeMotor;
	
	public Cubetake() {
		leftIntakeMotor = new Spark(RobotMap.LEFT_INTAKE_PORT);
		rightIntakeMotor = new Spark(RobotMap.RIGHT_INTAKE_PORT);
	}
	
	public void spinIn() {
		leftIntakeMotor.set(Constants.INTAKE_VELOCITY);
		rightIntakeMotor.set(-Constants.INTAKE_VELOCITY);
	}
	
	public void spinOut() {
		leftIntakeMotor.set(-Constants.INTAKE_VELOCITY);
		rightIntakeMotor.set(Constants.INTAKE_VELOCITY);
	}
	
	public void stop() {
		leftIntakeMotor.set(0.0);
		rightIntakeMotor.set(0.0);
	}
	
	@Override
	protected void initDefaultCommand() { }
}
