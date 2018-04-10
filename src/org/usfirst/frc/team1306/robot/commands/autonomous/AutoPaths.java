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
//		{0,0},
//		{264/12,0},
//		{276/12,194.97/12},
//		{295.47/12,188.97/12},
		{0,0},
		{284/12,0},
		{276/12,203.97/12},
		{293.47/12,290.97/12},
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
//		{0,0},
//		{120/12,0},
//		{198.47/12,30.4/12},
//		{258.47/12,30.4/12},
//		{0,0},
//		{185/12,0},
//		{239.47/12,32.4/12},
//		{274.47/12,38.4/12},
		{0,0},
		{210/12,0},
		{245.47/12,10.4/12},
		{272.47/12,16.4/12}, //280
	};
	
	/** Path backing up from the right scale-plate after placing */
	public final static double[][] rightScaleBackup = new double[][] {
		{0,0},
		{29/12,0},
		{28/12,62.57/12},
	};
	
	/** Path backing up from the left scale-plate after placing */
	public final static double[][] leftScaleBackup = new double[][] {
		{0,0},
		{29/12,0},
		{29/12,-62.57/12},
	};
	
	/** Path that approaches another cube after backing up from the right scale-plate */
	public final static double[][] rightScaleApproachCube = new double[][] {
		{0,0},
		{23.57/12,0},
		{105.61/12,15.47/12},
	};
	
	/** Path that approaches another cube after backing up from the left scale-plate */
	public final static double[][] leftScaleApproachCube = new double[][] {
		{0,0},
		{23.57/12,0},
		{32.61/12,-20.47/12},
	};
	
	/**----------------------------*/
	/** Ranking Point Switch Autos */
	/**----------------------------*/
	
	public final static double[][] switchPathLeft = new double[][]{
		/** Below is the path we had tuned for comp bot at MKE */
		{0,0},
 		{20.3/12,0},
		{40.6/12,83/12},
		{133.5/12,81/12},
	};
	
	public final static double[][] switchPathRight = new double[][] {
		/** Below is the path we had tuned for comp bot at MKE */
//		{0,0},
// 		{20.3/12,0},
//		{40.6/12,-85/12},
//		{138.5/12,-88/12},
		/** Below is the path we had tuned on practice bot before 7R */
//		{0,0},
// 		{20.3/12,0},
//		{40.6/12,-37/12},
//		{128.5/12,-40/12},
		{0,0},
 		{20.3/12,0},
		{40.6/12,-73/12},
		{128.5/12,-76/12},
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
		{167/12,0},
		{167/12,37/12},
	};
	
	/**-------------------------------------------*/
	/** Ranking Point Switch Auto Additonal Paths */
	/**-------------------------------------------*/
	
	/** Path for backing up after we place the first switch cube */
	public final static double[][] backupSwitchPath = new double[][] {
		{0,0},
		{76/12,0},
	};
	
	/** Path for backing up after we place the first switch cube */
	public final static double[][] forwardSwitchPath = new double[][] {
		{0,0},
		{88/12,-20/12},
	};
	
	/** Path for turning left into the stack of cubes after backing up */
	public final static double[][] stackTurnLeftPath = new double[][] {
		{0,0},
		{12.75/12,35/12},
		{65/12,65.25/12},
	};
	
	/** Path that reverses the @stackTurnLeftPath to put us in line with the switch again */
	public final static double[][] reverseStackTurnLeftPath = new double[][] {
		{0,0},
		{6.75/12,0},
		{65/12,-40.25/12},
	};
	
	/** Path for turning right into the stack of cubes after backing up */
	public final static double[][] stackTurnRightPath = new double[][] {
		{0,0},
		{12.75/12,-35/12},
		{70/12,-70.25/12},
	};
	
	/** Path that reverses the @stackTurnRightPath to put us in line with the switch again */
	public final static double[][] reverseStackTurnRightPath = new double[][] {
		{0,0},
		{6.75/12,0},
		{65/12,40.25/12},
	};
	
	/** Path for heading left towards the scale to be ready for tele-op */
	public final static double[][] scaleTurnLeftPath = new double[][] {
		{0,0},
		{30.65/12,0},
		{61.76/12,31.11/12},
		{-96.43/12,189.30/12},
	};
	
	/** Path that 
	
	/** Path for heading right towards the scale to be ready for tele-op */
	public final static double[][] scaleTurnRightPath = new double[][] {
		{0,0},
		{30.65/12,0},
		{61.76/12,-31.11/12},
		{-96.43/12,-189.30/12},
	};
}
