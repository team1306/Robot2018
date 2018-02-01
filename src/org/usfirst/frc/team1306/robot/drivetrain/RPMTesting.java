package org.usfirst.frc.team1306.robot.drivetrain;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

public class RPMTesting extends CommandBase{

	private Timer timer;
	
	
	@Override
	protected void initialize() {
		requires(drivetrain);
		
		timer = new Timer();
		}

	@Override
	protected void execute() {
		drivetrain.driveVelocity(500, 500);
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
