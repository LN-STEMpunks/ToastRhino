package frc.team3966.toastrhino.subsystems;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotMap;
import frc.team3966.toastrhino.commands.TankDrive;

public class Drive extends Subsystem {

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  // Motor Controllers
  private static VictorSP FRmotor = new VictorSP(RobotMap.FRmotor);
  private static VictorSP BRmotor = new VictorSP(RobotMap.BRmotor);
  private static VictorSP FLmotor = new VictorSP(RobotMap.FLmotor);
  private static VictorSP BLmotor = new VictorSP(RobotMap.BLmotor);

  // Motor encoders
  private static Encoder FRenc = new Encoder(RobotMap.FRencH, RobotMap.FRencL, false, EncodingType.k4X);
  private static Encoder BRenc = new Encoder(RobotMap.BRencH, RobotMap.BRencL, false, EncodingType.k4X);
  private static Encoder FLenc = new Encoder(RobotMap.FLencH, RobotMap.FLencL, false, EncodingType.k4X);
  private static Encoder BLenc = new Encoder(RobotMap.BLencH, RobotMap.BLencL, false, EncodingType.k4X);

  // PID Controllers
  private static PIDController FRctrl = new PIDController(RobotMap.driveP, RobotMap.driveI, RobotMap.driveD, FRenc, FRmotor);
  private static PIDController BRctrl = new PIDController(RobotMap.driveP, RobotMap.driveI, RobotMap.driveD, BRenc, BRmotor);
  private static PIDController FLctrl = new PIDController(RobotMap.driveP, RobotMap.driveI, RobotMap.driveD, FLenc, FLmotor);
  private static PIDController BLctrl = new PIDController(RobotMap.driveP, RobotMap.driveI, RobotMap.driveD, BLenc, BLmotor);

  public Drive () {
    FRmotor.setInverted(false);
    BRmotor.setInverted(false);
    FLmotor.setInverted(true);
    BLmotor.setInverted(true);

    // Not continuous.
    FRctrl.setContinuous(false);
    BRctrl.setContinuous(false);
    FLctrl.setContinuous(false);
    BLctrl.setContinuous(false);

    // Using the max speed as ratio
    FRctrl.setOutputRange(-RobotMap.topspeed, RobotMap.topspeed);
    BRctrl.setOutputRange(-RobotMap.topspeed, RobotMap.topspeed);
    FLctrl.setOutputRange(-RobotMap.topspeed, RobotMap.topspeed);
    BLctrl.setOutputRange(-RobotMap.topspeed, RobotMap.topspeed);

    // Safer to reset before use.
    FRctrl.reset();
    BRctrl.reset();
    FLctrl.reset();
    BLctrl.reset();

    // Only enable the PID controllers if we are using them.
    if (!RobotMap.usePID) {
      FRctrl.disable();
      BRctrl.disable();
      FLctrl.disable();
      BLctrl.disable();
    } else {
      FRctrl.enable();
      BRctrl.enable();
      FLctrl.enable();
      BLctrl.enable();
    }
  }

  public void dash_all() {
    SmartDashboard.putData("Front Left", FLmotor);
    SmartDashboard.putData("Front Right", FRmotor);
    SmartDashboard.putData("Back Left", BLmotor);
    SmartDashboard.putData("Back Right", BRmotor);
    SmartDashboard.putData("Front Left Ctrl", FLctrl);
    SmartDashboard.putData("Front Right Ctrl", FRctrl);
    SmartDashboard.putData("Back Left Ctrl", BLctrl);
    SmartDashboard.putData("Back Right Ctrl", BRctrl);
  }

  public void TankDrive(double rightspeed, double leftspeed) {
    if (!RobotMap.usePID) {
      FRmotor.set(rightspeed);
      BRmotor.set(rightspeed);
      FLmotor.set(leftspeed);
      BLmotor.set(leftspeed);
    } else {
      FRctrl.setSetpoint(rightspeed);
      BRctrl.setSetpoint(rightspeed);
      FLctrl.setSetpoint(leftspeed);
      BLctrl.setSetpoint(leftspeed);
    }

    this.dash_all();
  }

  public void Rotate (double clockwisespeed) {
    FRmotor.set(clockwisespeed);
    BRmotor.set(clockwisespeed);
    FLmotor.set(-clockwisespeed);
    BLmotor.set(-clockwisespeed);

    this.dash_all();
  }

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new TankDrive());
  }

  public void doNothing() {
    FRmotor.set(0.0);
    BRmotor.set(0.0);
    FLmotor.set(0.0);
    BLmotor.set(0.0);
  }
}

