package frc.team3966.toastrhino.subsystems;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Sensors extends Subsystem {

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static BuiltInAccelerometer accel = new BuiltInAccelerometer();

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new MySpecialCommand());
  }

  public void dash_accel () {
    SmartDashboard.putNumber("Accelerometer X", accel.getX());
    SmartDashboard.putNumber("Accelerometer Y", accel.getY());
    SmartDashboard.putNumber("Accelerometer Z", accel.getZ());
  }

  public void dash_all() {
    dash_accel();
  }
}

