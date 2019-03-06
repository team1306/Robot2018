package org.usfirst.frc.team1306.robot.pathing;

import org.usfirst.frc.team1306.robot.Constants;

/**
 * @Profile2DParams - Parameters needed for FalconPathPlanner paths, time for determining velocity, interval for creating velocity / heading array, and track width for rotating the robot as it drives.
 * @author Jackson Goth
 */
public class Profile2DParams {

	public double time, timeInterval, robotTrackWidth;
	
	public Profile2DParams(double time, double timeInterval, double robotTrackWidth) {
		this.time = time;
		this.timeInterval = timeInterval;
		this.robotTrackWidth = robotTrackWidth;
	}
	
	/** Generally the interval and track width are constant, so makes creating param objects easier for most paths */
	public Profile2DParams(double time) {
		this.time = time;
		this.timeInterval = Constants.PROFILE_STEP_TIME;
		this.robotTrackWidth = Constants.TRACK_WIDTH_FT;
	}
}
