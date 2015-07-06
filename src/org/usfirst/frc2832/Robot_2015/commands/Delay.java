package org.usfirst.frc2832.Robot_2015.commands;

import java.util.ArrayList;

import org.usfirst.frc2832.Robot_2015.commands.auton.Pathable;

import edu.wpi.first.wpilibj.command.Command;

/**
 *Delays code for a given amount of secconds, meant for command groups
 */
public class Delay extends Command implements Instruction, Pathable {
	double startTime;
	double delay = 0;
	
	public Delay(double seconds) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		delay = seconds;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	startTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (System.currentTimeMillis() >= startTime + (delay * 1000))
    	    return true;
    	else
    		return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

	@Override
	public ArrayList<Instruction> getPath() {
		ArrayList<Instruction> arr = new ArrayList<Instruction>();
		arr.add(this);
		return (arr);
	}
}
