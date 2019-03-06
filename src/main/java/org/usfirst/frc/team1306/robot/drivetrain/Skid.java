package org.usfirst.frc.team1306.robot.drivetrain;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/**
 * @Skid - Command to turn using only one side of the drive-train, so we turn to an angle like @AutoRotate but we don't have to turn on a dime.
 * @author Jackson Goth
 */
public class Skid extends CommandBase {
	
	private SkidSide side; //Side that gets put into a skid.
	private double goalAdjustment, goalAngle;
	private Timer timer;
	
	public Skid(SkidSide s, double d) {
		timer = new Timer();
		
		goalAdjustment = d;
		side = s;
	}
	
	@Override
	protected void initialize() {
		timer.reset();
		goalAngle = goalAdjustment + drivetrain.getGyroAngle();
	}

	@Override
	protected void execute() {
		double output = ((goalAngle - drivetrain.getGyroAngle()) / goalAngle) / 4; //Gives a range of outputs so faster when farther from goal-angle (acts like proportional gain).
		
		if(side.equals(SkidSide.LEFT)) {
			if(drivetrain.getGyroAngle() < goalAngle) {
				drivetrain.drivePercentOutput(0,-(output + 0.15)); } //0.15 is adjustment (acts like feed-forward gain).
			else { drivetrain.drivePercentOutput(0,(output + 0.15)); } //Left == Negative Right == Positive
		} else if(side.equals(SkidSide.RIGHT)) {
			if(drivetrain.getGyroAngle() < goalAngle) { drivetrain.drivePercentOutput((output + 0.15),0); } //0.15 is adjustment (acts like feed-forward gain).
			else { drivetrain.drivePercentOutput(-(output + 0.15),0); } //Left == Negative Right == Positive
		}
	}

	@Override
	protected boolean isFinished() {
		if(Math.abs(goalAngle - drivetrain.getGyroAngle()) < Constants.WITHIN_ANGLE_RANGE) { //If we're within our set range of the goal-angle.
			if(timer.hasPeriodPassed(Constants.WITHIN_ANGLE_CHECK_TIME)) { return true; } //Check if we've been within our set range for long enough to not determine we haven't overshot the goal-angle.
			else { return false; }
		} else {
			timer.reset();
			timer.start();
			return false;
		}
	}
	
	public enum SkidSide {LEFT,RIGHT};
}
