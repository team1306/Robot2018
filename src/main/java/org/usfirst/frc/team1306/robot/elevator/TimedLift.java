package org.usfirst.frc.team1306.robot.elevator;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.Timer;

/**
 * @TimedLift - Lift or drop the elevator for a given amount of time.
 * @author Jackson Goth
 */
public class TimedLift extends CommandBase {

	private Timer timer;
	private ElevatorAction action;
	private double time;
	
	public TimedLift(ElevatorAction a, double t) {
		requires(elevator);
		timer = new Timer();
		action = a;
		time = t;
	}
	
	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
		elevator.unbrake();
	}

	@Override
	protected void execute() {
		
		if(action.equals(ElevatorAction.LIFT)) {
			elevator.unbrake();
			elevator.movePercentOutput(Constants.ELEVATOR_POUTPUT_UP);
		} else {
			elevator.unbrake();
			elevator.movePercentOutput(Constants.ELEVATOR_POUTPUT_DOWN);
		}
	}

	@Override
	protected boolean isFinished() {
		if(timer.hasPeriodPassed(time)) {
			elevator.stop();
			elevator.brake();
			return true;
		} else { return false; }
	}
	
	public enum ElevatorAction {LIFT, DROP};
}
