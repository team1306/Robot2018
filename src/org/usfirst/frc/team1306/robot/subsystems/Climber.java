package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * @Climber
 * 
 * Subsystem controlling our climbing mechanism, a ___ that pulls us up the side of the scale.
 * 
 * @author Ethan Dong
 */
public class Climber extends Subsystem{

	private Spark climberMotor;
	
	public Climber() {
		climberMotor = new Spark(RobotMap.CLIMBER_SPARK);
	}
	
	public void pull() {
		//climbMotor.set(0.5);
	}
	
	public void stop() {
		climberMotor.set(0);
	}
	
	@Override
	protected void initDefaultCommand() { }
}
