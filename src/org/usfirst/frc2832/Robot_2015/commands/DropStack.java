package org.usfirst.frc2832.Robot_2015.commands;
import org.usfirst.frc2832.Robot_2015.subsystems.LiftControl;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *Drops the stack at a specific level.
 *level 0 = bottom level
 *level 1 = scoring platform level
 *level 2 = placing on top of a tote on the ground
 */
public class DropStack extends CommandGroup implements Instruction {

	public enum DropType {
		ground, platform, tote;
	}
	
	/**
	 *Drops the stack at a specific level.
	 *level 0 = bottom level
	 *level 1 = scoring platform level
	 *level 2 = placing on top of a tote on the ground
	 */
    public  DropStack(DropType drop) {
    	switch (drop) {
    	case ground:
    		addSequential (new SetForkPosition(0));
    		break;
    	case platform:
    		addSequential (new SetForkPosition(0.06));//the level of the scoring platform
    		break;
    	case tote:
    		addSequential(new SetForkPosition(LiftControl.metersToLevel[1]));
    		break;
    	}
    	addSequential(new OpenFork());
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
