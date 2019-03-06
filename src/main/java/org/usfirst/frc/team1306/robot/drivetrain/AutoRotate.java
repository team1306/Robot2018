package org.usfirst.frc.team1306.robot.drivetrain;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/**
 * @AutoRotate - Automatically turns the robot to a given angle on a dime using gyro feedback.
 * @author Jackson Goth
 */
public class AutoRotate extends CommandBase {
	
	private Timer timer; //Timer used to ensure we've reached goal angle for a constant period of time.
	private double goalAdjustment, goalAngle;

	public AutoRotate(double a) {
		timer = new Timer();
		this.goalAdjustment = a;
	}
	
	@Override
	protected void initialize() {
		timer.reset();
		goalAngle = goalAdjustment + drivetrain.getGyroAngle();
	}

	@Override
	protected void execute() {
		double output = ((goalAngle - drivetrain.getGyroAngle()) / goalAngle) / 4; //Gives a range of outputs so faster when farther from goal-angle (acts like proportional gain).
		if(drivetrain.getGyroAngle() < goalAngle) { drivetrain.drivePercentOutput((output + 0.15),-(output + 0.15)); } //0.15 is adjustment (acts like feed-forward gain).
		else { drivetrain.drivePercentOutput(-(output + 0.15),(output + 0.15)); } //Left == Negative Right == Positive
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
}
