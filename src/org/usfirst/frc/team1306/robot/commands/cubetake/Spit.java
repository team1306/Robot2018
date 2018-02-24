package org.usfirst.frc.team1306.robot.commands.cubetake;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.OI.Controller;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.triggers.ControllerButton;

import edu.wpi.first.wpilibj.Timer;

/**
 * @SpitCube - Command to spin the intake wheels outward; toggled or timed.
 * @author Ethan Dong
 */
public class Spit extends CommandBase{
	
	private boolean isTimed;
	private double runTime;
	private Timer timer;
	
	public Spit(double time) { //If double given, spit for this amount of time.
		isTimed = true;
		runTime = time;
		
		timer = new Timer();
	}
	
	public Spit() {
		isTimed = false; //Toggled spit
	}
	
	@Override
	protected void initialize() {
		if (isTimed) {
			timer.reset();
			timer.start();	
		}
	}

	@Override
	protected void execute() {
		cubetake.spit();
	}

	@Override
	protected boolean isFinished() {
		if(OI.getButtonStatus(Controller.P,ControllerButton.X)) {
			return false;
		} else {
			cubetake.stop();
			return true;
		}
//		if(isTimed) {
//			if(timer.hasPeriodPassed(runTime)) {
//				cubetake.stop();
//				return true; //End if timer has passed the runTime
//			} return false;
//		} return false; //Toggled so doesn't need to ever return true
	}
}

