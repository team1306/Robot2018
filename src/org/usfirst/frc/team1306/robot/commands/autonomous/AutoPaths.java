package org.usfirst.frc.team1306.robot.commands.autonomous;

/**
 * @AutoPaths - Place where autonomous two-dimensional robot paths are stored.
 * @author Jackson Goth
 */
public class AutoPaths {

	/** Ranking Point Switch Autos */
	
	public final static double[][] switchPathLeft = new double[][]{
		{0,0},
		{33.83/12,0},
		{67.66/12,65/12},
		{101.5/12,65/12},
	};
	
	public final static double[][] switchPathRight = new double[][] {
		{0,0},
		{33.83/12,0},
		{67.66/12,-55/12},
		{101.5/12,-55/12},
	};
}
