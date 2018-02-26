package org.usfirst.frc.team1306.robot.commands.autonomous;

import org.usfirst.frc.team1306.robot.drivetrain.AutoRotate;
import org.usfirst.frc.team1306.robot.drivetrain.Follow2DPath;
import org.usfirst.frc.team1306.robot.drivetrain.Follow2DPath.DriveDirection;
import org.usfirst.frc.team1306.robot.pathing.FalconPathPlanner;
import org.usfirst.frc.team1306.robot.pathing.Profile2DParams;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ScoreCenterCube extends CommandGroup {

	public ScoreCenterCube(String switchLocation) {
		
		if(switchLocation.equals("L")) {
			
		} else if(switchLocation.equals("R")) {
			
			FalconPathPlanner path = new FalconPathPlanner(AutoPaths.footBack);
			path.calculate(new Profile2DParams(2,0.1,24.5/12));
			
			addSequential(new Follow2DPath(path,DriveDirection.BACKWARDS,2.5));
			addSequential(new AutoRotate(-90));
			addSequential(new Follow2DPath(path,DriveDirection.FORWARD,2.5));
			addSequential(new Follow2DPath(path,DriveDirection.BACKWARDS,2.5));
			addSequential(new AutoRotate(90));
			addSequential(new Follow2DPath(path,DriveDirection.FORWARD,2.5));
		}	
	}
}
