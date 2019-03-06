package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.lib.util.PrimCommandParams;
import org.usfirst.frc.team1306.lib.util.PrimCommandParams.CommandType;
import org.usfirst.frc.team1306.lib.util.PrimCommandParams.FinishedType;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.subsystems.PrimitiveSubsystem;
import edu.wpi.first.wpilibj.Timer;

/**
 * @PrimitiveCommand
 * 
 * This command's purpose is meant to speed up the process of creating and modifying very simple 
 * commands that use PrimitiveSubsystems to spin a motor, activate pneumatics, etc. This will hopefully 
 * condense a few very simple commands down into one common class with the same functionality.
 * 
 * @author Jackson Goth
 */
public class PrimitiveCommand extends CommandBase {
	
	private PrimitiveSubsystem mechanism;
	private PrimCommandParams params;
	private Timer timer;
	
	/** Creates a new PrimitiveCommand for a given subsystem and given parameters */
	public PrimitiveCommand(PrimitiveSubsystem s, PrimCommandParams p) {
		mechanism = s;
		params = p;
		
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
		if(params.commandType.equals(CommandType.SPIN_POS)) {
			mechanism.spinAllMotors(mechanism.motorSpeed);
		} else if(params.commandType.equals(CommandType.SPIN_NEG)) {
			mechanism.spinAllMotors(-mechanism.motorSpeed);
		} else if(params.commandType.equals(CommandType.PUSH)) {
			mechanism.pushOut();
		} else if(params.commandType.equals(CommandType.PULL)) {
			mechanism.pullIn();
		}
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
