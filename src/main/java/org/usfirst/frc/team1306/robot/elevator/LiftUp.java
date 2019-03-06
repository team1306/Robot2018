package org.usfirst.frc.team1306.robot.elevator;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.Controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.triggers.ControllerButton;

/**
 * @LiftUp - Command that lifts the elevator up at a constant speed when a button is pressed, triggers brake when done.
 * @author Jackson Goth
 */
public class LiftUp extends CommandBase {

	@Override
	protected void initialize() {
		elevator.unbrake();
	}

	@Override
	protected void execute() {
		elevator.movePercentOutput(Constants.ELEVATOR_POUTPUT_UP);
	}

	@Override
	protected boolean isFinished() {
		if(OI.getButtonStatus(Controller.S,ControllerButton.LB)) {
			return false;
		} else {
			elevator.stop();
			elevator.brake();
			return true;
		}
	}
}
