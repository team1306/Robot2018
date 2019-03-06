package org.usfirst.frc.team1306.robot.drivetrain;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.Axis;
import org.usfirst.frc.team1306.robot.OI.Controller;
import org.usfirst.frc.team1306.robot.OI.Joystick;
import org.usfirst.frc.team1306.robot.OI.Trigger;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.drivetrain.Settings.DriveMode;

/**
 * @Drive - Drives the robot in a specified mode (tank-drive, arcade, etc.)
 * @authors Sam Roquitte and Jackson Goth
 */
public class DriveCommand extends CommandBase {

	private DriveMode driveMode; //What mode to drive in
	
	public DriveCommand(DriveMode mode) {
		requires(drivetrain);
		driveMode = mode;
	}

	@Override
	protected void execute() {

		if(driveMode.equals(DriveMode.TANK_DRIVE)) {
			/**
			 * If Trigger pressed it will figure out which one and either go forward or backward based on the values from them.
			 * Otherwise it assumes it is being controlled by joysticks and will drive robot based on their respective inputs.
			 */
			if(OI.getTriggerVal(Controller.P, Trigger.L) >= Constants.DEADBAND || OI.getTriggerVal(Controller.P, Trigger.R) >= Constants.DEADBAND) {
				if(OI.getTriggerVal(Controller.P, Trigger.R) >= Constants.DEADBAND) {
					drivetrain.drivePercentOutput(0.75*OI.getTriggerVal(Controller.P, Trigger.R), 0.75*OI.getTriggerVal(Controller.P, Trigger.R));
				} else if(OI.getTriggerVal(Controller.P, Trigger.L) >= Constants.DEADBAND) {
					drivetrain.drivePercentOutput(-0.75*OI.getTriggerVal(Controller.P, Trigger.L), -0.75*OI.getTriggerVal(Controller.P, Trigger.L));
				}
			} else {
				drivetrain.drivePercentOutput(0.75*OI.getJoyVal(Controller.P, Joystick.L, Axis.Y), 0.75*OI.getJoyVal(Controller.P, Joystick.R, Axis.Y));
			}
		} else if(driveMode.equals(DriveMode.ARCADE)) {
			/**
			 * Each time this runs it checks to see if either trigger is pulled past the deadband.
			 * It subtracts the inputs from the left side of controller from the right to find whether the driver wants to go forward or backward.
			 * Then it adds the x-axis of the left joystick to the trigger inputs which allow the fine adjustments needed for turning.
			 * If the joystick is being used without trigger input it will turn the robot it's place.
			 */
			if (OI.getTriggerVal(Controller.P, Trigger.R) >= Constants.DEADBAND || OI.getTriggerVal(Controller.P, Trigger.L) >= Constants.DEADBAND) {
				double triggerVal = OI.getTriggerVal(Controller.P, Trigger.R) - OI.getTriggerVal(Controller.P, Trigger.L);
				drivetrain.drivePercentOutput(0.90*(triggerVal+OI.getJoyVal(Controller.P, Joystick.L, Axis.X)), 0.90*(triggerVal-OI.getJoyVal(Controller.P, Joystick.L, Axis.X)));
			} else {
				if (OI.getJoyVal(Controller.P, Joystick.L, Axis.X) >= Constants.DEADBAND || OI.getJoyVal(Controller.P, Joystick.L, Axis.X) <= -Constants.DEADBAND) {
					drivetrain.drivePercentOutput(0.90*(OI.getJoyVal(Controller.P, Joystick.L, Axis.X)), -0.90*(OI.getJoyVal(Controller.P, Joystick.L, Axis.X)));
				} else { drivetrain.stop(); }
			}
		}
	}

	@Override
	protected boolean isFinished() {
		return false; //Should never end
	}
}

//testcode
