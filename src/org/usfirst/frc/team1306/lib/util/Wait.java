package org.usfirst.frc.team1306.lib.util;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/**
 * @Wait - Command that forces a delay onto a CommandGroup, which we can use to potentially avoid colliding with alliance parters during autonomous.
 * @author Jackson Goth
 */
public class Wait extends CommandBase {

	private Timer timer;
	private double time;
	
	public Wait(double t) {
		timer = new Timer();
		time = t;
	}
	
	@Override
	protected boolean isFinished() {
		return timer.hasPeriodPassed(time);
	}
}
