package org.usfirst.frc.team1306.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.commands.SmartDashboardUpdate;
import org.usfirst.frc.team1306.robot.commands.autonomous.AutonomousCommand;
import org.usfirst.frc.team1306.robot.commands.autonomous.AutonomousCommand.AutoMode;

/**
 * @Project_Fwoosh
 * Framework Which Occasionally Offers Significant Help
 * 
 * Basic framework for robot code which will hopefully contain most of the subsystems we will use in the next FRC season. 
 * 
 * @author Jackson Goth
 */
public class Robot extends IterativeRobot {

	private Command autonomousCommand;
	private SendableChooser<Command> chooser = new SendableChooser<>();
	
	/**
	 * This function is run when the robot is first started up and we use it for
	 * sending an autonomous mode selection to the smartdashboard, setting up driver
	 * cameras, and initializing subsystems.
	 */
	@Override
	public void robotInit() {
		
		CommandBase.init(); //Initializes all Subsystems
		//CameraServer.getInstance().startAutomaticCapture("usb",0); //Camera 1
		
		chooser.addObject("Follow Path", new AutonomousCommand(AutoMode.FOLLOW_PATH));
		chooser.addObject("NewAuto", new AutonomousCommand(AutoMode.TESTING));
		chooser.addDefault("Do Nothing", new AutonomousCommand(AutoMode.DO_NOTHING));
		SmartDashboard.putData("Auto mode", chooser);
		
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
		autonomousCommand = chooser.getSelected();

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
	public void testPeriodic() {
		LiveWindow.run();
	}
}
