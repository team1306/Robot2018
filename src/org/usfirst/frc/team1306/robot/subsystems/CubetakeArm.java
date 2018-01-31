package org.usfirst.frc.team1306.robot.subsystems;
import org.usfirst.frc.team1306.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CubetakeArm extends Subsystem {

	private TalonSRX talon;
	private DoubleSolenoid solenoid;
	
	public CubetakeArm() {
		talon = new TalonSRX(RobotMap.CUBETAKEARM_PORT);
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
