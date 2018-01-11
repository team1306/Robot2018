package org.usfirst.frc.team1306.robot.commands.cubetake;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * @SpitCube
 * 
 * Command to spin the intake wheels outward, pushout out a power cube.
 * 
 * @author Ethan Dong
 */
public class SpitCube extends CommandBase{
	
	public SpitCube() {
		requires(intake);
	}

	@Override
	protected void execute() {
		intake.spinOut();
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
