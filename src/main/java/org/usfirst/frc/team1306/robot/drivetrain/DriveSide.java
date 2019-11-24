package org.usfirst.frc.team1306.robot.drivetrain;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.IMotorControllerEnhanced;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;

import org.usfirst.frc.team1306.lib.util.PIDParameters;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @DriveSide
 * 
 *            This object contains one side of the drivetrain (either left or
 *            right), and it's purpose is to make adding/removing motor
 *            controllers from the drivetrain subsystem much faster/easier in
 *            code. To adjust the number of motor controllers all you have to do
 *            is change a few lines of code in CommandBase.java where the
 *            drivetrain settings are initialized. To make code in the
 *            drivetrain subsystem simpler, the methods of this class are very
 *            similar to that of a motor controller with methods like
 *            'set(motorSpeed)' and 'changeControlMode(controlMode)'.
 * 
 * @author Jackson Goth
 */
public class DriveSide {

	SpeedControllerGroup controller;
	BaseMotorController master;
	/**
	 * Initializes the new DriveSide using a given array filled with this side's
	 * corresponding controllers
	 * @throws Exception
	 */
	public DriveSide(ArrayList<BaseMotorController> c)  {

		if (c.size() > 1) {
			master = c.get(0);
			SpeedController[] controllers = new SpeedController[c.size() - 1];
			for (int i = 1; i < c.size(); i++) {
				controllers[i - 1] = (SpeedController) c.get(i);
			}
			controller = new SpeedControllerGroup((SpeedController) c.get(0), controllers);
		} 
	}

	/** Has this side of the drivetrain spin at the given speed */
	public void set(ControlMode mode, double speed) {
		try {
			controller.set(speed);
		} catch (Exception e) {
			SmartDashboard.putString("ERROR:", "Drivetrain setting controller to invalid controlmode");
		}
	}

	/** Initializes the drivetrain encoders */
	public void initEncoders() {
		try {
			master.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
			master.setSensorPhase(false);
			master.configNominalOutputForward(0, 0);
			master.configNominalOutputReverse(0, 0);
			master.configPeakOutputForward(1, 0);
			master.configPeakOutputReverse(-1, 0);
			((IMotorControllerEnhanced)(master)).getSensorCollection().setQuadraturePosition(0, 0);
		} catch (Exception e) {
			SmartDashboard.putString("ERROR:", "Drivetrain configuring settings on invalid controller");
		}
	}

	public void reverseSensor() {
		master.setSensorPhase(true);
	}

	/** Sets up the PIDF control values */
	public void setPIDParams(PIDParameters params) {
		try {
			master.config_kF(0, params.f, 0);
			master.config_kP(0, params.p, 0);
			master.config_kI(0, params.i, 0);
			master.config_kD(0, params.d, 0);
		} catch (Exception e) {
			SmartDashboard.putString("ERROR:", "Drivetrain configuring settings on invalid controller");
		}
	}

	/** Resets the encoder reading back to zero */
	public void resetEncoderPos() {
		try {
			((IMotorControllerEnhanced)(master)).getSensorCollection().setQuadraturePosition(0, 0);
		} catch (Exception e) {
			SmartDashboard.putString("ERROR:", "Drivetrain unable to reset encoder");
		}
	}

	/** Returns the current position from the encoder */
	public double getEncoderPos() {
		try {
			return ((IMotorControllerEnhanced)(master)).getSensorCollection().getQuadraturePosition();
		} catch (Exception e) {
			SmartDashboard.putString("ERROR:", "Drivetrain unable to access encoder position");
			return 0;
		}
	}

	/** Returns the current velocity from the encoder */
	public double getEncoderVel() {
		try {
			return ((IMotorControllerEnhanced)(master)).getSensorCollection().getQuadratureVelocity();
		} catch (Exception e) {
			SmartDashboard.putString("ERROR:", "Drivetrain unable to access encoder velocity");
			return 0;
		}
	}
}
