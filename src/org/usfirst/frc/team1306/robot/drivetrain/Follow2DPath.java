package org.usfirst.frc.team1306.robot.drivetrain;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.pathing.FalconPathPlanner;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @Follow2DPath
 * 
 * This command drives the robot along a FalconPathPlanner 2D path, using feedback from the gyro and will end after a certain amount of time.
 * 
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
		direction = dir;
		path = p;
		time = t;
		
		initAngle = drivetrain.gyro.getAngle();
		timer = new Timer();
	}
	
	@Override
	protected void initialize() {
		/* Resets the timer that will determine when the command will end */
		timer.reset();
		timer.start();
		
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
		
		/** Calculating heading correction, to keep robot properly oriented along path */
		double gyroCorrection = 0, initCorrection = (path.heading[counter][1] + initAngle - drivetrain.gyro.getAngle()) * 2;
		try {
			if(direction.equals(DriveDirection.BACKWARDS)) { gyroCorrection = initCorrection; }   
			else { gyroCorrection = -initCorrection; }
			SmartDashboard.putNumber("2DPath-GyroCorrection:",gyroCorrection);
		} catch(Exception e) { SmartDashboard.putString("ERROR:","2DPath array is out of bounds"); }

		/** Drives the robot backwards or forwards along path */
		if(direction.equals(DriveDirection.BACKWARDS)) { drivetrain.driveSpeed(-(leftSpeed - gyroCorrection),-(rightSpeed + gyroCorrection)); }
		else { drivetrain.driveSpeed(leftSpeed + gyroCorrection, rightSpeed - gyroCorrection); }
	}

	@Override
	protected boolean isFinished() {
		return timer.hasPeriodPassed(time);
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