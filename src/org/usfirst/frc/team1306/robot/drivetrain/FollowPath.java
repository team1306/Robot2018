package org.usfirst.frc.team1306.robot.drivetrain;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.pathing.Profile;
import org.usfirst.frc.team1306.robot.subsystems.Drivetrain.Side;
import edu.wpi.first.wpilibj.Timer;

/**
 * @FollowPath
 * 
 * This command is meant to drive the robot along a given path/profile and applies a P-loop to how far the drivetrain is off from the path.
 * 
 * @author Jackson Goth
 */
public class FollowPath extends CommandBase {

	private Profile profile;
	private Timer timer;
	private int counter;
	private double initAngle;
	
	public FollowPath(Profile p) {
		requires(drivetrain);
		profile = p;
		
		timer = new Timer();
	}
	
	@Override
	protected void initialize() {
		
		/* Resets the timer that will determine when the command will end */
		timer.reset();
		timer.start();
		
		drivetrain.resetEncoders();
		
		counter = 0; //Resets counter that's used to determine position in profile
		initAngle = drivetrain.gyro.getAngle();
	}

	@Override
	protected void execute() {
		
		counter = (int) (timer.get() / Constants.PROFILE_STEP_TIME);
		
		double speed = profile.path.get(counter).velocity;
		double leftError = profile.path.get(counter).position - Math.abs(drivetrain.getEncoderPos(Side.LEFT)*Constants.ENCODER_INCHES_CONVERSION);
		double rightError = profile.path.get(counter).position - Math.abs(drivetrain.getEncoderPos(Side.RIGHT)*Constants.ENCODER_INCHES_CONVERSION);
		double leftAdjSpeed = speed + (leftError * Constants.ENCODER_ERROR_MULTIPLIER);
		double rightAdjSpeed = speed + (rightError * Constants.ENCODER_ERROR_MULTIPLIER);
		
		double angleError = (drivetrain.gyro.getAngle() - initAngle) * Constants.GYRO_ERROR_MULTIPLIER;
		
		drivetrain.driveSpeed((leftAdjSpeed*Constants.IPS_TO_RPM_CONVERSION)-angleError,(rightAdjSpeed*Constants.IPS_TO_RPM_CONVERSION)+angleError);
	}

	@Override
	protected boolean isFinished() {
		return timer.hasPeriodPassed(profile.maxTime);
	}

	@Override
	protected void end() {
		drivetrain.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
