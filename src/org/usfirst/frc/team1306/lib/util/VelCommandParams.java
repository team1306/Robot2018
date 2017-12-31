package org.usfirst.frc.team1306.lib.util;

import org.usfirst.frc.team1306.lib.util.PrimCommandParams.FinishedType;
import org.usfirst.frc.team1306.robot.OI.Controller;
import org.usfirst.frc.team1306.robot.triggers.ControllerButton;

/**
 * @VelCommandParams
 * 
 * This is an object to store the parameters necesarry for a VelocityCommand and contains the additional
 * information the command needs for time-based and while-held commands.
 * 
 * @author Jackson Goth
 */
public class VelCommandParams {
	
	public FinishedType finishedType; //When should the VelocityCommand finish
	public ControllerButton button; //If the command is button-controlled which button is being used
	public Controller controller; //If the command is button-controlled which controller is being used
	public double time; //If the command is time-controlled how long should it run
	
	/** A basic parameters object for commands that aren't time or button based */
	public VelCommandParams(FinishedType fType) {
		finishedType = fType;
	}
	
	/** A parameters object for button based commands */
	public VelCommandParams(Controller c, ControllerButton b, FinishedType fType) {
		finishedType = fType;
		controller = c;
		button = b;
	}
	
	/** A parameters object for time based commands */
	public VelCommandParams(double t, FinishedType fType) {
		finishedType = fType;
		time = t;
	}
}