package org.usfirst.frc2832.Robot_2015.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc2832.Robot_2015.Robot;
import org.usfirst.frc2832.Robot_2015.RobotMap;
import org.usfirst.frc2832.Robot_2015.subsystems.LiftControl;

/**
 *Brings the lift down to the bottom (stopped via limit switch).
 *Also resets encoder values appropriately.
 */
public class  ZeroLift extends Command {

	/**
	 *Brings the lift down to the bottom (stopped via limit switch).
	 *Also resets encoder values appropriately.
	 */
    public ZeroLift() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	LiftControl.moveToPosition(-.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (!RobotMap.forkliftMotor.isRevLimitSwitchClosed())
        {
        	RobotMap.forkliftMotor.setPosition(0);
        	return true;
        }
        else
        {
        	return false;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
