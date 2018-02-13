package org.usfirst.frc.team1306.robot.commands.cubetake;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/**
 * @SpitCube
 * 
 * Command to spin the intake wheels outward, toggled or timed.
 * 
 * @author Ethan Dong
 */
public class SpitCube extends CommandBase{
	
	private boolean isTimed;
	private Timer timer;
	private double runTime;
	
	public SpitCube(double time) {
		isTimed = true;
		runTime = time;
		
		timer = new Timer();
	}
	
	public SpitCube() {
		isTimed = false;
	}
	
	@Override
	protected void initialize() {
		if (isTimed) {
			timer.reset();
			timer.start();	
		}
	}

	@Override
	protected void execute() {
		intake.spit();
	}

	@Override
	protected boolean isFinished() {
		if(isTimed) {
			if(timer.hasPeriodPassed(runTime)) {
				intake.stop();
				return true; //End if timer has passed the runTime
			} return false;
		} return false; //Toggled so doesn't need to ever return true
	}

	@Override
	protected void end() {
		intake.stop();
	}

	@Override
	protected void interrupted() {
		end(); //Should stop the intake if toggled again
	}
}

