package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.cubetake.IntakeCube;
import org.usfirst.frc.team1306.robot.commands.cubetake.ScoreCube;
import org.usfirst.frc.team1306.robot.commands.cubetake.Spit;
import org.usfirst.frc.team1306.robot.drivetrain.AutoRotate;
import org.usfirst.frc.team1306.robot.drivetrain.Follow2DPath;
import org.usfirst.frc.team1306.robot.drivetrain.Follow2DPath.DriveDirection;
import org.usfirst.frc.team1306.robot.drivetrain.FollowPath;
import org.usfirst.frc.team1306.robot.drivetrain.Skid;
import org.usfirst.frc.team1306.robot.drivetrain.Skid.SkidSide;
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

	public enum AutoMode {PLACE_SWITCH_SPLIT, PLACE_BOTH_LEFT, PLACE_BOTH_RIGHT, PLACE_SWITCH_STRAIGHT, AUTO_RUN, DO_NOTHING};
	public enum StartingPosition {PORTAL_LEFT, PORTAL_RIGHT, EXCHANGE_LEFT, EXCHANGE_RIGHT};
	public enum Plate {SWITCH_CLOSE, SWITCH_FAR, SCALE_CLOSE, SCALE_FAR}; //Note: close and far refers to our switch and whether we own the closer side of our starting position

	public AutonomousCommand(AutoMode mode, StartingPosition pos) {

		String gameMessage = DriverStation.getInstance().getGameSpecificMessage();
		while(gameMessage.length() < 3) { gameMessage = DriverStation.getInstance().getGameSpecificMessage(); }
		String switchLocation = gameMessage.substring(0,1);
		String scaleLocation = gameMessage.substring(1,2);
//		SmartDashboard.putString("SecretMessage:",gameMessage);
//		SmartDashboard.putString("SwitchLocation:",switchLocation);
//		SmartDashboard.putString("ScaleLocation:",scaleLocation);
		
		Profile2DParams params = new Profile2DParams(Constants.AUTO_PROFILE_TIME,Constants.PROFILE_STEP_TIME,Constants.TRACK_WIDTH/12); //Max profile time, time in-between steps, and track width in feet

		if(mode.equals(AutoMode.PLACE_SWITCH_SPLIT)) {
			
			if(switchLocation.equals("L")) {
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.switchPathLeft);
				path.calculate(params);
		
				addSequential(new Follow2DPath(path,DriveDirection.FORWARD,Constants.AUTO_PROFILE_TIME + 0.25));
				addSequential(new Spit(2));
			} else {
				FalconPathPlanner path = new FalconPathPlanner(AutoPaths.switchPathRight);
				path.calculate(params);
				
				addSequential(new Follow2DPath(path,DriveDirection.FORWARD,Constants.AUTO_PROFILE_TIME + 0.25));
				addSequential(new Spit(2));
			}
			
		} else if(mode.equals(AutoMode.PLACE_BOTH_LEFT)) { 
			if(scaleLocation.equals("L")) {
				FalconPathPlanner scalePath = new FalconPathPlanner(AutoPaths.scaleLeftStartLeft);
				FalconPathPlanner scaleReversePath = new FalconPathPlanner(AutoPaths.scaleReverse);
				FalconPathPlanner secondCubePath = new FalconPathPlanner(AutoPaths.secondCubePickup);
				scalePath.calculate(params);
				scaleReversePath.calculate(new Profile2DParams(2,Constants.PROFILE_STEP_TIME,Constants.TRACK_WIDTH/12));
				secondCubePath.calculate(new Profile2DParams(1,Constants.PROFILE_STEP_TIME,Constants.TRACK_WIDTH/12));
				
				addSequential(new Follow2DPath(scalePath,DriveDirection.FORWARD,Constants.AUTO_PROFILE_TIME + 0.1));
				addSequential(new ScoreCube());
				addSequential(new Follow2DPath(scaleReversePath,DriveDirection.BACKWARDS,2.1));
				addSequential(new AutoRotate(150));
				addParallel(new IntakeCube());
				addSequential(new Follow2DPath(secondCubePath,DriveDirection.FORWARD,1.1));
				
				if(switchLocation.equals("L")) {
					addSequential(new Skid(SkidSide.RIGHT,25));
					addSequential(new ScoreCube());
				} else {
					FalconPathPlanner platformZonePath = new FalconPathPlanner(AutoPaths.crossPlatformZone);
					FalconPathPlanner platformAdjPath = new FalconPathPlanner(AutoPaths.platformZoneAdj);
					platformZonePath.calculate(new Profile2DParams(2,Constants.PROFILE_STEP_TIME,Constants.TRACK_WIDTH/12));
					platformAdjPath.calculate(new Profile2DParams(1,Constants.PROFILE_STEP_TIME,Constants.TRACK_WIDTH/12));
					
					addSequential(new Follow2DPath(secondCubePath,DriveDirection.BACKWARDS,1.1));
					addSequential(new AutoRotate(-60));
					addSequential(new Follow2DPath(platformZonePath,DriveDirection.FORWARD,2.1));
					addSequential(new AutoRotate(90));
					addSequential(new Follow2DPath(platformAdjPath,DriveDirection.FORWARD,1.1));
					addSequential(new ScoreCube());
				}
			} else if(switchLocation.equals("L")) {
				FalconPathPlanner switchPath = new FalconPathPlanner(AutoPaths.switchLeftStartLeft);
				FalconPathPlanner footBack = new FalconPathPlanner(AutoPaths.footBack);
				FalconPathPlanner switchBackUp = new FalconPathPlanner(AutoPaths.switchBackUp);
				FalconPathPlanner switchLeftScaleRight = new FalconPathPlanner(AutoPaths.switchLeftScaleRight);
				switchPath.calculate(params);
				footBack.calculate(new Profile2DParams(1,Constants.PROFILE_STEP_TIME,1.1));
				switchBackUp.calculate(new Profile2DParams(2,Constants.PROFILE_STEP_TIME,Constants.TRACK_WIDTH/12));
				switchLeftScaleRight.calculate(params);
				
				addSequential(new Follow2DPath(switchPath,DriveDirection.FORWARD,Constants.AUTO_PROFILE_TIME + 0.1));
				addSequential(new ScoreCube());
				addSequential(new Follow2DPath(footBack,DriveDirection.BACKWARDS,1.1));
				addParallel(new IntakeCube());
				addSequential(new Follow2DPath(footBack,DriveDirection.FORWARD,1.1));
				addSequential(new Follow2DPath(switchBackUp,DriveDirection.BACKWARDS,2.1));
				addSequential(new AutoRotate(-25.55));
				addSequential(new Follow2DPath(switchLeftScaleRight,DriveDirection.FORWARD,Constants.AUTO_PROFILE_TIME + 0.1));
				addSequential(new ScoreCube());
			} else {
				FalconPathPlanner scalePath = new FalconPathPlanner(AutoPaths.scaleRightStartLeft);
				FalconPathPlanner scaleReversePath = new FalconPathPlanner(AutoPaths.scaleReverse);
				FalconPathPlanner secondCubePickup = new FalconPathPlanner(AutoPaths.secondCubePickup);
				scalePath.calculate(new Profile2DParams(8,Constants.PROFILE_STEP_TIME,Constants.TRACK_WIDTH/12));
				scaleReversePath.calculate(new Profile2DParams(2,Constants.PROFILE_STEP_TIME,Constants.TRACK_WIDTH/12));
				secondCubePickup.calculate(new Profile2DParams(1,Constants.PROFILE_STEP_TIME,Constants.TRACK_WIDTH/12));
				
				addSequential(new Follow2DPath(scalePath,DriveDirection.FORWARD,8.1));
				addSequential(new ScoreCube());
				addSequential(new Follow2DPath(scaleReversePath,DriveDirection.BACKWARDS,2.1));
				addSequential(new AutoRotate(-150));
				addParallel(new IntakeCube());
				addSequential(new Follow2DPath(secondCubePickup,DriveDirection.FORWARD,1.1));
				addSequential(new Skid(SkidSide.LEFT,-25));
				addSequential(new ScoreCube());
			}
			
		} else if(mode.equals(AutoMode.PLACE_BOTH_RIGHT)) {
			
			if(scaleLocation.equals("R")) {
				FalconPathPlanner scalePath = new FalconPathPlanner(AutoPaths.scaleRightStartRight);
				FalconPathPlanner scaleReversePath = new FalconPathPlanner(AutoPaths.scaleReverse);
				FalconPathPlanner secondCubePickup = new FalconPathPlanner(AutoPaths.secondCubePickup);
				scalePath.calculate(params);
				scaleReversePath.calculate(new Profile2DParams(2,Constants.PROFILE_STEP_TIME,Constants.TRACK_WIDTH/12));
				secondCubePickup.calculate(new Profile2DParams(1,Constants.PROFILE_STEP_TIME,Constants.TRACK_WIDTH/12));
				
				addSequential(new Follow2DPath(scalePath,DriveDirection.FORWARD,Constants.AUTO_PROFILE_TIME + 0.1));
				addSequential(new ScoreCube());
				addSequential(new Follow2DPath(scaleReversePath,DriveDirection.BACKWARDS,2.1));
				addSequential(new AutoRotate(-150));
				addParallel(new IntakeCube());
				addSequential(new Follow2DPath(secondCubePickup,DriveDirection.FORWARD,1.1));
				
				
				if(switchLocation.equals("L")) {
					FalconPathPlanner platformZonePath = new FalconPathPlanner(AutoPaths.crossPlatformZone);
					FalconPathPlanner platformAdjPath = new FalconPathPlanner(AutoPaths.platformZoneAdj);
					platformZonePath.calculate(new Profile2DParams(2,Constants.PROFILE_STEP_TIME,Constants.TRACK_WIDTH/12));
					platformAdjPath.calculate(new Profile2DParams(1,Constants.PROFILE_STEP_TIME,Constants.TRACK_WIDTH/12));
					
					addSequential(new Follow2DPath(secondCubePickup,DriveDirection.BACKWARDS,1.1));
					addSequential(new AutoRotate(60));
					addSequential(new Follow2DPath(platformZonePath,DriveDirection.FORWARD,2.1));
					addSequential(new AutoRotate(-90));
					addSequential(new Follow2DPath(platformAdjPath,DriveDirection.FORWARD,1.1));
					addSequential(new ScoreCube());
				} else {
					addSequential(new Skid(SkidSide.LEFT,-25));
					addSequential(new ScoreCube());
				}
			} else if(switchLocation.equals("R")) {
				FalconPathPlanner switchPath = new FalconPathPlanner(AutoPaths.switchRightStartRight);
				FalconPathPlanner footBack = new FalconPathPlanner(AutoPaths.footBack);
				FalconPathPlanner switchBackUp = new FalconPathPlanner(AutoPaths.switchBackUp);
				FalconPathPlanner switchRightScaleLeft = new FalconPathPlanner(AutoPaths.switchRightScaleLeft);
				switchPath.calculate(params);
				footBack.calculate(new Profile2DParams(1,Constants.PROFILE_STEP_TIME,1.1));
				switchBackUp.calculate(new Profile2DParams(2,Constants.PROFILE_STEP_TIME,Constants.TRACK_WIDTH/12));
				switchRightScaleLeft.calculate(params);
				
				addSequential(new Follow2DPath(switchPath,DriveDirection.FORWARD,Constants.AUTO_PROFILE_TIME + 0.1));
				addSequential(new ScoreCube());
				addSequential(new Follow2DPath(footBack,DriveDirection.BACKWARDS,1.1));
				addParallel(new IntakeCube());
				addSequential(new Follow2DPath(footBack,DriveDirection.FORWARD,1.1));
				addSequential(new Follow2DPath(switchBackUp,DriveDirection.BACKWARDS,2.1));
				addSequential(new AutoRotate(25.55));
				addSequential(new Follow2DPath(switchRightScaleLeft,DriveDirection.FORWARD,Constants.AUTO_PROFILE_TIME + 0.1));
				addSequential(new ScoreCube());
			} else {
				FalconPathPlanner scalePath = new FalconPathPlanner(AutoPaths.scaleLeftStartRight);
				FalconPathPlanner scaleReversePath = new FalconPathPlanner(AutoPaths.scaleReverse);
				FalconPathPlanner secondCubePickup = new FalconPathPlanner(AutoPaths.secondCubePickup);
				scalePath.calculate(new Profile2DParams(8,Constants.PROFILE_STEP_TIME,Constants.TRACK_WIDTH/12));
				scaleReversePath.calculate(new Profile2DParams(2,Constants.PROFILE_STEP_TIME,Constants.TRACK_WIDTH/12));
				secondCubePickup.calculate(new Profile2DParams(1,Constants.PROFILE_STEP_TIME,Constants.TRACK_WIDTH/12));
				
				addSequential(new Follow2DPath(scalePath,DriveDirection.FORWARD,8.1));
				addSequential(new ScoreCube());
				addSequential(new Follow2DPath(scaleReversePath,DriveDirection.BACKWARDS,2.1));
				addSequential(new AutoRotate(150));
				addParallel(new IntakeCube());
				addSequential(new Follow2DPath(secondCubePickup,DriveDirection.FORWARD,1.1));
				addSequential(new Skid(SkidSide.RIGHT,25));
				addSequential(new ScoreCube());
			}
			
		} else if(mode.equals(AutoMode.PLACE_SWITCH_STRAIGHT)) {
			if(switchLocation.equals("L") ) {
				
			} else {
				
			}
			
		} else if(mode.equals(AutoMode.AUTO_RUN)) {
			
			addSequential(new FollowPath(new Profile(120,40,60,120,4.75))); //Distance, Velocity, Accel, Jerk, Max Time
			
		} else { SmartDashboard.putString("ERROR:","No auto mode selected!"); }
	}
}
