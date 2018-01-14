package org.usfirst.frc.team1306.robot.commands.cubetake;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class FlipCube extends CommandBase {

	public FlipCube() {
		requires(intake);
	}

	@Override
	protected void execute() {
		intake.flip();
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		intake.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
