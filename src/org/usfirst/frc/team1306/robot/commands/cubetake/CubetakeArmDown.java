package org.usfirst.frc.team1306.robot.commands.cubetake;

import org.usfirst.frc.team1306.robot.commands.CommandBase;

public class CubetakeArmDown extends CommandBase{

	@Override
	protected void execute() {
		intake.CubetakeArmDown();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

}
