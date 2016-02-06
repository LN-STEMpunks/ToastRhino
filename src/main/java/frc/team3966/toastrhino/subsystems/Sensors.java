package frc.team3966.toastrhino.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotMap;

/**
 *
 */
public class Sensors extends Subsystem {

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static BuiltInAccelerometer accel = new BuiltInAccelerometer();
  public static AnalogGyro gyro = new AnalogGyro(RobotMap.gyroport);
  public static AnalogInput gripper_pos = new AnalogInput(1);
  public static PowerDistributionPanel PDP = new PowerDistributionPanel();

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new MySpecialCommand());
  }

  public void dash_accel() {
    SmartDashboard.putData("InternalAccelerometer", accel);
  }

  public void dash_gyro() {
    SmartDashboard.putData("Analog 0 Gyro", gyro);
  }
  
  public void dash_voltage() {
    
  }

  public void dash_all() {
    dash_accel();
    dash_gyro();
  }
}
