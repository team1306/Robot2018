package org.usfirst.frc.team1306.robot.drivetrain;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.pathing.FalconPathPlanner;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @Follow2DPath - This command drives the robot along a FalconPathPlanner 2D path, using sensor feedback to correct rotational error.
 * @author Jackson Goth
 */
public class Follow2DPath extends CommandBase {

	private DriveDirection direction;
	private FalconPathPlanner path;
	private Timer timer;
	private int counter;
	private double initAngle, time;
	
	public Follow2DPath(FalconPathPlanner p, DriveDirection dir, double t) {
		requires(drivetrain);
		timer = new Timer();
		
		direction = dir;
		path = p;
		time = t;
	}
	
	@Override
	protected void initialize() {
		/* Resets the timer that will determine when the command will end */
		timer.reset();
		timer.start();
		
		initAngle = drivetrain.getGyroAngle();
		drivetrain.resetEncoders();
		counter = 0; //Resets counter that's used to determine position in profile
	}

	@Override
	protected void execute() {
		
		counter = (int) (timer.get() / 0.1);
		
		/** Getting speed each side of drivetrain should be at */
		double leftSpeed = 0, rightSpeed = 0;
		try {
			leftSpeed = path.smoothLeftVelocity[counter][1] * Constants.FPS_TO_RPM_CONVERSION;
			rightSpeed = path.smoothRightVelocity[counter][1] * Constants.FPS_TO_RPM_CONVERSION;
		} catch(Exception e) { SmartDashboard.putString("ERROR:","2DPath array is out of bounds"); }
		
		double conversion = ((256 * 4) / 600) * 2;
		
		leftSpeed *= conversion;
		rightSpeed *= conversion;

		/** Calculating heading correction, to keep robot properly oriented along path */
		double gyroCorrection = 0;
		try {
			double initCorrection = (path.heading[counter][1] + drivetrain.getGyroAngle() - initAngle);
			if(Constants.GYRO_DEBUG) { SmartDashboard.putNumber("Heading:",path.heading[counter][1]); }
			if(direction.equals(DriveDirection.BACKWARDS)) { gyroCorrection = initCorrection * Constants.GYRO_ERROR_MULTIPLIER; }   
			else { gyroCorrection = -(initCorrection * Constants.GYRO_ERROR_MULTIPLIER); }
			if(Constants.GYRO_DEBUG) { SmartDashboard.putNumber("2DPath-GyroCorrection:",gyroCorrection); }
		} catch(Exception e) { SmartDashboard.putString("ERROR:","2DPath array is out of bounds"); }

		/** Drives the robot backwards or forwards along path */
		if(direction.equals(DriveDirection.BACKWARDS)) { drivetrain.driveVelocity(-(leftSpeed + gyroCorrection),-(rightSpeed - gyroCorrection)); }
		else { drivetrain.driveVelocity(leftSpeed + gyroCorrection, rightSpeed - gyroCorrection); }
	}

	@Override
	protected boolean isFinished() {
		if(timer.hasPeriodPassed(time)) { return true; }
		else { return false; }
	}

	@Override
	protected void end() {
		drivetrain.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}

	public enum DriveDirection {FORWARD,BACKWARDS};
}
