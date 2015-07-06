package org.usfirst.frc2832.Robot_2015.commands;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc2832.Robot_2015.Robot;
import org.usfirst.frc2832.Robot_2015.RobotMap;

/**
 * Command to tell the rollers to begin spinning in a direction.
 */
public class  SpinRollers extends Command implements Instruction {
	
    private double speed = 0;
	private boolean isLOut;
	private boolean isROut;
	private double maxSpeed = Preferences.getInstance().getDouble("RollerSpeed", 0.75);
	
	private long endTime;
    
	/**
	 * Command to tell the rollers to begin spinning in a direction.
	 * @param isForward if true, spins forward. If false, spins backwards.
	 */
    public SpinRollers(boolean isExpel) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.rollers);
    	if (isExpel) {
    		isLOut = true;
    		isROut = true;
    	} else {
    		isLOut = false;
    		isROut = false;
    	}
    	
    	
    	endTime = System.currentTimeMillis() + 100;
    }
    
    public SpinRollers(boolean isOutL, boolean isOutR, int timeout) {
    	isLOut = isOutL;
    	isROut = isOutR;
    	endTime = System.currentTimeMillis() + timeout;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(isLOut)
    		RobotMap.leftIngestor.set(1.0 * Preferences.getInstance().getDouble("RollerSpeed", 1));
    	else
    		RobotMap.leftIngestor.set(-1.0 * Preferences.getInstance().getDouble("RollerSpeed", 1));
    	if(isROut)
    		RobotMap.rightIngestor.set(-1.0 * Preferences.getInstance().getDouble("RollerSpeed", 1));
    	else
    		RobotMap.rightIngestor.set(1.0 * Preferences.getInstance().getDouble("RollerSpeed", 1));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (System.currentTimeMillis() >= endTime);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
