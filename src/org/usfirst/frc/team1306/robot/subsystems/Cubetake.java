package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;
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
	
	public Cubetake() {
		leftIntakeMotor = new Spark(RobotMap.LEFT_INTAKE_PORT);
		rightIntakeMotor = new Spark(RobotMap.RIGHT_INTAKE_PORT);
	}
	
	public void spinIn() {
		if(Constants.INTAKE_ENABLED) {
			leftIntakeMotor.set(0.3);
			rightIntakeMotor.set(-0.45);
		}
	}
	
	public void spinOut() {
		if(Constants.INTAKE_ENABLED) {
			leftIntakeMotor.set(-0.3);
			rightIntakeMotor.set(0.45);
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
	
	@Override
	protected void initDefaultCommand() { }
}
