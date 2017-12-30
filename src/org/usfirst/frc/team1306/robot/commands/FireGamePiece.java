package org.usfirst.frc.team1306.robot.commands;

import com.ctre.CANTalon.TalonControlMode;

public class FireGamePiece extends CommandBase {

	@Override
	protected void execute() {
		shooter.changeControlMode(TalonControlMode.Speed);
		shooter.spinAll(1000);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		shooter.stopAll();
	}

	@Override
	protected void interrupted() {
		end();
	}
}