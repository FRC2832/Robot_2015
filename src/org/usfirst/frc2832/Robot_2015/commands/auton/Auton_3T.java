package org.usfirst.frc2832.Robot_2015.commands.auton;

import java.util.ArrayList;

import org.usfirst.frc2832.Robot_2015.FieldLocation;
import org.usfirst.frc2832.Robot_2015.Robot;
import org.usfirst.frc2832.Robot_2015.RobotMap;
import org.usfirst.frc2832.Robot_2015.commands.AutonomousMovePath;
import org.usfirst.frc2832.Robot_2015.commands.CloseFork;
import org.usfirst.frc2832.Robot_2015.commands.Delay;
import org.usfirst.frc2832.Robot_2015.commands.DropStack;
import org.usfirst.frc2832.Robot_2015.commands.DropStack.DropType;
import org.usfirst.frc2832.Robot_2015.commands.Instruction;
import org.usfirst.frc2832.Robot_2015.commands.SetForkPosition;
import org.usfirst.frc2832.Robot_2015.commands.SpinRollers;
import org.usfirst.frc2832.Robot_2015.commands.StopRollers;
import org.usfirst.frc2832.Robot_2015.commands.ToggleIngestors;
import org.usfirst.frc2832.Robot_2015.subsystems.LiftControl;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *DEPRECATED
 *Autonomous command; grabs all three totes and moves to the auto zone
 */
public class  Auton_3T extends Command implements Pathable {
	/**
	 *Autonomous command; grabs all three totes and moves to the auto zone
	 */
    public Auton_3T() {
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
    	if((int)Robot.oi.startPos.getSelected() != 5)
    		path.add(Robot.endPosition);
    	else
    	{
    		//pick up the first tote
    		path.add((Instruction) new CloseFork());
        	path.add((Instruction) new Delay(0.3));
        	path.add((Instruction) new SetForkPosition(0.55));
    		path.add(Robot.startPosition);
    		
    		
    		//push bin out of way
    		path.add((Instruction) new SpinRollers(true, false, 100));
    		path.add((Instruction) new ToggleIngestors());
    		path.add(new FieldLocation(Robot.startPositions[4].x, Robot.startPositions[4].y -.25, Robot.startPosition.heading));
    		path.add((Instruction) new SpinRollers(true, false, 300));
    		path.add((Instruction) new StopRollers());
    		
    		
    		//move in front of the next tote
    		path.add(Robot.startPositions[3]);
    		path.add((Instruction) new SpinRollers(false, false, 300));
    		path.add(new FieldLocation(Robot.startPositions[3].x, Robot.startPositions[3].y - .5, Robot.startPositions[3].heading)); //move a bit forward
    		
    		
    		//pick up the 2nd tote
    		path.add((Instruction) new ToggleIngestors());
    		path.add((Instruction) new StopRollers());
    		path.add((Instruction) new DropStack(DropType.tote));
    		path.add((Instruction) new SetForkPosition(0));
    		path.add((Instruction) new CloseFork());
        	path.add((Instruction) new Delay(0.4));
        	path.add((Instruction) new SetForkPosition(0.55));
        	
        	
        	//push bin out of way
        	path.add((Instruction) new SpinRollers(true, false, 100));
    		path.add((Instruction) new ToggleIngestors());
    		path.add(new FieldLocation(Robot.startPositions[2].x, Robot.startPositions[2].y -.75, Robot.startPosition.heading));
    		path.add((Instruction) new SpinRollers(true, false, 300));
    		path.add((Instruction) new StopRollers());
        	
        	
    		//move to the third tote
        	path.add(new FieldLocation(Robot.startPositions[1].x, Robot.startPositions[1].y - 1.0, Robot.startPositions[1].heading));
        	path.add((Instruction) new ToggleIngestors());
        	path.add((Instruction) new SpinRollers(false, false, 200));
    		path.add((Instruction) new StopRollers());
    		
    	
    		
    		
    		//redefine position constants as if we were in position 2
    		FieldLocation startPosition = Robot.startPositions[1];
    		FieldLocation midPosition = new FieldLocation(startPosition.x + Global.distToMidPos + 0.25, startPosition.y - 1.0, 0);
            FieldLocation endPosition =  new FieldLocation(startPosition.x + Global.distToEndPos + 0.15, startPosition.y - 1.0, 0);
    		
           
            
            //drive forward, drop, and back up
    		path.add(midPosition);
    		path.add((Instruction) new ToggleIngestors());
    		path.add(new DropStack(DropType.ground));
    		path.add(endPosition);
    	}
    	
    	return (path);
	}
}
