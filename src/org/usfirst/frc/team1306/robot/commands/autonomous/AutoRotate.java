package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutoRotate extends CommandBase {
	
	private double d, degrees;
	private Timer timer;
	
	public AutoRotate(double d) {
		this.d = d;
		timer = new Timer();
	}
	
	@Override
	protected void initialize() {
		timer.reset();
		degrees = d + drivetrain.getGyroAngle();
	}

	@Override
	protected void execute() {
		
		double proportion = (degrees - drivetrain.getGyroAngle()) / degrees;
		SmartDashboard.putNumber("Degreees",degrees);
		SmartDashboard.putNumber("degrees - drivetrain.getGyroAngle()",(degrees - drivetrain.getGyroAngle()));
		double output = proportion / 4;
		if(drivetrain.getGyroAngle() < degrees) {
			drivetrain.drivePercentOutput((output + 0.15),-(output + 0.15));
		} else {
			drivetrain.drivePercentOutput(-(output + 0.15),(output + 0.15));
		} //Left == Negative Right == Positive
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
