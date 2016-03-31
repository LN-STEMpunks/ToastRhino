package frc.team3966.toastrhino.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.team3966.toastrhino.RobotModule;
import frc.team3966.toastrhino.util.AppliedFunctions;

/**
 *
 */
public class AutoHighGoal extends Command {

  public static final double convertFactor = Math.pow(10, 9);
  private static double startTime = 0; //dont touch :P
  boolean kicked = false;

  public AutoHighGoal() {
    requires(RobotModule.drive);
    requires(RobotModule.armAim);
    requires(RobotModule.armBallGrab);
    requires(RobotModule.navigation);
  }

  // Called just before this Command runs the first time
  protected void initialize() {
    startTime = System.nanoTime();
    RobotModule.armAim.setHeightAbsolute(20.0);
    RobotModule.drive.doNothing();
    
    kicked = false;
  }

  // Called repeatedly when this Command is scheduled to run
  protected void execute() {
    // Do some stuff
    if (System.nanoTime() < (startTime + (2.3 * convertFactor))) {
      // Over defense
      RobotModule.drive.TankDrive(0.7, 0.7);
    } else if (System.nanoTime() < (startTime + ( 2.7 * convertFactor))) { // Time to stop
      RobotModule.drive.doNothing();
    } else if (System.nanoTime() < (startTime + ( 4.0 * convertFactor))) { // Give autoaim time
      RobotModule.autoAim();
    } else if (System.nanoTime() < (startTime + ( 5.0 * convertFactor))) { // 
      RobotModule.drive.doNothing();
      RobotModule.armBallGrab.grab(1.0);
    } else if (System.nanoTime() < (startTime + ( 6.0 * convertFactor))) {
      RobotModule.armBallGrab.grab(1.0);
      if (kicked == false) {
        RobotModule.kickOut();
        kicked = true;
      }
    } else if (System.nanoTime() > (startTime + ( 10.0 * convertFactor))) {
      RobotModule.drive.doNothing();
      RobotModule.armBallGrab.grab(0.0);
    }
    
  }

  // Make this return true when this Command no longer needs to run execute()
  protected boolean isFinished() {
    return (System.nanoTime() > (startTime + ( 10 * convertFactor)));
  }

  // Called once after isFinished returns true
  protected void end() {
    // Set things to zero.
    RobotModule.drive.doNothing();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  protected void interrupted() {
    RobotModule.drive.doNothing();
  }
}