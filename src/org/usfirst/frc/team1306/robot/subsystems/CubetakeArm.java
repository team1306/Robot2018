package org.usfirst.frc.team1306.robot.subsystems;

import org.usfirst.frc.team1306.robot.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CubetakeArm extends Subsystem {

	private DoubleSolenoid solenoid;
	
	public CubetakeArm() {
		solenoid = new DoubleSolenoid(RobotMap.CUBETAKE_CHANNEL_ONE_PORT, RobotMap.CUBETAKE_CHANNEL_TWO_PORT);
	}
	
	public void CubetakeArmUp() {
		solenoid.set(DoubleSolenoid.Value.kReverse);
	}

	public void CubetakeArmDown() {
		solenoid.set(DoubleSolenoid.Value.kForward);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
}
