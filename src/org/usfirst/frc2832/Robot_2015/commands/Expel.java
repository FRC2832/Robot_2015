package org.usfirst.frc2832.Robot_2015.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *Spins rollers to expel tote and opens the ingestors
 */
public class Expel extends CommandGroup {
    
    public  Expel() {
    	addSequential(new SpinRollers(false),3);
    	addSequential(new OpenIngestor());
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
