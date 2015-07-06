package org.usfirst.frc2832.Robot_2015.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Ingest extends CommandGroup {
    
    public Ingest() {
    	addSequential(new CloseIngestor());
    	addSequential(new SpinRollers(true),2);
    	addSequential(new StopRollers());
    }
}
