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
	private double time, prevOutput;
	private boolean decelerate;
	
	public TimedLift(ElevatorAction a, double t) {
		timer = new Timer();
		action = a;
		time = t;
	}
	
	@Override
	protected void initialize() {
		timer.reset();
		timer.start();
		prevOutput = 0.0;
		decelerate = false;
	}

	@Override
	protected void execute() {
		double output;
		if(!decelerate) {
			if(action.equals(ElevatorAction.LIFT)) { 
				output = Constants.ELEVATOR_POUTPUT_UP;
				if(output <= prevOutput - Constants.ELEVATOR_RAMP_RATE) { output = prevOutput - Constants.ELEVATOR_RAMP_RATE; }
				else { output = Constants.ELEVATOR_POUTPUT_UP; }
			}
			else {
				output = Constants.ELEVATOR_POUTPUT_DOWN; 
				if(output >= prevOutput + Constants.ELEVATOR_RAMP_RATE) { output = prevOutput + Constants.ELEVATOR_RAMP_RATE; }
				else { output = Constants.ELEVATOR_POUTPUT_DOWN; }
			}
			prevOutput = output;
			elevator.movePercentOutput(output);
		} else if(prevOutput < -Constants.ELEVATOR_MINIMUM_OUTPUT) {
			prevOutput += Constants.ELEVATOR_RAMP_RATE;
			elevator.movePercentOutput(prevOutput);
		} else if(prevOutput > Constants.ELEVATOR_MINIMUM_OUTPUT) {
			prevOutput -= Constants.ELEVATOR_RAMP_RATE;
			elevator.movePercentOutput(prevOutput);
		} else {
			elevator.stop();
			elevator.brake();
		}
	}

	@Override
	protected boolean isFinished() {
		if(timer.hasPeriodPassed(time - Constants.ELEVATOR_DECELERATE_TIME)) {
			decelerate = true;
			return false;
		} else if(timer.hasPeriodPassed(time)) {
			elevator.stop();
			elevator.brake();
			return true;
		} else { return false; }
	}
	
	public enum ElevatorAction {LIFT, DROP};
}
