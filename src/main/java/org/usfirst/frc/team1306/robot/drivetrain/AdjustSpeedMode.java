package org.usfirst.frc.team1306.robot.drivetrain;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * @AdjustSpeedMode
 * 
 * Adjusts the speed-mode of the drivetrain so we can have faster modes (for driving across the field)
 * and slower modes (for placing power cubes).  Basically fast for speed, slow for precision.
 * This is done through the use of a multiplier on the 'percentoutput' speeds.
 * 
 * @author Jackson Goth
 */
public class AdjustSpeedMode extends CommandBase {

	private Speed speed; //Speed-mode the drivetrain will be adjusted to.
	
	public AdjustSpeedMode(Speed s) {
		speed = s;
	}
	
	@Override
	protected void initialize() {
		drivetrain.adjust(speed);
	}

	@Override
	protected boolean isFinished() {
		return true;
	}
	
	public enum Speed {SLOW,FAST};
}
