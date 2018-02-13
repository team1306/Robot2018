package org.usfirst.frc.team1306.robot.commands.cubetake;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * @ActuateArms - Push the intake arms into the down position, allowing for pick-up of power-cubes.
 * @author Ethan Dong
 */
public class ActuateArms extends CommandBase{

	@Override
	protected void execute() {
		cubetake.actuate();
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
}
