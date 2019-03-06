package org.usfirst.frc.team1306.robot.drivetrain;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/**
 * @TimedDrive - Drives the robot at a 'percentoutput' speed (-1.0 -> 1.0) for a given amount of time.
 * @author Finn Voichick and Jackson Goth
 */
public class TimedDrive extends CommandBase {

	private Timer timer;
	private double speed, time;
	
	public TimedDrive(double s, double t) {
		timer = new Timer();
		speed = s;
		time = t;
	}
	
	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
	}

	@Override
	protected void execute() {
		drivetrain.drivePercentOutput(speed,speed);
	}

	@Override
	protected boolean isFinished() {
		if(timer.hasPeriodPassed(time)) { drivetrain.stop(); return true; }
		else { return false; }
	}
}
