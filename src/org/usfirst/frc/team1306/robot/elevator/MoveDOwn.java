package org.usfirst.frc.team1306.robot.elevator;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.Controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.triggers.ControllerButton;

public class MoveDOwn extends CommandBase {

public MoveDOwn() {
		
	}
	
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		elevator.vbus(-0.8);
	}

	@Override
	protected boolean isFinished() {
		if(OI.getButtonStatus(Controller.P,ControllerButton.BACK)) {
			return false;
		} else {
			elevator.stop();
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
