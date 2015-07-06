package org.usfirst.frc2832.Robot_2015.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc2832.Robot_2015.Robot;
import org.usfirst.frc2832.Robot_2015.RobotMap;
import org.usfirst.frc2832.Robot_2015.TrajectoryController;
import org.usfirst.frc2832.Robot_2015.subsystems.LiftControl;

/**
 *Lowers the fork to a specific level.
 */
public class  LowerOneLevel extends Command {
	
	private TrajectoryController trajectory;
	private long timeoutTime;
	private double target = 0;
	/**
	 * Lowers the fork to a specific level.
	 * @param level The level to go to; <i>not</i> in meters, 
	 * but rather in "levels" (i.e. totes)
	 */
    public LowerOneLevel() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timeoutTime = System.currentTimeMillis() + 1000;
    	
    	RobotMap.forkliftMotor.enableControl();
    	target = LiftControl.moveLevels(-1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//if we hit a limit switch, stop moving and change our value of zero/max.
    	/*if(!RobotMap.forkliftMotor.isRevLimitSwitchClosed())
    	{
    		RobotMap.forkliftMotor.setPosition( 0 );	// This is now zero
    		LiftControl.zeroValue = RobotMap.forkliftMotor.getPosition();
    		target = RobotMap.forkliftMotor.getPosition();
    		LiftControl.setMaxValue();
    	}*/
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Math.abs(RobotMap.forkliftMotor.getPosition() - target) <= 100 ||
        		(System.currentTimeMillis() >= timeoutTime && Math.abs(RobotMap.forkliftMotor.getSpeed()) < 50));
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
