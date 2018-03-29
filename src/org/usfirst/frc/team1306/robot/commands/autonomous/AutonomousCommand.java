package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.lib.util.Wait;
import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.cubetake.ActuateArms;
import org.usfirst.frc.team1306.robot.commands.cubetake.RetractArms;
import org.usfirst.frc.team1306.robot.commands.cubetake.SpitSlow;
import org.usfirst.frc.team1306.robot.drivetrain.Follow2DPath;
import org.usfirst.frc.team1306.robot.drivetrain.Follow2DPath.DriveDirection;
import org.usfirst.frc.team1306.robot.drivetrain.TimedDrive;
import org.usfirst.frc.team1306.robot.elevator.TimedLift;
import org.usfirst.frc.team1306.robot.elevator.TimedLift.ElevatorAction;
import org.usfirst.frc.team1306.robot.pathing.FalconPathPlanner;
import org.usfirst.frc.team1306.robot.pathing.Profile2DParams;
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

	public AutonomousCommand(AutoMode mode, StartingPosition pos, double delay) {

		String gameMessage = DriverStation.getInstance().getGameSpecificMessage(); //Pulls the game message into a string (ex. LLL or LRL)
		while(gameMessage.length() < 3) { gameMessage = DriverStation.getInstance().getGameSpecificMessage(); } //Loop to ensure we get the full game message and not a portion of it.
		String switchLocation = gameMessage.substring(0, 1); //Extracting which side our switch is on.
		String scaleLocation = gameMessage.substring(1, 2); //Extracting which side our scale is on.
		
		if(delay > 0) { addSequential(new Wait(delay)); }
		
		if(mode.equals(AutoMode.CENTER_SWITCH_RP)) {
			
			double switchRPPathTime = 3.0;
			Profile2DParams switchRPPathParams = new Profile2DParams(switchRPPathTime);
//			double backupPathTime = 2.0;
//			Profile2DParams backupPathParams = new Profile2DParams(backupPathTime);
//			double pickupPathTime = 2.0;
//			Profile2DParams pickupPathParams = new Profile2DParams(pickupPathTime);
//			double scalePathTime = 9.0;
//			Profile2DParams scalePathParams = new Profile2DParams(scalePathTime);
			
			if(switchLocation.equals("L")) { //If our switch is to the robot's left...
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.switchPathLeft);
				path.calculate(switchRPPathParams); //Calculates the velocity / heading profiles.
				addSequential(new Follow2DPath(path, DriveDirection.FORWARD, getFollowTime(switchRPPathTime)));
				addSequential(new SpitSlow(Constants.CUBETAKE_SPIT_TIME));
				
//				FalconPathPlanner backupPath = new FalconPathPlanner(AutoPaths.backupSwitchPath);
//				backupPath.calculate(backupPathParams);
//				FalconPathPlanner pickupPath = new FalconPathPlanner(AutoPaths.stackTurnRightPath);
//				pickupPath.calculate(pickupPathParams);
//				FalconPathPlanner scalePath = new FalconPathPlanner(AutoPaths.scaleTurnRightPath);
//				scalePath.calculate(scalePathParams);
//				addSequential(new Follow2DPath(backupPath, DriveDirection.BACKWARDS, getFollowTime(backupPathTime)));
//				addSequential(new ActuateArms());
//				addSequential(new Follow2DPath(pickupPath, DriveDirection.FORWARD, getFollowTime(pickupPathTime)));
//				addSequential(new Collect(Constants.CUBETAKE_COLLECT_TIME));
//				addSequential(new RetractArms());
				
//				addSequential(new Follow2DPath(scalePath, DriveDirection.BACKWARDS, getFollowTime(scalePathTime)));
				
//				addSequential(new Follow2DPath(pickupPath, DriveDirection.BACKWARDS, getFollowTime(pickupPathTime)));
//				addSequential(new Follow2DPath(backupPath, DriveDirection.FORWARD, getFollowTime(backupPathTime)));
//				addSequential(new SpitSlow(Constants.CUBETAKE_SPIT_TIME));
				
			} else { //Our switch must be to our robot's right so...
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.switchPathRight);
				path.calculate(switchRPPathParams); //Calculates the velocity / heading profiles.
				addSequential(new Follow2DPath(path, DriveDirection.FORWARD, getFollowTime(switchRPPathTime)));
				addSequential(new SpitSlow(Constants.CUBETAKE_SPIT_TIME));
				
//				FalconPathPlanner backupPath = new FalconPathPlanner(AutoPaths.backupSwitchPath);
//				backupPath.calculate(backupPathParams);
//				FalconPathPlanner pickupPath = new FalconPathPlanner(AutoPaths.stackTurnLeftPath);
//				pickupPath.calculate(pickupPathParams);
//				FalconPathPlanner scalePath = new FalconPathPlanner(AutoPaths.scaleTurnLeftPath);
//				scalePath.calculate(scalePathParams);
//				addSequential(new Follow2DPath(backupPath, DriveDirection.BACKWARDS, getFollowTime(backupPathTime)));
//				addSequential(new ActuateArms());
//				addSequential(new Follow2DPath(pickupPath, DriveDirection.FORWARD, getFollowTime(pickupPathTime)));
//				addSequential(new Collect(Constants.CUBETAKE_COLLECT_TIME));
//				addSequential(new RetractArms());
				
//				addSequential(new Follow2DPath(scalePath, DriveDirection.BACKWARDS, getFollowTime(scalePathTime)));
				
//				addSequential(new Follow2DPath(pickupPath, DriveDirection.BACKWARDS, getFollowTime(pickupPathTime)));
//				addSequential(new Follow2DPath(backupPath, DriveDirection.FORWARD, getFollowTime(backupPathTime)));
//				addSequential(new SpitSlow(Constants.CUBETAKE_SPIT_TIME));
			}
			
		} else if(mode.equals(AutoMode.PORTAL_SCALE_GAMBLE)) {
			
			double scaleGamblePathTime = 5.0;
			Profile2DParams scalePathParams = new Profile2DParams(scaleGamblePathTime);
			double autoLinePathTime = 3.0;
			Profile2DParams autoLineParams = new Profile2DParams(autoLinePathTime);
			
			if(scaleLocation.equals("L") && pos.equals(StartingPosition.PORTAL_LEFT)) {
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.leftPortalLeftScale);
				path.calculate(scalePathParams);
				addSequential(new Follow2DPath(path, DriveDirection.FORWARD, getFollowTime(scaleGamblePathTime)));
				addSequential(new TimedLift(ElevatorAction.LIFT, 1.0));
				addSequential(new ActuateArms());
				addSequential(new SpitSlow(Constants.CUBETAKE_SPIT_TIME));
				addSequential(new RetractArms());
				addSequential(new TimedLift(ElevatorAction.DROP, 0.75));
				
			} else if(scaleLocation.equals("R") && pos.equals(StartingPosition.PORTAL_RIGHT)) {
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.rightPortalRightScale);
				path.calculate(scalePathParams);
				addSequential(new Follow2DPath(path, DriveDirection.FORWARD, getFollowTime(scaleGamblePathTime)));
				addSequential(new TimedLift(ElevatorAction.LIFT, 1.0));
				addSequential(new ActuateArms());
				addSequential(new SpitSlow(Constants.CUBETAKE_SPIT_TIME));
				addSequential(new RetractArms());
				addSequential(new TimedLift(ElevatorAction.DROP, 0.75));
				
			} else {
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.autoLinePath);
				path.calculate(autoLineParams);
				addSequential(new Follow2DPath(path, DriveDirection.FORWARD, getFollowTime(autoLinePathTime)));
			}
			
		} else if(mode.equals(AutoMode.PORTAL_SWITCH_GAMBLE)) {
			
			double switchGamblePathTime = 5.0;
			Profile2DParams switchGambleParams = new Profile2DParams(switchGamblePathTime);
			double autoLinePathTime = 3.0;
			Profile2DParams autoLineParams = new Profile2DParams(autoLinePathTime);
			
			if(switchLocation.equals("L") && pos.equals(StartingPosition.PORTAL_LEFT)) {
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.leftPortalSwitchPath);
				path.calculate(switchGambleParams);
				addSequential(new Follow2DPath(path, DriveDirection.FORWARD, getFollowTime(switchGamblePathTime)));
				addSequential(new SpitSlow(Constants.CUBETAKE_SPIT_TIME));
				
			} else if(switchLocation.equals("R") && pos.equals(StartingPosition.PORTAL_RIGHT)) {
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.rightPortalSwitchPath);
				path.calculate(switchGambleParams);
				addSequential(new Follow2DPath(path, DriveDirection.FORWARD, getFollowTime(switchGamblePathTime)));
				addSequential(new SpitSlow(Constants.CUBETAKE_SPIT_TIME));
				
			} else {
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.autoLinePath);
				path.calculate(autoLineParams);
				addSequential(new Follow2DPath(path, DriveDirection.FORWARD, getFollowTime(autoLinePathTime)));
			}
			
		} else if(mode.equals(AutoMode.COMBO_GAMBLE)) {
			
			double scaleGamblePathTime = 5.0;
			Profile2DParams scaleGamblePathParams = new Profile2DParams(scaleGamblePathTime);
			double switchGamblePathTime = 6.0;
			Profile2DParams switchGamblePathParams = new Profile2DParams(scaleGamblePathTime);
			double autoLinePathTime = 3.0;
			Profile2DParams autoLineParams = new Profile2DParams(autoLinePathTime);
			
			if(scaleLocation.equals("L") && pos.equals(StartingPosition.PORTAL_LEFT)) {
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.leftPortalLeftScale);
				path.calculate(scaleGamblePathParams);
				addSequential(new Follow2DPath(path, DriveDirection.FORWARD, getFollowTime(scaleGamblePathTime)));
				addSequential(new TimedLift(ElevatorAction.LIFT, 1.0));
				addSequential(new ActuateArms());
				addSequential(new SpitSlow(Constants.CUBETAKE_SPIT_TIME));
				addSequential(new RetractArms());
				addSequential(new TimedLift(ElevatorAction.DROP, 0.75));
				
			} else if(scaleLocation.equals("R") && pos.equals(StartingPosition.PORTAL_RIGHT)) {
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.rightPortalRightScale);
				path.calculate(scaleGamblePathParams);
				addSequential(new Follow2DPath(path, DriveDirection.FORWARD, getFollowTime(scaleGamblePathTime)));
				addSequential(new TimedLift(ElevatorAction.LIFT, 1.0));
				addSequential(new ActuateArms());
				addSequential(new SpitSlow(Constants.CUBETAKE_SPIT_TIME));
				addSequential(new RetractArms());
				addSequential(new TimedLift(ElevatorAction.DROP, 0.75));
				
			} else if(switchLocation.equals("L") && pos.equals(StartingPosition.PORTAL_LEFT)) {
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.leftPortalLeftSwitch);
				path.calculate(switchGamblePathParams);
				addSequential(new Follow2DPath(path, DriveDirection.FORWARD, getFollowTime(switchGamblePathTime)));
				addSequential(new SpitSlow(Constants.CUBETAKE_SPIT_TIME));
				addSequential(new TimedDrive(-0.40, 0.50));
				addSequential(new ActuateArms());
				addSequential(new TimedDrive(0.40, 0.50));
				addSequential(new RetractArms());
				addSequential(new SpitSlow(Constants.CUBETAKE_SPIT_TIME));
				
			} else if(switchLocation.equals("R") && pos.equals(StartingPosition.PORTAL_RIGHT)) {
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.rightPortalRightSwitch);
				path.calculate(switchGamblePathParams);
				addSequential(new Follow2DPath(path, DriveDirection.FORWARD, getFollowTime(switchGamblePathTime)));
				addSequential(new SpitSlow(Constants.CUBETAKE_SPIT_TIME));
				addSequential(new TimedDrive(-0.40, 0.50));
				addSequential(new ActuateArms());
				addSequential(new TimedDrive(0.40, 0.50));
				addSequential(new RetractArms());
				addSequential(new SpitSlow(Constants.CUBETAKE_SPIT_TIME));
				
			} else {
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.autoLinePath);
				path.calculate(autoLineParams);
				addSequential(new Follow2DPath(path, DriveDirection.FORWARD, autoLinePathTime + Constants.PROFILE_TIME_ADJ));
			}
			
		} else if(mode.equals(AutoMode.AUTO_RUN)) {
			
			double autoLinePathTime = 3.0;
			Profile2DParams autoLineParams = new Profile2DParams(autoLinePathTime);
			
			FalconPathPlanner path = new FalconPathPlanner(AutoPaths.autoLinePath);
			path.calculate(autoLineParams);
			addSequential(new Follow2DPath(path, DriveDirection.FORWARD, autoLinePathTime + Constants.PROFILE_TIME_ADJ));

		} else { SmartDashboard.putString("ERROR:","No auto mode selected!"); }
	}
	
	private double getFollowTime(double pathTime) { return pathTime + Constants.PROFILE_TIME_ADJ; }
}
