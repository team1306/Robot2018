package org.usfirst.frc.team1306.robot.commands.autonomous;

/**
 * @AutoPaths - Place where autonomous two-dimensional robot paths are stored.
 * @author Jackson Goth
 */
public class AutoPaths {

	/** Scale / Switch Dual Autos */
	
	public final static double[][] scaleLeftStartLeft = new double[][] {
		{0,0},
		{120/12,0},
		{198.47/12,-30.4/12},
		{258.47/12,-30.4/12},
	};
	
	public final static double[][] scaleRightStartRight = new double[][] {
		{0,0},
		{120/12,0},
		{198.47/12,30.4/12},
		{258.47/12,30.4/12},
	};
	
	public final static double[][] scaleReverse = new double[][] {
		{0,0},
		{42/12,0},
	};
	
	public final static double[][] secondCubePickup = new double[][] {
		{0,0},
		{16/12,0},
	};
	
	public final static double[][] crossPlatformZone = new double[][] {
		{0,0},
		{140/12,0},
	};
	
	public final static double[][] platformZoneAdj = new double[][] {
		{0,0},
		{6/12,0},
	};
	
	/** Ranking Point Switch Autos */
	
	public final static double[][] switchPathLeft = new double[][]{
		{0,0},
		{20.3/12,0},
		{40.6/12,116/12},
		{144.5/12,115/12},
	};
	
	public final static double[][] switchPathRight = new double[][] {
		{0,0},
		{20.3/12,0},
		{40.6/12,-79/12},
		{153.5/12,-75/12},
	};
	
	public final static double[][] footBack = new double[][] {
		{0,0},
		{12/12,0},
		{24/12,0},
	};
}
