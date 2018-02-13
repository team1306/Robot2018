package org.usfirst.frc.team1306.robot.commands.cubetake;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * @Collect - Command to spin the intake wheels inward; toggled.
 * @author Ethan Dong
 */
public class Collect extends CommandBase{
	
	@Override
	protected void execute() {
		cubetake.intake();
	}

	@Override
	protected boolean isFinished() {
		return false; //Toggled so doesn't need to ever return true
	}
}
