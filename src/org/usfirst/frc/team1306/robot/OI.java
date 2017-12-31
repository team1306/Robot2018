package org.usfirst.frc.team1306.robot;

import org.usfirst.frc.team1306.lib.util.PrimCommandParams;
import org.usfirst.frc.team1306.lib.util.PrimCommandParams.CommandType;
import org.usfirst.frc.team1306.lib.util.PrimCommandParams.FinishedType;
import org.usfirst.frc.team1306.robot.commands.CommandBase;
import org.usfirst.frc.team1306.robot.commands.FireGamePiece;
import org.usfirst.frc.team1306.robot.commands.PrimitiveCommand;
import org.usfirst.frc.team1306.robot.triggers.ControllerButton;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * @OI
 * 
 * This class is the glue that binds the controls on the physical operator interface to the commands and command groups that allow control of the robot.
 * It is also where commands can get joystick/trigger readings and set the rumble on the controller.
 * 
 * @author Jackson Goth and Sam Roquitte
 */
public class OI {
	
	//Declare primary and secondary xbox controllers
	private static XboxController primaryController = null;
	private static XboxController secondaryController = null;
	
	public OI() {
		
		//Declare ports of xbox controllers
		primaryController = new XboxController(RobotMap.PRIMARY_PORT);
		secondaryController = new XboxController(RobotMap.SECONDARY_PORT);
		
		//Declares and maps buttons to xbox controller buttons for primary controller
		Button pbuttonA = new JoystickButton(primaryController, ControllerButton.A.value);
		Button pbuttonB = new JoystickButton(primaryController, ControllerButton.B.value);
		Button pbuttonX = new JoystickButton(primaryController, ControllerButton.X.value);
		Button pbuttonY = new JoystickButton(primaryController, ControllerButton.Y.value);
//		Button pbuttonRB = new JoystickButton(primaryController, ControllerButton.RB.value);
		Button pbuttonLB = new JoystickButton(primaryController, ControllerButton.LB.value); 
//		Button pbuttonStart = new JoystickButton(primaryController, ControllerButton.START.value);
//		Button pbuttonBack = new JoystickButton(primaryController, ControllerButton.BACK.value);
//		Button primaryDPadUp = new DPadPress(primaryController, DPadDirection.UP);
//		Button primaryDPadRight = new DPadPress(primaryController, DPadDirection.RIGHT);
//		Button primaryDPadLeft = new DPadPress(primaryController, DPadDirection.LEFT);
//		Button primaryDPadDown = new DPadPress(primaryController, DPadDirection.DOWN);
		
		//Declares and maps buttons to xbox controller buttons for secondary controller
//		Button sbuttonA = new JoystickButton(secondaryController, ControllerButton.A.value);
//		Button sbuttonB = new JoystickButton(secondaryController, ControllerButton.B.value);
//		Button sbuttonX = new JoystickButton(secondaryController, ControllerButton.X.value);
//		Button sbuttonY = new JoystickButton(secondaryController, ControllerButton.Y.value);
//		Button sbuttonRB = new JoystickButton(secondaryController, ControllerButton.RB.value);
//		Button sbuttonLB = new JoystickButton(secondaryController, ControllerButton.LB.value);
//		Button sbuttonStart = new JoystickButton(secondaryController,ControllerButton.START.value);
//		Button sbuttonBack = new JoystickButton(secondaryController, ControllerButton.BACK.value);
//		Button secondaryDPadUp = new DPadPress(secondaryControlsler, DPadDirection.UP);
//		Button secondaryDPadRight = new DPadPress(secondaryController, DPadDirection.RIGHT);
//		Button secondaryDPadLeft = new DPadPress(secondaryController, DPadDirection.LEFT);
//		Button secondaryDPadDown = new DPadPress(secondaryController, DPadDirection.DOWN);
		
		pbuttonA.whenPressed(new FireGamePiece());
		pbuttonB.whenPressed(new PrimitiveCommand(CommandBase.geartake, new PrimCommandParams(CommandType.PUSH,FinishedType.INSTANT)));
		pbuttonY.whenPressed(new PrimitiveCommand(CommandBase.geartake, new PrimCommandParams(CommandType.PULL,FinishedType.INSTANT)));
		pbuttonX.toggleWhenPressed(new PrimitiveCommand(CommandBase.intake, new PrimCommandParams(CommandType.SPIN,FinishedType.TOGGLED)));
	
		pbuttonLB.toggleWhenPressed(new PrimitiveCommand(CommandBase.geartake, new PrimCommandParams(CommandType.SPIN,FinishedType.TOGGLED)));
	}
	
