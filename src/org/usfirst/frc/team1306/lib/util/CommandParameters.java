package org.usfirst.frc.team1306.lib.util;

import org.usfirst.frc.team1306.robot.OI.Controller;
import org.usfirst.frc.team1306.robot.triggers.ControllerButton;

/**
 * @CommandParameters
 * 
 * This is an object to store the parameters necesarry for a PrimitiveCommand and contains the additional
 * information the command needs for time-based and while-held commands.
 * 
 * @author Jackson Goth
 */
public class CommandParameters {

	public CommandType commandType; //What the PrimitiveSubsystem should do
	public FinishedType finishedType; //When should the PrimitiveCommand finish
	public ControllerButton button; //If the command is button-controlled which button is being used
	public Controller controller; //If the command is button-controlled which controller is being used
	public double time; //If the command is time-controlled how long should it run
	
	/** A basic parameters object for commands that aren't time or button based */
	public CommandParameters(CommandType cType, FinishedType fType) {
		commandType = cType;
		finishedType = fType;
	}
	
	/** A parameters object for button based commands */
	public CommandParameters(CommandType cType, Controller c, ControllerButton b, FinishedType fType) {
		commandType = cType;
		finishedType = fType;
		controller = c;
		button = b;
	}
	
	/** A parameters object for time based commands */
	public CommandParameters(CommandType cType, double t, FinishedType fType) {
		commandType = cType;
		finishedType = fType;
		time = t;
	}
	
	public enum CommandType {SPIN,PUSH,PULL};
	public enum FinishedType {WHILE_HELD,TOGGLED,INSTANT,TIME}
}