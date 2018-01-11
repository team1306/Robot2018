package org.usfirst.frc.team1306.robot.commands.intake;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class SpitBlock extends CommandBase{
	
	public SpitBlock() {
		requires(intake2);
	}

	@Override
	protected void execute() {
		intake2.spinOut();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		intake2.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
