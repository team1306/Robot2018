package org.usfirst.frc.team1306.robot.commands.cubetake;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

import edu.wpi.first.wpilibj.Timer;

/**
 * @SpitCube - Command to spin the intake wheels outward, toggled.
 * @author Ethan Dong
 */
public class SpitCube extends CommandBase{
	
	private boolean check;
	private Timer timer;
	private double time;
	
	public SpitCube(double time) {
		check = true;
		timer = new Timer();
		this.time = time;
	}
	
	public SpitCube() {
		check = false;
	}
	
	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	@Override
	protected void execute() {
		intake.spinOut();
	}

	@Override
	protected boolean isFinished() {
		if(check == true) {
			if(timer.hasPeriodPassed(time)) {
				intake.stop();
				return true;
			}
			return false;
		}
		return false; //Toggled so doesn't need to ever return true
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
