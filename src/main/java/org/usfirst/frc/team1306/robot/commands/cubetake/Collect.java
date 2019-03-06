package org.usfirst.frc.team1306.robot.commands.cubetake;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.Controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.triggers.ControllerButton;
import edu.wpi.first.wpilibj.Timer;

/**
 * @Collect - Command to spin the intake wheels inward; while-held or timed.
 * @author Ethan Dong
 */
public class Collect extends CommandBase{
	
	private Timer timer;
	private boolean timed = false; //Whether the command is timed or not.
	private double time; //Time the command will run if timed.
	
	public Collect(double t) { //Command must be timed.
		requires(cubetake);
		timer = new Timer();
		timed = true;
		time = t;
	}
	
	
	public Collect() { //Command must be while-held.
		requires(cubetake);
		timed = false; 
	}
	
	@Override
	protected void initialize() {
		if(timed) {
			timer.reset();
			timer.start();
		}
	}

	@Override
	protected void execute() {
		cubetake.intake();
	}

	@Override
	protected boolean isFinished() {
		if(OI.getButtonStatus(Controller.P,ControllerButton.X)) {
			return false;
		} else if(timed) { //If timed, check if time has passed.
			if(timer.hasPeriodPassed(time)) { cubetake.stop(); return true; }
			else { return false; }
		} else { cubetake.stop(); return true; }
	}
}
