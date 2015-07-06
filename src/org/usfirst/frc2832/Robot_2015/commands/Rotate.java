package org.usfirst.frc2832.Robot_2015.commands;

import org.usfirst.frc2832.Robot_2015.RobotMap;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Command for rotating the robot.
 */
public class  Rotate extends Command {

	private double angle;
	private double startingAngle;
	private double rotateSpeed;
	public PIDController pid;
	/**
	 * Rotates the robot by a given degree value, relatively.
	 * @param degrees The amount of degrees to turn. Positive is clockwise
	 * @param rotateSpeed The % of maximum speed at which to turn (between 0 and 1)
	 */
    public Rotate(double degrees, double requestedRotateSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

    	angle = degrees;
    	rotateSpeed = requestedRotateSpeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	pid = new PIDController(0.1, 0.1, 0, RobotMap.imu, new TurnController(), 0.01);
    	
    	startingAngle = RobotMap.imu.getYaw();
    	double deltaAngle = angle + startingAngle;
//    	deltaAngle %= 360;
    	while (deltaAngle >= 180)
    		deltaAngle -= 360;
    	while (deltaAngle < -180)
    		deltaAngle += 360;
    	Preferences.getInstance().putDouble("YawSetpoint", deltaAngle);
    	
    	pid.setAbsoluteTolerance(2);
    	pid.setInputRange(-180, 180);
    	pid.setContinuous(true);
    	pid.setOutputRange(-1 * rotateSpeed, 1 * rotateSpeed);
    	pid.setSetpoint(deltaAngle);
    	pid.enable();
    	//SmartDashboard.putData("PID", pid);
    
    } 

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	//SmartDashboard.putData("PID", pid);	
    	//Make SmartDashboard readable over time
    	pid.setPID(Preferences.getInstance().getDouble("R.P", pid.getP()), 
    			Preferences.getInstance().getDouble("R.I", pid.getI()),
    			Preferences.getInstance().getDouble("R.D", pid.getD()));
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if(pid.onTarget())
        {
        	pid.disable();
        	return true;
        }
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	pid.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	pid.disable();
    }
}
