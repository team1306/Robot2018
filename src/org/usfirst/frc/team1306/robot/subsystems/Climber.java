package org.usfirst.frc.team1306.robot.subsystems;
import org.usfirst.frc.team1306.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem{

	private Spark climbMotor;
	
	public Climber() {
		climbMotor = new Spark(RobotMap.CLIMBER_SPARK_PORT);
	}
	
	public void climbUp() {
		climbMotor.set(0.5);
	}
	
	public void stop() {
		climbMotor.set(0.0);
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
