package org.usfirst.frc2832.Robot_2015.commands.auton;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

import org.usfirst.frc2832.Robot_2015.FieldLocation;
import org.usfirst.frc2832.Robot_2015.Robot;
import org.usfirst.frc2832.Robot_2015.commands.AutonomousMovePath;
import org.usfirst.frc2832.Robot_2015.commands.Instruction;

/**
 *Autonomous command; moves to the autozone.
 */
public class  Auton_AutoZone extends Command implements Pathable {
	/**
	 *Autonomous command; moves to the autozone.
	 */
    public Auton_AutoZone() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Scheduler.getInstance().add(new AutonomousMovePath(getPath()));
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
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
		ArrayList<Instruction> path = new ArrayList<>();
    	
		if((int)Robot.oi.startPos.getSelected() != 6)
		{
			path.add(Robot.startPosition);
    		path.add(Robot.endPosition);
		}
		else
		{
			path.add(new FieldLocation(0, 0, 180));
			path.add(new FieldLocation(-2.5, 0, 180));
		}
    	
    	return (path);
	}
}

