package org.usfirst.frc.team1306.robot.commands.cubetake;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * @IntakeCube - Command to spin the intake wheels inward; toggled.
 * @author Ethan Dong
 */
public class IntakeCube extends CommandBase{
	
	@Override
	protected void execute() {
		intake.spinIn();
	}

	@Override
	protected boolean isFinished() {
		return false; //Toggled so doesn't need to ever return true
	}
}
