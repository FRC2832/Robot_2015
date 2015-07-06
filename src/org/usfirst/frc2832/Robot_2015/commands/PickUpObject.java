package org.usfirst.frc2832.Robot_2015.commands;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *Currently not implemented.
 */
public class PickUpObject extends CommandGroup implements Instruction {
	
	private double level;
	
	/**
	 *Currently not implemented.
	 */
    public  PickUpObject(boolean isHigh) {
    	// TODO: Remove delay and implement.
    	
    	level = isHigh ? 1.2 : 0.2;
    	
    	addSequential(new Ingest());
    	
    	// TODO: Uncomment
    	//addSequential(new RaiseToLevel(level));
    }
    
    @Override
    protected void initialize() {
    	
    	
    }
}
