package frc.team3966.toastrhino.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Navigation extends Subsystem {

  private static AHRS navx;
  private static boolean warned = false;

  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new MySpecialCommand());
    SmartDashboard.putBoolean("NavX Online", false);
  }

  public void initNavX() {
    try {
      /* Communicate w/navX-MXP via the MXP SPI Bus.                                     */
      /* Alternatively:  I2C.Port.kMXP, SerialPort.Port.kMXP or SerialPort.Port.kUSB     */
      /* See http://navx-mxp.kauailabs.com/guidance/selecting-an-interface/ for details. */
      navx = new AHRS(SPI.Port.kMXP);
      navx.resetDisplacement();
      navx.reset();
      navx.zeroYaw();
      SmartDashboard.putBoolean("NavX Online", true);
    } catch (UnsatisfiedLinkError ex ) {
      SmartDashboard.putBoolean("NavX Online", false);
    } catch (Exception ex ) {
      SmartDashboard.putBoolean("NavX Online", false);
      DriverStation.reportError("Error instantiating navX-MXP:  " + ex.getMessage(), true);
    }
  }

  private void warn() {
    if (!warned) {
      DriverStation.reportError("NavX NOT ONLINE!", false);
      warned = true;
    }
  }

  public boolean isMoving() {
    if (navx != null) return navx.isMoving();
    else {
      this.warn();
      return false;
    }
  }

  public double getAngle() {
    if (navx != null) return navx.getAngle();
    else {
      this.warn();
      return 180;
    }
  }
  public double getYaw() {
    if (navx != null) return navx.getYaw();
    else {
      this.warn();
      return 0.0;
    }
  }

  public double getRoll() {
    if (navx != null) return navx.getRoll();
    else {
      this.warn();
      return 0.0;
    }
  }

  public double getPitch() {
    if (navx != null) return navx.getPitch();
    else {
      this.warn();
      return 0.0;
    }
  }

  public double getDisplacementX() {
    if (navx != null) return navx.getDisplacementX();
    else {
      this.warn();
      return 0.0;
    }
  }

  public double getDisplacementY() {
    if (navx != null) return navx.getDisplacementY();
    else {
      this.warn();
      return 0.0;
    }
  }

  public double getDisplacementZ() {
    if (navx != null) return navx.getDisplacementZ();
    else {
      this.warn();
      return 0.0;
    }
  }

  public float getTempC() {
    if (navx != null) return navx.getTempC();
    else {
      this.warn();
      return 25.0f;
    }
  }

  public double getDisplacementTotal() {
    double X = 0.0, Y = 0.0, Z = 0.0;
    X = this.getDisplacementX();
    Y = this.getDisplacementY();
    Z = this.getDisplacementZ();

    return Math.sqrt(X * X + Y * Y + Z * Z);
  }

}
