package org.usfirst.frc.team1306.robot.elevator;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.subsystems.Elevator.Position;

/**
 * @Elevate - Lifts / drops the elevator to a given setpoint using motion profiling.
 * @author Jackson Goth
 */
public class Elevate extends CommandBase {

	private Position position;
	
	public Elevate(Position p) {
		position = p;
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected boolean isFinished() {
		return elevator.checkInRange(position);
	}
}
