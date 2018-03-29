package org.usfirst.frc.team1306.robot.elevator;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.Axis;
import org.usfirst.frc.team1306.robot.OI.Controller;
import org.usfirst.frc.team1306.robot.OI.Joystick;
import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * @Elevate - Lifts / drops the elevator using joystick-control.
 * @author Jackson Goth
 */
public class Elevate extends CommandBase {

	@Override
	protected void execute() {
		double currentJoyVal = OI.getJoyVal(Controller.S, Joystick.L, Axis.Y);
		if(currentJoyVal >= Constants.DEADBAND) {
			elevator.movePercentOutput(-currentJoyVal);
		} else if(currentJoyVal <= Constants.DEADBAND) {
			elevator.movePercentOutput(-(currentJoyVal / 2));
		} else {
			elevator.stop();
			elevator.brake();
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}
}
