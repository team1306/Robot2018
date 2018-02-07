package org.usfirst.frc.team1306.robot.commands.cubetake;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AdvancedDrop extends CommandGroup{
	public AdvancedDrop() {
		addParallel(new SpitCube(2.0));
		addSequential(new CubetakeArmDown());
	}
}
