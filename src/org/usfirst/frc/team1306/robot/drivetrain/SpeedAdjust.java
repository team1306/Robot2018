package org.usfirst.frc.team1306.robot.drivetrain;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

/**
 * @SpeedAdjust
 * 
 * Adjusts the speed of the drivetrain so we can have faster modes (for driving across the field)
 * and slower modes (for placing power cubes).  Basically fast for speed, slow for precision.
 * 
 * @author Jackson Goth
 */
public class SpeedAdjust extends CommandBase {

	private Speed speed; //Speed to adjust to
	
	public SpeedAdjust(Speed s) {
		speed = s;
	}
	
	@Override
	protected void initialize() {
		drivetrain.adjust(speed);
	}

	@Override
	protected boolean isFinished() {
		return true; //Can end immediately
	}
	
	public enum Speed {SLOW,FAST};
}
