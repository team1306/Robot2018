package org.usfirst.frc.team1306.robot.drivetrain;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

public class RPMTesting extends CommandBase{

	private Timer timer;
	
	
	public RPMTesting() {
		timer = new Timer();
	}
	
	@Override
	protected void initialize() {
		
		timer.reset();
		timer.start();
	}

	@Override
	protected void execute() {
		drivetrain.driveVelocity(213*4, 213*4);
	}

	@Override
	protected boolean isFinished() {
		if(timer.hasPeriodPassed(10)) {
			drivetrain.stop();
			return true;
		}
		return false;
	}	
}
