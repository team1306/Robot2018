package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CubetakeArm extends Subsystem {

	private TalonSRX talon;
	private DigitalInput limitOne;
	private DigitalInput limitTwo;
	
	public CubetakeArm() {
		talon = new TalonSRX(RobotMap.CUBETAKEARM_PORT);
	}

	@Override
	protected void initDefaultCommand() {
		
	}
}
