package org.usfirst.frc.team1306.robot.commands.autonomous;

/**
 * @AutoPaths - Place where autonomous two-dimensional robot paths are stored.
 * @author Jackson Goth
 */
public class AutoPaths {

	/** Scale / Switch Dual Autos */
	
	public final static double[][] switchLeftStartLeft = new double[][] {
		{0,0},
		{40/12,0},
		{140/12,23/12},
		{187/12,23/12},
//		{185/12,17/12},
		{158/12,-37.39/12},
	};
	
	public final static double[][] switchRightStartRight = new double[][] {
		{0,0},
		{40/12,0},
		{140/12,-23/12},
		{187/12,-23/12},
//		{185/12,17/12},
		{158/12,37.39/12},
	};
	
	public final static double[][] switchBackUp = new double[][] {
		{0,0},
		{36.288/12,0},
	};
	
	public final static double[][] switchLeftScaleRight = new double[][] {
		{0,0},
		{45/12,0},
		{90.797/12,11.40/12},
		{195.138/12,11.40/12},
		{195.138/12,76.406/12},
	};
	
	public final static double[][] switchRightScaleLeft = new double[][] {
		{0,0},
		{45/12,0},
		{90.797/12,-11.40/12},
		{195.138/12,-11.40/12},
		{195.138/12,-76.406/12},
	};
	
	public final static double[][] scaleRightStartLeft = new double[][] {
		{0,0},
		{192/12,0},
		{192/12,-201.97/12},
		{258.47/12,-201.97/12},
	};
	
	public final static double[][] scaleLeftStartRight = new double[][] {
		{0,0},
		{192/12,0},
		{192/12,201.97/12},
		{258.47/12,201.97/12},
	};
	
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
		{40.6/12,64/12},
		{101.5/12,62/12},
	};
	
	public final static double[][] switchPathRight = new double[][] {
		{0,0},
		{20.3/12,0},
		{40.6/12,-64/12},
		{101.5/12,-62/12},
	};
	
	public final static double[][] footBack = new double[][] {
		{0,0},
		{12/12,0},
		{24/12,0},
	};
}
