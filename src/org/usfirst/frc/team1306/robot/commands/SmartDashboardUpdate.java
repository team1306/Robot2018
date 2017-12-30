package org.usfirst.frc.team1306.robot.commands;

import org.usfirst.frc.team1306.robot.Constants;
import org.usfirst.frc.team1306.robot.subsystems.Drivetrain.Side;
import org.usfirst.frc.team1306.robot.subsystems.Gyro.Axis;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @SmartDashboardUpdate
 * 
 * Updates the SmartDashboard with debug info for each subsystem if it's requested.
 * 
 * @author Jackson Goth
 */
public class SmartDashboardUpdate extends CommandBase {
	
	public SmartDashboardUpdate() {
		setRunWhenDisabled(true); //Useful to run at all times
	}
	
	@Override
	protected void execute() {
		
		if(Constants.GYRO_DEBUG) {
			SmartDashboard.putNumber("Gyro-X",drivetrain.gyro.getDisplacement(Axis.X));
			SmartDashboard.putNumber("Gyro-Y",drivetrain.gyro.getDisplacement(Axis.Y));
			SmartDashboard.putNumber("Gyro-Z",drivetrain.gyro.getDisplacement(Axis.Z));
			SmartDashboard.putNumber("Gyro-Yaw",drivetrain.gyro.getAngle());
		}
		
		if(Constants.DRIVETRAIN_DEBUG) {
			SmartDashboard.putNumber("LeftSide-Position:",drivetrain.getEncoderPos(Side.LEFT));
			SmartDashboard.putNumber("RightSide-Position:",drivetrain.getEncoderPos(Side.RIGHT));
			SmartDashboard.putNumber("LeftSide-AdjustPos:",Math.abs(drivetrain.getEncoderPos(Side.LEFT)/1024)*12.5663);
			SmartDashboard.putNumber("RightSide-AdjustPos:",Math.abs(drivetrain.getEncoderPos(Side.RIGHT)/1024)*12.5663);
			SmartDashboard.putNumber("LeftSide-Velocity:",drivetrain.getEncoderVel(Side.LEFT));
			SmartDashboard.putNumber("RightSide-Velocity:",drivetrain.getEncoderVel(Side.RIGHT));
		}
	}

	@Override
	protected boolean isFinished() {
		return false; //Should never end
	}
}