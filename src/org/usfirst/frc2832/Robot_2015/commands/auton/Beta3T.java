package org.usfirst.frc2832.Robot_2015.commands.auton;

import java.util.ArrayList;

import org.usfirst.frc2832.Robot_2015.commands.CloseFork;
import org.usfirst.frc2832.Robot_2015.commands.Delay;
import org.usfirst.frc2832.Robot_2015.commands.Expel;
import org.usfirst.frc2832.Robot_2015.commands.Instruction;
import org.usfirst.frc2832.Robot_2015.commands.LowerOneLevel;
import org.usfirst.frc2832.Robot_2015.commands.MoveDistance;
import org.usfirst.frc2832.Robot_2015.commands.OpenFork;
import org.usfirst.frc2832.Robot_2015.commands.RaiseOneLevel;
import org.usfirst.frc2832.Robot_2015.commands.Rotate;
import org.usfirst.frc2832.Robot_2015.commands.SetForkPosition;
import org.usfirst.frc2832.Robot_2015.commands.SpinRollers;
import org.usfirst.frc2832.Robot_2015.commands.StopRollers;
import org.usfirst.frc2832.Robot_2015.commands.ToggleIngestors;
import org.usfirst.frc2832.Robot_2015.subsystems.LiftControl;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *An experimental 3Tote autonomous
 */
public class Beta3T extends CommandGroup implements Pathable {
    
    public  Beta3T() {
    	/*
    		OPTIMIZATION NOTES
    		Current time: ~23 sec.
    		Total time used by delays: 3.1 sec.
    		Overall ways to optimize:
    		-push limits of trajectory controller. Allow us to use really steep trapezoids as much as possible.
    		-limit all timeouts.
    		-possibly toggle ingestors really fast so they stay only a little open, diminishing close time?
    		-find ways to move faster when pushing bins
    		-the stack must not be neat; if we can drop totes on top of others consistently, for example, do it
    		-possibly move while turning for a more curved trajectory? unlikely to work
    	*/
    	
    	//Pick up 1st Tote
    	addSequential (new CloseFork());
    	addSequential (new Delay(0.3)); //TODO: Optimize time
    	addParallel   (new MoveDistance(-.25, 0.9)); //TODO: Optimize movement speed if possible
    	addSequential   (new SetForkPosition(LiftControl.metersToLevel[3])); //TODO: Optimize height
    	//At this point, the tote is lifted and above bin.
    	
    	addParallel (new ToggleIngestors()); //CLOSE
    	
    	//Push bin out of way
    	addParallel (new SpinRollers(false, true, 500));
    	addSequential (new MoveDistance(1.8, 0.6)); //TODO: Optimize speed
    	addSequential( new Delay(0.01)); //TODO: Is this necessary at all?
    	addSequential( new ToggleIngestors()); //OPEN
    	addSequential( new Delay(0.3)); //Time given to open ingestors. Likely can be a tad lower.
    	
    	//drive to second tote 
    	//addSequential (new Strafe(true, 0.6, 400));
    	addSequential (new MoveDistance(0.65, 1)); //TODO: Optimize difference going forward
    	addParallel (new ToggleIngestors()); //CLOSE
    	addSequential (new SpinRollers(false, false, 350));
    	addSequential (new Delay(0.01)); //TODO: WAY too high. time of spinning.
    	addParallel (new ToggleIngestors()); //OPEN TODO: Make parallel so rise while opening
    	//addSequential (new Delay(0.2)); //TODO: should not be necessary
    	
    	//lower lift and prepare to grab
    	addSequential (new SetForkPosition(LiftControl.metersToLevel[1]));
    	addSequential (new OpenFork());
    	addSequential (new Delay(0.4)); //TODO: Optimize delay
    	addSequential (new SetForkPosition(LiftControl.metersToLevel[0]));
    	addSequential (new Delay(0.01)); //TODO: Optimize delay
    	
    	//2nd tote
    	addSequential (new CloseFork());  
    	addSequential (new Delay(0.4)); //TODO: WAY too high
    	addParallel (new SetForkPosition(LiftControl.metersToLevel[3]));
    	addSequential (new ToggleIngestors()); //CLOSE TODO: Can this be don in parallel with above command, at least partially?
    											//i.e. moving part way then toggling while raising
    	
    	//push bin out of way
    	addParallel (new SpinRollers(false, true, 500)); //Why is this different # than above? 500 vs. 300
    	addSequential (new MoveDistance(1.9, 0.6)); //TODO: Raise speed
    	addSequential( new Delay(0.01)); //TODO: Optimize
    	addSequential( new ToggleIngestors()); //OPEN
    	
    	//drive to 3rd tote
    	addSequential (new MoveDistance(0.75, 1));
    	addParallel (new ToggleIngestors()); //CLOSE
    	addSequential (new SpinRollers(false, false, 3500));
    	
    	//3rd tote and drive to auto zone
    	addParallel   (new SpinRollers(false, false, 500));
    	addSequential (new Rotate (90, 0.9)); //TODO: how fast can we spin?
    	addParallel (new MoveDistance (3.9,1)); //TODO: raise distance if possible

    	//let go of stack
    	addSequential (new SetForkPosition(LiftControl.metersToLevel[1])); //TODO: Should be parallel with movement
    	addParallel (new OpenFork());
    	addSequential (new ToggleIngestors()); //OPEN
    	addSequential (new Delay(0.1));
    	addSequential (new MoveDistance(-.4,1));
    	
    	
    	
        // Add Commands here:

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
    }
    
    

	@Override
	public ArrayList<Instruction> getPath() {
		// TODO Auto-generated method stub
		return null;
	}
}
