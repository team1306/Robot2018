package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.drivetrain.Follow2DPath;
import org.usfirst.frc.team1306.robot.drivetrain.Follow2DPath.DriveDirection;
import org.usfirst.frc.team1306.robot.drivetrain.FollowPath;
import org.usfirst.frc.team1306.robot.pathing.FalconPathPlanner;
import org.usfirst.frc.team1306.robot.pathing.Profile;
import org.usfirst.frc.team1306.robot.pathing.Profile2DParams;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @AutonomousCommand
 * 
 * This is our autonomous command station whose goal is to have the robot perform a selected action in the correct manner.
 * To do this we'll intake the game's secret message and adjust the robot's path to always place the power cubes on our alliance's plates.
 * 
 * @author Jackson Goth
 */
public class AutonomousCommand extends CommandGroup {

	public enum AutoMode {PLACE_SWITCH_SPLIT, PLACE_SWITCH_STRAIGHT, AUTO_RUN, DO_NOTHING};
	public enum StartingPosition {PORTAL_LEFT, PORTAL_RIGHT, EXCHANGE_LEFT, EXCHANGE_RIGHT};

	public AutonomousCommand(AutoMode mode, StartingPosition pos) {

		String gameMessage = DriverStation.getInstance().getGameSpecificMessage();
		String switchLocation = gameMessage.substring(0,1);
		String scaleLocation = gameMessage.substring(1,2);
		SmartDashboard.putString("SecretMessage:",gameMessage);
		SmartDashboard.putString("SwitchLocation:",switchLocation);
		SmartDashboard.putString("ScaleLocation:",scaleLocation);
		
		Profile2DParams params = new Profile2DParams(Constants.AUTO_PROFILE_TIME,Constants.PROFILE_STEP_TIME,Constants.TRACK_WIDTH/12); //Max profile time, time in-between steps, and track width in feet
		
		if(mode.equals(AutoMode.PLACE_SWITCH_SPLIT)) {
			
			if(switchLocation.equals("L")) {
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.switchPathLeft);
				path.calculate(params);
				
				addSequential(new Follow2DPath(path,DriveDirection.FORWARD,Constants.AUTO_PROFILE_TIME + 0.5));	
			} else if(switchLocation.equals("R")) {
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.switchPathRight);
				path.calculate(params);
				
				addSequential(new Follow2DPath(path,DriveDirection.FORWARD,Constants.AUTO_PROFILE_TIME + 0.5));	
			}
			
		} else if(mode.equals(AutoMode.PLACE_SWITCH_STRAIGHT)) {
			
			if(switchLocation.equals("L") ) {
				
			} else if(switchLocation.equals("R")) {
				
			}
			
		} else if(mode.equals(AutoMode.AUTO_RUN)) {
			
			addSequential(new FollowPath(new Profile(120,40,60,120,4.75))); //Distance, Velocity, Accel, Jerk, Max Time
			
		} else if(mode.equals(AutoMode.DO_NOTHING)) { }
	}
}
