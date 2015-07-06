package org.usfirst.frc2832.Robot_2015.commands.auton;

import java.util.ArrayList;

import org.usfirst.frc2832.Robot_2015.FieldLocation;
import org.usfirst.frc2832.Robot_2015.Robot;
import org.usfirst.frc2832.Robot_2015.commands.AutonomousMovePath;
import org.usfirst.frc2832.Robot_2015.commands.CloseFork;
import org.usfirst.frc2832.Robot_2015.commands.Delay;
import org.usfirst.frc2832.Robot_2015.commands.DropStack;
import org.usfirst.frc2832.Robot_2015.commands.SpinRollers;
import org.usfirst.frc2832.Robot_2015.commands.StopRollers;
import org.usfirst.frc2832.Robot_2015.commands.ToggleIngestors;
import org.usfirst.frc2832.Robot_2015.commands.DropStack.DropType;
import org.usfirst.frc2832.Robot_2015.commands.Instruction;
import org.usfirst.frc2832.Robot_2015.commands.SetForkPosition;
import org.usfirst.frc2832.Robot_2015.subsystems.LiftControl;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *Autonomous command; grabs the nearest tote and bin, turns to grab another tote, and moves to the autozone.
 */
public class  Auton_2T1B extends Command implements Pathable {
	/**
	 *Autonomous command; grabs the nearest tote and bin, turns to grab another tote, and moves to the autozone.
	 */
    public Auton_2T1B() {
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
    	if((int)Robot.oi.startPos.getSelected() % 2 == 1 || (int)Robot.oi.startPos.getSelected() == 0)
    		path.add(Robot.endPosition);
    	else
    	{
    		
    		path.add((Instruction) new CloseFork());
        	path.add((Instruction) new Delay(0.5));
        	path.add((Instruction) new SetForkPosition(LiftControl.metersToLevel[2]));
    		path.add(Robot.startPosition);
    		
    		path.add(new FieldLocation(Robot.startPosition.x, Robot.startPosition.y + Global.moveFwdAfterBin, 90));
    		
    		path.add((Instruction) new DropStack(DropType.tote));
    		path.add((Instruction) new SetForkPosition(0));
    		path.add((Instruction) new CloseFork());
        	path.add((Instruction) new Delay(0.5));
        	path.add((Instruction) new SetForkPosition(LiftControl.metersToLevel[2]));

        	
    		//redefine position constants as if we were one position back
    		FieldLocation startPosition = Robot.startPositions[(int)Robot.oi.startPos.getSelected()-1];
    		FieldLocation midPosition =  new FieldLocation(startPosition.x + Global.distToMidPos, startPosition.y - 0.5,0);
            FieldLocation endPosition = new FieldLocation(startPosition.x + Global.distToEndPos, startPosition.y - 0.5,0);
    		
            path.add(new FieldLocation(startPosition.x, startPosition.y - 0.5, startPosition.heading));
            path.add((Instruction) new ToggleIngestors());
    		path.add((Instruction) new SpinRollers(false));
    		path.add((Instruction) new Delay(0.25));
    		path.add((Instruction) new StopRollers());
  
    		path.add(midPosition);
    		
    	}
    	
    	return (path);
	}
}
