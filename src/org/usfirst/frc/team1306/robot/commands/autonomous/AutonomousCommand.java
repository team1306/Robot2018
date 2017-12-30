package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.drivetrain.FollowPath;
import org.usfirst.frc.team1306.robot.pathing.Profile;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @AutonomousCommand
 * 
 * The autonomous command station that tells the robot what to do for multiple different automous routiness.
 * 
 * @author Jackson Goth
 */
public class AutonomousCommand extends CommandGroup {

	public enum AutoMode {FOLLOW_PATH, FOLLOW_2D_PATH, TESTING, DO_NOTHING};

	public AutonomousCommand(AutoMode mode) {

		if(mode.equals(AutoMode.FOLLOW_PATH)) {
			
			addSequential(new FollowPath(new Profile(96,18.25,45,45,15))); //Distance, Velocity, Accel, Jerk, Max Time
		}
	}
}