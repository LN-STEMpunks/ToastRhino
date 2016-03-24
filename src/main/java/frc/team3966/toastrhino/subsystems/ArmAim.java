package frc.team3966.toastrhino.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.AnalogTrigger;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotMap;
import frc.team3966.toastrhino.RobotModule;

/**
 *
 */
public class ArmAim extends Subsystem {

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  Encoder Aenc;

  //arm "encoder"
  Potentiometer pot = new AnalogPotentiometer(2, 100, 0);
  
  private boolean PIDenabled = true;
  
  boolean AnalogInputs = false;
  AnalogInput HallBackInput;

  PIDController armHeight;

  class armMotor extends VictorSP {
    public armMotor(int channel) {
      super(channel);
    }
    @Override
    public void pidWrite(double output) {
      super.set(output / 300.0);
    }
  }

  VictorSP Amotor = new armMotor(RobotMap.Amotor);

  public ArmAim() {
    try {
      HallBackInput = new AnalogInput(3);
      AnalogInputs = true;
    } catch (UnsatisfiedLinkError e) {
      RobotModule.logger.warn("Hall Effect no Link");;
      AnalogInputs = false;
    }
    Amotor.setInverted(false);
    armHeight = new PIDController(10.0, 0.1, 1.0, Aenc, Amotor);
  }

  public void dash_all() {
    SmartDashboard.putData("Amotor", Amotor);
    if (AnalogInputs) {
      SmartDashboard.putData("Back Hall", HallBackInput);
    }
    SmartDashboard.putNumber("Arm Potentiometer", pot.pidGet());
    if (armHeight != null) SmartDashboard.putData("ArmPID", armHeight);
  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new MySpecialCommand());
  }
  //

  public void setAmotor(double speed) {
    Amotor.set(speed);
  }
  
  public void setHeightRelative(double speed) {
    if (armHeight != null && PIDenabled) armHeight.setSetpoint(speed * 200);
    else if (PIDenabled == false) {
      setAmotor(speed);
    }
  }
  
  public void enablePID(boolean enabled) {
    PIDenabled = enabled;
    if (PIDenabled) {
      if (armHeight != null) armHeight.enable();
    } else {
      if (armHeight != null) armHeight.disable();
    }
  }
}
