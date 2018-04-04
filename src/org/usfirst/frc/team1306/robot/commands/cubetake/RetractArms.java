package org.usfirst.frc.team1306.robot.commands.cubetake;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * @RetractArms - Pull the cubetake arms back into the robot, allowing for the storage of power-cubes.
 * @author Ethan Dong
 */
public class RetractArms extends CommandBase {

	@Override
	protected void execute() {
		cubetake.retract();
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
}
