package org.usfirst.frc.team1306.robot.commands;

import java.util.ArrayList;
import org.usfirst.frc.team1306.robot.OI;
import org.usfirst.frc.team1306.robot.RobotMap;
import org.usfirst.frc.team1306.robot.drivetrain.Settings;
import org.usfirst.frc.team1306.robot.drivetrain.Settings.ControllingType;
import org.usfirst.frc.team1306.robot.drivetrain.Settings.DriveMode;
import org.usfirst.frc.team1306.robot.subsystems.Cubetake;
import org.usfirst.frc.team1306.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1306.robot.subsystems.Elevator;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Command;

/**
 * @CommandBase
 * 
 * This class is the abstract for all other commands. This static class contains instances of all the subsystems and the OI class 
 * so that each command that extends this class can have access to the subsystems.
 * 
 * @authors Jackson Goth, Sam Roquitte, and Ethan Dong
 */
public abstract class CommandBase extends Command {

	private static Settings driveConfig;
	protected static Cubetake cubetake;
	protected static Drivetrain drivetrain;
	protected static Elevator elevator;
	protected static OI oi;
	
	public static void init() {
		
		/* Drivetrain configuration which tells the subsystem how many Talon SRXs are present, if encoders and gyro are present, and what driving mode the driver wants */
		driveConfig = new Settings();
		driveConfig.add(new TalonSRX(RobotMap.LEFT_DRIVETRAIN_TALON),ControllingType.LEFT_MASTER);
		driveConfig.add(new TalonSRX(RobotMap.RIGHT_DRIVETRAIN_TALON),ControllingType.RIGHT_MASTER);
		driveConfig.add(new VictorSPX(RobotMap.LEFT_DRIVETRAIN_VICTOR),ControllingType.LEFT_SLAVE);
		driveConfig.add(new VictorSPX(RobotMap.RIGHT_DRIVETRAIN_VICTOR),ControllingType.RIGHT_SLAVE);
		driveConfig.addEncoders(true);
		driveConfig.setDriveMode(DriveMode.ARCADE);
		drivetrain = new Drivetrain(driveConfig);
		
		ArrayList<BaseMotorController> elevatorMotors = new ArrayList<BaseMotorController>();
		elevatorMotors.add(new TalonSRX(RobotMap.ELEVATOR_TALON));
		elevator = new Elevator(elevatorMotors);
		
		cubetake = new Cubetake();
		
		oi = new OI(); //OI is always initialized last
	}
}
