package org.usfirst.frc.team1306.lib.util;

import java.util.ArrayList;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SolenoidBase;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @PrimitiveSubsystem
 * 
 * This subsystem's purpose is meant to speed up the process of creating and modifying very simple subsystems that use speed-controllers
 * like the TalonSR or Spark. This will hopefully condense a few very simple subsystems down into one common class with the same functionality.
 * 
 * @author Jackson Goth
 */
public class PrimitiveSubsystem extends Subsystem {

	public double motorSpeed;
	private Command defaultCommand;
	private ArrayList<PWMSpeedController> controllers;
	private ArrayList<SolenoidBase> pneumatics;
	private boolean enabled = true;
	private String mechanism;
	
	public PrimitiveSubsystem(String name) {
		controllers = new ArrayList<PWMSpeedController>();
		pneumatics = new ArrayList<SolenoidBase>();
		defaultCommand = null;
		mechanism = name;
		motorSpeed = 0.0;
	}
	
	public PrimitiveSubsystem(Command dCommand, String name) {
		controllers = new ArrayList<PWMSpeedController>();
		pneumatics = new ArrayList<SolenoidBase>();
		defaultCommand = dCommand;
		mechanism = name;
		motorSpeed = 0.0;
	}
	
	/** Sets the speed the motor or motors should run at */
	public void setMotorSpeed(double speed) {
		if(enabled) {
			motorSpeed = speed;
		}
	}
	
	/** Adds a single solenoid on a specified channel */
	public void addSolenoid(int channel) {
		pneumatics.add(new Solenoid(channel));
	}
	
	/** Adds a double solenoid on the specified channels */
	public void addDoubleSolenoid(int forwardChannel, int reverseChannel) {
		pneumatics.add(new DoubleSolenoid(forwardChannel,reverseChannel));
	}
	
	/** Adds the appropriate type of speed-controller to the list of motors this subsystem can access */
	public void addSpeedController(SpeedController controllerType, int port) {
		try {
			if(controllerType.equals(SpeedController.TALON_SR)) {
				controllers.add(new Talon(port));
			} else if(controllerType.equals(SpeedController.SPARK)) {
				controllers.add(new Spark(port));
			}
		} catch(Exception e) {
			SmartDashboard.putString("ERROR:",mechanism + " is trying to re-use an existing or non-existent port");
		}
		
	}
	
	/** Pushes out all pneumatics whether they're running off single or double solenoids*/
	public void pushOut() {
		for(int i = 0; i < pneumatics.size(); i++) {
			if(pneumatics.get(i) instanceof Solenoid && enabled) {
				((Solenoid) pneumatics.get(i)).set(true);
			} else if (pneumatics.get(i) instanceof DoubleSolenoid && enabled) {
				((DoubleSolenoid) pneumatics.get(i)).set(DoubleSolenoid.Value.kForward);
			}
		}
	}
	
	/** Pulls in or disables all pneumatics whether they're running off single or double solenoids*/
	public void pullIn() {
		for(int i = 0; i < pneumatics.size(); i++) {
			if(pneumatics.get(i) instanceof Solenoid && enabled) {
				((Solenoid) pneumatics.get(i)).set(false);
			} else if (pneumatics.get(i) instanceof DoubleSolenoid && enabled) {
				((DoubleSolenoid) pneumatics.get(i)).set(DoubleSolenoid.Value.kReverse);
			}
		}
	}
	
	/** Spins all motors at a given speed */
	public void spinAllMotors(double speed) {
		for(int i = 0; i < controllers.size(); i++) {
			controllers.get(i).set(speed);
		}
	}
	
	/** Spins a specified motor at a given speed */
	public void spinMotor(int motor, double speed) {
		if(enabled) {
			try {
				controllers.get(motor).set(speed);
			} catch(Exception e) { 
				SmartDashboard.putString("ERROR:",mechanism + " is trying to use a non-existent motor...");
			}
		}
	}
	
	/** Stops all motors */
	public void stopAll() {
		for(int i = 0; i < controllers.size(); i++) {
			controllers.get(i).set(0.0);
		}
	}
	
	/** Disable the subsystem */
	public void disable() {
		enabled = false;
	}
	
	/** Sets the default command if one was provided */
	@Override
	protected void initDefaultCommand() {
		if(defaultCommand != null) {
			setDefaultCommand(defaultCommand);
		}
	}
	
	public enum SpeedController {TALON_SR,VICTOR_SPX,SPARK};
}
