package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import frc.team3966.toastrhino.RobotModule;

/**
 *
 */
public class Shoot extends Command {

  NetworkTable GRIPtable;

  public Shoot() {
    requires(RobotModule.arm);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    RobotModule.arm.Smotors((RobotModule.oi.controller.getRawAxis(4) + 1.0) / 2.0);
    RobotModule.arm.Amotor(RobotModule.oi.controller.getRawAxis(2));
    RobotModule.arm.KickMotor(RobotModule.oi.controller.getRawAxis(0));
  }

  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
  }
}
