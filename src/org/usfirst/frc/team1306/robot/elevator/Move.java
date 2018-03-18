package org.usfirst.frc.team1306.robot.elevator;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.Controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.triggers.ControllerButton;

public class Move extends CommandBase {

	public Move() {
		
	}
	
	@Override
	protected void initialize() {
		elevator.unbrake();
	}

	@Override
	protected void execute() {
		elevator.vbus(0.35);
	}

	@Override
	protected boolean isFinished() {
		if(OI.getButtonStatus(Controller.S,ControllerButton.RB)) {
			return false;
		} else {
			elevator.stop();
			elevator.brake();
			return true;
		}
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}
}
