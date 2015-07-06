package org.usfirst.frc2832.Robot_2015.commands.auton;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc2832.Robot_2015.FieldLocation;
import org.usfirst.frc2832.Robot_2015.Robot;
import org.usfirst.frc2832.Robot_2015.RobotMap;
import org.usfirst.frc2832.Robot_2015.commands.AutonomousMovePath;
import org.usfirst.frc2832.Robot_2015.commands.CloseFork;
import org.usfirst.frc2832.Robot_2015.commands.CloseIngestor;
import org.usfirst.frc2832.Robot_2015.commands.Delay;
import org.usfirst.frc2832.Robot_2015.commands.DropStack;
import org.usfirst.frc2832.Robot_2015.commands.Instruction;
import org.usfirst.frc2832.Robot_2015.commands.OpenFork;
import org.usfirst.frc2832.Robot_2015.commands.OpenIngestor;
import org.usfirst.frc2832.Robot_2015.commands.PickUpObject;
import org.usfirst.frc2832.Robot_2015.commands.SetForkPosition;
import org.usfirst.frc2832.Robot_2015.commands.DropStack.DropType;
import org.usfirst.frc2832.Robot_2015.commands.SpinRollers;
import org.usfirst.frc2832.Robot_2015.commands.StopRollers;
import org.usfirst.frc2832.Robot_2015.commands.ToggleIngestors;
import org.usfirst.frc2832.Robot_2015.commands.ToggleUpperClamp;
import org.usfirst.frc2832.Robot_2015.subsystems.LiftControl;

/**
 *Autonomous command; grabs the nearest tote and bin and moves to the autozone.
 */
public class  Auton_1T1B extends Command implements Pathable {
	/**
	 *Autonomous command; grabs the nearest tote and bin and moves to the autozone.
	 */
    public Auton_1T1B() {
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
    	if((int)Robot.oi.startPos.getSelected() % 2 == 1)
    		path.add(Robot.endPosition);
    	else
    	{
    		/*if ((int) Robot.oi.startPos.getSelected() == 0) {
    			// Procedure for stacking without score platform in way
    			
	    		path.add((Instruction) new CloseFork());
	        	path.add((Instruction) new SetForkPosition(LiftControl.metersToLevel[2]));
	    		path.add(Robot.startPosition);
	    		path.add(new FieldLocation(Robot.startPosition.x, Robot.startPosition.y + Global.moveFwdAfterBin, 90));
	    		//redefine mid and end so that we drive straight, not angled.
	    		FieldLocation midPosition = new FieldLocation(Robot.startPosition.x + Global.distToMidPos, Robot.startPosition.y + Global.moveFwdAfterBin, 0);
	    		FieldLocation endPosition = new FieldLocation(Robot.startPosition.x + Global.distToEndPos, Robot.startPosition.y + Global.moveFwdAfterBin, 0);
	    		path.add((Instruction) new DropStack(DropType.tote));
	    		path.add((Instruction) new SetForkPosition(0));
	    		path.add((Instruction) new CloseFork());
	        	path.add((Instruction) new Delay(0.3));
	        	path.add((Instruction) new SetForkPosition(0.2));
	    		
	    		path.add(midPosition);
	    		path.add(new DropStack(DropType.ground));
	    		path.add(endPosition);
    		} else {*/
    			// Going over score platform
    			
    			path.add(Robot.startPosition);
    			
    			// Grab on
        		path.add((Instruction) new CloseFork());
        		path.add((Instruction) new Delay(0.5));
        		path.add((Instruction) new SetForkPosition(LiftControl.metersToLevel[3]));
        		path.add((Instruction) new FieldLocation(Robot.startPosition.x, Robot.startPosition.y + 1, Robot.startPosition.heading));
        		path.add((Instruction) new ToggleIngestors());
        		path.add((Instruction) new SpinRollers(false, false, 300));

        		
        		// Turn and go into autozone
        		if((int)Robot.oi.startPos.getSelected() == 0)
        			path.add(new FieldLocation(Robot.midPosition.x, Robot.midPosition.y + 1, Robot.midPosition.heading));
        		else
        			path.add(new FieldLocation(Robot.midPosition.x + Global.distOverBump, Robot.midPosition.y + 1, Robot.midPosition.heading));
        		

        		// Release
        		//path.add((Instruction) new OpenFork());
        		path.add((Instruction) new ToggleIngestors());
    		//}
    	}
    	
    	return (path);
	}
}
