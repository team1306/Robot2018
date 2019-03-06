package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.lib.util.VelCommandParams;
import org.usfirst.frc.team1306.lib.util.PrimCommandParams.FinishedType;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.subsystems.VelocitySubsystem;
import edu.wpi.first.wpilibj.Timer;

/**
 * @VelocityCommand
 * 
 * This command's purpose is meant to speed up the process of creating and modifying very simple
 * PIDF command's that use VelocitySubsystems to reliably spin a motor at a given speed. This will hopefully
 * condense a few of these types of commands into one common class with the same functionality.
 * 
 * @author Jackson Goth
 */
public class VelocityCommand extends CommandBase {

	private VelocitySubsystem mechanism;
	private VelCommandParams params;
	private Timer timer;
	private double speed;
	
	/** Creates a new VelocityCommand for a given subsystem and given parameters */
	public VelocityCommand(VelocitySubsystem m, VelCommandParams p, double s) {
		mechanism = m;
		params = p;
		speed = s;
		
		if(params.finishedType.equals(FinishedType.TIME)) {
			timer = new Timer(); //If the command is running for a period of time, it'll need a timer
		}
	}

	@Override
	protected void initialize() {
		if(params.finishedType.equals(FinishedType.TIME)) {
			timer.reset();
			timer.start();
		}
	}

	@Override
	protected void execute() {
		mechanism.spinAll(speed);
	}

	@Override
	protected boolean isFinished() {
		if(params.finishedType.equals(FinishedType.INSTANT)) {
			return true; //If command is supposed to finish immediately, return true
		} else if(params.finishedType.equals(FinishedType.TIME)) {
			return timer.hasPeriodPassed(params.time); //If command is time based, return true if that period has passed
		} else if(params.finishedType.equals(FinishedType.WHILE_HELD)) {
			return !OI.getButtonStatus(params.controller,params.button); //If button based, check if button is still pressed
		} else { return false; } //Otherwise keep going...
	}

	@Override
	protected void end() {
		if(params.finishedType.equals(FinishedType.TOGGLED)) {
			mechanism.stopAll(); //If toggled the command needs to stop motors when un-toggled
		}
	}

	@Override
	protected void interrupted() {
		end();
	}
}
