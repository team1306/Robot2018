package org.usfirst.frc.team1306.robot.commands.cubetake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @IntakeCube
 * 
 * CommandGroup that puts the carriage in the "down-position", pulls in a 
 * power-cube and then raises the carriage to the carrying position.
 * 
 * @author Jackson Goth
 */
public class IntakeCube extends CommandGroup {

	public IntakeCube() {
		//addSequential(new Elevate(Position.DOWN))
	}
}
