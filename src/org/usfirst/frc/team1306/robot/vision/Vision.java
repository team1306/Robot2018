package org.usfirst.frc.team1306.robot.vision;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @Vision
 * 
 * Object used for connecting to JeVois and pulling data from it. We use the JeVoisInterface written by 
 * Team 1736 Robot Casserole for this, so credit goes to them.
 * 
 * @author Jackson Goth
 */
public class Vision {

	private JeVoisInterface cam;
	
	public Vision() {
		cam = new JeVoisInterface(true);
	}
	
	/** Gets whether the cube is visible or not */
	public boolean isVisible() {
		try { return cam.isTgtVisible(); }
		catch(Exception e) { SmartDashboard.putString("ERROR:","Cannot get cube visibility data"); }
		return false;
	}
	
	/** Gets the angle from camera to the power cube (degrees) (Negative --> Center --> Positive) */
	public double getAngle() {
		try { return cam.getTgtAngle_Deg(); }
		catch(Exception e) { SmartDashboard.putString("ERROR:","Cannot get cube angle"); }
		return 0;
	}
	
	/** Gets the distance from camera to power cube (in) */
	public double getRange() {
		try { return cam.getTgtRange_in(); }
		catch(Exception e) { SmartDashboard.putString("ERROR:","Cannot get cube range"); }
		return 0;
	}
}
