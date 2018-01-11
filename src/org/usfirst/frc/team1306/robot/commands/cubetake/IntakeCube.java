package org.usfirst.frc.team1306.robot.commands.cubetake;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * @IntakeCube
 * 
 * Command to spin the intake wheels inward, pulling in a power cube.
 * 
 * @author Ethan Dong
 */
public class IntakeCube extends CommandBase{
	
	public IntakeCube() {
		requires(intake);
	}

	@Override
	protected void execute() {
		intake.spinIn();
	}

	@Override
	protected boolean isFinished() {
		return false; //Toggled so doesn't need to ever return true
	}

	@Override
	protected void end() {
		intake.stop();
	}

	@Override
	protected void interrupted() {
		end(); //Should stop the intake if toggled again
	}
}
