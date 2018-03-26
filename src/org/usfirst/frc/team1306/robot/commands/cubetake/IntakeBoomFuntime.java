package org.usfirst.frc.team1306.robot.commands.cubetake;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeBoomFuntime extends CommandGroup {

	public IntakeBoomFuntime() {
		addParallel(new Spit(0.5));
		addSequential(new ActuateArms(),0.25);
		addSequential(new RetractArms(),0.5);
	}

}
