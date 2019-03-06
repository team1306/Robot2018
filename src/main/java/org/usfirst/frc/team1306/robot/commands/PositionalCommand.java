package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.pathing.Profile;
import org.usfirst.frc.team1306.robot.subsystems.PositionalSubsystem;
import edu.wpi.first.wpilibj.Timer;

/**
 * @PositionalCommand
 * 
 * This command's purpose is meant to speed up the prcess of creating and modifying positional control
 * command's that use the PositionalSubsystems to reliably turn the motor to the correct rotation. This will
 * hopefully simply and make creating these types of subsystems easier without losing functionality.
 * 
 * @author Jackson Goth
 */
public class PositionalCommand extends CommandBase {

	private PositionalSubsystem mechanism;
	private Profile rotProfile;
	private Timer timer;
	private double rotation;
	private int counter;
	
	public PositionalCommand(PositionalSubsystem m, double r) {
		mechanism = m;
		rotation = r;
		
		/** Creates a new profile for the mechanism to turn with, everything is in rotations */
		rotProfile = new Profile(-(mechanism.getPosition() - r),Constants.ROT_VEL,Constants.ROT_ACCEL,Constants.ROT_JERK,Constants.ROT_TIME);
	}
	
	@Override
	protected void initialize() {
		/* Resets the timer that may determine when the command will end */
		timer.reset();
		timer.start();
		
		counter = 0; //Resets counter that's used to determine position in profile
	}

	@Override
	protected void execute() {
		counter = (int) (timer.get() / Constants.PROFILE_STEP_TIME); //Updates position in profile from timer
		double speed = rotProfile.path.get(counter).velocity; //Get's velocity for this period of time
		
		mechanism.turn(speed*60); //Puts velocity in rpm and tells the mechanism to turn that fast
	}

	@Override
	protected boolean isFinished() { //Stops the turn when within range or max allowed turning time has been reached
		return Math.abs(mechanism.getPosition() - rotation) < Constants.ROT_WITHIN_RANGE || timer.hasPeriodPassed(rotProfile.maxTime);
	}

	@Override
	protected void end() {
		mechanism.stop();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
