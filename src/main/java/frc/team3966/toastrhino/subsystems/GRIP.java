package frc.team3966.toastrhino.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team3966.toastrhino.RobotModule;

/**
 *
 */
public class GRIP extends Subsystem {

  private double lastGoodValueY = 120.0;

  private double lastGoodValueX = 150.0;

  // What is GRIP Table?
  // Init table:
  NetworkTable GRIPtable;
  
  private double[] blank = new double[0];

  public GRIP() {
    GRIPtable = NetworkTable.getTable("GRIP/goal");
    try {
    	GRIPtable.globalDeleteAll();
    } catch (Exception e) {
    	RobotModule.logger.error("Error deleting GRIP network tables: " + e.getMessage());
    }
  }

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new MySpecialCommand());
  }

  public double getCenterY() {
    // Load Grip values from the network table
    double[] GRIPcenterY = GRIPtable.getNumberArray("centerY", blank);
    
    double centerY = 0;
    if (GRIPcenterY.length == 1) {
      centerY = GRIPcenterY[0];
      lastGoodValueY = centerY;
    } else if (GRIPcenterY.length > 1) {
      for (double i : GRIPcenterY) {
        centerY += i;
      }
      centerY /= (double)GRIPcenterY.length;
      lastGoodValueY = centerY;
    } else {
      return lastGoodValueY;
    }
    return centerY; // actualvalue pls
  }
  
  // Uses centerY to calulate value
  public double getDistanceToGoal() { // Returns in inches
    return 0.5808 * getCenterY() + 32.799;
  }
  
  public double getCenterX() {
	    // Load Grip values from the network table
	    double[] GRIPcenterX = GRIPtable.getNumberArray("centerX", blank);
	    double[] GRIParea = GRIPtable.getNumberArray("area", blank);
	    
	    double centerX = 0;
	    if (GRIPcenterX.length == 1) {
	      centerX = GRIPcenterX[0];
        lastGoodValueX = centerX;
	    } else if (GRIPcenterX.length > 1) {
		    int indexUsed = 0;
		    double max = 0;
		    for (int i = 0; i < GRIParea.length; i++) {
		    	if (GRIParea[i] > max) indexUsed = i;
		    }
		    lastGoodValueX = GRIPcenterX[indexUsed];
	      return GRIPcenterX[indexUsed];
	    } else {
	      return lastGoodValueX;
	    }
	    return centerX; // actualvalue pls
	  }

  public void dash_all() {
    SmartDashboard.putNumber("Contour CenterY", getCenterY());
    SmartDashboard.putNumber("Contour CenterX", getCenterX());
  }

}
