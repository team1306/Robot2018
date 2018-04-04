package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.cubetake.ActuateArms;
import org.usfirst.frc.team1306.robot.commands.cubetake.Collect;
import org.usfirst.frc.team1306.robot.commands.cubetake.RetractArms;
import org.usfirst.frc.team1306.robot.commands.cubetake.SpitFast;
import org.usfirst.frc.team1306.robot.commands.cubetake.SpitSlow;
import org.usfirst.frc.team1306.robot.drivetrain.Follow2DPath;
import org.usfirst.frc.team1306.robot.drivetrain.Follow2DPath.DriveDirection;
import org.usfirst.frc.team1306.robot.elevator.TimedLift;
import org.usfirst.frc.team1306.robot.elevator.TimedLift.ElevatorAction;
import org.usfirst.frc.team1306.robot.pathing.FalconPathPlanner;
import org.usfirst.frc.team1306.robot.pathing.Profile2DParams;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
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

	public enum AutoRoutine {CENTER_SWITCH_RP, SCALE_AUTO, PORTAL_SWITCH, AUTO_RUN, DO_NOTHING};
	public enum StartingPosition {PORTAL_LEFT, PORTAL_RIGHT, EXCHANGE_RIGHT};

	public AutonomousCommand(AutoRoutine mode, StartingPosition pos, double delay) {

		String gameMessage = DriverStation.getInstance().getGameSpecificMessage(); //Pulls the game message into a string (ex. LLL or LRL)
		while(gameMessage.length() < 3) { gameMessage = DriverStation.getInstance().getGameSpecificMessage(); } //Loop to ensure we get the full game message and not a portion of it.
		String switchLocation, scaleLocation;
		if(Constants.FMS_TRICK) {
			if(Constants.FMS_TRICK_SWITCH_LOCATION.equals("L") || Constants.FMS_TRICK_SWITCH_LOCATION.equals("R")) {
				switchLocation = Constants.FMS_TRICK_SWITCH_LOCATION;
			} else { switchLocation = gameMessage.substring(0, 1); }
			if(Constants.FMS_TRICK_SCALE_LOCATION.equals("L") || Constants.FMS_TRICK_SCALE_LOCATION.equals("R")) {
				scaleLocation = Constants.FMS_TRICK_SCALE_LOCATION;
			} else { scaleLocation = gameMessage.substring(1, 2);  }
		} else { 
			switchLocation = gameMessage.substring(0, 1); //Extracting which side our switch is on.
			scaleLocation = gameMessage.substring(1, 2); //Extracting which side our scale is on.
		}

		if(delay > 0) { addSequential(new WaitCommand(delay)); }
		
		if(mode.equals(AutoRoutine.CENTER_SWITCH_RP)) {
			
			double switchRPPathTime = 3.125;
			Profile2DParams switchRPPathParams = new Profile2DParams(switchRPPathTime);
			double backupPathTime = 1.25;
			Profile2DParams backupPathParams = new Profile2DParams(backupPathTime);
			double forwardPathTime = 1.875;
			Profile2DParams forwardPathParams = new Profile2DParams(forwardPathTime);
			double pickupPathTime = 2.125;
			Profile2DParams pickupPathParams = new Profile2DParams(pickupPathTime);
			double unpickupPathTime = 2.125;
			Profile2DParams unpickupPathParams = new Profile2DParams(unpickupPathTime);
			
			if(switchLocation.equals("L")) { //If our switch is to the robot's left...
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.switchPathLeft);
				path.calculate(switchRPPathParams); //Calculates the velocity / heading profiles.
				addSequential(new Follow2DPath(path, DriveDirection.FORWARD, getFollowTime(switchRPPathTime)));
				addSequential(new SpitFast(Constants.CUBETAKE_SPIT_TIME));
				
				FalconPathPlanner backupPath = new FalconPathPlanner(AutoPaths.backupSwitchPath);
				backupPath.calculate(backupPathParams);
				FalconPathPlanner pickupPath = new FalconPathPlanner(AutoPaths.stackTurnRightPath);
				pickupPath.calculate(pickupPathParams);
				FalconPathPlanner forwardPath = new FalconPathPlanner(AutoPaths.forwardSwitchPath);
				forwardPath.calculate(forwardPathParams);
				FalconPathPlanner unpickupPath = new FalconPathPlanner(AutoPaths.reverseStackTurnRightPath);
				unpickupPath.calculate(unpickupPathParams);
				
				addSequential(new Follow2DPath(backupPath, DriveDirection.BACKWARDS, getFollowTime(backupPathTime)));
				addSequential(new ActuateArms());
				addSequential(new Follow2DPath(pickupPath, DriveDirection.FORWARD, getFollowTime(pickupPathTime)));
				addSequential(new Collect(Constants.CUBETAKE_COLLECT_TIME));

				addSequential(new Follow2DPath(unpickupPath, DriveDirection.BACKWARDS, getFollowTime(unpickupPathTime)));
				addSequential(new RetractArms());
				addSequential(new Follow2DPath(forwardPath, DriveDirection.FORWARD, getFollowTime(forwardPathTime)));
				addSequential(new SpitFast(Constants.CUBETAKE_SPIT_TIME));
				
			} else { //Our switch must be to our robot's right so...
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.switchPathRight);
				path.calculate(switchRPPathParams); //Calculates the velocity / heading profiles.
				addSequential(new Follow2DPath(path, DriveDirection.FORWARD, getFollowTime(switchRPPathTime)));
				addSequential(new SpitFast(Constants.CUBETAKE_SPIT_TIME));
				
				FalconPathPlanner backupPath = new FalconPathPlanner(AutoPaths.backupSwitchPath);
				backupPath.calculate(backupPathParams);
				FalconPathPlanner pickupPath = new FalconPathPlanner(AutoPaths.stackTurnLeftPath);
				pickupPath.calculate(pickupPathParams);
				FalconPathPlanner forwardPath = new FalconPathPlanner(AutoPaths.forwardSwitchPath);
				forwardPath.calculate(forwardPathParams);
				FalconPathPlanner unpickupPath = new FalconPathPlanner(AutoPaths.reverseStackTurnLeftPath);
				unpickupPath.calculate(unpickupPathParams);
				
				addSequential(new Follow2DPath(backupPath, DriveDirection.BACKWARDS, getFollowTime(backupPathTime)));
				addSequential(new ActuateArms());
				addSequential(new Follow2DPath(pickupPath, DriveDirection.FORWARD, getFollowTime(pickupPathTime)));
				addSequential(new Collect(Constants.CUBETAKE_COLLECT_TIME));

				addSequential(new Follow2DPath(unpickupPath, DriveDirection.BACKWARDS, getFollowTime(unpickupPathTime)));
				addSequential(new RetractArms());
				addSequential(new Follow2DPath(forwardPath, DriveDirection.FORWARD, getFollowTime(forwardPathTime)));
				addSequential(new SpitFast(Constants.CUBETAKE_SPIT_TIME));
				
			}
		} else if(mode.equals(AutoRoutine.SCALE_AUTO)) {
			
			double scaleBackupTime = 2.5;
			Profile2DParams scaleBackupParams = new Profile2DParams(scaleBackupTime);
			double scaleApproachCubeTime = 3.0;
			Profile2DParams scaleApproachCubeParams = new Profile2DParams(scaleApproachCubeTime);
			
			if(scaleLocation.equals("L")) {
				
				double farScalePathTime = 7.0;
				Profile2DParams scalePathParams = new Profile2DParams(farScalePathTime);
				
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.rightPortalLeftScale);
				path.calculate(scalePathParams);
				FalconPathPlanner backupPath = new FalconPathPlanner(AutoPaths.leftScaleBackup);
				backupPath.calculate(scaleBackupParams);
				FalconPathPlanner approachPath = new FalconPathPlanner(AutoPaths.leftScaleApproachCube);
				approachPath.calculate(scaleApproachCubeParams);
				
				addSequential(new Follow2DPath(path, DriveDirection.FORWARD, getFollowTime(farScalePathTime)));
				addSequential(new TimedLift(ElevatorAction.LIFT, 2.1));
				addSequential(new ActuateArms());
				addSequential(new SpitSlow(Constants.CUBETAKE_SPIT_TIME));
				addSequential(new RetractArms());
				addSequential(new TimedLift(ElevatorAction.DROP, 1.0));
				addSequential(new ActuateArms());
				
				addSequential(new Follow2DPath(backupPath, DriveDirection.BACKWARDS, getFollowTime(scaleBackupTime)));
				addSequential(new Follow2DPath(approachPath, DriveDirection.FORWARD, getFollowTime(scaleApproachCubeTime)));
				addSequential(new Collect(Constants.CUBETAKE_COLLECT_TIME));
				
			} else {
				
				double closeScalePathTime = 5.5;
				Profile2DParams scalePathParams = new Profile2DParams(closeScalePathTime);
				
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.rightPortalRightScale);
				path.calculate(scalePathParams);
				FalconPathPlanner backupPath = new FalconPathPlanner(AutoPaths.rightScaleBackup);
				backupPath.calculate(scaleBackupParams);
				FalconPathPlanner approachPath = new FalconPathPlanner(AutoPaths.rightScaleApproachCube);
				approachPath.calculate(scaleApproachCubeParams);
				
				addSequential(new Follow2DPath(path, DriveDirection.FORWARD, getFollowTime(closeScalePathTime)));
				addSequential(new TimedLift(ElevatorAction.LIFT, 2.1));
				addSequential(new ActuateArms());
				addSequential(new SpitSlow(Constants.CUBETAKE_SPIT_TIME));
				addSequential(new RetractArms());
				addSequential(new TimedLift(ElevatorAction.DROP, 1.0));
				addSequential(new ActuateArms());
				
				addSequential(new Follow2DPath(backupPath, DriveDirection.BACKWARDS, getFollowTime(scaleBackupTime)));
				addSequential(new Follow2DPath(approachPath, DriveDirection.FORWARD, getFollowTime(scaleApproachCubeTime)));
				addSequential(new Collect(Constants.CUBETAKE_COLLECT_TIME));
				
			}
		} else if(mode.equals(AutoRoutine.PORTAL_SWITCH)) {
			
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
		} else if(mode.equals(AutoRoutine.AUTO_RUN)) {
			
			double autoLinePathTime = 3.0;
			Profile2DParams autoLineParams = new Profile2DParams(autoLinePathTime);
			
			FalconPathPlanner path = new FalconPathPlanner(AutoPaths.autoLinePath);
			path.calculate(autoLineParams);
			addSequential(new Follow2DPath(path, DriveDirection.FORWARD, autoLinePathTime + Constants.PROFILE_TIME_ADJ));
		} else { SmartDashboard.putString("ERROR:","No auto mode selected!"); }
		
	}
	
	private double getFollowTime(double pathTime) { return pathTime + Constants.PROFILE_TIME_ADJ; }
}
