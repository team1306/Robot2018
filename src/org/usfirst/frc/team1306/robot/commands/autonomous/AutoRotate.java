package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

public class AutoRotate extends CommandBase {
	
	private double degrees;
	private Timer timer;
	
	public AutoRotate(double d) {
		degrees = d + drivetrain.getGyroAngle();
		timer = new Timer();
	}
	
	@Override
	protected void initialize() {
		timer.reset();
	}

	@Override
	protected void execute() {
		
		double proportion = (degrees - drivetrain.getGyroAngle()) / degrees;
		double output = proportion / 5;
		if(drivetrain.getGyroAngle() < degrees) {
			drivetrain.drivePercentOutput((output + 0.2),-(output + 0.2));
		} else {
			drivetrain.drivePercentOutput(-(output - 0.2),(output - 0.2));
		}
	}

	@Override
	protected boolean isFinished() {
		if(Math.abs(degrees - drivetrain.getGyroAngle()) < 4) {
			if(timer.hasPeriodPassed(1)) {
				return true;
			} else {
				return false;
			}
		} else {
			timer.reset();
			timer.start();
			return false;
		}
	}
}
