package org.usfirst.frc.team1306.robot.commands.intake;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class IntakeBlock extends CommandBase{
	
	public IntakeBlock() {
		requires(intake2);
	}

	@Override
	protected void execute() {
		intake2.spinIn();
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
