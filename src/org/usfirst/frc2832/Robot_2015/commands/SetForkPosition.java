package org.usfirst.frc2832.Robot_2015.commands;

import org.usfirst.frc2832.Robot_2015.RobotMap;
import org.usfirst.frc2832.Robot_2015.subsystems.LiftControl;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *Sets the fork position in meters
 */
public class SetForkPosition extends Command implements Instruction{

	double position = 0;
	private long timeoutTime;

    public SetForkPosition(double position) {
    	this.position = position;
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    /*	if(position == 0)
    *		RobotMap.forkliftMotor.enableBrakeMode(false);
    *	else
    *		RobotMap.forkliftMotor.enableBrakeMode(true);
    */	
    	LiftControl.moveToPosition(position * LiftControl.pulsePerMeter);
    	
    	timeoutTime = System.currentTimeMillis() + 2000;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() { 
    	boolean isFinished = (Math.abs(RobotMap.forkliftMotor.getPosition() - position * LiftControl.pulsePerMeter) <= 1000 ||
         		(System.currentTimeMillis() >= timeoutTime && Math.abs(RobotMap.forkliftMotor.getSpeed()) < 50));
    	//SmartDashboard.putNumber("deltaEpsilon", Math.abs(RobotMap.forkliftMotor.getPosition() - position * LiftControl.pulsePerMeter));
    	return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
