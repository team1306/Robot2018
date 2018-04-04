package org.usfirst.frc.team1306.robot.commands.cubetake;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.Controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.triggers.ControllerButton;
import edu.wpi.first.wpilibj.Timer;

/**
 * @SpitCube - Command to spin the intake wheels outward; while-held or timed.
 * @author Ethan Dong
 */
public class SpitSlow extends CommandBase{
	
	private Timer timer;
	private boolean timed; //Whether the command is timed or not.
	private double time; //Time the command will run if timed.
	
	public SpitSlow(double t) { //Command must be timed.
		timer = new Timer();
		timed = true;
		time = t;
	}
	
	public SpitSlow() { //Command must be while-held.
		timed = false;
	}
	
	@Override
	protected void initialize() {
		if (timed) {
			timer.reset();
			timer.start();	
		}
	}

	@Override
	protected void execute() {
		cubetake.spitSlow();
	}

	@Override
	protected boolean isFinished() {
		if(OI.getButtonStatus(Controller.P, ControllerButton.A)) {
			return false; 
		} else if(timed) { //If timed, check if time has passed.
			if(timer.hasPeriodPassed(time)) { cubetake.stop(); return true; }
			else { return false; }
		} else { cubetake.stop(); return true; }
	}
}

