package org.usfirst.frc2832.Robot_2015.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc2832.Robot_2015.Robot;
import org.usfirst.frc2832.Robot_2015.RobotMap;
import org.usfirst.frc2832.Robot_2015.subsystems.LiftControl;

/**
 *Raises the fork. Only finishes when limit switch is tripped 
 *(or force stopped/Robot.lift used)
 */
public class  RaiseFork extends Command {

	private double speed = 0.01;

	/**
	 *Raises the fork. Only finishes when limit switch is tripped 
	 *(or force stopped/Robot.lift used)
	 */
    public RaiseFork() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lift);
    }
    public RaiseFork(double speed)
    {
    	requires(Robot.lift);
    	speed = Math.max(0,Math.min(speed, 0.2));
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	LiftControl.moveToPosition(RobotMap.forkliftMotor.getPosition() - speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//RobotMap.forkliftMotor.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
