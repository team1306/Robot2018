package org.usfirst.frc.team1306.robot.commands.autonomous;

/**
 * @AutoPaths - Place where autonomous two-dimensional robot paths are stored.
 * @author Jackson Goth
 */
public class AutoPaths {

	/**-----------------------------------------------*/
	/** Scale / Switch Gamble Autos - TODO Tune these */
	/**-----------------------------------------------*/
	
	/** Path that places in the corner of the left switch from the left portal */
	public final static double[][] leftPortalLeftSwitch = new double[][] {
		{0,0},
		{40/12,0},
		{140/12,23/12},
		{187/12,23/12},
		{158/12,-37.39/12},
	};
	
	/** Path that places in the corner of the right switch from the right portal */
	public final static double[][] rightPortalRightSwitch = new double[][] {
		{0,0},
		{40/12,0},
		{140/12,-23/12},
		{187/12,-23/12},
		{158/12,37.39/12},
	};
	
	/** Path around the back of the switch to place in the right scale after putting a cube in the left switch */
	public final static double[][] switchLeftScaleRight = new double[][] {
		{0,0},
		{45/12,0},
		{90.797/12,11.40/12},
		{195.138/12,11.40/12},
		{195.138/12,76.406/12},
	};
	
	/** Path around the back of the switch to place in the left scale after putting a cube in the right switch */
	public final static double[][] switchRightScaleLeft = new double[][] {
		{0,0},
		{45/12,0},
		{90.797/12,-11.40/12},
		{195.138/12,-11.40/12},
		{195.138/12,-76.406/12},
	};
	
	/** Path going around the back of the switch to place in the right scale */
	public final static double[][] leftPortalRightScale = new double[][] {
		{0,0},
		{192/12,0},
		{192/12,-201.97/12},
		{258.47/12,-201.97/12},
	};
	
	/** Path going around the back of the switch to place in the left scale */
	public final static double[][] rightPortalLeftScale = new double[][] {
		{0,0},
		{192/12,0},
		{192/12,201.97/12},
		{258.47/12,201.97/12},
	};
	
	/** Fastest scale placment path, for driving straight into our left scale if we own it */
	public final static double[][] leftPortalLeftScale = new double[][] {
		{0,0},
		{120/12,0},
		{198.47/12,-30.4/12},
		{258.47/12,-30.4/12},
	};
	
	/** Fastest scale placment path, for driving straight into our right scale if we own it */
	public final static double[][] rightPortalRightScale = new double[][] {
		{0,0},
		{120/12,0},
		{198.47/12,30.4/12},
		{258.47/12,30.4/12},
	};
	
	/**----------------------------*/
	/** Ranking Point Switch Autos */
	/**----------------------------*/
	
	public final static double[][] switchPathLeft = new double[][]{
		/** Below is the path we had tuned for comp bot at MKE */
		{0,0},
 		{20.3/12,0},
		{40.6/12,89/12},
		{139.5/12,87/12},
	};
	
	public final static double[][] switchPathRight = new double[][] {
		/** Below is the path we had tuned for comp bot at MKE */
		{0,0},
 		{20.3/12,0},
		{40.6/12,-85/12},
		{138.5/12,-88/12},
	};
	
	/** Path that just goes one foot back */
	public final static double[][] footBack = new double[][] {
		{0,0},
		{12/12,0},
	};
	
	/** Path that just crosses the auto line */
	public final static double[][] autoLinePath = new double[][] {
		{0,0},
		{160/12,0},
	};
	
	/** TODO Tune these values **/
	/** Path for placing on your switch if you start at the left portal */
	public final static double[][] leftPortalSwitchPath = new double[][] {
		{0,0},
		{127/12,0},
		{127/12,-39/12},
	};
	
	/** TODO Tune these values **/
	/** Path for placing on your switch if you start at the right portal */
	public final static double[][] rightPortalSwitchPath = new double[][] {
		{0,0},
		{127/12,0},
		{127/12,39/12},
	};
}
