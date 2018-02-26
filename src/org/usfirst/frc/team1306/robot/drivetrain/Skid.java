package org.usfirst.frc.team1306.robot.drivetrain;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/**
 * @Skid - Command to turn using only one side of the drive-train, so we turn to an angle like @AutoRotate but we don't have to turn on a dime.
 * @author Jackson Goth
 */
public class Skid extends CommandBase {
	
	private SkidSide side; //Side that gets put into a skid
	private double desiredAngle, degrees;
	private Timer timer;
	
	public Skid(SkidSide s, double d) {
		desiredAngle = d;
		side = s;
		
		timer = new Timer();
	}
	
	@Override
	protected void initialize() {
		timer.reset();
		degrees = desiredAngle + drivetrain.getGyroAngle();
	}

	@Override
	protected void execute() {
		
		double proportion = (degrees - drivetrain.getGyroAngle()) / degrees;
		double output = proportion / 4;
		
		if(side.equals(SkidSide.LEFT)) {
			if(drivetrain.getGyroAngle() < degrees) {
				drivetrain.drivePercentOutput(0,-(output + 0.15));
			} else {
				drivetrain.drivePercentOutput(0,(output + 0.15));
			} //Left == Negative Right == Positive
		} else if(side.equals(SkidSide.RIGHT)) {
			if(drivetrain.getGyroAngle() < degrees) {
				drivetrain.drivePercentOutput((output + 0.15),0);
			} else {
				drivetrain.drivePercentOutput(-(output + 0.15),0);
			} //Left == Negative Right == Positive
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
	
	public enum SkidSide {LEFT,RIGHT};
}
