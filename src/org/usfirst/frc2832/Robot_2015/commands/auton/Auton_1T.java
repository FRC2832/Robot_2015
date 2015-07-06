package org.usfirst.frc2832.Robot_2015.commands.auton;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

import org.usfirst.frc2832.Robot_2015.FieldLocation;
import org.usfirst.frc2832.Robot_2015.Robot;
import org.usfirst.frc2832.Robot_2015.commands.AutonomousMovePath;
import org.usfirst.frc2832.Robot_2015.commands.CloseFork;
import org.usfirst.frc2832.Robot_2015.commands.Delay;
import org.usfirst.frc2832.Robot_2015.commands.DropStack;
import org.usfirst.frc2832.Robot_2015.commands.Instruction;
import org.usfirst.frc2832.Robot_2015.commands.PickUpObject;
import org.usfirst.frc2832.Robot_2015.commands.SetForkPosition;
import org.usfirst.frc2832.Robot_2015.commands.DropStack.DropType;
import org.usfirst.frc2832.Robot_2015.subsystems.LiftControl;

/**
 *Autonomous command; grabs the nearest tote and moves to the autozone.
 * 03/18/15	Mr. Zobel  Commented out setting down object and backing up.
 */
public class  Auton_1T extends Command implements Pathable {
	/**
	 *Autonomous command; grabs the nearest tote and moves to the autozone.
	 */
    public Auton_1T() {
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
		ArrayList<Instruction> path = new ArrayList<>();  	
		if((int)Robot.oi.startPos.getSelected() % 2 == 0)
			path.add(Robot.endPosition);
		else
		{
			path.add((Instruction) new CloseFork());
			path.add((Instruction) new Delay(1));
			path.add((Instruction) new SetForkPosition(0.2));
			path.add(Robot.startPosition);
			if((int)Robot.oi.startPos.getSelected() == 1)
				path.add(Robot.midPosition);
			else
				path.add(new FieldLocation(Robot.midPosition.x + Global.distOverBump, Robot.midPosition.y, Robot.midPosition.heading));
		}
    	//path.add(new DropStack(DropType.ground));
    	//path.add(Robot.endPosition);
    	
    	return (path);
	}
}
