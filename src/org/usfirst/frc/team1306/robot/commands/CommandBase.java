package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.drivetrain.Settings;
import org.usfirst.frc.team1306.robot.drivetrain.Settings.Device;
import org.usfirst.frc.team1306.robot.drivetrain.Settings.DriveMode;
import org.usfirst.frc.team1306.robot.drivetrain.Settings.TalonType;
import org.usfirst.frc.team1306.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1306.robot.subsystems.PrimitiveSubsystem;
import org.usfirst.frc.team1306.robot.subsystems.PrimitiveSubsystem.SpeedController;
import org.usfirst.frc.team1306.robot.subsystems.VelocitySubsystem;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @CommandBase
 * 
 * This class is the abstract for all other commands. This static class contains instances of all the subsystems and the OI class 
 * so that each command that extends this class can have access to the subsystems.
 * 
 * @author Jackson Goth
 */
public abstract class CommandBase extends Command {

	private static Settings driveConfig;
	
	public static PrimitiveSubsystem intake;
	protected static VelocitySubsystem shooter;
	protected static Drivetrain drivetrain;
	protected static OI oi;
	
	public static void init() {
		
		/* Drivetrain configuration which tells the subsystem how many Talon SRXs are present, if encoders and gyro are present, and what driving mode the driver wants */
		driveConfig = new Settings();
		driveConfig.add(new TalonSRX(RobotMap.LEFT_TALON_1_PORT),TalonType.LEFT_MASTER);
		driveConfig.add(new TalonSRX(RobotMap.RIGHT_TALON_1_PORT),TalonType.RIGHT_MASTER);
		driveConfig.add(new TalonSRX(RobotMap.LEFT_TALON_2_PORT),TalonType.LEFT_SLAVE);
		driveConfig.add(new TalonSRX(RobotMap.RIGHT_TALON_2_PORT),TalonType.RIGHT_SLAVE);
		driveConfig.add(Device.ENCODER);
		driveConfig.add(Device.GYRO); 
		driveConfig.setDriveMode(DriveMode.ARCADE);
		drivetrain = new Drivetrain(driveConfig);

		intake = new PrimitiveSubsystem("Intake");
		intake.addSpeedController(SpeedController.SPARK,RobotMap.INTAKE_PORT);
		
		oi = new OI(); //OI is always initialized last
	}
}