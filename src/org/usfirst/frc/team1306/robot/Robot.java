package org.usfirst.frc.team1306.robot;

import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.commands.SmartDashboardUpdate;
import org.usfirst.frc.team1306.robot.commands.autonomous.AutonomousCommand;
import org.usfirst.frc.team1306.robot.commands.autonomous.AutonomousCommand.AutoMode;
import org.usfirst.frc.team1306.robot.commands.autonomous.AutonomousCommand.StartingPosition;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @Robot2018 - Robot code running on BadgerBOTs Team 1306's Power UP robot, ?.
 * @authors Jackson Goth, Sam Roquitte, and Ethan Dong
 */
public class Robot extends IterativeRobot {

	private Command autonomousCommand;
	private SendableChooser<AutoMode> type = new SendableChooser<>();
	private SendableChooser<StartingPosition> position = new SendableChooser<>();
	
	/**
	 * This function is run when the robot is first started up and we use it for
	 * sending an autonomous mode selection to the smartdashboard, setting up driver
	 * cameras, and initializing subsystems.
	 */
	@Override
	public void robotInit() {
		
		CommandBase.init(); //Initializes all Subsystems
		//CameraServer.getInstance().startAutomaticCapture("usb",0); //Camera 1
		
		type.addObject("Switch RP", AutoMode.PLACE_SWITCH_SPLIT);
		type.addObject("Scale/Switch Left", AutoMode.PLACE_BOTH_LEFT);
		type.addObject("Scale/Switch Right", AutoMode.PLACE_BOTH_RIGHT);
		type.addObject("Switch Straight", AutoMode.PLACE_SWITCH_STRAIGHT);
		type.addObject("Baseline", AutoMode.AUTO_RUN);
		type.addDefault("Do Nothing", AutoMode.DO_NOTHING);
		
		position.addObject("Left of Exchange", StartingPosition.EXCHANGE_LEFT);
		position.addObject("Right of Exchange", StartingPosition.EXCHANGE_RIGHT);
		position.addObject("Left Portal", StartingPosition.PORTAL_LEFT);
		position.addDefault("Right Portal", StartingPosition.PORTAL_RIGHT);
		
		SmartDashboard.putString("Auto:","Type: " + type + " Location: " + position);
		
		new SmartDashboardUpdate().start();
	}

	/** This function is called continuously while the robot is disabled */
	@Override
	public void disabledPeriodic() {
		SmartDashboard.putString("STATUS:","DISABLED");
		Scheduler.getInstance().run();
	}

	/** This function is called once each time the robot enters autonomous */
	@Override
	public void autonomousInit() {
		autonomousCommand = new AutonomousCommand(type.getSelected(),position.getSelected());
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	/** This function is called continuously during autonomous */
	@Override
	public void autonomousPeriodic() {
		SmartDashboard.putString("STATUS:","AUTONOMOUS");
		Scheduler.getInstance().run();
	}

	/** This function is called once the robot enters the driver controlled period */
	@Override
	public void teleopInit() {
		if (autonomousCommand != null) {
			autonomousCommand.cancel(); //Stops the autonomous command
		}
	}

	/** This function is called continuously while the robot is under operator control */
	@Override
	public void teleopPeriodic() {
		SmartDashboard.putString("STATUS:","ENABLED");
		Scheduler.getInstance().run();
	}

	/** This function is called continuosly during test mode which is started through the driverstation */
	@Override
	public void testPeriodic() { }
}
