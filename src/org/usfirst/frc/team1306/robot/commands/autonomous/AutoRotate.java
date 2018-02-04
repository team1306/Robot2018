package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

public class AutoRotate extends CommandBase {
	
	private double degrees, initAngle;
	private Timer timer;
	
	public AutoRotate(double d) {
		degrees = d + drivetrain.getGyroAngle();
		timer = new Timer();
	}
	
	@Override
	protected void initialize() {
		initAngle = drivetrain.getGyroAngle();
		timer.reset();
	}

	@Override
	protected void execute() {
		
		double output;
		
//		if(Math.abs(degrees - drivetrain.getGyroAngle()) > 0) {
//			output = (degrees - drivetrain.getGyroAngle()) + 0.01; 
//			
//			
//		} else {
//			output = 0;
//		}
//		
//		if(degrees - drivetrain.getGyroAngle() > 0) {
//			
//			drivetrain.drivePercentOutput(output,-output);
//		} else {
//			drivetrain.drivePercentOutput(-output,output);
//		}
		
		
		
		if(degrees - drivetrain.getGyroAngle() > 0) {
			drivetrain.drivePercentOutput(0.25,-0.25);
		} else if(degrees - drivetrain.getGyroAngle() < 0) {
			drivetrain.drivePercentOutput(-0.25,0.25);
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
