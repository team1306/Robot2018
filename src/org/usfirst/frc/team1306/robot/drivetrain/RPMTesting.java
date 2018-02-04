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
		
		double leftSpeed = 250;
		double rightSpeed = 250;
		
		double conversion = ((256 * 4) / 600) * 2;
		
		leftSpeed *= conversion;
		rightSpeed *= conversion;
		drivetrain.driveVelocity(leftSpeed,rightSpeed);
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
