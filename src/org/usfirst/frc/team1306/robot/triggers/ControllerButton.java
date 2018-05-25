package org.usfirst.frc.team1306.robot.triggers;

/**
 * @ControllerButton - An enum containing evey button on an XboxController with the value it can be accessed at.
 * @author Jackson Goth, Mateo Silver
 */
public enum ControllerButton {

	A(1),B(2),X(3),Y(4),LB(5),RB(6),BACK(7),START(8),LS(9),RS(10),XBOX(13);
	
	public int value;
	
	private ControllerButton(int v) {
		value = v;
	}
}