	public enum Controller {P,S}; //Controller (primary or secondary)
	public enum Joystick {L,R}; //Joystick (left or right)
	public enum Axis {X,Y}; //Joystick axis (x or y)
	
	public enum Trigger {L,R}; //Trigger (left or right)
	
	public enum Side {L,R}; //Side (left or right) (for rumble)
	
	/** Returns the joystick value (from -1.0 to 1.0) for a specified controller's joystick's axis (uses deadband) */
	public static double getJoyVal(Controller c, Joystick j, Axis a) {
		XboxController controller;
		Hand side;
		
		if(c.equals(Controller.P)) { controller = primaryController; }
		else { controller = secondaryController; }
		if(j.equals(Joystick.L)) { side = Hand.kLeft; }
		else { side = Hand.kRight; }
		
		if(a.equals(Axis.X)) {
			return Math.pow(deadband(controller.getXAxis(side)),Constants.JOYSTICK_MULTIPLIER);
		} else {
			return Math.pow(deadband(controller.getYAxis(side)),Constants.JOYSTICK_MULTIPLIER);
		}
	}
	
	/** Returns the value of the specified trigger (from 0.0 to 1.0) */
	public static double getTriggerVal(Controller c, Trigger t) {
		XboxController controller;
		if(c.equals(Controller.P)) { controller = primaryController; }
		else { controller = secondaryController; }
		
		if(t.equals(Trigger.L)) {
			return controller.getLT();
		} else {
			return controller.getRT();
		}
	}
	
	/** Returns the value of a specified button on a specified controller */
	public static boolean getButtonStatus(Controller c, ControllerButton b) {
		XboxController controller;
		if(c.equals(Controller.P)) { controller = primaryController; }
		else { controller = secondaryController; }
		
		return controller.getRawButton(b.value);
	}
	
	/** Sets the rumble of a specified controller to a specified amount of rumble */
	public static void setRumble(Controller c, Side s, double rumbleness) {
		XboxController controller;
		if(c.equals(Controller.P)) { controller = primaryController; }
		else { controller = secondaryController; }
		
		if(s.equals(Side.L)) {
			controller.setRumble(GenericHID.RumbleType.kLeftRumble, rumbleness);
		} else {
			controller.setRumble(GenericHID.RumbleType.kRightRumble, rumbleness);
		}
	}
	
	/** Resets the rumble on the specified side of a specified controller */
	public static void resetRumble(Controller c, Side s) {
		XboxController controller;
		if(c.equals(Controller.P)) { controller = primaryController; }
		else { controller = secondaryController; }
		
		if(s.equals(Side.L)) {
			controller.setRumble(GenericHID.RumbleType.kLeftRumble, 0);
		} else {
			controller.setRumble(GenericHID.RumbleType.kRightRumble, 0);
		}
	}
	
	/** Applies deadband to joystick values to prevent false readings when joystick is idle. */
	private static double deadband(double value) {
		if (value < -Constants.DEADBAND) {
			return (value + Constants.DEADBAND) / (1.0 - Constants.DEADBAND);
		} else if (value > Constants.DEADBAND) {
			return (value - Constants.DEADBAND) / (1.0 - Constants.DEADBAND);
		} else { return 0; }
	}
}