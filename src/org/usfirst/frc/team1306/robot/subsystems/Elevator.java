package org.usfirst.frc.team1306.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem {

	public Elevator() {
		
	}
	
	public void stop() {
		
	}
	
	@Override
	protected void initDefaultCommand() { }

	public enum Position {FLOOR,SWITCH,CARRYING,SCALE};
}
