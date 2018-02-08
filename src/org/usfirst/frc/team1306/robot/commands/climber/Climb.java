package org.usfirst.frc.team1306.robot.commands.climber;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.Controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.triggers.ControllerButton;

public class Climb extends CommandBase{
	
	@Override
	protected void execute() {
		if(OI.getButtonStatus(Controller.S, ControllerButton.START)) {
			climber.climbUp();
		}
	}

	@Override
	protected boolean isFinished() {
		if(!OI.getButtonStatus(Controller.S, ControllerButton.START)) {
			return true;
		}
		return false;
	}

	@Override
	protected void end() {
		climber.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}