package org.usfirst.frc.team1306.robot.triggers;

import org.usfirst.frc.team1306.robot.XboxController;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * @DPadPress
 * 
 * A trigger used to start commands when something on the D-Pad is pressed.
 * 
 * @author Finn Voichick
 */
public class DPadPress extends Trigger {

	/** The Xbox controller to check. */
	private final XboxController xbox;
	/** The direction to check for. */
	private final DPadDirection direction;

	/** Constructs a DPadPress Trigger. It is triggered when the given direction is pressed on the given Xbox controller. */
	public DPadPress(XboxController xbox, DPadDirection direction) {
		this.xbox = xbox;
		this.direction = direction;
	}

	/** Find if the given direction is being pressed on the Xbox controller. */
	@Override
	public boolean get() {
		return xbox.getPOV() == direction.getAngle();
	}
}