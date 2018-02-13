package org.usfirst.frc.team1306.robot.commands.cubetake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @ScoreCube
 * 
 * CommandGroup that handles all tasks involved in deploying a cube; running the motor, 
 * dropping the cubetake arms, and then raising the cubetake arms again.
 * 
 * @author Ethan Dong and Jackson Goth
 */
public class ScoreCube extends CommandGroup {
	
	public ScoreCube() {
		addParallel(new Spit(2));
		addSequential(new ActuateArms());
		addSequential(new RetractArms(),1);
	}
}
