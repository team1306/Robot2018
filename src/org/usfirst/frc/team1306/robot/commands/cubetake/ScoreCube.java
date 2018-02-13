package org.usfirst.frc.team1306.robot.commands.cubetake;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * @ScoreCube
 * 
 * CommandGroup that handles all tasks involved in deploying a cube; running the motor, dropping pneumatics, then raising pneumatics again.
 * 
 * @author Ethan Dong and Jackson Goth
 */
public class ScoreCube extends CommandGroup {
	
	public ScoreCube() {
		addParallel(new SpitCube(2));
		addSequential(new CubetakeArmDown());
		addSequential(new CubetakeArmUp(),1);
	}
}
