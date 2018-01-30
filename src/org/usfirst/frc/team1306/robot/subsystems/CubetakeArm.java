package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CubetakeArm extends Subsystem {

	private TalonSRX talon;
	private DigitalInput limittop;
	private DigitalInput limitbottom;
	
	public CubetakeArm() {
		talon = new TalonSRX(RobotMap.CUBETAKEARM_PORT);
		limittop = new DigitalInput(RobotMap.CUBETAKEARM_LIMITTOP);
		limitbottom = new DigitalInput(RobotMap.CUBETAKEARM_LIMITBOTTOM);
	}
	
	public void ArmUp() {
		if (limittop.get() == true) {
			talon.set(ControlMode.PercentOutput, 0);
		}
		talon.set(ControlMode.PercentOutput, 0.2);
	}
	
	public void ArmDown() {
		if (limitbottom.get() == true) {
			talon.set(ControlMode.PercentOutput, 0);
		}
		talon.set(ControlMode.PercentOutput, -0.2);
	}

	@Override
	protected void initDefaultCommand() {
		
	}
}
