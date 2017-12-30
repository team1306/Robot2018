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
 * @Drive
 * 
 * Drives the robot in a specified mode (tank-drive, arcade, etc.) by pulling the status of the triggers and joysticks.
 * 
 * @author Sam Roquitte and Jackson Goth
 */
public class Drive extends CommandBase {

	private DriveMode driveMode;
	
	public Drive(DriveMode mode) {
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
					drivetrain.driveVBus(OI.getTriggerVal(Controller.P, Trigger.R), OI.getTriggerVal(Controller.P, Trigger.R));
				} else if(OI.getTriggerVal(Controller.P, Trigger.L) >= Constants.DEADBAND) {
					drivetrain.driveVBus(-OI.getTriggerVal(Controller.P, Trigger.L), -OI.getTriggerVal(Controller.P, Trigger.L));
				}
			} else {
				drivetrain.driveVBus(OI.getJoyVal(Controller.P, Joystick.L, Axis.Y), OI.getJoyVal(Controller.P, Joystick.R, Axis.Y));
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
				drivetrain.driveVBus(triggerVal+OI.getJoyVal(Controller.P, Joystick.L, Axis.X), triggerVal-OI.getJoyVal(Controller.P, Joystick.L, Axis.X));
			} else {
				if (OI.getJoyVal(Controller.P, Joystick.L, Axis.X) >= Constants.DEADBAND || OI.getJoyVal(Controller.P, Joystick.L, Axis.X) <= -Constants.DEADBAND) {
					drivetrain.driveVBus(OI.getJoyVal(Controller.P, Joystick.L, Axis.X), -OI.getJoyVal(Controller.P, Joystick.L, Axis.X));
				} else {
					drivetrain.stop();
				}
			}
		}
		
	}

	@Override
	protected boolean isFinished() {
		return false; //Should never end
	}
}
