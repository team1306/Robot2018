package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.cubetake.Spit;
import org.usfirst.frc.team1306.robot.drivetrain.Follow2DPath;
import org.usfirst.frc.team1306.robot.drivetrain.Follow2DPath.DriveDirection;
import org.usfirst.frc.team1306.robot.elevator.Elevate;
import org.usfirst.frc.team1306.robot.pathing.FalconPathPlanner;
import org.usfirst.frc.team1306.robot.pathing.Profile2DParams;
import org.usfirst.frc.team1306.robot.subsystems.Elevator.Position;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @AutonomousCommand
 * 
 * This is the command station for our autonomous routines.  We use the game's secret message, robot starting position, and a selected routine to get the robot
 * to accomplish certain tasks for many different scenarios without placing on the wrong plate.
 * 
 * @author Jackson Goth
 */
public class AutonomousCommand extends CommandGroup { 

	public enum AutoMode {CENTER_SWITCH_RP, PORTAL_SCALE_GAMBLE, PORTAL_SWITCH_GAMBLE, COMBO_GAMBLE, AUTO_RUN, DO_NOTHING};
	public enum StartingPosition {PORTAL_LEFT, PORTAL_RIGHT, EXCHANGE_RIGHT};

	public AutonomousCommand(AutoMode mode, StartingPosition pos) {

		String gameMessage = DriverStation.getInstance().getGameSpecificMessage(); //Pulls the game message into a string (ex. LLL or LRL)
		while(gameMessage.length() < 3) { gameMessage = DriverStation.getInstance().getGameSpecificMessage(); } //Loop to ensure we get the full game message and not a portion of it.
		String switchLocation = gameMessage.substring(0,1); //Extracting which side our switch is on.
		String scaleLocation = gameMessage.substring(1,2); //Extracting which side our scale is on.
		
		/**TODO Seperate params to make driving quicker **/
		Profile2DParams params = new Profile2DParams(Constants.AUTO_PROFILE_TIME,Constants.PROFILE_STEP_TIME,Constants.TRACK_WIDTH/12); //Max profile time, time in-between steps, and track width in feet

		if(mode.equals(AutoMode.CENTER_SWITCH_RP)) {
			
			double startingPathTime = 5.0;
			Profile2DParams startingPathParams = new Profile2DParams(startingPathTime);
			
			if(switchLocation.equals("L")) { //If our switch is to the robot's left...
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.switchPathLeft);
				path.calculate(startingPathParams); //Calculates the velocity / heading profiles.
				addSequential(new Follow2DPath(path, DriveDirection.FORWARD, startingPathTime + Constants.PROFILE_TIME_ADJ));
				addSequential(new Spit(Constants.CUBETAKE_SPIT_TIME));
				
			} else { //Our switch must be to our robot's right so...
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.switchPathRight);
				path.calculate(startingPathParams); //Calculates the velocity / heading profiles.
				addSequential(new Follow2DPath(path, DriveDirection.FORWARD, startingPathTime + Constants.PROFILE_TIME_ADJ));
				addSequential(new Spit(Constants.CUBETAKE_SPIT_TIME));
			}
			
		} else if(mode.equals(AutoMode.PORTAL_SCALE_GAMBLE)) {
			
			double startingPathTime = 5.0;
			Profile2DParams startingPathParams = new Profile2DParams(startingPathTime);
			
			if(pos.equals(StartingPosition.PORTAL_LEFT) && scaleLocation.equals("L")) {
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.leftPortalLeftScale);
				path.calculate(startingPathParams);
				addSequential(new Follow2DPath(path, DriveDirection.FORWARD, startingPathTime + Constants.PROFILE_TIME_ADJ));
				addSequential(new Elevate(Position.SCALE));
				addSequential(new Spit(Constants.CUBETAKE_SPIT_TIME));
				addSequential(new Elevate(Position.FLOOR));
			} else if(pos.equals(StartingPosition.PORTAL_RIGHT) && scaleLocation.equals("R")) {
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.rightPortalRightScale);
				path.calculate(startingPathParams);
				addSequential(new Follow2DPath(path, DriveDirection.FORWARD, startingPathTime + Constants.PROFILE_TIME_ADJ));
				addSequential(new Elevate(Position.SCALE));
				addSequential(new Spit(Constants.CUBETAKE_SPIT_TIME));
				addSequential(new Elevate(Position.FLOOR));
			} else {
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.autoLinePath);
				path.calculate(startingPathParams);
				addSequential(new Follow2DPath(path, DriveDirection.FORWARD, startingPathTime + Constants.PROFILE_TIME_ADJ));
			}
			
		} else if(mode.equals(AutoMode.PORTAL_SWITCH_GAMBLE)) {
			
			if(switchLocation.equals("L") && pos.equals(StartingPosition.PORTAL_LEFT)) {
				
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.leftPortalSwitchPath);
				path.calculate(params);
				addSequential(new Follow2DPath(path,DriveDirection.FORWARD,Constants.AUTO_PROFILE_TIME+.25));
				addSequential(new Spit(2));
			} else if(switchLocation.equals("R") && pos.equals(StartingPosition.PORTAL_RIGHT)) {
				
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.rightPortalSwitchPath);
				path.calculate(params);
				addSequential(new Follow2DPath(path,DriveDirection.FORWARD,Constants.AUTO_PROFILE_TIME+.25));
				addSequential(new Spit(2));
			} else {
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.autoLinePath);
				path.calculate(params);
				addSequential(new Follow2DPath(path,DriveDirection.FORWARD,Constants.AUTO_PROFILE_TIME+.25));
			}
			
		} else if(mode.equals(AutoMode.AUTO_RUN)) {
			
			FalconPathPlanner path = new FalconPathPlanner(AutoPaths.autoLinePath);
			path.calculate(params);
			addSequential(new Follow2DPath(path,DriveDirection.FORWARD,Constants.AUTO_PROFILE_TIME+.25));

		} else { SmartDashboard.putString("ERROR:","No auto mode selected!"); }
	}
}
