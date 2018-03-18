package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1306.robot.Constants;

/**
 * @Cubetake
 * 
 * Subsystem controlling our power-cube intake, which features two pneumatically actuated arms that are 4 wheels long.
 * 
 * @author Ethan Dong
 */
public class Cubetake extends Subsystem{

	private Spark leftIntakeMotor;
	private Spark rightIntakeMotor;
	private DoubleSolenoid intakeSolenoid;
	
	public Cubetake() {
		leftIntakeMotor = new Spark(RobotMap.LEFT_CUBETAKE_SPARK);
		rightIntakeMotor = new Spark(RobotMap.RIGHT_CUBETAKE_SPARK);
		intakeSolenoid = new DoubleSolenoid(RobotMap.CUBETAKE_SOLENOID_CHANNEL_ONE, RobotMap.CUBETAKE_SOLENOID_CHANNEL_TWO);
	}
	
	public void intake() {
		if(Constants.CUBETAKE_ENABLED) {
			leftIntakeMotor.set(Constants.CUBETAKE_SPEED_SLOWER+.15);
			rightIntakeMotor.set(-Constants.CUBETAKE_SPEED_FASTER-.15);
		}
	}
	
	public void spit() {
		if(Constants.CUBETAKE_ENABLED) {
			leftIntakeMotor.set(-Constants.CUBETAKE_SPEED_SLOWER);
			rightIntakeMotor.set(Constants.CUBETAKE_SPEED_FASTER);
		}
	}
	
	public void throwExchange() {
		if(Constants.CUBETAKE_ENABLED) {
			leftIntakeMotor.set(-Constants.CUBETAKE_THROUGH);
			rightIntakeMotor.set(Constants.CUBETAKE_THROUGH);
		}
	}
	
	public void actuate() {
		if(Constants.CUBETAKE_ENABLED) { intakeSolenoid.set(DoubleSolenoid.Value.kForward); }
	}
	
	public void retract() {
		if(Constants.CUBETAKE_ENABLED) { intakeSolenoid.set(DoubleSolenoid.Value.kReverse); }
	}

	public void stop() {
		leftIntakeMotor.set(0);
		rightIntakeMotor.set(0);
	}
	
	@Override
	protected void initDefaultCommand() { }
}
